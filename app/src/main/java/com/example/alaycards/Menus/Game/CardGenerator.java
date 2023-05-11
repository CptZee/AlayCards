package com.example.alaycards.Menus.Game;

import android.util.Log;

import com.example.alaycards.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardGenerator {
    public static List<Integer> generateEasy() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        int size = 6;

        while(list.size() < (size/2)){
            int number = random.nextInt(8);
            if(!list.contains(number))
                list.add(number);
        }

        // Create a separate list to store the duplicates
        List<Integer> duplicates = new ArrayList<>();

        // Create duplicates for each number in the shuffled list
        for (Integer number : list) {
            duplicates.add(number);
        }

        // Add the duplicates to the original list
        list.addAll(duplicates);

        //Shuffle the list to finalize
        Collections.shuffle(list);

        return list;
    }

    public static List<Integer> generateNormal() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        int size = 12;

        while(list.size() < (size/2)){
            int number = random.nextInt(8);
            if(!list.contains(number))
                list.add(number);
        }

        // Create a separate list to store the duplicates
        List<Integer> duplicates = new ArrayList<>();

        // Create duplicates for each number in the shuffled list
        for (Integer number : list) {
            duplicates.add(number);
        }

        // Add the duplicates to the original list
        list.addAll(duplicates);

        //Shuffle the list to finalize
        Collections.shuffle(list);

        return list;
    }

    public static List<Integer> generateHard() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        int size = 16;

        while(list.size() < (size/2)){
            int number = random.nextInt(8);
            if(!list.contains(number))
                list.add(number);
        }

        // Create a separate list to store the duplicates
        List<Integer> duplicates = new ArrayList<>();

        // Create duplicates for each number in the shuffled list
        for (Integer number : list) {
            duplicates.add(number);
        }

        // Add the duplicates to the original list
        list.addAll(duplicates);

        //Shuffle the list to finalize
        Collections.shuffle(list);

        return list;
    }

    public static List<Integer> convertToCards(List<Integer> toConvert){
        List<Integer> list = new ArrayList<>();
        for(int i : toConvert){
            switch (i) {
                case 0:
                    list.add(R.drawable.apolaki);
                    Log.i("CardHelper", "Apolaki was selected");
                    break;
                case 1:
                    list.add(R.drawable.bakunawa);
                    Log.i("CardHelper", "Bakunawa was selected");
                    break;
                case 2:
                    list.add(R.drawable.bathala);
                    Log.i("CardHelper", "Bathala was selected");
                    break;
                case 3:
                    list.add(R.drawable.kapre);
                    Log.i("CardHelper", "Kapre was selected");
                    break;
                case 4:
                    list.add(R.drawable.mayari);
                    Log.i("CardHelper", "Mayari was selected");
                    break;
                case 5:
                    list.add(R.drawable.nuno);
                    Log.i("CardHelper", "Nuno was selected");
                    break;
                case 6:
                    list.add(R.drawable.sarimanok);
                    Log.i("CardHelper", "Sarimanok was selected");
                    break;
                case 7:
                    list.add(R.drawable.tikbalang);
                    Log.i("CardHelper", "Tikbalang was selected");
                    break;
            }
        }
        return list;
    }
}
