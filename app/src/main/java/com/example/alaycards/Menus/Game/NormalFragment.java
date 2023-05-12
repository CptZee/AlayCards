package com.example.alaycards.Menus.Game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alaycards.Data.Enum.Difficulty;
import com.example.alaycards.Data.Helper.ScoreHelper;
import com.example.alaycards.Data.Score;
import com.example.alaycards.R;
import com.example.alaycards.Services.NormalLevelMusicService;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Locale;

public class NormalFragment extends EasyFragment {
    public NormalFragment() {
        super(R.layout.fragment_normal);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView one = view.findViewById(R.id.normal_1);
        ImageView two = view.findViewById(R.id.normal_2);
        ImageView three = view.findViewById(R.id.normal_3);
        ImageView four = view.findViewById(R.id.normal_4);
        ImageView five = view.findViewById(R.id.normal_5);
        ImageView six = view.findViewById(R.id.normal_6);
        ImageView seven = view.findViewById(R.id.normal_7);
        ImageView eight = view.findViewById(R.id.normal_8);
        ImageView nine = view.findViewById(R.id.normal_9);
        ImageView ten = view.findViewById(R.id.normal_10);
        ImageView eleven = view.findViewById(R.id.normal_11);
        ImageView twelve = view.findViewById(R.id.normal_12);

        getActivity().startService(new Intent(getContext(), NormalLevelMusicService.class));

        view.findViewById(R.id.normal_complete).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.normal_complete).setOnClickListener(v -> finish(view));

        view.findViewById(R.id.normal_reset).setOnClickListener(v ->
                generateItems()
        );

        view.findViewById(R.id.normal_return).setOnClickListener(v ->
                EasyFragment.returnToMenu((AppCompatActivity) getActivity(), view)
        );
        //Countdown code
        timer = view.findViewById(R.id.normal_timer);
        startCountdown();

        //Code for the cards
        imageViews.add(one);
        imageViews.add(two);
        imageViews.add(three);
        imageViews.add(four);
        imageViews.add(five);
        imageViews.add(six);
        imageViews.add(seven);
        imageViews.add(eight);
        imageViews.add(nine);
        imageViews.add(ten);
        imageViews.add(eleven);
        imageViews.add(twelve);

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
                card.animate().rotationYBy(180).setDuration(150);
                MediaPlayer.create(getContext(), R.raw.fx_card).start();
                card.postDelayed(()->{
                    card.setImageResource(cards.get(finalI));
                    if (selectedItem == null) {
                        selectedItem = card;
                        selectedItemDrawableID = cards.get(finalI);
                        return;
                    }
                    compare(finalI1, view);
                }, 300);
            });
        }
    }

    @Override
    protected void generateItems() {
        cards = CardGenerator.convertToCards(CardGenerator.generateNormal());
        for (int i = 0; i < 12; i++) {
            imageViews.get(i).setImageResource(R.drawable.card);
            imageViews.get(i).setEnabled(true);
            if(imageViews.get(i).getRotationY() == 180)
                imageViews.get(i).animate().rotationYBy(180).setDuration(0).start();
            validating = false;
            selectedItem = null;
        }
    }

    @Override
    protected void startCountdown() {
        countDownTimer = new CountDownTimer(3 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                timer.setText(timeLeftFormatted);
                if (seconds <= 15 && minutes < 1){
                    MediaPlayer.create(getContext(), R.raw.fx_clock).start();
                    timer.setTextColor(Color.parseColor("#FF0000"));
                }
            }

            @Override
            public void onFinish() {
                timer.setText("GAME OVER!");
                timer.setTextColor(Color.parseColor("#FF0000"));
                for (ImageView card : imageViews) {
                    if (card != null)
                        card.setEnabled(false);
                }
                showGameOverDialog();
            }
        };
        countDownTimer.start();
    }

    @Override
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
                selectedItem = null;
                validating = false;
                if (isFinished()) {
                    showVictoryDialog();
                    view.postDelayed(()->
                                    view.findViewById(R.id.normal_complete).setVisibility(View.VISIBLE)
                            , 300);
                    countDownTimer.cancel();
                }
            } else {
                toCompare.animate().rotationYBy(180).setDuration(150).withEndAction(() ->
                        toCompare.setRotation(0));
                selectedItem.animate().rotationYBy(180).setDuration(150).withEndAction(() -> {
                    selectedItem.setRotation(0);
                    selectedItem = null;
                    validating = false;
                });
                toCompare.setImageResource(R.drawable.card);
                selectedItem.setImageResource(R.drawable.card);
            }
        }, 750);
    }

    @Override
    protected void finish(View view){
        SpannableString completeMessage = new SpannableString("Hard level completed!");
        completeMessage.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, completeMessage.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        Score score = new Score();

        String[] timeParts = timeLeftFormatted.split(":");
        int minutes = Integer.parseInt(timeParts[0]); // Parse minutes value
        int seconds = Integer.parseInt(timeParts[1]); // Parse seconds value
        int totalMilliseconds = (minutes * 60 + seconds) * 1000;

        score.setRemainingTime(totalMilliseconds);
        score.setDate(Calendar.getInstance().getTime());
        score.setDifficulty(Difficulty.NORMAL);

        ScoreHelper.get(getContext()).insert(score);

        returnToMenu((AppCompatActivity) getActivity(), view);
        Snackbar.make(view.findViewById(R.id.normal_complete), completeMessage, Snackbar.LENGTH_SHORT).show();
    }
}
