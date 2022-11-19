package Ciphers.ClassicalCiphers.Ciphers;

public class AffineCipher implements Crypting {

    private final int key1;
    private final int key2;

    public AffineCipher(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public String encrypt(String message) {

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' '){
                ciphertext.append((char) ((((key1 * (message.charAt(i) - 'A')) + key2) % 26) + 'A'));
            } else {
                ciphertext.append(message.charAt(i));
            }
        }

        return ciphertext.toString();
    }

    @Override
    public String decrypt(String message) {

        StringBuilder encryptedmessage = new StringBuilder();
        int a_inv = 0;
        int flag = 0;

        for (int i = 0; i < 26; i++) {
            flag = (key1 * i) % 26;
            if (flag == 1) {
                a_inv = i;
            }
        }

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' ') {
                encryptedmessage.append( (char) (((a_inv * ((message.charAt(i) + 'A' - key2)) % 26)) + 'A') );
            } else {
                encryptedmessage.append(message.charAt(i));
            }
        }

        return encryptedmessage.toString();
    }
}
