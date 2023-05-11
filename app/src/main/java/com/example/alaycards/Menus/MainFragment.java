package com.example.alaycards.Menus;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alaycards.R;

public class MainFragment extends Fragment {
    public MainFragment() {
        super(R.layout.fragment_menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.main_exit).setOnClickListener(v-> getActivity().finish());
        view.findViewById(R.id.main_leaderboard).setOnClickListener( v->
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new LeaderboardFragment(), "leaderboard")
                        .addToBackStack("leaderboard")
                        .commit()
        );
        view.findViewById(R.id.main_play).setOnClickListener( v->
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new DifficultyFragment(), "play")
                        .addToBackStack("play")
                        .commit()
        );
    }
}
