package com.example.agritechturaapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.agritechturaapp.CalendarAdapter;
import com.example.agritechturaapp.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
