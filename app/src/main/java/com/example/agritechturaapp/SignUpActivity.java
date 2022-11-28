package com.example.agritechturaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private TextView signupButton;
    private EditText fullname, email, password, cPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog loadBar;
    private FirebaseUser User;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        loadBar = new ProgressDialog(this);



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
        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuthentication();
            }
        });
    }

    private void PerformAuthentication() {
        String name = fullname.getText().toString().trim();
        String emailAdd = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String conpass = cPassword.getText().toString().trim();


        if (name.isEmpty()){
            fullname.setError("Enter fullname!");
            Toast.makeText(this, "Please input your full name....", Toast.LENGTH_SHORT).show();
        }
        else if (emailAdd.isEmpty()) {
            email.setError("Enter correct E-mail!");
            Toast.makeText(this, "Please input correct E-Mail...", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty()) {
            password.setError("Enter Password!");
            Toast.makeText(this, "Please input your password...", Toast.LENGTH_SHORT).show();
        } else if (conpass.isEmpty()) {
            cPassword.setError("Enter confirm passwrd");
            Toast.makeText(this, "Please enter your confirm password...", Toast.LENGTH_SHORT).show();
        }else{
            register(name,emailAdd, pass, conpass);

        }
    }

    private void register(String name, String emailAdd, String pass, String conpass) {
        loadBar.setTitle("Creating Account");
        loadBar.setMessage("Please wait!");
        loadBar.setCanceledOnTouchOutside(false);
        loadBar.show();
        mAuth.createUserWithEmailAndPassword(emailAdd,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User = mAuth.getCurrentUser();
                    assert User != null;
                    String userId = User.getUid();
                   reference = FirebaseDatabase.getInstance().getReference("users").child(userId);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId", userId);
                    hashMap.put("email", emailAdd);
                    hashMap.put("password", pass);
                    hashMap.put("confirm password", conpass);
                    hashMap.put("fullname", name);
                    hashMap.put("profileImage", "default");
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                loadBar.dismiss();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(SignUpActivity.this, "User has been registered sucessfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                loadBar.dismiss();
                                Toast.makeText(SignUpActivity.this, " " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    loadBar.dismiss();
                    Toast.makeText(SignUpActivity.this, "Account Created Successfull!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


}



