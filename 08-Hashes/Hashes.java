
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    private final String CHARSETFORCABRUTA = "abcdefABCDEF1234567890!";
    
    public String getSHA512AmbSalt(String pw, String salt){
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(pw.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        String hash = null;
        try {
            byte[] abSalt = salt.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), abSalt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] abHash = factory.generateSecret(spec).getEncoded();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < abHash.length; i++) {
                sb.append(Integer.toString((abHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String cifrate (String algorisme, String pw, String salt){
        String hash = null;
        switch (algorisme){
            case "SHA-512":
                hash = getSHA512AmbSalt(pw, salt);
                break;
            case "PBKDF2":
                hash = getPBKDF2AmbSalt(pw, salt);
                break;
            default:
                System.out.printf("Algorisme no reconegut\n");
        }
        npass++;
        return hash;
    }


    public String forcaBruta(String alg, String hash, String salt){
        npass = 0;
        for(char Caracter1 : CHARSETFORCABRUTA.toCharArray()){
            if(cifrate(alg, String.valueOf(Caracter1), salt).equals(hash)){return String.valueOf(Caracter1);}
            for(char Caracter2 : CHARSETFORCABRUTA.toCharArray()){
                if(cifrate(alg, "" + Caracter1 + Caracter2, salt).equals(hash)){return "" + Caracter1 + Caracter2;}
                for(char Caracter3 : CHARSETFORCABRUTA.toCharArray()){
                    if(cifrate(alg, "" + Caracter1 + Caracter2 + Caracter3, salt).equals(hash)){return "" + Caracter1 + Caracter2 + Caracter3;}
                    for(char Caracter4 : CHARSETFORCABRUTA.toCharArray()){
                        if(cifrate(alg, "" + Caracter1 + Caracter2 + Caracter3 + Caracter4, salt).equals(hash)){return "" + Caracter1 + Caracter2 + Caracter3 + Caracter4;}
                        for(char Caracter5 : CHARSETFORCABRUTA.toCharArray()){
                            if(cifrate(alg, "" + Caracter1 + Caracter2 + Caracter3 + Caracter4 + Caracter5, salt).equals(hash)){return "" + Caracter1 + Caracter2 + Caracter3 + Caracter4 + Caracter5;}
                            for(char Caracter6 : CHARSETFORCABRUTA.toCharArray()){
                                if(cifrate(alg, "" + Caracter1 + Caracter2 + Caracter3 + Caracter4 + Caracter5 + Caracter6, salt).equals(hash)){return "" + Caracter1 + Caracter2 + Caracter3 + Caracter4 + Caracter5 + Caracter6;}
                            }
                        }
                    }
                }
            }
        }
        return null;    
    }

    public String getInterval(long t1, long t2){
        long interval = t2 - t1;
        long dies = interval / (1000 * 60 * 60 * 24);
        long hores = (interval / (1000 * 60 * 60)) % 24;
        long minuts = (interval / (1000 * 60)) % 60;
        long segons = (interval / 1000) % 60;
        long millis = interval % 1000;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args){
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
