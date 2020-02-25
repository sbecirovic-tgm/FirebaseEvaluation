package com.example.firebaseeva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView textView;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText email, password;

    private Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                }
            }
        };

        email = (EditText) findViewById(R.id.editText_Email);
        password = (EditText) findViewById(R.id.editText_Password);

        loginBtn = (Button) findViewById(R.id.button_login);
        registerBtn = (Button) findViewById(R.id.button_register);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void signIn(View view) {
        final String emailString = email.getText().toString();
        final String passwordString = password.getText().toString();

        if(TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString)) {
            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        updateUI();
                    }else {
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void updateUI() {
        startActivity(new Intent(this, AccountActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
