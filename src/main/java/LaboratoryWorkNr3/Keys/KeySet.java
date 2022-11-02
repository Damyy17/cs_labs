package LaboratoryWorkNr3.Keys;

import java.math.BigInteger;

public class KeySet {

    private final BigInteger modulus;
    private final BigInteger publicKey;
    private final BigInteger privateKey;

    public KeySet(BigInteger modulus, BigInteger publicKeyExponent, BigInteger privateKeyExponent){
        this.modulus = modulus;
        this.publicKey = publicKeyExponent;
        this.privateKey = privateKeyExponent;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    @Override
    public String toString() {
        return "KeySet = {" + ",\n" +
                "modulus=" + modulus + ",\n" +
                " publicKey=" + publicKey + ",\n" +
                " privateKey=" + privateKey +"\n" +
                '}';
    }
}
