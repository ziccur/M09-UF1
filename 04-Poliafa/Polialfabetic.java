
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    
    private static Random random;
    private static final int CLAU = 9213;
    private static final char[] alfabet = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static  char[] alfabetPermutat = new char[alfabet.length];

    public static void permutaAlfabet(){
        List<Character> alfabetList = new ArrayList<>();
        for (char c : alfabet) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList, random);
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i] = alfabetList.get(i);
        }

    }

    private static int indexOf(char[] var0, char var1) {
        for(int var2 = 0; var2 < var0.length; ++var2) {
            if (var0[var2] == var1) {
                return var2;
            }
        }
        return -1;
    }

    public static String xifraPoliAlfa(String cadena){
        random = new Random(CLAU);
        StringBuilder sb = new StringBuilder();
        for(char c : cadena.toCharArray()){
            permutaAlfabet();
            if(Character.isLetter(c) && Character.isUpperCase(c)){
                sb.append(alfabetPermutat[indexOf(alfabet, c)]);
            }
        }
        return sb.toString();
    }

    public static String desxifraPoliAlfa(String cadena){
        random = new Random(CLAU);
        StringBuilder sb = new StringBuilder();
        for(char c : cadena.toCharArray()){
            permutaAlfabet();
            if(Character.isLetter(c) && Character.isUpperCase(c)){
                sb.append(alfabet[indexOf(alfabetPermutat, c)]);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println("#########################\n#  Prova de xifratge    #\n#########################");

        String[] textos = {"!¿HOLA, COM ESTÀS?!", "12345ABC67890", "    2A5    ", "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ"};

        for (String text : textos) {
            random = new Random(CLAU);
            String cifrado = xifraPoliAlfa(text);
            random = new Random(CLAU);  // Reiniciar el Random con la misma semilla para descifrar correctamente
            String descifrado = desxifraPoliAlfa(cifrado);
            System.out.println(text + " -> " + cifrado + " -> " + descifrado);
        }
    }


}
