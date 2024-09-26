/**
 * El xifrat ROT13 consisteix a substituir cada lletra per la que està 13 posicions més a la dreta
    alfabèticament, i si s’arriba al final de l’alfabet es torna a començar per el principi.
*/

import java.util.Scanner;

public class Rot13 {

    // Sugerencia: Les constants han d'anar amb majuscula per millor lectura
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
        return  translate(cadena, true);
    }

    public static String desxifraRot13( String text ){ return desxifraRot13( text.toCharArray()); }
    public static String desxifraRot13( char[] cadena ){
        return translate(cadena, false);
    }

    public static String translate( char[] cadena, boolean encrypt){
        String result = ""; // recomenació: al ser un string que es modifica tota l'estona utilitza sting buffed
        for (int i = 0; i < cadena.length; i++){
            if(Character.isLetter(cadena[i])){ //sugerencia: buscartots els mòduls aprovats i mitjana notes >= 8 dirctament en les arrays de lletres per fer el codi més optim ja que retornaria la posició
                if(Character.isUpperCase(cadena[i])){
                    if(encrypt){
                        result += majuscules[(indexOf(majuscules, cadena[i]) + 13) % majuscules.length];
                    }else{
                        result += majuscules[(indexOf(majuscules, cadena[i]) - 13) % majuscules.length];
                    }
                } else {
                    if(encrypt){
                        result += minuscules[(indexOf(minuscules, cadena[i]) + 13) % minuscules.length];
                    }else{
                        result += minuscules[(indexOf(minuscules, cadena[i]) - 13) % minuscules.length];
                    }
                }
            } else {
                result += cadena[i];
            }
        }
        return result;
    }

    public static void main(String[] args){
        if(args.length == 0){
            System.out.println("ERROR: parametro no indicat.\n Usa $java Rot13 encrypt/desencrypt ");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        if(args[0].equals("encrypt")){
            System.out.println("Introdueix un text per encriptar:");
            System.out.println(xifraRot13(scanner.nextLine()));
            scanner.close();
        }else if(args[0].equals("desencrypt")){
            System.out.println("Introdueix un text per desencriptar:");
            System.out.println(desxifraRot13(scanner.nextLine()));
            scanner.close();
        }
    }
}