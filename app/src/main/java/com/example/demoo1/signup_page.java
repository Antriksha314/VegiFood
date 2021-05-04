package com.example.demoo1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_page extends AppCompatActivity {
    EditText emailId, password;
    Button  signnup;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.new_emailid);
        password = findViewById(R.id.new_password);
        signnup = findViewById(R.id.signup_button);
        signnup.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(signup_page.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(signup_page.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                                Toast.makeText(signup_page.this, "Signup Unsuccessfully, Please try again", Toast.LENGTH_SHORT).show();
                            else{
                                Intent intToHome = new Intent(signup_page.this,navigation_home_page.class);
                                startActivity(intToHome);
                            }
                        }
                    });


                } else {
                    Toast.makeText(signup_page.this, "Error Ocurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
