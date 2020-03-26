package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void aktivitaetAnzeigen(View view) {
        startActivity(new Intent(HomeActivity.this, AktivitaetKategorie.class));
    }
    public void klubAnzeigen(View view) {
        startActivity(new Intent(HomeActivity.this, KlubActivity.class));
    }
    public void austauschAnzeigen(View view) {
        //startActivity(new Intent(HomeActivity.this, KlubActivity.class));
    }
    public void profilAnzeigen(View view) {
        startActivity(new Intent(HomeActivity.this, ProfilActivity.class));
    }
}
