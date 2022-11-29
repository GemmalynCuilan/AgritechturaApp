package com.example.agritechturaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NotesActivity extends AppCompatActivity {

    private EditText notes_text, content_text;
    private ImageButton saveButton, menuButton;
    private TextView page_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotesActivity.this, NoteListActivity.class));
            }
        });
        notes_text = findViewById(R.id.notes_text);
        content_text = findViewById(R.id.content_text);
        saveButton = findViewById(R.id.saveButton);
        page_title = findViewById(R.id.page_title);

        saveButton.setOnClickListener( (view) -> saveNote());
    }

   void saveNote() {
        String noteTitle = notes_text.getText().toString();
        String noteContent = content_text.getText().toString();
        if(noteTitle==null || noteContent.isEmpty()){
            notes_text.setError("Title is required");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());
        saveNoteToFirebase(note);
    }
    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Utility.showToast(NotesActivity.this, "Note added successfully!");
                    finish();
                }else{
                    Utility.showToast(NotesActivity.this, "Failed while adding note!");
                }
            }
        });
    }
}