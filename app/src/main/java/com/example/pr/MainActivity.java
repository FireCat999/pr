package com.example.pr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        if(!network.isConnectedOrConnecting() || network==null){
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

    public void reg(View view) {
        Intent intent= new Intent(MainActivity.this,reg.class);
        MainActivity.this.startActivity(intent);
    }


    public void vhod(View view) {
        EditText mail = findViewById(R.id.mail);
        EditText pass = findViewById(R.id.pass);
        String url = "https://food.madskill.ru/auth/login";
        if(!mail.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST,url,response -> {Intent intent = new Intent(MainActivity.this,oteli.class);
                MainActivity.this.startActivity(intent);
                },error -> { AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Не удалось войти в аккаунт")
                        .setMessage("Ошибка на сервере")
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();}){
                @Override
                public HashMap<String ,String > getParams(){
                    HashMap<String,String> Params = new HashMap<String ,String >();
                    Params.put("email",mail.getText().toString());
                    Params.put("password",pass.getText().toString());
                    return Params;
                }
             };
            requestQueue.add(request);
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Не удалось войти в аккаунт")
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