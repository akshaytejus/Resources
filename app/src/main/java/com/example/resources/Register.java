package com.example.resources;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    Button sign_up = findViewById(R.id.signUpButton);
    TextView login = findViewById(R.id.loginClick);
    EditText name = findViewById(R.id.nameInputText);
    EditText user_name = findViewById(R.id.userNameInputText);
    EditText email = findViewById(R.id.emailInputText);
    EditText password = findViewById(R.id.passwordInputText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sign_up.setOnClickListener(view -> sendCredentials());

        login.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });

    }

    public void sendCredentials(){
        RegisterAPI model = new RegisterAPI(name.getText().toString(),user_name.getText().toString(),email.getText().toString(),password.getText().toString());
        Api api = RetrofitClient.getInstance().create(Api.class);
        Call<AuthModel> call = api.register(model);
        call.enqueue(new Callback<AuthModel>() {
            @Override
            public void onResponse(@NonNull Call<AuthModel> call, @NonNull Response<AuthModel> response) {
                assert response.body() != null;
                if(response.body().getValue().equals("no")) {
                    Toast.makeText(getApplicationContext(),"Account not found",Toast.LENGTH_SHORT).show();
                }
                else {
                    ExecutorService service = Executors.newSingleThreadExecutor();
                    service.execute(() -> {
                        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("Access_key",response.body().getAccess_token());
                        myEdit.apply();
                    });
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthModel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),"Account not Found",Toast.LENGTH_SHORT).show();
            }
        });
    }
}