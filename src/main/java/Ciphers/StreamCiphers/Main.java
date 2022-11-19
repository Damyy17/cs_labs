package Ciphers.StreamCiphers;

import Ciphers.BlockCiphers.DESCipher.DES;
import Ciphers.StreamCiphers.RC4Cipher.RC4;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //RC4 Stream Cipher 
        System.out.println("RC4 Stream Cipher: ");
        RC4 rc4 = new RC4();
        String message = "hellloworrldd";
        String key = "amazing";
        System.out.println("Encrypted message:" + Arrays.toString(rc4.encrypt(message, key)));
        System.out.println("Decrypted message: " + rc4.decrypt(rc4.encrypt(message, key), StandardCharsets.UTF_8, key));
        
        System.out.println();

        System.out.println("-------------------------------------------------------------");

        System.out.println();
        DES des = new DES();
        System.out.println("DES Block Cipher: ");

        String message2 = "17072001DAAC3012";
        String key2 = "ABCD0123456789AB";
        System.out.println("Encrypted message:" + des.encrypt(message2, key2));
        System.out.println("Decrypted message:" + des.decrypt(des.encrypt(message2, key2), key2));
    }

}