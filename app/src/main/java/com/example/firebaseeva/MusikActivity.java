package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MusikActivity extends AppCompatActivity {

    private Button buttonArr[];
    private FirebaseFirestore db;
    private static final String MUSIK = "musikTanz";
    private static final String KAT = "kat";
    private static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musik);

        db = FirebaseFirestore.getInstance();

        buttonArr = new Button[2];
        buttonArr[0] = (Button) findViewById(R.id.button_musik1);
        buttonArr[1] = (Button) findViewById(R.id.button_musik2);

        this.getData();

    }

    public void getData() {

        List<Object> data = new ArrayList<>();

        db.collection("Veranstaltungen").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int counter = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            if (document.getString(KAT).equals(MUSIK)) {
                                String name = document.getString(NAME);
                                buttonArr[counter].setText(name);
                                counter++;
                            }
                        } else {
                            Toast.makeText(MusikActivity.this, "Dokument existiert nicht", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(MusikActivity.this, "Daten konnten nicht geladen werden", Toast.LENGTH_LONG);
                }
            }
        });
    }

    public void home(View view) {
        startActivity(new Intent(MusikActivity.this, HomeActivity.class));
    }

}
