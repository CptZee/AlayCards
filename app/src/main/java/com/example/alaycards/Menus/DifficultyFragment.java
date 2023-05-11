package com.example.alaycards.Menus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alaycards.Menus.Game.EasyFragment;
import com.example.alaycards.Menus.Game.HardFragment;
import com.example.alaycards.Menus.Game.NormalFragment;
import com.example.alaycards.R;
import com.example.alaycards.Services.MenuMusicService;

public class DifficultyFragment extends Fragment {
    public DifficultyFragment() {
        super(R.layout.fragment_difficulty);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.fx_button);

        view.findViewById(R.id.difficulty_easy).setOnClickListener(v -> {
            player.start();
            view.postDelayed(()-> {
                getActivity().stopService(new Intent(getContext(), MenuMusicService.class));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new EasyFragment(), "easy")
                        .addToBackStack("easy")
                        .commit();
            }, 150);
        });

        view.findViewById(R.id.difficulty_normal).setOnClickListener(v -> {
            player.start();
            view.postDelayed(()-> {
                getActivity().stopService(new Intent(getContext(), MenuMusicService.class));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new NormalFragment(), "normal")
                        .addToBackStack("normal")
                        .commit();
            }, 150);
        });

        view.findViewById(R.id.difficulty_hard).setOnClickListener(v -> {
            player.start();
            view.postDelayed(()-> {
                getActivity().stopService(new Intent(getContext(), MenuMusicService.class));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new HardFragment(), "hard")
                        .addToBackStack("hard")
                        .commit();
            }, 150);
        });

        view.findViewById(R.id.difficulty_return).setOnClickListener(v -> getActivity().onBackPressed());
    }
}
