package com.example.imransk.buproject1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private EditText email_log, password_log;
    private Button logInBtn, sign_UpBtn;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_log = findViewById(R.id.email_login);
        password_log = findViewById(R.id.password_login);
        logInBtn = findViewById(R.id.log_in_button);
        sign_UpBtn = findViewById(R.id.sign_Up_button);
        progressBar = findViewById(R.id.progressBar_login);

        auth=FirebaseAuth.getInstance();
/*
        final String email_login=email_log.getText().toString();
        final String password_login=password_log.getText().toString();*/

        sign_UpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logIn();

            }
        });
    }

    public void logIn() {
        String email = email_log.getText().toString().trim();
        final String password = password_log.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            email_log.setError("Enter email address!");
            email_log.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {

            password_log.setError("Enter passqord at last 6");
            password_log.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                password_log.setError("Enter password more than 5 char");
                            } else {
                                Toast.makeText(LogInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            /*Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                            startActivity(intent);*/
                            Toast.makeText(LogInActivity.this, "Success Login ", Toast.LENGTH_SHORT).show();
//                            finish();
                        }

                        // ...
                    }
                });
    }
}

