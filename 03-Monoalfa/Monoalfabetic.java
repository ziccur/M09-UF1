
import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic{

    private static final char[] MINUSCULES = "aàábcçdeèéfghiìíïjklmnñoöòópqrstuùúüvwxyz".toCharArray(); 
    private static final char[] MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    private static final char[] MINUSCULESPERMUTAT = permutaAlfabet(MINUSCULES);
    private static final char[] MAJUSCULESPERMUTAT = permutaAlfabet(MAJUSCULES);

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




}
