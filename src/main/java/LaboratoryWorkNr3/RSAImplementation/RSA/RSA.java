package LaboratoryWorkNr3.RSAImplementation.RSA;

import java.math.BigInteger;

public class RSA implements Encryption{

    public BigInteger messageToInteger(String message){
        return new BigInteger(1, message.getBytes());
    }

    public String integerToMessage(BigInteger message){
        return new String(message.toByteArray());
    }

    public BigInteger encrypt(BigInteger plainTest, BigInteger publicKey, BigInteger modulus){
        return plainTest.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger cipherText, BigInteger privateKey, BigInteger modulus){
        return cipherText.modPow(privateKey, modulus);
    }

}
