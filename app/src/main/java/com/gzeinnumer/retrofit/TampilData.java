package com.gzeinnumer.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gzeinnumer.retrofit.Api.RetroServer;
import com.gzeinnumer.retrofit.Model.ResponseReadData;
import com.gzeinnumer.retrofit.Model.ResultReadDataItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TampilData extends AppCompatActivity {

    RecyclerView recyclerView;

    public AdapterData adapter;
    private RecyclerView.LayoutManager manager;
    private List<ResultReadDataItem> mItem = new ArrayList<>();
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
        RetroServer.getInstance().getBiodata().enqueue(new Callback<ResponseReadData>() {
            @Override
            public void onResponse(Call<ResponseReadData> call, Response<ResponseReadData> response) {
                pd.hide();
                Log.d("RETRO","RESPONSE = "+response.body().getKode());
                mItem=response.body().getResultReadData();
                adapter = new AdapterData(TampilData.this, mItem);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseReadData> call, Throwable t) {
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
