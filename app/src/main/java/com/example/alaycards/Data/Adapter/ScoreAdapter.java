package com.example.alaycards.Data.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.alaycards.Data.Score;
import com.example.alaycards.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private List<Score> list;
    public ScoreAdapter(List<Score> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scores, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getPlace().setText("Place: " + (position + 1));
        Score data = list.get(position);
        viewHolder.getID().setText("Run ID: " + data.getID());
        viewHolder.getTime().setText("Date: " + new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(data.getDate()));
        int remainingTime = data.getRemainingTime();
        int minutes = remainingTime / 1000 / 60;
        int seconds = (remainingTime / 1000) % 60;
        viewHolder.getRemaining().setText("Remaining Time: " + String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView place, ID, time, remaining;

        public TextView getPlace() {
            return place;
        }

        public TextView getID() {
            return ID;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getRemaining() {
            return remaining;
        }

        public ViewHolder(View view) {
            super(view);
            place = view.findViewById(R.id.item_place);
            ID = view.findViewById(R.id.item_id);
            time = view.findViewById(R.id.item_time);
            remaining = view.findViewById(R.id.item_remaining);
        }
    }
}