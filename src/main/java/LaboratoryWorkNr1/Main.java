package LaboratoryWorkNr1;

import LaboratoryWorkNr1.ciphers.AffineCipher;
import LaboratoryWorkNr1.ciphers.CaesarSubstitutionCipher;
import LaboratoryWorkNr1.ciphers.PlayFairCipher;
import LaboratoryWorkNr1.ciphers.VigenereCipher;

public class Main {
    public static void main(String[] args) {

        //########################################################
        System.out.println("Substitution Caesar Cipher :");
        String plaintext1 = "MAGICAL";
        int key1 = 4;
        System.out.println("Plaintext = '" + plaintext1 + "' and the key = " + key1);
        CaesarSubstitutionCipher cipher1 = new CaesarSubstitutionCipher(key1);
        System.out.println("Encrypted message -> " + cipher1.encrypt(plaintext1));
        System.out.println("Decrypted message -> " + cipher1.decrypt(cipher1.encrypt(plaintext1)));
        //########################################################
        System.out.println("---------------------------------------------");
        //########################################################
        System.out.println("Vigenere Cipher :");
        String plaintext2 = "BANANA";
        String key2 = "HELLO";
        System.out.println("Plaintext = '" + plaintext2 + "' and the key = " + key2);
        VigenereCipher cipher2 = new VigenereCipher(key2);
        System.out.println("Encrypted message -> " + cipher2.encrypt(plaintext2));
        System.out.println("Decrypted message -> " + cipher2.decrypt(cipher2.encrypt(plaintext2)));
        //########################################################
        System.out.println("---------------------------------------------");
        System.out.println("PlayFair Cipher :");
        String textToBeEncrypted = "hi world";
        String key = "java";
        System.out.println("Plaintext = '" + textToBeEncrypted + "' and the key = " + key);
        PlayFairCipher playFairCipher = new PlayFairCipher();
        String matrix = playFairCipher.createMatrix(key.replace(" ", ""));
        String[] pairs = playFairCipher.divideInPairs(textToBeEncrypted.replace("j", "i"));
        String encryptedMessage = playFairCipher.encrypt(pairs, matrix);
        System.out.println("Encrypted message -> " + encryptedMessage);
        System.out.println("Decrypted message -> " + playFairCipher.decrypt(playFairCipher.divideInPairs(encryptedMessage), matrix));
        //########################################################
        System.out.println("---------------------------------------------");
        System.out.println("Affine Cipher :");
        int key_one = 17;
        int key_two = 20;
        AffineCipher affineCipher = new AffineCipher(key_one, key_two);
        String text = "HI WORLD";
        System.out.println("Plaintext = '" + text + "' and the keys = " + key_one + ", " + key_two);
        System.out.println("Encrypted message -> " + affineCipher.encrypt(text));
        System.out.println("Decrypted message -> " + affineCipher.decrypt(affineCipher.encrypt(text)));
    }
}
