package com.gzeinnumer.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.gzeinnumer.retrofit.Api.ApiRequestBiodata;
import com.gzeinnumer.retrofit.Api.RetroServer;
import com.gzeinnumer.retrofit.Model.DataModel;
import com.gzeinnumer.retrofit.Model.ResponModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TampilData extends AppCompatActivity {

    RecyclerView recyclerView;

    public AdapterData adapter;
    private RecyclerView.LayoutManager manager;
    private List<DataModel> mItem = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);
        recyclerView = findViewById(R.id.recyclerView);

        pd = new ProgressDialog(this);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        pd.setMessage("Loading Data ...");
        pd.setCancelable(false);
        pd.show();

        initData();
    }

    private void initData() {
        ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);
        Call<ResponModel> getData = api.getBiodata();
        getData.enqueue(new Callback<ResponModel>(){
            @Override
            public void onResponse(retrofit2.Call<ResponModel> call, Response<ResponModel> response) {
                pd.hide();
                Log.d("RETRO","RESPONSE = "+response.body().getKode());
                mItem=response.body().getResult();
                adapter = new AdapterData(TampilData.this, mItem);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(retrofit2.Call<ResponModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO","FAILED = Respon Data Gagal");
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initData();
    }
}
