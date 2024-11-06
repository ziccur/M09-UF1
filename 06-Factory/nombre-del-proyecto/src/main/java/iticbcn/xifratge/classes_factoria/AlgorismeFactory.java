package iticbcn.xifratge.classes_factoria;
import iticbcn.xifratge.TextXifrat;
import iticbcn.xifratge.exceptions.ClauNoSuportada;
import iticbcn.xifratge.interfaces.Xifrador;

public abstract class AlgorismeFactory {

    public abstract Xifrador creaXifrador();
    
    public abstract TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;

    public abstract String desxifra(TextXifrat textXifrat, String clau) throws ClauNoSuportada;
}
