package com.supervielle.framework.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Random;

public class NumberUtils {

    public static String randomNumber(int length) {
        String randomNumber = "";
        for (int i = 0; i <= length; i++) {
            randomNumber = randomNumber + RandomUtils.nextBytes(length);
        }
        return randomNumber;
    }

    public static String pin(int size) {
        String pin = "";
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while (list.size() > 0) {
            int index = rand.nextInt(list.size());
            pin = pin + list.remove(index);
        }
        return pin;
    }

    public static String generateRandomNumericString(int length) {
        if (length <= 1) {
            throw new IllegalArgumentException("Length should be greater or equal to 1");
        }
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 1; i <= length; i++) {
            sb.append(rnd.nextInt(9));
        }
        return sb.toString();
    }

    public static double getMaxOfNumbers(double n1, double n2, double n3) {
        double max = Math.max(n1, n2);
        if (max > n2) {
            return Math.max(n1, n3);
        } else {
            return max = Math.max(n2, n3);
        }
    }

}
