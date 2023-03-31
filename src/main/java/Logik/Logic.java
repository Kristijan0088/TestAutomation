package Logik;

import java.util.Random;

public class Logic {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int MIN_LENGTH = 100;

    public static String generate() {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = MIN_LENGTH + random.nextInt(50);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            char c = ALPHABET.charAt(index);
            sb.append(c);

        }

        return sb.toString();

    }

}
