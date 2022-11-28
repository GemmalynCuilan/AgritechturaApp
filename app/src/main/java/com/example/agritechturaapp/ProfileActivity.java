package com.example.agritechturaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    private TextView name, emailAdd;

    private FirebaseUser User;
    private DatabaseReference reference;
    private CircleImageView profileImage;
    private String userID;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });


        name =  (TextView) findViewById(R.id.fullname);
        emailAdd =  (TextView) findViewById(R.id.emailAddress);

        profileImage = (CircleImageView) findViewById(R.id.profileImage);

        mAuth = FirebaseAuth.getInstance();
        User = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users").child(User.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;
                name.setText(user.getName());
                name.setAllCaps(true);
                emailAdd.setText(User.getEmail());

                if(user.getProfileImage().equals("default")){
                    profileImage.setImageResource(R.drawable.agrilog1o);
                }else{
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(profileImage);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Error, please report this bug!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}