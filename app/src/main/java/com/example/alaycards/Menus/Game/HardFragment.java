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

public class HardFragment extends EasyFragment {
    public HardFragment() {
        super(R.layout.fragment_hard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView one = view.findViewById(R.id.hard_1);
        ImageView two = view.findViewById(R.id.hard_2);
        ImageView three = view.findViewById(R.id.hard_3);
        ImageView four = view.findViewById(R.id.hard_4);
        ImageView five = view.findViewById(R.id.hard_5);
        ImageView six = view.findViewById(R.id.hard_6);
        ImageView seven = view.findViewById(R.id.hard_7);
        ImageView eight = view.findViewById(R.id.hard_8);
        ImageView nine = view.findViewById(R.id.hard_9);
        ImageView ten = view.findViewById(R.id.hard_10);
        ImageView eleven = view.findViewById(R.id.hard_11);
        ImageView twelve = view.findViewById(R.id.hard_12);
        ImageView thirteen = view.findViewById(R.id.hard_13);
        ImageView fourteen = view.findViewById(R.id.hard_14);
        ImageView fifteen = view.findViewById(R.id.hard_15);
        ImageView sixteen = view.findViewById(R.id.hard_16);

        view.findViewById(R.id.hard_complete).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.hard_complete).setOnClickListener(v -> finish(view));

        view.findViewById(R.id.hard_reset).setOnClickListener(v ->
                generateItems()
        );

        view.findViewById(R.id.hard_return).setOnClickListener(v ->
                EasyFragment.returnToMenu((AppCompatActivity) getActivity())
        );
        //Countdown code
        timer = view.findViewById(R.id.hard_timer);
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
        imageViews.add(thirteen);
        imageViews.add(fourteen);
        imageViews.add(fifteen);
        imageViews.add(sixteen);

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

        thirteen.setOnClickListener(v -> {
            thirteen.setImageResource(cards.get(12));
            if (selectedItem == null) {
                selectedItem = thirteen;
                selectedItemDrawableID = cards.get(12);
                return;
            }
            compare(thirteen, view);
        });

        fourteen.setOnClickListener(v -> {
            fourteen.setImageResource(cards.get(13));
            if (selectedItem == null) {
                selectedItem = fourteen;
                selectedItemDrawableID = cards.get(13);
                return;
            }
            compare(fourteen, view);
        });

        fifteen.setOnClickListener(v -> {
            fifteen.setImageResource(cards.get(14));
            if (selectedItem == null) {
                selectedItem = fifteen;
                selectedItemDrawableID = cards.get(14);
                return;
            }
            compare(fifteen, view);
        });

        sixteen.setOnClickListener(v -> {
            sixteen.setImageResource(cards.get(15));
            if (selectedItem == null) {
                selectedItem = sixteen;
                selectedItemDrawableID = cards.get(15);
                return;
            }
            compare(sixteen, view);
        });

        generateItems();
    }

    @Override
    protected void generateItems() {
        cards = CardGenerator.convertToCards(CardGenerator.generateHard());
        for (int i = 0; i < 16; i++) {
            imageViews.get(i).setImageResource(R.drawable.card);
        }
    }

    @Override
    protected void startCountdown() {
        countDownTimer = new CountDownTimer(1 * 60 * 1000, 1000) {
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
                view.findViewById(R.id.hard_complete).setVisibility(View.VISIBLE);
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
        score.setDifficulty(Difficulty.HARD);

        ScoreHelper.get(getContext()).insert(score);

        returnToMenu((AppCompatActivity) getActivity());
        Snackbar.make(view.findViewById(R.id.hard_complete), completeMessage, Snackbar.LENGTH_SHORT).show();
    }
}
