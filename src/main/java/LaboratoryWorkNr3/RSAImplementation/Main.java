package LaboratoryWorkNr3.RSAImplementation;

import LaboratoryWorkNr3.RSAImplementation.Keys.KeyGenerator;
import LaboratoryWorkNr3.RSAImplementation.Keys.KeySet;
import LaboratoryWorkNr3.RSAImplementation.RSA.RSA;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        // First let's generate the keys
        KeySet myKey = new KeyGenerator().generateKey();
        System.out.println(myKey.toString());
        System.out.println();

        //Encryption
        String plaintext = "i hope it works";
        BigInteger cipher, integerDecryptedMessage;
        RSA rsa = new RSA();
        cipher = rsa.encrypt(rsa.messageToInteger(plaintext), myKey.getPublicKey(), myKey.getModulus());
        System.out.println("Encrypted text:" + "\n" +
                "Plain text = " + plaintext  + "\n" +
                "Cipher text = " + cipher +
                "\n");

        //Decryption
        integerDecryptedMessage = rsa.decrypt(cipher, myKey.getPrivateKey(), myKey.getModulus());
        System.out.println("Decrypted text:" + "\n" +
                "Cipher text = " + cipher + "\n" +
                "Decrypted message, integer type = " +  integerDecryptedMessage + "\n" +
                "Decrypted message = " + rsa.integerToMessage(integerDecryptedMessage)
                );

    }
}
