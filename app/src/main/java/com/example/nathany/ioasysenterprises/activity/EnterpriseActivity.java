package com.example.nathany.ioasysenterprises.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nathany.ioasysenterprises.R;
import com.example.nathany.ioasysenterprises.adapter.EnterpriseAdapter;
import com.example.nathany.ioasysenterprises.model.EnterpriseCatalog;
import com.example.nathany.ioasysenterprises.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterpriseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EnterpriseAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);

        SharedPreferences preferences = getSharedPreferences("HEADERS", MODE_PRIVATE);
        //recupera os headers
        String token = preferences.getString("access-token","");
        String cliente = preferences.getString("client","");
        String uid = preferences.getString("uid", "");


        progressBar = findViewById(R.id.progressBarLoading);


        LoginService service = new LoginService();
        Call<EnterpriseCatalog> enterpriseCall = service.API().listEnterprises(token,cliente,uid);

        enterpriseCall.enqueue(new Callback<EnterpriseCatalog>() {
            @Override
            public void onResponse(Call<EnterpriseCatalog> call, Response<EnterpriseCatalog> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(EnterpriseActivity.this, "ERROR: " + response.code(), Toast.LENGTH_SHORT).show();
                }else {

                    EnterpriseCatalog catalog = response.body();
                    listEnterprise(catalog);
                }
            }

            @Override
            public void onFailure(Call<EnterpriseCatalog> call, Throwable t) {

                Log.i("ERROR", t.getMessage());


            }
        });
    }


    private void listEnterprise(EnterpriseCatalog catalog) {

        //recupera a recycleview
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new EnterpriseAdapter(catalog, this);

        //oculta progressBar
        progressBar.setVisibility(View.INVISIBLE);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(EnterpriseActivity.this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja sair do aplicativo?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

    }
}
