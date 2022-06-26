package com.example.pr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class avtaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtaz);
    }

    public void nazad(View view) {
        Intent intent= new Intent(avtaz.this,oteli.class);
        avtaz.this.startActivity(intent);
    }
}

