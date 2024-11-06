package iticbcn.xifratge.interfaces;

import iticbcn.xifratge.TextXifrat;
import iticbcn.xifratge.exceptions.ClauNoSuportada;

public interface Xifrador {
    
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada;
}
