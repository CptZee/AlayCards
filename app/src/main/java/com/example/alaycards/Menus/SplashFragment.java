package com.example.alaycards.Menus;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.alaycards.Data.Helper.ScoreHelper;
import com.example.alaycards.R;

public class SplashFragment extends Fragment {
    public SplashFragment() {
        super(R.layout.fragment_splash);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageMonitor = view.findViewById(R.id.splash);
        Glide.with(this).asGif().load(R.raw.loading).into(imageMonitor);
        view.postDelayed(()->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new MainFragment(), "home")
                    .commit();
        }, 5000);

        ScoreHelper.get(getContext()).onCreate(ScoreHelper.get(getContext()).getWritableDatabase());
    }
}
