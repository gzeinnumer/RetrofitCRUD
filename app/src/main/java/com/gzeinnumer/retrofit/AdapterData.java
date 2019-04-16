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

import com.gzeinnumer.retrofit.Api.RetroServer;
import com.gzeinnumer.retrofit.Model.ResponseDeleteData;
import com.gzeinnumer.retrofit.Model.ResponseUpdateData;
import com.gzeinnumer.retrofit.Model.ResultReadDataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.MyHolder> {

    private Context context;
    private List<ResultReadDataItem> mList;

    Dialog dialog;


    public AdapterData(Context context, List<ResultReadDataItem> mList) {
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
        final ResultReadDataItem data = mList.get(i);
        myHolder.currentId.setText(data.getId());
        myHolder.currentNama.setText(data.getNama());
        myHolder.currentNim.setText(data.getNim());
        myHolder.currentJurusan.setText(data.getJurusan());
        myHolder.currentProdi.setText(data.getProdi());
        myHolder.currentAlamat.setText(data.getAlamat());

        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);

                final EditText dialogId, dialogNama, dialogNim, dialogJurusan, dialogProdi, dialogAlamat;
                Button btnEdit, btnDelete;

                dialogId = dialog.findViewById(R.id.dialogId);
                dialogNama = dialog.findViewById(R.id.dialogNama);
                dialogNim = dialog.findViewById(R.id.dialogNim);
                dialogJurusan = dialog.findViewById(R.id.dialogJurusan);
                dialogProdi = dialog.findViewById(R.id.dialogProdi);
                dialogAlamat = dialog.findViewById(R.id.dialogAlamat);

                btnEdit = dialog.findViewById(R.id.btnEdit);
                btnDelete = dialog.findViewById(R.id.btnDelete);

                dialogId.setText(data.getId());
                dialogNama.setText(data.getNama());
                dialogNim.setText(data.getNim());
                dialogJurusan.setText(data.getJurusan());
                dialogProdi.setText(data.getProdi());
                dialogAlamat.setText(data.getAlamat());

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        RetroServer.getInstance().sentDeleteData(data.getId()).enqueue(new Callback<ResponseDeleteData>() {
                            @Override
                            public void onResponse(Call<ResponseDeleteData> call, Response<ResponseDeleteData> response) {
                                Log.d("RETRO", "Response = "+response.body().toString());
                                String kode = String.valueOf(response.body().getKode());

                                if(kode.equals("1")){
                                    Toast.makeText(context,"Data didelete",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    // notofidatachange
                                }else{
                                    Toast.makeText(context,"Data gagal didelete",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDeleteData> call, Throwable t) {
                                Log.d("RETRO", "Failure = " + "Gagal mengirim request");

                            }
                        });
                    }
                });
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        RetroServer.getInstance().sentUpdateData(data.getId(),
                                dialogNama.getText().toString(),
                                dialogNim.getText().toString(),
                                dialogJurusan.getText().toString(),
                                dialogProdi.getText().toString(),
                                dialogAlamat.getText().toString()).enqueue(new Callback<ResponseUpdateData>() {
                            @Override
                            public void onResponse(Call<ResponseUpdateData> call, Response<ResponseUpdateData> response) {
                                Log.d("RETRO", "Response = "+response.body().toString());
                                String kode = String.valueOf(response.body().getKode());

                                if(kode.equals("1")){
                                    Toast.makeText(context,"Data diupdate",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    // notofidatachange
                                }else{
                                    Toast.makeText(context,"Data gagal diupdate",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseUpdateData> call, Throwable t) {
                                Log.d("RETRO", "Failure = " + "Gagal mengirim request");

                            }
                        });
                    }
                });

                dialog.show();
                return true;
            }
        });
        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView currentId, currentNama, currentNim, currentJurusan, currentProdi, currentAlamat;
        CardView cardView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            currentId = itemView.findViewById(R.id.tampilId);
            currentNama = itemView.findViewById(R.id.tampilNama);
            currentNim = itemView.findViewById(R.id.tampilNim);
            currentJurusan = itemView.findViewById(R.id.tampilJurusan);
            currentProdi = itemView.findViewById(R.id.tampilProdi);
            currentAlamat = itemView.findViewById(R.id.tampilAlamat);
        }
    }
}
