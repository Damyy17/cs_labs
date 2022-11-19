package Ciphers.ClassicalCiphers.Ciphers;

import java.util.Locale;

public class CaesarSubstitutionCipher implements Crypting {

    private final int key;

    public CaesarSubstitutionCipher(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String inputMessage) {

        inputMessage = inputMessage.toLowerCase(Locale.ROOT);
        StringBuilder encryptedMessage = new StringBuilder();

        for (Character letter : inputMessage.toCharArray()) {
            int pos = ALPHABET.indexOf(letter);
            int encryptedPos = (key + pos)%LETTERS_OF_ALPHABET;
            char encryptedLetter = ALPHABET.charAt(encryptedPos);

            encryptedMessage.append(encryptedLetter);
        }

        return encryptedMessage.toString().toUpperCase();
    }

    @Override
    public String decrypt(String inputMessage) {

        inputMessage = inputMessage.toLowerCase(Locale.ROOT);
        StringBuilder decryptedMessage = new StringBuilder();

        for (Character letter : inputMessage.toCharArray()) {
            int pos = ALPHABET.indexOf(letter);
            int decryptedPos = (pos-key)%LETTERS_OF_ALPHABET;

            if (decryptedPos < 0){
                decryptedPos = ALPHABET.length() + decryptedPos;
            }

            char decryptedLetter = ALPHABET.charAt(decryptedPos);
            decryptedMessage.append(decryptedLetter);
        }

        return decryptedMessage.toString().toUpperCase(Locale.ROOT);
    }
}
