package Ciphers.AsymmetricCiphers.RSACipher;

import java.math.BigInteger;

public interface Encryption {

    BigInteger messageToInteger(String message);

    String integerToMessage(BigInteger message);

    BigInteger encrypt(BigInteger plainTest, BigInteger publicKey, BigInteger modulus);

    BigInteger decrypt(BigInteger cipher, BigInteger privateKey, BigInteger modulus);
}
