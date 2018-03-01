package com.example.imransk.buproject1;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends Activity {
    private EditText email_input, password_input;
    private Button signIn_btn, signUp_btn;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        signIn_btn = (Button) findViewById(R.id.sign_in_button);
        signUp_btn = (Button) findViewById(R.id.sign_up_button);
        email_input = (EditText) findViewById(R.id.email);
        password_input = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            }
        });

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUP();
            }
        });
    }

    public void signUP() {
        String email = email_input.getText().toString().trim();
        String password = password_input.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            email_input.setError("Enter E-mail");
            email_input.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {

            password_input.setError("Enter password minmum 6 characters");
            password_input.requestFocus();
            return;
        }

        if (password.length() < 6) {
            password_input.setError("Enter password minmum 6 characters");
            password_input.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(SignUpActivity.this, " SignUp Success", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUpActivity.this, "Sorry Having some problem \nSignUp Failed", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
