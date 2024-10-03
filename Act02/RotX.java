

/**
 * El xifrat ROT13 consisteix a substituir cada lletra per la que està 13 posicions més a la dreta
    alfabèticament, i si s’arriba al final de l’alfabet es torna a començar per el principi.
*/

public class RotX {

    // Sugerencia: Les constants han d'anar amb majuscula per millor lectura
    private static final char[] MINUSCULES = "aàábcçdeèéfghiìíïjklmnñoöòópqrstuùúüvwxyz".toCharArray(); 
    private static final char[] MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    private static int indexOf(char[] cadena, char caracter){
        for(int i = 0; i < cadena.length; i++){
            if (cadena[i] == caracter){
                return i;
            }
        }
        return -1;
    }

    public static String xifraRotX(String text, int desplaçament){ return xifraRotX(text.toCharArray(), desplaçament); }
    public static String xifraRotX(char[] cadena, int desplaçament){
        return  translate(cadena, true, desplaçament);
    }

    public static String desxifraRotX( String text, int desplaçament ){ return desxifraRotX( text.toCharArray(), desplaçament); }
    public static String desxifraRotX( char[] cadena, int desplaçament ){
        return translate(cadena, false, desplaçament);
    }
    public static String translate( String text, boolean encrypt, int desplaçament){ return translate(text.toCharArray(), encrypt, desplaçament);}
    public static String translate(char[] cadena, boolean encrypt, int desplaçament) {
        if(desplaçament > MINUSCULES.length){
            System.out.println("No es pot indicar un despaçament tant gran");
            return null;
        }
        StringBuilder result = new StringBuilder(); // recomenació: al ser un string que es modifica tota l'estona utilitza StringBuilder
        for (int i = 0; i < cadena.length; i++) {
            if (Character.isLetter(cadena[i])) { // sugerencia: buscartots els mòduls aprovats i mitjana notes >= 8 directament en les arrays de lletres per fer el codi més optim ja que retornaria la posició
                
                if (Character.isUpperCase(cadena[i])) {
                    
                    int index = indexOf(MAJUSCULES, cadena[i]);

                    if (encrypt) {
                        result.append(MAJUSCULES[(index + desplaçament) % MAJUSCULES.length]);
                    } else {
                        result.append(MAJUSCULES[(index - desplaçament + MAJUSCULES.length) % MAJUSCULES.length]);
                    }
                } else {

                    int index = indexOf(MINUSCULES, cadena[i]);

                    if (encrypt) {
                        result.append(MINUSCULES[(index + desplaçament) % MINUSCULES.length]);
                    } else {
                        result.append(MINUSCULES[(index - desplaçament + MINUSCULES.length) % MINUSCULES.length]);
                    }
                }
            } else {
                result.append(cadena[i]);
            }
        }
        return result.toString();
    }

    public static void forcaBrutaRotX(String text){ forcaBrutaRotX(text.toCharArray());}
    public static void forcaBrutaRotX(char[] cadena){
        for (int i = 1; i < MINUSCULES.length; i++){
            System.out.println(i+": " + desxifraRotX(cadena, i));
        }
    }

    public static void main(String[] args){
        final String[] FRASESDEPROVA = {"Hola com estas", "aàábcçdeèéfghiìíïjklmnñoöòópqrstuùúüvwxyz", "   1   ", "Seras ra$rit0", "¿¿Esto se descifra??!"};

        for (int i = 0; i < FRASESDEPROVA.length; i++ ){
            int desplaçament = 12;
            String fraseDeprova = FRASESDEPROVA[i];
            System.out.println("Frase original: "+ fraseDeprova + " | Frase codificada: "+ xifraRotX(fraseDeprova, desplaçament) + " | Frase descompilada: " + desxifraRotX(xifraRotX(fraseDeprova, desplaçament), desplaçament));
        }

        String frase = "La última frase de prova existent";
        System.out.println("\nLa frase original és:" + frase);

        forcaBrutaRotX(xifraRotX(frase, 34));
    }
}