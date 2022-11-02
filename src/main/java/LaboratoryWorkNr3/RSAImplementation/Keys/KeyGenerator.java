package LaboratoryWorkNr3.RSAImplementation.Keys;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class KeyGenerator {

    private static final List<BigInteger> bigIntegerPrimes = new ArrayList<>(6550);

    static {
        for (int i : Primes.primeNumbers)
            bigIntegerPrimes.add(BigInteger.valueOf(i));
    }

    public KeySet generateKey(){
        SecureRandom secureRandom = new SecureRandom();

        BigInteger p = BigInteger.valueOf(2);
        while(isDivisibleByPrime(p.add(BigInteger.ONE)) && isDivisibleByPrime(p.subtract(BigInteger.ONE)))
            p = BigInteger.probablePrime(2048, secureRandom);

        BigInteger q = BigInteger.valueOf(2);
        while (isDivisibleByPrime(q.add(BigInteger.ONE)) && isDivisibleByPrime(q.subtract(BigInteger.ONE)))
            q = BigInteger.probablePrime(2050, secureRandom);

        BigInteger n = p.multiply(q);
        BigInteger tmp = p.subtract(BigInteger.ONE).multiply(
                q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(tmp);

        return new KeySet(n, e, d);
    }

    public boolean isDivisibleByPrime(BigInteger n){
        BigInteger reduced = n;
        boolean foundDivisor = true;

        while (foundDivisor && !reduced.equals(BigInteger.ZERO)){
            foundDivisor = false;

            for (BigInteger p : bigIntegerPrimes){
                if (reduced.mod(p).equals(BigInteger.ZERO)){
                    reduced = reduced.divide(p);
                    foundDivisor = true;
                    break;
                }
            }
        }

        return reduced.compareTo(BigInteger.valueOf(65537)) <= 0;
    }
}
