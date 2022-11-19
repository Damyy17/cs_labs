package LaboratoryWorkNr4.Implementation;

import LaboratoryWorkNr3.RSAImplementation.Keys.KeyGenerator;
import LaboratoryWorkNr3.RSAImplementation.Keys.KeySet;
import LaboratoryWorkNr3.RSAImplementation.RSA.RSA;
import LaboratoryWorkNr4.Implementation.Hashing.DataBase;
import LaboratoryWorkNr4.Implementation.Hashing.SHA384;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;

public class TestLab4 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        DataBase dataBase = new DataBase();
        SHA384 sha384 = new SHA384();
        String salt = sha384.getSalt();

        //password
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password: ");
        String password = scanner.nextLine();

        //hashing
        String securePassword = sha384.getSecurePassword(password, salt);
        System.out.println("Hashed password: " + securePassword);

        dataBase.addToDB(securePassword);


        //using rsa asymmetric cipher to encrypt
        KeySet myKey = new KeyGenerator().generateKey();
        System.out.println(myKey.toString());
        System.out.println();
        BigInteger cipher, integerDecryptedMessage;
        RSA rsa = new RSA();

        //encrypt the password
        cipher = rsa.encrypt(rsa.messageToInteger(securePassword), myKey.getPublicKey(), myKey.getModulus());
        System.out.println("Encrypted text:" + "\n" +
                "Plain text = " + password  + "\n" +
                "Cipher text = " + cipher +
                "\n");

        //decryption
        integerDecryptedMessage = rsa.decrypt(cipher, myKey.getPrivateKey(), myKey.getModulus());
        System.out.println("Decrypted text:" + "\n" +
                "Cipher text = " + cipher + "\n" +
                "Decrypted message, integer type = " +  integerDecryptedMessage + "\n" +
                "Decrypted message = " + rsa.integerToMessage(integerDecryptedMessage)
        );

        //hashing decrypted message
        String decrPassword = rsa.integerToMessage(integerDecryptedMessage);

        if (dataBase.checkDbIfContains(decrPassword)){
            System.out.println("Password is correct!");
        } else {
            System.out.println("Wrong password!");
        }
    }
}
