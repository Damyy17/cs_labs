package Ciphers.BlockCiphers.DESCipher;

interface Cipher {

    String encrypt(String em, String key);
    String decrypt(String dm, String key);
}
