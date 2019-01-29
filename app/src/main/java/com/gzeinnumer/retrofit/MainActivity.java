package com.gzeinnumer.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gzeinnumer.retrofit.Api.ApiRequestBiodata;
import com.gzeinnumer.retrofit.Api.RetroServer;
import com.gzeinnumer.retrofit.Model.ResponModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edt_nama, edt_usia, edt_domisili;
    Button btn_insertdata, btnTampil;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nama = findViewById(R.id.edt_nama);
        edt_usia = findViewById(R.id.edt_usia);
        edt_domisili = findViewById(R.id.edt_domisili);
        btn_insertdata = findViewById(R.id.btn_insertdata);

        btnTampil = findViewById(R.id.btnTampil);

        pd = new ProgressDialog(this);

        btn_insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send data ... ");
                pd.setCancelable(false);
                pd.show();
                String nama = edt_nama.getText().toString();
                String usia = edt_usia.getText().toString();
                String domisili = edt_domisili.getText().toString();

                ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);

                Call<ResponModel> sendBio = api.sendBiodata(nama, usia, domisili);
                sendBio.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        pd.hide();
                        Log.d("RETRO", "Response = "+response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1")){
                            Toast.makeText(getApplicationContext(),"Data disimpan",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Data gagal disimpan",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Failure = " + "Gagal mgnirim request");
                    }
                });
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TampilData.class);
                startActivity(intent);
            }
        });
    }
}
