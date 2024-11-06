package iticbcn.xifratge.classes_factoria;

import iticbcn.xifratge.TextXifrat;
import iticbcn.xifratge.XifradorMonoalfabetic;
import iticbcn.xifratge.exceptions.ClauNoSuportada;
import iticbcn.xifratge.interfaces.Xifrador;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        return new XifradorMonoalfabetic();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        Xifrador xifrador = creaXifrador();
        return xifrador.xifra(msg, clau);
    }

    @Override
    public String desxifra(TextXifrat textXifrat, String clau) throws ClauNoSuportada {
        Xifrador xifrador = creaXifrador();
        return xifrador.desxifra(textXifrat, clau);
    }

}
