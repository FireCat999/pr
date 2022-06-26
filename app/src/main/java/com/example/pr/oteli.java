package com.example.pr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class oteli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oteli);
    }

    public void avtaz(View view) {
        Intent intent= new Intent(oteli.this,avtaz.class);
        oteli.this.startActivity(intent);
    }

    public void kan(View view) {
        Intent intent= new Intent(oteli.this,kan.class);
        oteli.this.startActivity(intent);
    }
}
