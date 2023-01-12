package com.example.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Button login_button = findViewById(R.id.loginButton);
        TextView register = findViewById(R.id.registerClick);

        login_button.setOnClickListener(view -> {
            Intent intent = new Intent(Entry.this, Login.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(Entry.this, Register.class);
            startActivity(intent);
        });
    }
}