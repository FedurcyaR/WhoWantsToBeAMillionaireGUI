package loim;

import java.util.List;

public abstract class Kerdes {
    protected String kerd;
    protected List<String> valaszok;

    public abstract boolean helyesValasz(String valaszBetu);

    public String getKerdes(){
        return kerd;
    }

    public List<String> getValaszok(){
        return valaszok;
    }
}
