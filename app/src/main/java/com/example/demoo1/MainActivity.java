package com.example.demoo1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class MainActivity extends AppCompatActivity {
    EditText emailId, password;
    Button login;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        login = findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = emailId.getText().toString();
               String pwd = password.getText().toString();
               if (email.isEmpty()) {
                   emailId.setError("Please enter email id");
                   emailId.requestFocus();
               } else if (pwd.isEmpty()) {
                   password.setError("Please enter password");
                   password.requestFocus();
               } else if (email.isEmpty() && pwd.isEmpty()) {
                   Toast.makeText(MainActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
               } else if (!(email.isEmpty() && pwd.isEmpty())) {
                   mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful())
                               Toast.makeText(MainActivity.this, "Login Error, Please Login again", Toast.LENGTH_SHORT).show();
                           else{
                               Intent intToHome = new Intent(MainActivity.this,home.class);
                               startActivity(intToHome);
                           }
                       }
                   });


               } else {
                   Toast.makeText(MainActivity.this, "Error Ocurred", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}