import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    // Definiciones a nivel de clase
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis"; // Puedes cambiarla si deseas

    // Método para generar la clave a partir de la contraseña
    private static SecretKey generateKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        return new SecretKeySpec(Arrays.copyOf(key, 32), ALGORISME_XIFRAT); // Clave de 256 bits
    }

    // Método para cifrar con AES
    public static byte[] xifraAES(String msg, String password) throws Exception {
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKey secretKey = generateKey(password);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    // Método para descifrar con AES
    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKey secretKey = generateKey(password);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        byte[] original = cipher.doFinal(bMsgXifrat);
        return new String(original, "UTF-8");
    }

    // Método main
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
