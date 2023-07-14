package com.jaytech.qcmvault;

import android.annotation.NonNull;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.jaytech.qcmvault.LoginActivity;
import android.app.ActionBar;

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView toSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        ActionBar action = getActionBar();
        action.hide();
        
        mAuth = FirebaseAuth.getInstance();
        toSignUp = findViewById(R.id.signup_text_clickable);
        emailEditText = findViewById(R.id.loginEmail);
        passwordEditText = findViewById(R.id.loginPassword);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();

                    if(!password.isEmpty() && !email.isEmpty()){
                        loginWithEmailAndPassword(email, password);
                        passwordEditText.setError(null);
                        emailEditText.setError(null);
                    }else{
                        if(password.isEmpty()){
                            passwordEditText.setError("Mot de passe ne peut pas être vide");
                            passwordEditText.requestFocus();
                        }
                        if(email.isEmpty()){
                            emailEditText.setError("Email ne peut pas être vide");
                            emailEditText.requestFocus();
                        }

                    }
                }
            });
            
            toSignUp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                    startActivity(intent);
                }
            });
        toSignUp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                    startActivity(intent);
                }
            });
    }

    private void loginWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Login success, update UI or navigate to the next activity
                        Intent i = new Intent(LoginActivity.this,AnneeActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Login successful",
                                       Toast.LENGTH_SHORT).show();
                    } else {
                        // Login failed, display an error message to the user
                        Toast.makeText(LoginActivity.this, "Login failed",
                                       Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Intent i = new Intent(LoginActivity.this, AnneeActivity.class);
            startActivity(i);
            finish();
            }
        
    }
    
    
    
}
