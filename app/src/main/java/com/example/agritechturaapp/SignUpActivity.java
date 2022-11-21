package com.example.agritechturaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signupButton;
    private EditText fullname, email, password, cPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);

        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        cPassword = (EditText) findViewById(R.id.cPassword);


        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signupButton:
                signupButton();
                break;
            case R.id.backButton:
                startActivity(new Intent(this, LoginActivity.class));

        }
    }

    private void signupButton() {
        String name = fullname.getText().toString().trim();
        String emailAdd = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String conPass = cPassword.getText().toString().trim();

        if(name.isEmpty()){
            fullname.setError("Full name is required");
            fullname.requestFocus();
            return;
        }
        if(emailAdd.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if(conPass.isEmpty()){
            cPassword.setError("confirm password is required");
            cPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailAdd, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    User user = new User(name, emailAdd, pass);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this,"User has been registered sucessfully!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this, "Failed to register!Try again!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }else{
                    Toast.makeText(SignUpActivity.this, "Failed to register!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}