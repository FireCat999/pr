package com.example.pr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class kan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kan);
    }

    public void b(View view) {
        Intent intent= new Intent(kan.this,oteli.class);
        kan.this.startActivity(intent);
    }
}
