package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import iticbcn.xifratge.exceptions.ClauNoSuportada;
import iticbcn.xifratge.interfaces.Xifrador;

public class XifradorPolialfabetic implements Xifrador{
    
    private static Random random;
    private static final int CLAU = 9213;
    private static final char[] alfabet = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static char[] alfabetPermutat = new char[alfabet.length];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        return desxifraPoliAlfa(new String(xifrat.getBytes()));
    }

    public void permutaAlfabet(){
        List<Character> alfabetList = new ArrayList<>();
        for (char c : alfabet) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList, random);
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i] = alfabetList.get(i);
        }

    }

    private int indexOf(char[] var0, char var1) {
        for(int var2 = 0; var2 < var0.length; ++var2) {
            if (var0[var2] == var1) {
                return var2;
            }
        }
        return -1;
    }

    public  String xifraPoliAlfa(String cadena){
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

    public String desxifraPoliAlfa(String cadena){
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
}
