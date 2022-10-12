package LaboratoryWorkNr2.StreamCiphers;

import java.nio.charset.Charset;

interface RC4CRYPT {
    int SBOX_LENGTH = 256;
    int KEY_MINIMAL_LENGTH = 5;

    byte[] encrypt(String message, String key);
    String decrypt(byte[] message, Charset charset, String key);

}
