package LaboratoryWorkNr1;

import LaboratoryWorkNr1.ciphers.CaesarSubstitutionCipher;

public class Main {
    public static void main(String[] args) {
        System.out.println("Substitution Caesar Cipher :");
        CaesarSubstitutionCipher cipher1 = new CaesarSubstitutionCipher(4);
        System.out.println("Encrypted message -> " + cipher1.encrypt("ATTACKATONCE"));

        System.out.println("Decrypted message -> " + cipher1.decrypt(cipher1.encrypt("ATTACKATONCE")));
    }
}
