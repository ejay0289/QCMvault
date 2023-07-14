package com.jaytech.qcmvault;

import android.annotation.NonNull;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.jaytech.qcmvault.SignUpActivity;

public class SignUpActivity extends Activity {
    FirebaseDatabase usersbase;
    FirebaseAuth mAuth;
    EditText emailEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    TextView toLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ActionBar action = getActionBar();
        action.hide();
        
        toLogin = findViewById(R.id.login_text_clickable);
        
        mAuth = FirebaseAuth.getInstance();
//        
        usernameEditText = findViewById(R.id.usernameText);
        emailEditText = findViewById(R.id.emailEditText); // Replace with your actual EditText ID
        passwordEditText = findViewById(R.id.passwordEditText); // Replace with your actual EditText ID
        toLogin = findViewById(R.id.login_text_clickable);
              Button signUpButton = findViewById(R.id.signUpButton); // Replace with your actual Button ID
       
        passwordEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // No implementation needed
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String password = s.toString(); // Get the email text entered
                    
                    if (!(password.length()<6)) {
                        passwordEditText.setError(null); // Clear error if email is valid
                    } else {
                        passwordEditText.setError("Password must be atleast 6 characters long"); // Show error if email is invalid
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // No implementation needed
                }
            });
        
              
              
              signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                    String username = usernameEditText.getText().toString();
                    if(!password.isEmpty() && !email.isEmpty() && !username.isEmpty() ){
                        signUpWithEmailAndPassword(email, password,username);
                        passwordEditText.setError(null);
                        emailEditText.setError(null);
                        usernameEditText.setError(null);
                    }else{
                        if(password.isEmpty()){
                            passwordEditText.setError("Mot de passe ne peut pas être vide");
                            passwordEditText.requestFocus();
                        }
                        if(email.isEmpty()){
                            emailEditText.setError("Email ne peut pas être vide");
                            emailEditText.requestFocus();

                        }
                        if(username.isEmpty()){
                            usernameEditText.setError("nom d'utilisateur ne peut pas être vide");
                            usernameEditText.requestFocus();
                        }
                    }   }
            });
//            
        toLogin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
    }
    
    private void signUpWithEmailAndPassword(final String email, String password,final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

//
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build();

                        user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Display name updated successfully
                                        // You can also retrieve the updated display name using user.getDisplayName()
                                    } else {
                                        // Failed to update display name
                                    }
                                }
                            });          Toast.makeText(SignUpActivity.this, username,
                        Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivity.this,AnneeActivity.class);
                        startActivity(i);
                        finish();
                    } else {

                        Toast.makeText(SignUpActivity.this, "Sign-up failed.",
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
            Intent i = new Intent(SignUpActivity.this, AnneeActivity.class);
            startActivity(i);
            finish();
        }
    }
    
    
}
