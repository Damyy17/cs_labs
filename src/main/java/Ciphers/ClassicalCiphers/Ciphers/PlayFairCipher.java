package Ciphers.ClassicalCiphers.Ciphers;

import java.util.LinkedHashSet;
import java.util.Set;

public class PlayFairCipher {

    private static final String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public String createMatrix(String key) {
        
        String matrixstr = key + alphabet;
        StringBuilder matrix = new StringBuilder();
        Set<Character> set = new LinkedHashSet<>();

        for (char letter : matrixstr.toLowerCase().toCharArray()) {
            set.add(letter);
        }

        for (Object letter : set){
            matrix.append(letter);
        }

        return matrix.toString();
    }

    public String[] divideInPairs(String message){

        message = formatMessage(message);
        String[] pairs = new String[message.length() / 2];
        int j = 0;

        for (int i = 0; i < message.length(); i = i + 2) {
            pairs[j] = message.substring(i, i + 2);
            j++;
        }

        return pairs;
    }

    public String formatMessage(String message){

        message = message.toLowerCase().replace(" ", "");
        StringBuilder mes = new StringBuilder(message);

        for (int i = 0; i < message.length() - 1; i += 2) {
            if (mes.charAt(i) == mes.charAt(i + 1)) {
                mes.insert(i + 1, "x");
            }
        }
        if (mes.length() % 2 == 1) {
            mes.append("x");
        }

        return mes.toString();
    }

    public String encrypt(String[] pairs, String matrix){

        StringBuilder cipherText = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char letter1;
            char letter2;

            if (col1 == col2) {
                letter2 = matrix.charAt(((row2 + 1) % 5 * 5 + col2));
                letter1 = matrix.charAt(((row1 + 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                letter1 = matrix.charAt(row1 * 5 + ((col1 + 1) % 5));
                letter2 = matrix.charAt(row2 * 5 + ((col2 + 1) % 5));
            } else {
                letter1 = matrix.charAt(row1 * 5 + col2);
                letter2 = matrix.charAt(row2 * 5 + col1);
            }
            cipherText.append(Character.toString(letter1)).append(Character.toString(letter2));
        }
        return cipherText.toString();
    }

    public String decrypt(String[] pairs, String matrix){

        StringBuilder cipherText = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char letter1;
            char letter2;

            if (col1 == col2) {
                letter2 = matrix.charAt(((row2 - 1) % 5 * 5 + col2));
                letter1 = matrix.charAt(((row1 - 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                letter1 = matrix.charAt(row1 * 5 + ((col1 - 1) % 5));
                letter2 = matrix.charAt(row2 * 5 + ((col2 - 1) % 5));
            } else {
                letter1 = matrix.charAt(row1 * 5 + col2);
                letter2 = matrix.charAt(row2 * 5 + col1);
            }
            cipherText.append(Character.toString(letter1)).append(Character.toString(letter2));
        }
        return cipherText.toString();
    }
}
