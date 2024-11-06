package iticbcn.xifratge;

import iticbcn.xifratge.classes_factoria.AlgorismeAES;
import iticbcn.xifratge.classes_factoria.AlgorismeFactory;
import iticbcn.xifratge.classes_factoria.AlgorismeMonoalfabetic;
import iticbcn.xifratge.classes_factoria.AlgorismePolialfabetic;
import iticbcn.xifratge.classes_factoria.AlgorismeRotX;
import iticbcn.xifratge.exceptions.ClauNoSuportada;

public class TestXifratge {
    public static void main(String[] args) {
        AlgorismeFactory[] aFactory = { 
            new AlgorismeAES(), 
            new AlgorismeMonoalfabetic(), 
            new AlgorismePolialfabetic(), 
            new AlgorismeRotX() 
        };
        String[] aNames = { "AES", "Monoalfabètic", "Polialfabètic", "RotX" };
        String[] msgs = { 
            "Test 01: Àlgid, Ülrich, Vàlid",
            "Test 02: Caràcters especials ¡!¿?*-123[]{}@#" 
        };
        String[][] claus = { 
            { "Clau Secreta 01" }, 
            { "ErrorClau", null },
            { "ErrorClau2", "123456" }, 
            { "-1", "13", "1000", "Errorclau" } 
        };

        for (int i = 0; i < aFactory.length; i++) {
            AlgorismeFactory alg = aFactory[i];
            String nom = aNames[i];
            System.out.println("==========================");
            System.out.println("Algorisme: " + nom);
            System.out.println("==========================");

            for (String msg : msgs) {
                for (String clau : claus[i]) {
                    System.out.println("msg: " + msg);
                    System.out.println("Clau: " + clau);
                    TextXifrat tx = null;
                    try {
                        tx = alg.xifra(msg, clau);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("TextXifrat: " + tx);
                    String desxifrat = null;
                    try {
                        desxifrat = alg.desxifra(tx, clau);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("Desxifrat: " + desxifrat);
                    System.out.println("------------------");
                }
            }
        }
    }
}