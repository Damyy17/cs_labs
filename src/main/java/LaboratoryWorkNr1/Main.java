package LaboratoryWorkNr1;

import LaboratoryWorkNr1.ciphers.AffineCipher;
import LaboratoryWorkNr1.ciphers.CaesarSubstitutionCipher;
import LaboratoryWorkNr1.ciphers.PlayFairCipher;
import LaboratoryWorkNr1.ciphers.VigenereCipher;

public class Main {
    public static void main(String[] args) {

        //########################################################
        System.out.println("Substitution Caesar Cipher :");
        CaesarSubstitutionCipher cipher1 = new CaesarSubstitutionCipher(4);
        System.out.println("Encrypted message -> " + cipher1.encrypt("MAGICAL"));
        System.out.println("Decrypted message -> " + cipher1.decrypt(cipher1.encrypt("MAGICAL")));
        //########################################################
        System.out.println("---------------------------------------------");
        //########################################################
        System.out.println("Vigenere Cipher :");
        VigenereCipher cipher2 = new VigenereCipher("HELLO");
        System.out.println("Encrypted message -> " + cipher2.encrypt("BANANA"));
        System.out.println("Decrypted message -> " + cipher2.decrypt(cipher2.encrypt("BANANA")));
        //########################################################
        System.out.println("---------------------------------------------");
        System.out.println("PlayFair Cipher :");
        String textToBeEncrypted = "hi world";
        String key = "java";
        PlayFairCipher playFairCipher = new PlayFairCipher();
        String matrix = playFairCipher.createMatrix(key.replace(" ", ""));
        String[] pairs = playFairCipher.divideInPairs(textToBeEncrypted.replace("j", "i"));
        String encryptedMessage = playFairCipher.encrypt(pairs, matrix);
        System.out.println("Encrypted message -> " + encryptedMessage);
        System.out.println("Decrypted message -> " + playFairCipher.decrypt(playFairCipher.divideInPairs(encryptedMessage), matrix));
        //########################################################
        System.out.println("---------------------------------------------");
        System.out.println("Affine Cipher :");
        AffineCipher affineCipher = new AffineCipher(17, 20);
        String text = "HI WORLD";
        System.out.println("Encrypted message -> " + affineCipher.encrypt(text));
        System.out.println("Decrypted message -> " + affineCipher.decrypt(affineCipher.encrypt(text)));
    }
}
