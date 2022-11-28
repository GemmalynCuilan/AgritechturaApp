package com.example.agritechturaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.firestore.Query;

public class NoteListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton floatingBtn, menuButton;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        
        menuButton = findViewById(R.id.menuButton);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NotesActivity.class));
            }
        });
        menuButton.setOnClickListener((view) -> showMenu());
        setupRecyclerView();
    }

    private void setupRecyclerView() {
    }

    private void showMenu() {
        Query query =Utility.getCollectionReferenceForNotes().orderBy("timestamp",Query.Direction.DESCENDING);


    }
}