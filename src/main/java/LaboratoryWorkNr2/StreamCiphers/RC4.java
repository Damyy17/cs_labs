package LaboratoryWorkNr2.StreamCiphers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RC4 {
    private static final int SBOX_LENGTH = 256;
    private static final int KEY_MINIMAL_LENGTH = 5;

    private byte[] key = new byte[SBOX_LENGTH - 1];
    private int[] sbox = new int[SBOX_LENGTH];

    private void reset(){
        Arrays.fill(key, (byte) 0);
        Arrays.fill(sbox, 0);   
    }

    public byte[] encrypt(String message, Charset charset, String key){
        reset();
        setKey(key);
        byte[] crypt = crypt(message.getBytes());
        reset();
        return crypt;
    }

    public byte[] encrypt(String message, String key) throws InvalidKeyException{
        return encrypt(message, StandardCharsets.UTF_8, key);
    }

    public String decrypt(byte[] message, Charset charset, String key) throws InvalidKeyException{
        reset();
        setKey(key);
        byte[] msg = crypt(message);
        reset();
        return new String(msg);
    }


    public byte[] crypt(final byte[] message){
        sbox = initSbox(key);
        byte[] code = new byte[message.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < message.length; n++) {
            i = (i + 1) % SBOX_LENGTH;
            j = (j + sbox[i]) % SBOX_LENGTH;
            swap(i, j, sbox);
            int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH];
            code[n] = (byte) (rand ^ message[n]); 
        }
        return code;
    }

    private int[] initSbox(byte[] key){
        int[] sbox = new int[SBOX_LENGTH];
        int j = 0;

        for (int i = 0; i < SBOX_LENGTH; i++) {
            sbox[i] = i;
        }

        for (int i = 0; i < SBOX_LENGTH; i++) {
            j = (j + sbox[i] +(key[i % key.length]) & 0xFF) % SBOX_LENGTH;
            swap(i, j, sbox);
        }
        return sbox;
    }

    private void swap(int i, int j, int[] sbox){
        int temp = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = temp;
    }

    public void setKey(String key){
        if (!(key.length() >= KEY_MINIMAL_LENGTH && key.length() < SBOX_LENGTH)) {
                       throw new InvalidKeyException("Invalid key, key has to be between " +
                       KEY_MINIMAL_LENGTH+" and "+(SBOX_LENGTH-1));
        }
        this.key = key.getBytes();
    }
}
