package com.gzeinnumer.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.retrofit.Api.ApiRequestBiodata;
import com.gzeinnumer.retrofit.Api.RetroServer;
import com.gzeinnumer.retrofit.Model.DataModel;
import com.gzeinnumer.retrofit.Model.ResponModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.MyHolder> {

    private Context context;
    private List<DataModel> mList;

    Dialog dialog;


    public AdapterData(Context context, List<DataModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final DataModel data = mList.get(i);
        myHolder.currentId.setText(data.getId());
        myHolder.currentNama.setText(data.getNama());
        myHolder.currentUsia.setText(data.getUsia());
        myHolder.currentDomisili.setText(data.getDomisili());

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);

                final EditText dialogId, dialogNama, dialogUsia, dialogDomisili;
                Button btnEdit, btnDelete;

                dialogId = dialog.findViewById(R.id.dialogId);
                dialogNama = dialog.findViewById(R.id.dialogNama);
                dialogUsia = dialog.findViewById(R.id.dialogUsia);
                dialogDomisili = dialog.findViewById(R.id.dialogDomisili);

                btnEdit = dialog.findViewById(R.id.btnEdit);
                btnDelete = dialog.findViewById(R.id.btnDelete);

                dialogId.setText(data.getId());
                dialogNama.setText(data.getNama());
                dialogUsia.setText(data.getUsia());
                dialogDomisili.setText(data.getDomisili());

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);

                        Call<ResponModel> deleteBio = api.sentDeleteData(data.getId());
                        deleteBio.enqueue(new Callback<ResponModel>() {
                            @Override
                            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                                Log.d("RETRO", "Response = "+response.body().toString());
                                String kode = response.body().getKode();

                                if(kode.equals("1")){
                                    Toast.makeText(context,"Data didelete",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    // notofidatachange
                                }else{
                                    Toast.makeText(context,"Data gagal didelete",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponModel> call, Throwable t) {
                                Log.d("RETRO", "Failure = " + "Gagal mengirim request");
                            }
                        });
                    }
                });
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);

                        Call<ResponModel> updateBio = api.sentUpdateData(
                                data.getId(),
                                dialogNama.getText().toString(),
                                dialogUsia.getText().toString(),
                                dialogDomisili.getText().toString());
                        updateBio.enqueue(new Callback<ResponModel>() {
                            @Override
                            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                                Log.d("RETRO", "Response = "+response.body().toString());
                                String kode = response.body().getKode();

                                if(kode.equals("1")){
                                    Toast.makeText(context,"Data diupdate",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    // notofidatachange
                                }else{
                                    Toast.makeText(context,"Data gagal diupdate",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponModel> call, Throwable t) {
                                Log.d("RETRO", "Failure = " + "Gagal mengirim request");
                            }
                        });
                    }
                });
                dialog.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView currentId, currentNama, currentUsia, currentDomisili;
        CardView cardView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            currentId = itemView.findViewById(R.id.tampilId);
            currentNama = itemView.findViewById(R.id.tampilNama);
            currentUsia = itemView.findViewById(R.id.tampilUsia);
            currentDomisili = itemView.findViewById(R.id.tampilDomisili);
        }
    }
}
