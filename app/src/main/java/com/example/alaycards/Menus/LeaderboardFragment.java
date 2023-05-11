package com.example.alaycards.Menus;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alaycards.Data.Adapter.ScoreAdapter;
import com.example.alaycards.Data.Enum.Difficulty;
import com.example.alaycards.Data.Helper.ScoreHelper;
import com.example.alaycards.Data.Score;
import com.example.alaycards.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardFragment extends Fragment {
    public LeaderboardFragment() {
        super(R.layout.fragment_leaderboard);
    }

    private List<Score> list;
    private TextView indicator;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView easy = view.findViewById(R.id.leaderboard_easy);
        TextView normal = view.findViewById(R.id.leaderboard_normal);
        TextView hard = view.findViewById(R.id.leaderboard_hard);
        indicator = view.findViewById(R.id.leaderboard_data_indicator);
        RecyclerView listView = view.findViewById(R.id.leaderboard_list);
        final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.fx_button);

        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setAdapter(new ScoreAdapter(loadEasy()));
        easy.setTypeface(null, Typeface.BOLD);

        view.findViewById(R.id.leaderboard_return).setOnClickListener(v-> {
            player.start();
            view.postDelayed(()-> getActivity().onBackPressed(), 150);
        });

        easy.setOnClickListener(v->{
            player.start();
            view.postDelayed(()->{
                listView.setAdapter(new ScoreAdapter(loadEasy()));
                easy.setTypeface(null, Typeface.BOLD);
                normal.setTypeface(null, Typeface.NORMAL);
                hard.setTypeface(null, Typeface.NORMAL);
            }, 150);
        });
        normal.setOnClickListener(v->{
            player.start();
            view.postDelayed(()->{
                listView.setAdapter(new ScoreAdapter(loadNormal()));
                easy.setTypeface(null, Typeface.NORMAL);
                normal.setTypeface(null, Typeface.BOLD);
                hard.setTypeface(null, Typeface.NORMAL);
            }, 150);
        });
        hard.setOnClickListener(v->{
            player.start();
            view.postDelayed(()->{
                listView.setAdapter(new ScoreAdapter(loadHard()));
                easy.setTypeface(null, Typeface.NORMAL);
                normal.setTypeface(null, Typeface.NORMAL);
                hard.setTypeface(null, Typeface.BOLD);
            }, 150);
        });
    }

    public List<Score> loadEasy(){
        list = new ArrayList<>();
        for(Score score : ScoreHelper.get(getContext()).get()){
            if(score.getDifficulty() == Difficulty.EASY)
                list.add(score);
        }
        if(list.isEmpty()) {
            indicator.setText("No easy score runs found!");
            indicator.setVisibility(View.VISIBLE);
        }else
            indicator.setVisibility(View.INVISIBLE);
        sortList();
        return list;
    }

    public List<Score> loadNormal(){
        list = new ArrayList<>();
        for(Score score : ScoreHelper.get(getContext()).get()){
            if(score.getDifficulty() == Difficulty.NORMAL)
                list.add(score);
        }
        if(list.isEmpty()) {
            indicator.setText("No normal score runs found!");
            indicator.setVisibility(View.VISIBLE);
        }else
            indicator.setVisibility(View.INVISIBLE);
        sortList();
        return list;
    }

    public List<Score> loadHard(){
        list = new ArrayList<>();
        for(Score score : ScoreHelper.get(getContext()).get()){
            if(score.getDifficulty() == Difficulty.HARD)
                list.add(score);
        }
        if(list.isEmpty()) {
            indicator.setText("No hard score runs found!");
            indicator.setVisibility(View.VISIBLE);
        }else
            indicator.setVisibility(View.INVISIBLE);
        sortList();
        return list;
    }

    public void sortList(){
        @SuppressLint({"NewApi", "LocalSuppress"})
        Comparator<Score> lowestIntegerComparator = Comparator.comparingInt(Score::getRemainingTime);

        Collections.sort(list, lowestIntegerComparator);
    }
}
