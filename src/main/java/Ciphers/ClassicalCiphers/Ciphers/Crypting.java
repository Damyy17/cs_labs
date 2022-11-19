package Ciphers.ClassicalCiphers.Ciphers;

public interface Crypting {

    String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    int LETTERS_OF_ALPHABET = 26;

    String encrypt(String em);
    String decrypt(String dm);
}
