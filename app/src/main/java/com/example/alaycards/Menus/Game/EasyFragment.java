package com.example.alaycards.Menus.Game;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.alaycards.Menus.MainFragment;
import com.example.alaycards.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class EasyFragment extends Fragment {
    public EasyFragment() {
        super(R.layout.fragment_easy);
    }

    @ContentView
    public EasyFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }

    protected TextView timer;
    protected CountDownTimer countDownTimer;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this instanceof NormalFragment || this instanceof HardFragment)
            return;
        view.findViewById(R.id.easy_complete).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.easy_complete).setOnClickListener(v -> {
            SpannableString completeMessage = new SpannableString("Easy level completed!");
            completeMessage.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, completeMessage.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            returnToMenu((AppCompatActivity) getActivity());
            Snackbar.make(view.findViewById(R.id.easy_complete), completeMessage, Snackbar.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.easy_reset).setOnClickListener(v ->
                generateItems()
        );

        view.findViewById(R.id.easy_return).setOnClickListener(v ->
                EasyFragment.returnToMenu((AppCompatActivity) getActivity())
        );

        //Countdown code
        timer = view.findViewById(R.id.easy_timer);
        startCountdown();
    }

    protected static void returnToMenu(AppCompatActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new MainFragment(), "home")
                .commit();
    }

    protected void generateItems() {

    }

    protected void startCountdown() {
        countDownTimer = new CountDownTimer(5 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                timer.setText(timeLeftFormatted);
            }
            @Override
            public void onFinish() {
                timer.setText("GAME OVER!");
                timer.setTextColor(Color.parseColor("#FF0000"));
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
