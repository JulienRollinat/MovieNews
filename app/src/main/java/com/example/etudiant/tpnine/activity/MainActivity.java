package com.example.etudiant.tpnine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.etudiant.tpnine.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, ListMovieActivity.class);
                startActivity(i); //modification de l'Intent pour aller directement à la page listMovie
            }
        }, 2000);
    }


}
