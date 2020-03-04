package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KlubActivity extends AppCompatActivity {

    private TextView kname, tage, uhrzeit, adresse, telNr;
    private Button buttonArr[];
    private FirebaseFirestore db;
    private static final String ADRESSE = "adresse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klub);

        db = FirebaseFirestore.getInstance();

        buttonArr = new Button[7];
        buttonArr[0] = (Button) findViewById(R.id.button_klub1);
        buttonArr[1] = (Button) findViewById(R.id.button_klub2);
        buttonArr[2] = (Button) findViewById(R.id.button_klub3);
        buttonArr[3] = (Button) findViewById(R.id.button_klub4);
        buttonArr[4] = (Button) findViewById(R.id.button_klub5);
        buttonArr[5] = (Button) findViewById(R.id.button_klub6);
        buttonArr[6] = (Button) findViewById(R.id.button_klub7);

        this.getData();
    }

    public void getData() {

        List<Object> data = new ArrayList<>();

        db.collection("Klubdaten").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int counter = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {

                            String adresse = document.getString(ADRESSE);
                            buttonArr[counter].setText(adresse);
                            counter++;

                        } else {
                            Toast.makeText(KlubActivity.this, "Dokument existiert nicht", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(KlubActivity.this, "Daten konnten nicht geladen werden", Toast.LENGTH_LONG);
                }
            }
        });
    }

}
