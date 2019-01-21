package com.example.nathany.ioasysenterprises.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nathany.ioasysenterprises.R;
import com.example.nathany.ioasysenterprises.model.User;
import com.example.nathany.ioasysenterprises.service.LoginService;
import com.example.nathany.ioasysenterprises.service.UserService;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    private EditText etUseremail;
    private EditText etPassword;

    private String userEmail;
    private String password;


    LoginService loginService = new LoginService();
    UserService service = loginService.API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // oculta Toolbar

    }

    public void userLogin(View view){

        etUseremail = findViewById(R.id.editText_user_email);
        etPassword = findViewById(R.id.editText_password);

        userEmail = etUseremail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        //se os campos estiverem vazios
        if(userEmail.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
        else {


            User user = new User(userEmail, password);

            Call<User> userCall = service.userLogin(user);

            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();

                    } else {

                        getHeaders(response.headers());
                        Intent intent = new Intent(LoginActivity.this, EnterpriseActivity.class);
                        startActivity(intent);
                        //limpando os campos
                        etUseremail.setText("");
                        etPassword.setText("");
                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Erro na conexão" + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void getHeaders(Headers headers) {

        String token = headers.get("access-token");
        String client = headers.get("client");
        String uid = headers.get("uid");



        SharedPreferences preferences = getSharedPreferences("HEADERS", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("access-token", token);
        editor.putString("client", client);
        editor.putString("uid", uid);

        //gravando informações
        editor.commit();


    }

}
