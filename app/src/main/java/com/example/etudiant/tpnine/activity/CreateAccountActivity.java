package com.example.etudiant.tpnine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.etudiant.tpnine.R;
import com.example.etudiant.tpnine.manager.WsManager;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity implements WsManager.Listener {

    static Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().setTitle("Créer un compte");
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3f569b")));
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_create_account);

            Button CreateAccBtn = (Button) findViewById(R.id.CompteButton);

            CreateAccBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    EditText firstNameEditText =(EditText) findViewById(R.id.editFirstName);

                    EditText NameEditText =(EditText) findViewById(R.id.editName);

                    EditText MailEdit =(EditText) findViewById(R.id.editEmail);

                    EditText PasswordEdit =(EditText) findViewById(R.id.editPassword);

                    map.put("login", MailEdit.getText().toString());

                    map.put("pass", PasswordEdit.getText().toString());

                    map.put("nom", NameEditText.getText().toString());

                    map.put("prenom", firstNameEditText.getText().toString());

                    SendData();
                }

            });

    }

    public void SendData() {
        WsManager wsManager = new WsManager();
        wsManager.sendRequest("ws/resto/addCompte", map, this, true);
        map.clear();
        Intent i = new Intent(CreateAccountActivity.this, ListMovieActivity.class);
        startActivity(i);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("TPResto", "error: " + errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Log.d("TPResto", "success: " + content);
        Context context = getApplicationContext();
        CharSequence text = "Votre compte a été créé !";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("TpNine", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("connected", true);
    }


}
