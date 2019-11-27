package com.example.etudiant.tpnine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.etudiant.tpnine.R;
import com.example.etudiant.tpnine.manager.WsManager;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements WsManager.Listener  {

    static Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Movie", 0);

        if(pref.getBoolean("connected", true))
        {
            Intent i = new Intent(LoginActivity.this, ListMovieActivity.class);
            startActivity(i);
        }

        setContentView(R.layout.activity_login);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        Button CreateConexBtn = (Button) findViewById(R.id.connexionButton);

        CreateConexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

            EditText MailEdit = (EditText)findViewById(R.id.editMail);

            EditText PasswordEdit = (EditText)findViewById(R.id.editPassword);

            map.put("login", MailEdit.getText().toString());

            map.put("pass", PasswordEdit.getText().toString());

            SendData();
            }

        });


        Button CreateAccBtn = (Button) findViewById(R.id.CompteButton);
        CreateAccBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(i);

                }
            });
    }

    public void SendData() {
        WsManager wsManager = new WsManager();
        wsManager.sendRequest("ws/resto/connexion", map, this, true);
        map.clear();
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("Movie", "error: " + errorMessage);
        Context context = getApplicationContext();
        CharSequence text = "Il y a une erreur dans votre login et/ou votre mot de passe !";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent i = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onSuccess(String content) {

        if(!content.contains("error"))
        {
            Log.d("TPResto", "success: " + content);
            SharedPreferences pref = getApplicationContext().getSharedPreferences("TpNine", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("connected", true);
            Intent i = new Intent(LoginActivity.this, ListMovieActivity.class);
            startActivity(i);
        }else
        {
            Context context = getApplicationContext();
            CharSequence text = "Il y a une erreur dans votre login et/ou votre mot de passe !";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent i = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
