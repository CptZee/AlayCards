package com.example.alaycards.Menus.Game;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alaycards.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class HardFragment extends EasyFragment {
    public HardFragment() {
        super(R.layout.fragment_hard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.hard_complete).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.hard_complete).setOnClickListener(v -> {
            SpannableString completeMessage = new SpannableString("Hard level completed!");
            completeMessage.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, completeMessage.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            returnToMenu((AppCompatActivity) getActivity());
            Snackbar.make(view.findViewById(R.id.hard_complete), completeMessage, Snackbar.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.hard_reset).setOnClickListener(v ->
                generateItems()
        );

        view.findViewById(R.id.hard_return).setOnClickListener(v ->
                EasyFragment.returnToMenu((AppCompatActivity) getActivity())
        );
        //Countdown code
        timer = view.findViewById(R.id.hard_timer);
        startCountdown();
    }

    @Override
    protected void generateItems() {

    }
    @Override
    protected void startCountdown() {
        countDownTimer = new CountDownTimer(1 * 60 * 1000, 1000) {
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
