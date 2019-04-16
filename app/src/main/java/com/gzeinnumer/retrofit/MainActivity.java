package com.gzeinnumer.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gzeinnumer.retrofit.Api.RetroServer;
import com.gzeinnumer.retrofit.Model.ResponseInsertData;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_nim)
    EditText edtNim;
    @BindView(R.id.edt_jurusan)
    EditText edtJurusan;
    @BindView(R.id.edt_prodi)
    EditText edtProdi;
    @BindView(R.id.edt_alamat)
    EditText edtAlamat;
    @BindView(R.id.btn_insertdata)
    Button btnInsertdata;
    @BindView(R.id.btnTampil)
    Button btnTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pd = new ProgressDialog(this);

        btnInsertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send data ... ");
                pd.setCancelable(false);
                pd.show();

                String nama = edtNama.getText().toString().trim();
                String nim = edtNim.getText().toString().trim();
                String jurusan = edtJurusan.getText().toString().trim();
                String prodi = edtProdi.getText().toString().trim();
                String alamat =  edtAlamat.getText().toString().trim();

                RetroServer.getInstance().sendBiodata(nama, nim, jurusan, prodi, alamat).enqueue(new Callback<ResponseInsertData>() {
                    @Override
                    public void onResponse(Call<ResponseInsertData> call, Response<ResponseInsertData> response) {
                        pd.hide();
                        Log.d("RETRO", "Response = " + response.body().toString());
                        String kode = String.valueOf(response.body().getKode());

                        if (kode.equals("1")) {
                            Toast.makeText(getApplicationContext(), "Data disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseInsertData> call, Throwable t) {
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
