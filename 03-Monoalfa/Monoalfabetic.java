
import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic{

    private static final char[] MINUSCULES = "aàábcçdeèéfghiìíïjklmnñoöòópqrstuùúüvwxyz".toCharArray(); 
    private static final char[] MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static final char[] MINUSCULESPERMUTADA = permutaAlfabet(MINUSCULES); 
    private static final char[] MAJUSCULESPERMUTADA = new String(MINUSCULESPERMUTADA).toUpperCase().toCharArray();

    public static char[] permutaAlfabet(char[] alfabet){
        char[] result = new char[alfabet.length];
        ArrayList<Character> nouAlfabet = new ArrayList<Character>(); 
        for( int i = 0; i < alfabet.length; i++){
            nouAlfabet.add(alfabet[i]);
        }
        
        Collections.shuffle(nouAlfabet);

        for (int i = 0; i < nouAlfabet.size(); i++) {
            result[i] = nouAlfabet.get(i);
        }

        return result;
    }


   private static int indexOf(char[] var0, char var1) {
        for(int var2 = 0; var2 < var0.length; ++var2) {
            if (var0[var2] == var1) {
                return var2;
            }
        }
        return -1;
    }

    public static String xifraMonoAlfa(String cadena){
        StringBuilder sb = new StringBuilder();
        for(char c : cadena.toCharArray()){
            if(Character.isLetter(c)){
                if(Character.isUpperCase(c)){
                    sb.append(MAJUSCULESPERMUTADA[indexOf(MAJUSCULES, c)]);
                }else{
                    sb.append(MINUSCULESPERMUTADA[indexOf(MINUSCULES, c)]);
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String desxifraMonoAlfa(String cadena){
        StringBuilder sb = new StringBuilder();
        for(char c : cadena.toCharArray()){
            if(Character.isLetter(c)){
                if(Character.isUpperCase(c)){
                    sb.append(MAJUSCULES[indexOf(MAJUSCULESPERMUTADA, c)]);
                }else{
                    sb.append(MINUSCULES[indexOf(MINUSCULESPERMUTADA, c)]);
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("#########################\n#  Prova de xifratge    #\n#########################");

        String[] textos = {"!¿Hola, com estàs?!", "12345abc67890", "    2a5    ", "aàábcçdeèéfghiìíïjklmnñoöòópqrstuùúüvwxyz" };

        for(String text : textos){
            System.out.println(text + " -> " + xifraMonoAlfa(text) + " -> " + desxifraMonoAlfa(xifraMonoAlfa(text)));
        }
    }



}
