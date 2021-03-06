package com.example.firebaseeva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, email, password, passwortWh, alterEingabe;
    Button registerBtn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseFirestore db;
    Map<String, Object> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase setzen
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(RegisterActivity.this, Interessen.class)
                    .putExtra("map", (Serializable) userData));
                }
            }
        };

        firstName = (EditText) findViewById(R.id.editText_Vorname);
        lastName = (EditText) findViewById(R.id.editText_Nachname);
        email = (EditText) findViewById(R.id.editText_Email);
        password = (EditText) findViewById(R.id.editText_Passwort1);
        passwortWh = (EditText) findViewById(R.id.editText_Passwort2);

        registerBtn = (Button) findViewById(R.id.button_registrieren);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        userData = new HashMap<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void signUp() {
        final String emailString = email.getText().toString();
        final String passwordString = password.getText().toString();
        final String passwordWhString = passwortWh.getText().toString();
        final String firstnameString = firstName.getText().toString();
        final String lastnameString = lastName.getText().toString();
        //final int alterString = Integer.parseInt(this.alterEingabe.getText().toString());


        if(TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString)) {
            Toast.makeText(RegisterActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();
        }else if(!passwordString.equals(passwordWhString)) {
            Toast.makeText(RegisterActivity.this, "Passwort stimmt nicht überein", Toast.LENGTH_LONG).show();
        }else {
            firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Map<String, String> name = new HashMap<>();
                        name.put("vorname", firstnameString);
                        name.put("nachname", lastnameString);
                        userData.put("name", name);
                        userData.put("BenutzerName", firstnameString.toLowerCase()+""+lastnameString);
                        userData.put("eMailAdresse", emailString);
                        //userData.put("alter", alterString);
                        db.collection("Profil").document(user.getEmail()).set(userData);
                    }
                }
            });
        }
    }
}
