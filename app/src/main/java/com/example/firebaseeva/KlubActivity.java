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
import java.util.List;
import java.util.Map;

public class KlubActivity extends AppCompatActivity {

    TextView kname, tage, uhrzeit, adresse, telNr;
    private Button buttonArr[];
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klub);

        db = FirebaseFirestore.getInstance();

        buttonArr = new Button[2];
        buttonArr[0] = (Button) findViewById(R.id.button_klub1);
        buttonArr[1] = (Button) findViewById(R.id.button_klub2);
        //buttonArr[2] = (Button) findViewById(R.id.button_klub3);

        this.getData();
    }

    public void getData() {

        final List<Object> data = new ArrayList<>();

        db.collection("Klubdaten").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int docCounter = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (docCounter < buttonArr.length) {
                            data.add(document.getData());
                            Map<String, Object> dataMap = (Map<String, Object>) data.get(docCounter);
                            buttonArr[docCounter].setText(dataMap.get("adresse").toString());
                            docCounter++;
                        }
                    }
                } else {
                    Toast.makeText(KlubActivity.this, "Daten konnten nicht geladen werden", Toast.LENGTH_LONG);
                }
            }
        });
    }

}
