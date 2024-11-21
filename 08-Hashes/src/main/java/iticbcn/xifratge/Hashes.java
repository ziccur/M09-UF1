package iticbcn.xifratge;

import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    private final String CHARSETFORCABRUTA = "abcdefABCDEF1234567890!";
    private final int LONGITUDMAXIMACONTRASENYA = 6;
    
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
            java.util.HexFormat hex = java.util.HexFormat.of();
            hash = hex.formatHex(abHash);
        } catch (java.security.NoSuchAlgorithmException | java.security.spec.InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }

    

    public String forcaBruta(String alg, String hash, String salt){
        for(char Caracter1 : CHARSETFORCABRUTA.toCharArray()){
            for(char Caracter2 : CHARSETFORCABRUTA.toCharArray()){
                for(char Caracter3 : CHARSETFORCABRUTA.toCharArray()){
                    for(char Caracter4 : CHARSETFORCABRUTA.toCharArray()){
                        for(char Caracter5 : CHARSETFORCABRUTA.toCharArray()){
                            for(char Caracter6 : CHARSETFORCABRUTA.toCharArray()){
                            }
                        }
                    }
                }
            }
        }
    }

    public String getInterval(long t1, long t2){
        return null;
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
