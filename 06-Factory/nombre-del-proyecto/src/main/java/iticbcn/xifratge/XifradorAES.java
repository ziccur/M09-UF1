package iticbcn.xifratge;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import iticbcn.xifratge.interfaces.Xifrador;

public class XifradorAES implements Xifrador {

    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private final int MIDA_IV = 16;
    private final byte[] iv = new byte[MIDA_IV];

    private final String CLAU = "LaClauSecretaQueVulguis";

    private SecretKey generateKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        return new SecretKeySpec(Arrays.copyOf(key, 32), ALGORISME_XIFRAT);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) {
        try {
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            SecretKey secretKey = generateKey(clau);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return new TextXifrat(cipher.doFinal(msg.getBytes("UTF-8")));
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) {
        try {
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            SecretKey secretKey = generateKey(clau);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            byte[] original = cipher.doFinal(xifrat.getBytes());
            return new String(original, "UTF-8");
        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            return null;
        }
        
    }
}
