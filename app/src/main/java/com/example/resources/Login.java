package com.example.resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText user_name = findViewById(R.id.userNameInputText);
    EditText password = findViewById(R.id.passwordInputText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = findViewById(R.id.loginButton);
        TextView register = findViewById(R.id.registerClick);

        login_button.setOnClickListener(view -> getCredentials());

        register.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    public void getCredentials() {
        LoginAPI model = new LoginAPI(user_name.getText().toString(),password.getText().toString());
        Api api = RetrofitClient.getInstance().create(Api.class);
        Call<AuthModel> call = api.login(model);
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
                    Intent intent = new Intent(Login.this, MainActivity.class);
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