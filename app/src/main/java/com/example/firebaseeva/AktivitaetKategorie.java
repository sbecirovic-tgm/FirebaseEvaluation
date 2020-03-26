package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AktivitaetKategorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitaet_kategorie);

    }

    public void bewegungAnzeigen(View view) {
        //startActivity(new Intent(AktivitaetKategorie.this, Bewegung.class));
    }

}
