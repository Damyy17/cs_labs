package LaboratoryWorkNr1.ciphers;

import LaboratoryWorkNr1.Crypting;

import java.util.Locale;

public class VigenereCipher implements Crypting {

    private final String key;

    public VigenereCipher(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {

        StringBuilder encryptedMessage = new StringBuilder();
        message = message.toUpperCase(Locale.ROOT);

        for (int i = 0, j =0; i < message.length(); i++) {

            char letter = message.charAt(i);
            if (letter < 'A' || letter > 'Z')
                continue;
            encryptedMessage.append((char) ((letter + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }

        return encryptedMessage.toString();
    }

    @Override
    public String decrypt(String message) {

        StringBuilder decryptedMessage = new StringBuilder();
        message = message.toUpperCase(Locale.ROOT);

        for (int i = 0, j =0; i < message.length(); i++) {

            char letter = message.charAt(i);
            if (letter < 'A' || letter > 'Z')
                continue;
            decryptedMessage.append((char) ((letter - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }

        return decryptedMessage.toString();
    }
}
