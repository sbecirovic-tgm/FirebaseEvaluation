package com.example.firebaseeva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilActivity extends AppCompatActivity {

    private TextView infoTV;
    private Button logoutBtn;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = FirebaseAuth.getInstance().getCurrentUser();
        infoTV = (TextView) findViewById(R.id.textView_info);
        infoTV.setText(user.getEmail().toString());

        logoutBtn = (Button) findViewById(R.id.button_logout);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(ProfilActivity.this, MainActivity.class));
    }
}
