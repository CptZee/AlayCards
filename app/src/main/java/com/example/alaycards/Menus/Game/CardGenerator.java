package com.example.alaycards.Menus.Game;

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

        // Generate unique random numbers for the first half of the list
        for (int i = 0; i < size / 2; i++) {
            int number = random.nextInt(8);
            list.add(number);
        }

        // Shuffle the list
        Collections.shuffle(list);

        // Create a separate list to store the duplicates
        List<Integer> duplicates = new ArrayList<>();

        // Create duplicates for each number in the shuffled list
        for (Integer number : list) {
            duplicates.add(number);
        }

        // Add the duplicates to the original list
        list.addAll(duplicates);

        return list;
    }

    public static List<Integer> generateNormal() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        int size = 12;

        // Generate unique random numbers for the first half of the list
        for (int i = 0; i < size / 2; i++) {
            int number = random.nextInt(8);
            list.add(number);
        }

        // Shuffle the list
        Collections.shuffle(list);

        // Create a separate list to store the duplicates
        List<Integer> duplicates = new ArrayList<>();

        // Create duplicates for each number in the shuffled list
        for (Integer number : list) {
            duplicates.add(number);
        }

        // Add the duplicates to the original list
        list.addAll(duplicates);

        return list;
    }

    public static List<Integer> generateHard() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        int size = 16;

        // Generate unique random numbers for the first half of the list
        for (int i = 0; i < size / 2; i++) {
            int number = random.nextInt(8);
            list.add(number);
        }

        // Shuffle the list
        Collections.shuffle(list);

        // Create a separate list to store the duplicates
        List<Integer> duplicates = new ArrayList<>();

        // Create duplicates for each number in the shuffled list
        for (Integer number : list) {
            duplicates.add(number);
        }

        // Add the duplicates to the original list
        list.addAll(duplicates);

        return list;
    }

    public static List<Integer> convertToCards(List<Integer> toConvert){
        List<Integer> list = new ArrayList<>();
        for(int i : toConvert){
            switch (i) {
                case 0:
                    list.add(R.drawable.apolaki);
                    break;
                case 1:
                    list.add(R.drawable.bakunawa);
                    break;
                case 2:
                    list.add(R.drawable.bathala);
                    break;
                case 3:
                    list.add(R.drawable.kapre);
                    break;
                case 4:
                    list.add(R.drawable.mayari);
                    break;
                case 5:
                    list.add(R.drawable.nuno);
                    break;
                case 6:
                    list.add(R.drawable.sarimanok);
                    break;
                case 7:
                    list.add(R.drawable.tikbalang);
                    break;
            }
        }
        return list;
    }
}
