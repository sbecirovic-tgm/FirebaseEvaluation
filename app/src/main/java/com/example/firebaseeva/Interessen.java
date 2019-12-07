package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interessen extends AppCompatActivity {

    Button speichern;
    Spinner mahlzeit, vortrag, ausflug, bewegung, bildung, musikTanz;
    FirebaseFirestore db;
    Map<String, Boolean> interresen;
    List<Spinner> spinnerList;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interessen);

        db = FirebaseFirestore.getInstance();
        interresen = new HashMap<>();
        spinnerList = new ArrayList<>();
        setUpSpinner();

        speichern = (Button) findViewById(R.id.speichernBtn);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speichern();
            }
        });
    }

    public void setUpSpinner() {
        mahlzeit = (Spinner) findViewById(R.id.mahlzeitSpinner);
        vortrag = (Spinner) findViewById(R.id.vortragSpinner);
        ausflug = (Spinner) findViewById(R.id.ausflugSpinner);
        bewegung = (Spinner) findViewById(R.id.bewegungSpinner);
        bildung = (Spinner) findViewById(R.id.bildungSpinner);
        musikTanz = (Spinner) findViewById(R.id.musikTanzSpinner);

        String[] items = {"true", "false"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mahlzeit.setAdapter(adapter);
        vortrag.setAdapter(adapter);
        ausflug.setAdapter(adapter);
        bewegung.setAdapter(adapter);
        bildung.setAdapter(adapter);
        musikTanz.setAdapter(adapter);
        spinnerList.add(mahlzeit);
        spinnerList.add(vortrag);
        spinnerList.add(ausflug);
        spinnerList.add(bewegung);
        spinnerList.add(bildung);
        spinnerList.add(musikTanz);

        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void speichern() {
        for(Spinner spinner : spinnerList) {
            if ("true".equals(spinner.getSelectedItem())) {
                switch (spinner.getId()) {
                    case R.id.mahlzeitSpinner:
                        interresen.put("mahlzeit", true);;
                    case R.id.vortragSpinner:
                        interresen.put("mahlzeit", true);
                    case R.id.ausflugSpinner:
                        interresen.put("mahlzeit", true);
                    case R.id.bewegungSpinner:
                        interresen.put("mahlzeit", true);
                    case R.id.bildungSpinner:
                        interresen.put("mahlzeit", true);
                    case R.id.musikTanzSpinner:
                        interresen.put("mahlzeit", true);
                }
            }else if ("false".equals(spinner.getSelectedItem())) {
                switch (spinner.getId()) {
                    case R.id.mahlzeitSpinner:
                        interresen.put("mahlzeit", false);
                    case R.id.vortragSpinner:
                        interresen.put("mahlzeit", false);
                    case R.id.ausflugSpinner:
                        interresen.put("mahlzeit", false);
                    case R.id.bewegungSpinner:
                        interresen.put("mahlzeit", false);
                    case R.id.bildungSpinner:
                        interresen.put("mahlzeit", false);
                    case R.id.musikTanzSpinner:
                        interresen.put("mahlzeit", false);
                }
            }
        }

        db.collection("Profil").add(user);
        db.collection("Profil").document(user.toString()).set(interresen).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(Interessen.this, AccountActivity.class));
            }
        });
    }
}
