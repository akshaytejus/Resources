package com.example.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Button login_button = findViewById(R.id.loginButton);

        login_button.setOnClickListener(view -> {
            Intent intent = new Intent(Entry.this, Login.class);
            startActivity(intent);
        });
    }
}