package com.example.agritechturaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    Context context;
    List<Note> postLists;

    public NoteAdapter(Context context, List<Note> postLists) {
        this.context = context;
        this.postLists = postLists;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
       Note note = postLists.get(position);
        holder.notes_textView.setText(note.title);
        holder.content_textView.setText(note.content);
        holder.time_textView.setText(Utility.timestampToString(note.timestamp));
    }


    @Override
    public int getItemCount() {
        return postLists.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView notes_textView,  content_textView, time_textView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            notes_textView = itemView.findViewById(R.id.notes_textView);
            content_textView = itemView.findViewById(R.id.content_textView);
            time_textView = itemView.findViewById(R.id.time_textView);
        }
    }
}
