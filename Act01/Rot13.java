/**
 * El xifrat ROT13 consisteix a substituir cada lletra per la que està 13 posicions més a la dreta
    alfabèticament, i si s’arriba al final de l’alfabet es torna a començar per el principi.
*/

import java.util.Scanner;

public class Rot13 {

    private static final char[] minuscules = {'a', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] majuscules = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

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
        String result = "";
        for (int i = 0; i < cadena.length; i++){
            if(Character.isLetter(cadena[i])){
                if(Character.isUpperCase(cadena[i])){
                    result += majuscules[(indexOf(majuscules, cadena[i]) + 13) % majuscules.length];
                } else {
                    result += minuscules[(indexOf(minuscules, cadena[i]) + 13) % minuscules.length];

                }
            } else {
                result += cadena[i];
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println("Introdueix un text per encriptar:");
        Scanner scanner = new Scanner(System.in);
        System.out.println(xifraRot13(scanner.nextLine()));
        scanner.close();
    }
}