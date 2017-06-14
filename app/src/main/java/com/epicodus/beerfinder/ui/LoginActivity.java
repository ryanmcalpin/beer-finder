package com.epicodus.beerfinder.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.signUpLink) TextView mSignUpLink;
    @Bind(R.id.logInButton) Button mLoginButton;
    @Bind(R.id.emailInput) EditText mEmailView;
    @Bind(R.id.passwordInput) EditText mPasswordView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    finish();
                }
            }
        };

        mSignUpLink.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        createAuthProgDialog();
    }

    private void createAuthProgDialog() {
        mAuthProgDialog = new ProgressDialog(this);
        mAuthProgDialog.setTitle("Loading...");
        mAuthProgDialog.setMessage("Authenticating with Firebase...");
        mAuthProgDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        if (v == mSignUpLink) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();
        }
        if (v == mLoginButton) {
            loginWithPassword();
        }
    }

    private void loginWithPassword() {
        String email = mEmailView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
        if (email.trim().equals("")) {
            mEmailView.setError("Please enter your email");
            return;
        }
        if (password.trim().equals("")) {
            mPasswordView.setError("Please enter your password");
            return;
        }
        mAuthProgDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgDialog.dismiss();
                if (!task.isSuccessful()) {
                    Log.w("signInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
