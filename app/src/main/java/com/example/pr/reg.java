package com.example.pr;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class reg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null || !networkInfo.isConnectedOrConnecting()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ошибка соединения")
                    .setMessage("Нет доступа к сети")
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            builder.show();
        }
    }

    public void back(View view) {
        Intent intent= new Intent(reg.this,MainActivity.class);
        reg.this.startActivity(intent);
    }

    public void zareg(View view) {
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        EditText mail = findViewById(R.id.mail);
        EditText pass1 = findViewById(R.id.pass1);
        EditText pass2 = findViewById(R.id.pass2);

        if(!name.getText().toString().isEmpty() && !surname.getText().toString().isEmpty()&& !mail.getText().toString().isEmpty()&&
                !pass1.getText().toString().isEmpty()&&!pass2.getText().toString().isEmpty()) {
            if(pass1.getText().toString().equals(pass2.getText().toString())) {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                String url = "https://food.madskill.ru/auth/register";
                StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
                    Intent intent = new Intent(reg.this, oteli.class);
                    reg.this.startActivity(intent);
                }, error -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Не удалось создать аккаунт")
                            .setMessage("Ошибка на сервере")
                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder.show();
                }) {
                    public HashMap<String, String> getParams() {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("email", mail.getText().toString());
                        params.put("password", pass1.getText().toString());
                        params.put("login", name.getText().toString().split(" ")[0]);
                        return params;
                    }
                };
                requestQueue.add(request);
            } else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Не удалось создать аккаунт")
                        .setMessage("Пароли не совпадают")
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Не удалось создать аккаунт")
                    .setMessage("Заполните все поля ввода")
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            builder.show();
        }
    }
}
