package com.example.alaycards.Menus;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alaycards.Menus.Game.EasyFragment;
import com.example.alaycards.Menus.Game.HardFragment;
import com.example.alaycards.Menus.Game.NormalFragment;
import com.example.alaycards.R;

public class DifficultyFragment extends Fragment {
    public DifficultyFragment() {
        super(R.layout.fragment_difficulty);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.difficulty_easy).setOnClickListener( v->
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new EasyFragment(), "easy")
                        .addToBackStack("easy")
                        .commit()
        );
        view.findViewById(R.id.difficulty_normal).setOnClickListener( v->
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new NormalFragment(), "normal")
                        .addToBackStack("normal")
                        .commit()
        );
        view.findViewById(R.id.difficulty_hard).setOnClickListener( v->
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new HardFragment(), "hard")
                        .addToBackStack("hard")
                        .commit()
        );
        view.findViewById(R.id.difficulty_return).setOnClickListener( v-> getActivity().onBackPressed());
    }
}
