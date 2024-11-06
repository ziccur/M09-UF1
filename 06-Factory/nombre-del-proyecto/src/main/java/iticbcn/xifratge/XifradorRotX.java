package iticbcn.xifratge;

import iticbcn.xifratge.exceptions.ClauNoSuportada;
import iticbcn.xifratge.interfaces.Xifrador;

/**
 * El xifrat ROT13 consisteix a substituir cada lletra per la que està 13 posicions més a la dreta
    alfabèticament, i si s’arriba al final de l’alfabet es torna a començar per el principi.
*/

public class XifradorRotX implements Xifrador{

    // Sugerencia: Les constants han d'anar amb majuscula per millor lectura
    private static final char[] MINUSCULES = "aàábcçdeèéfghiìíïjklmnñoöòópqrstuùúüvwxyz".toCharArray(); 
    private static final char[] MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int clauInt;
        try {
            clauInt = Integer.parseInt(clau);
            if( clauInt < 0 || clauInt > 40){
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return new TextXifrat(xifraRotX(msg, clauInt).getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int clauInt;
        try {
            clauInt = Integer.parseInt(clau);
            if( clauInt < 0 || clauInt > 40){
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return desxifraRotX(new String(xifrat.getBytes()), Integer.parseInt(clau));
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    private  int indexOf(char[] cadena, char caracter){
        for(int i = 0; i < cadena.length; i++){
            if (cadena[i] == caracter){
                return i;
            }
        }
        return -1;
    }

    public  String xifraRotX(String text, int desplaçament){ return xifraRotX(text.toCharArray(), desplaçament); }
    public  String xifraRotX(char[] cadena, int desplaçament){
        return  translate(cadena, true, desplaçament);
    }

    public  String desxifraRotX( String text, int desplaçament ){ return desxifraRotX( text.toCharArray(), desplaçament); }
    public  String desxifraRotX( char[] cadena, int desplaçament ){
        return translate(cadena, false, desplaçament);
    }
    public  String translate( String text, boolean encrypt, int desplaçament){ return translate(text.toCharArray(), encrypt, desplaçament);}
    public  String translate(char[] cadena, boolean encrypt, int desplaçament) {
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

}