package com.example.agritechturaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.agritechturaapp.profile.ChangePassword;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;


public class Dashboard extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseUser User;
    private FirebaseAuth Auth;
    private RecyclerView myList;
    private  RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ImageView  menu_profile, menu_schedule, menu_tips;
    private String userId = "";
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //myList = (RecyclerView) findViewById(R.id.recycler_menu);
        //myList.setLayoutManager(new LinearLayoutManager(Dashboard.this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Agritectura");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

                menu_profile = (ImageView) findViewById(R.id.menu_profile);
                menu_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Dashboard.this, ProfileActivity.class));
                    }
                });
                menu_schedule = (ImageView) findViewById(R.id.menu_schedule);
                menu_schedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Dashboard.this, ScheduleReminder.class));
                    }
                });
                menu_tips = (ImageView) findViewById(R.id.menu_tips);
                menu_tips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Dashboard.this, TipsandTutorial.class));
                    }
                });

            }




            @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            Intent intent = new Intent(Dashboard.this, ProfileActivity.class);
            startActivity(intent);
        }

        if (id == R.id.nav_changePassword) {
            Intent intent = new Intent(Dashboard.this, ChangePassword.class);
            startActivity(intent);

        }else if (id == R.id.nav_logout) {
        Intent intent = new Intent(Dashboard.this, LoginActivity.class);
        Toast.makeText(Dashboard.this,"User has been Logout sucessfully!",Toast.LENGTH_LONG).show();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}
