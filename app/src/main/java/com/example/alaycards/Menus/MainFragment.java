package com.example.alaycards.Menus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alaycards.R;
import com.example.alaycards.Services.MenuMusicService;

public class MainFragment extends Fragment {
    public MainFragment() {
        super(R.layout.fragment_menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.fx_button);
        getActivity().startService(new Intent(getContext(), MenuMusicService.class));

        view.findViewById(R.id.main_exit).setOnClickListener(v -> {
            player.start();
            view.postDelayed(() ->
                            getActivity().finish()
                    , 150);
        });
        view.findViewById(R.id.main_leaderboard).setOnClickListener(v -> {
            player.start();
            view.postDelayed(() ->
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_container, new LeaderboardFragment(), "leaderboard")
                                    .addToBackStack("leaderboard")
                                    .commit()
                    , 150);
        });
        view.findViewById(R.id.main_play).setOnClickListener(v -> {
            player.start();
            view.postDelayed(() ->
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_container, new DifficultyFragment(), "play")
                                    .addToBackStack("play")
                                    .commit()
                    , 150);
        });
    }
}
