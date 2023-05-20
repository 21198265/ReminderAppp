package com.example.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button btn;
    Button loginbtn;
    FirebaseAuth mAuth;
    Button SignBtn;
    String emailAddress;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.emailAddress);
        editTextPassword = findViewById(R.id.password);
        btn = findViewById(R.id.SignBtn);
        loginbtn = findViewById(R.id.Login_btn);
        SignBtn = findViewById(R.id.SignBtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress, password;
                emailAddress = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(emailAddress)) {
                    Toast.makeText(LoginScreen.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginScreen.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginScreen.this, "Sign-in successful.",
                                            Toast.LENGTH_SHORT).show();
                                    // Perform any necessary actions after successful sign-in
                                } else {
                                    // If sign-in fails, display a message to the user
                                    Toast.makeText(LoginScreen.this, "Sign-in failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpPage();
            }
        });
    }
    public void openSignUpPage() {
        Intent intent = new Intent(this, SignUpScreen.class);
        startActivity(intent);
    }

}

