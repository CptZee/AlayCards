package com.example.alaycards.Menus.Game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.alaycards.Data.Enum.Difficulty;
import com.example.alaycards.Data.Helper.ScoreHelper;
import com.example.alaycards.Data.Score;
import com.example.alaycards.Menus.MainFragment;
import com.example.alaycards.R;
import com.example.alaycards.Services.EasyLevelMusicService;
import com.example.alaycards.Services.HardLevelMusicService;
import com.example.alaycards.Services.MenuMusicService;
import com.example.alaycards.Services.NormalLevelMusicService;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    protected String timeLeftFormatted;
    protected ImageView selectedItem;
    protected int selectedItemDrawableID;
    protected List<Integer> cards;
    protected boolean validating = false;
    protected final List<ImageView> imageViews = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView one = view.findViewById(R.id.easy_1);
        ImageView two = view.findViewById(R.id.easy_2);
        ImageView three = view.findViewById(R.id.easy_3);
        ImageView four = view.findViewById(R.id.easy_4);
        ImageView five = view.findViewById(R.id.easy_5);
        ImageView six = view.findViewById(R.id.easy_6);

        if (this instanceof NormalFragment || this instanceof HardFragment)
            return;


        getActivity().startService(new Intent(getContext(), EasyLevelMusicService.class));

        view.findViewById(R.id.easy_complete).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.easy_complete).setOnClickListener(v -> finish(view));

        view.findViewById(R.id.easy_reset).setOnClickListener(v ->
                generateItems()
        );

        view.findViewById(R.id.easy_return).setOnClickListener(v ->
                EasyFragment.returnToMenu((AppCompatActivity) getActivity())
        );

        //Countdown code
        timer = view.findViewById(R.id.easy_timer);
        startCountdown();

        //Code for the cards
        imageViews.add(one);
        imageViews.add(two);
        imageViews.add(three);
        imageViews.add(four);
        imageViews.add(five);
        imageViews.add(six);

        generateItems();

        for (int i = 0; i < imageViews.size(); i++) {
            ImageView card = imageViews.get(i);
            if(card == null)
                return;
            int finalI = i;
            int finalI1 = i;

            card.setOnClickListener(v -> {
                if(validating)
                    return;
                if(selectedItem == card)
                    return;
                card.setImageResource(cards.get(finalI));
                if (selectedItem == null) {
                    selectedItem = card;
                    selectedItemDrawableID = cards.get(finalI);
                    return;
                }
                compare(finalI1, view);
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    protected static void returnToMenu(AppCompatActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new MainFragment(), "home")
                .commit();
        activity.stopService(new Intent(activity, EasyLevelMusicService.class));
        activity.stopService(new Intent(activity, NormalLevelMusicService.class));
        activity.stopService(new Intent(activity, HardLevelMusicService.class));
    }

    protected void generateItems() {
        cards = CardGenerator.convertToCards(CardGenerator.generateEasy());
        for (int i = 0; i < 6; i++) {
            imageViews.get(i).setImageResource(R.drawable.card);
        }
    }

    protected void startCountdown() {
        countDownTimer = new CountDownTimer(5 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
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

    protected void compare(int index, View view) {
        validating = true;
        view.postDelayed(() -> {
            ImageView toCompare = imageViews.get(index);
            Drawable drawable = toCompare.getDrawable();
            Drawable originalDrawable = getResources().getDrawable(selectedItemDrawableID);
            if (drawable.getConstantState().equals(originalDrawable.getConstantState())) {
                Toast.makeText(getContext(), "Found a match!", Toast.LENGTH_SHORT).show();
                toCompare.setEnabled(false);
                selectedItem.setEnabled(false);
                if (isFinished()) {
                    view.findViewById(R.id.easy_complete).setVisibility(View.VISIBLE);
                    countDownTimer.cancel();
                }
            } else {
                toCompare.setImageResource(R.drawable.card);
                selectedItem.setImageResource(R.drawable.card);
            }
            selectedItem = null;
            validating = false;
        }, 750);
    }

    protected boolean isFinished(){
        for(ImageView card : imageViews){
            Drawable drawable = card.getDrawable();
            Drawable originalDrawable = getResources().getDrawable(R.drawable.card);
            if (drawable.getConstantState().equals(originalDrawable.getConstantState()))
                return false;
        }
        return true;
    }

    protected void finish(View view){
        SpannableString completeMessage = new SpannableString("Easy level completed!");
        completeMessage.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, completeMessage.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        Score score = new Score();

        String[] timeParts = timeLeftFormatted.split(":");
        int minutes = Integer.parseInt(timeParts[0]); // Parse minutes value
        int seconds = Integer.parseInt(timeParts[1]); // Parse seconds value
        int totalMilliseconds = (minutes * 60 + seconds) * 1000;

        score.setRemainingTime(totalMilliseconds);
        score.setDate(Calendar.getInstance().getTime());
        score.setDifficulty(Difficulty.EASY);

        ScoreHelper.get(getContext()).insert(score);

        returnToMenu((AppCompatActivity) getActivity());
        Snackbar.make(view.findViewById(R.id.easy_complete), completeMessage, Snackbar.LENGTH_SHORT).show();
    }
}