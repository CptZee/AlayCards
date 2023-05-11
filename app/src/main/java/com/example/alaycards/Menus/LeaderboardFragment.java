package com.example.alaycards.Menus;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alaycards.R;

public class LeaderboardFragment extends Fragment {
    public LeaderboardFragment() {
        super(R.layout.fragment_leaderboard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.leaderboard_return).setOnClickListener(v-> getActivity().onBackPressed());
    }
}
