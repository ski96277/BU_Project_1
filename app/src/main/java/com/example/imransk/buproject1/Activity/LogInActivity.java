package com.example.imransk.buproject1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.imransk.buproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInActivity extends Activity {
    private EditText email_log, password_log;
    private Button logInBtn, sign_UpBtn;
    private  Button forget_pass_btn;
    private ProgressBar progressBar;

    FirebaseAuth auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

      /*  ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);//hide title
        actionBar.setDisplayShowHomeEnabled(false);//hide icon*/

        email_log = findViewById(R.id.email_login);
        password_log = findViewById(R.id.password_login);
        logInBtn = findViewById(R.id.log_in_button);
        sign_UpBtn = findViewById(R.id.sign_Up_button);
        progressBar = findViewById(R.id.progressBar_login);
        forget_pass_btn=findViewById(R.id.forgot_password);

        auth = FirebaseAuth.getInstance();

        forget_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Forgot_Pass_Activity.class));
            }
        });
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), LoginSuccessActivity.class));
            finish();
        }


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
        if (!validEmail(email)) {
            email_log.setError("Enter Valid email address!");
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

                            Toast.makeText(LogInActivity.this, "Success Login ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginSuccessActivity.class));
                            finish();
                        }

                        // ...
                    }
                });
    }


    //E-mail validation
    private boolean validEmail(String email) {
        String email_pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern=Pattern.compile(email_pattern);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }

    /*@Override
    public void onBackPressed() {*//*
        super.onBackPressed();
        int backButtonCount=0;
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }*//*
    }*/
}

