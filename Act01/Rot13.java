

/**
 * El xifrat ROT13 consisteix a substituir cada lletra per la que està 13 posicions més a la dreta
    alfabèticament, i si s’arriba al final de l’alfabet es torna a començar per el principi.
*/

public class Rot13 {

    // Sugerencia: Les constants han d'anar amb majuscula per millor lectura
    private static final char[] MINUSCULES = {'a', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] MAJUSCULES = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    private static int indexOf(char[] cadena, char caracter){
        for(int i = 0; i < cadena.length; i++){
            if (cadena[i] == caracter){
                return i;
            }
        }
        return -1;
    }

    public static String xifraRot13(String text){ return xifraRot13(text.toCharArray()); }
    public static String xifraRot13(char[] cadena){
        return  translate(cadena, true);
    }

    public static String desxifraRot13( String text ){ return desxifraRot13( text.toCharArray()); }
    public static String desxifraRot13( char[] cadena ){
        return translate(cadena, false);
    }
    public static String translate( String text, boolean encrypt){ return translate(text.toCharArray(), encrypt);}
    public static String translate(char[] cadena, boolean encrypt) {
        StringBuilder result = new StringBuilder(); // recomenació: al ser un string que es modifica tota l'estona utilitza StringBuilder
        for (int i = 0; i < cadena.length; i++) {
            if (Character.isLetter(cadena[i])) { // sugerencia: buscartots els mòduls aprovats i mitjana notes >= 8 directament en les arrays de lletres per fer el codi més optim ja que retornaria la posició
                if (Character.isUpperCase(cadena[i])) {
                    if (encrypt) {
                        result.append(MAJUSCULES[(indexOf(MAJUSCULES, cadena[i]) + 13) % MAJUSCULES.length]);
                    } else {
                        result.append(MAJUSCULES[(indexOf(MAJUSCULES, cadena[i]) - 13 + MAJUSCULES.length) % MAJUSCULES.length]);
                    }
                } else {
                    if (encrypt) {
                        result.append(MINUSCULES[(indexOf(MINUSCULES, cadena[i]) + 13) % MINUSCULES.length]);
                    } else {
                        result.append(MINUSCULES[(indexOf(MINUSCULES, cadena[i]) - 13 + MINUSCULES.length) % MINUSCULES.length]);
                    }
                }
            } else {
                result.append(cadena[i]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args){
        String fraseDeprova = "Hola que tal, sóc en Yeray!";
        System.out.println("Aquest serà el text de prova que usarem: " + fraseDeprova);

        String textTraduit = translate(fraseDeprova, true);
        System.out.println("Codificat:   " + textTraduit);

        System.out.println("Descodificat:   " + translate(textTraduit, false));
    }
}