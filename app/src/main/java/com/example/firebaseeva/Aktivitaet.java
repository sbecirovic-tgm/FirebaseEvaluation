package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class Aktivitaet extends AppCompatActivity {

    private TextView name, datum, uhrzeit, ort, tele;
    private Button mapButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitaet);

        this.db = FirebaseFirestore.getInstance();

        this.name = (TextView) findViewById(R.id.textView_name);
        this.datum = (TextView) findViewById(R.id.textView_datum);
        this.uhrzeit = (TextView) findViewById(R.id.textView_uhrzeit);
        this.ort = (TextView) findViewById(R.id.textView_ort);
        this.tele = (TextView) findViewById(R.id.textView_tele);

        mapButton = (Button) findViewById(R.id.button_route);

        this.getData();
    }

    public void route(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=Porzellangasse+14,+Vienna+Austria");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void getData() {

    }

    public void home(View view) {
        startActivity(new Intent(Aktivitaet.this, HomeActivity.class));
    }
}
