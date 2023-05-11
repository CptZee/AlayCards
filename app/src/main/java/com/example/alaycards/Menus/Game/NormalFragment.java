package com.example.alaycards.Menus.Game;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

        view.findViewById(R.id.normal_complete).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.normal_complete).setOnClickListener(v -> finish(view));

        view.findViewById(R.id.normal_reset).setOnClickListener(v ->
                generateItems()
        );

        view.findViewById(R.id.normal_return).setOnClickListener(v ->
                EasyFragment.returnToMenu((AppCompatActivity) getActivity())
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

        one.setOnClickListener(v -> {
            one.setImageResource(cards.get(0));
            if (selectedItem == null) {
                selectedItem = one;
                selectedItemDrawableID = cards.get(0);
                return;
            }
            compare(one, view);
        });

        two.setOnClickListener(v -> {
            two.setImageResource(cards.get(1));
            if (selectedItem == null) {
                selectedItem = two;
                selectedItemDrawableID = cards.get(1);
                return;
            }
            compare(two, view);
        });

        three.setOnClickListener(v -> {
            three.setImageResource(cards.get(2));
            if (selectedItem == null) {
                selectedItem = three;
                selectedItemDrawableID = cards.get(2);
                return;
            }
            compare(three, view);
        });

        four.setOnClickListener(v -> {
            four.setImageResource(cards.get(3));
            if (selectedItem == null) {
                selectedItem = four;
                selectedItemDrawableID = cards.get(3);
                return;
            }
            compare(four, view);
        });

        five.setOnClickListener(v -> {
            five.setImageResource(cards.get(4));
            if (selectedItem == null) {
                selectedItem = five;
                selectedItemDrawableID = cards.get(4);
                return;
            }
            compare(five, view);
        });

        six.setOnClickListener(v -> {
            six.setImageResource(cards.get(5));
            if (selectedItem == null) {
                selectedItem = six;
                selectedItemDrawableID = cards.get(5);
                return;
            }
            compare(six, view);
        });

        seven.setOnClickListener(v -> {
            seven.setImageResource(cards.get(6));
            if (selectedItem == null) {
                selectedItem = seven;
                selectedItemDrawableID = cards.get(6);
                return;
            }
            compare(seven, view);
        });

        eight.setOnClickListener(v -> {
            eight.setImageResource(cards.get(7));
            if (selectedItem == null) {
                selectedItem = eight;
                selectedItemDrawableID = cards.get(7);
                return;
            }
            compare(eight, view);
        });

        nine.setOnClickListener(v -> {
            nine.setImageResource(cards.get(8));
            if (selectedItem == null) {
                selectedItem = nine;
                selectedItemDrawableID = cards.get(8);
                return;
            }
            compare(nine, view);
        });

        ten.setOnClickListener(v -> {
            ten.setImageResource(cards.get(9));
            if (selectedItem == null) {
                selectedItem = ten;
                selectedItemDrawableID = cards.get(9);
                return;
            }
            compare(ten, view);
        });

        eleven.setOnClickListener(v -> {
            eleven.setImageResource(cards.get(10));
            if (selectedItem == null) {
                selectedItem = eleven;
                selectedItemDrawableID = cards.get(10);
                return;
            }
            compare(eleven, view);
        });

        twelve.setOnClickListener(v -> {
            twelve.setImageResource(cards.get(11));
            if (selectedItem == null) {
                selectedItem = twelve;
                selectedItemDrawableID = cards.get(11);
                return;
            }
            compare(twelve, view);
        });

        generateItems();
    }

    @Override
    protected void generateItems() {
        cards = CardGenerator.convertToCards(CardGenerator.generateNormal());
        for (int i = 0; i < 12; i++) {
            imageViews.get(i).setImageResource(R.drawable.card);
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
    protected void compare(ImageView toCompare, View view) {
        Drawable drawable = toCompare.getDrawable();
        Drawable originalDrawable = getResources().getDrawable(selectedItemDrawableID);
        if (drawable.getConstantState().equals(originalDrawable.getConstantState())) {
            toCompare.setEnabled(false);
            selectedItem.setEnabled(false);
            selectedItem = null;
            Toast.makeText(getContext(), "You found a match!", Toast.LENGTH_SHORT).show();
            if(isFinished()) {
                view.findViewById(R.id.normal_complete).setVisibility(View.VISIBLE);
                countDownTimer.cancel();
            }
        } else {
            view.postDelayed(() -> {
                toCompare.setImageResource(R.drawable.card);
                selectedItem.setImageResource(R.drawable.card);
                selectedItem = null;
            }, 500);
        }
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

        returnToMenu((AppCompatActivity) getActivity());
        Snackbar.make(view.findViewById(R.id.normal_complete), completeMessage, Snackbar.LENGTH_SHORT).show();
    }
}
