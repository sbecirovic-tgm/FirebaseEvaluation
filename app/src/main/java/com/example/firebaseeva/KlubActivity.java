package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KlubActivity extends AppCompatActivity {

    TextView kname, tage, uhrzeit, adresse, telNr;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klub);

        db = FirebaseFirestore.getInstance();

        kname = (TextView) findViewById(R.id.kNameView);
        tage = (TextView) findViewById(R.id.tageView);
        uhrzeit = (TextView) findViewById(R.id.uhrzeitView);
        adresse = (TextView) findViewById(R.id.adresseView);
        telNr = (TextView) findViewById(R.id.telNrView);

        getData();
    }

    public void getData() {

        final List<Object> data = new ArrayList<>();

        db.collection("Klubdaten").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        data.add(document.getData());
                        Map<String, Object> dataMap = (Map<String, Object>) data.get(0);
                        int counter = 0;
                        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                            switch (counter) {
                                case 1:
                                    adresse.setText(entry.getValue().toString());
                                    break;
                                case 2:
                                    kname.setText(entry.getValue().toString());
                                    break;
                                case 5:
                                    Map<String, Object> datUhr = (Map<String, Object>) entry.getValue();
                                    tage.setText(datUhr.get("tage").toString());
                                    uhrzeit.setText(datUhr.get("uhrzeit").toString());
                                    break;
                                case 3:
                                    telNr.setText(entry.getValue().toString());
                            }
                            counter++;
                        }
                    }
                } else {
                    Toast.makeText(KlubActivity.this, "Daten konnten nicht geladen werden", Toast.LENGTH_LONG);
                }
            }
        });
    }
}
