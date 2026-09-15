package loim;

import java.util.*;

public class Tesztkerdes extends Kerdes{
    private String helyesBetu;

    public Tesztkerdes(String question, List<String> answers, String correct){
        this.kerd=question;
        this.valaszok=answers;
        this.helyesBetu=correct;
    }

    public String getHelyesBetu(){
        return helyesBetu;
    }

    @Override
    public boolean helyesValasz(String valaszbetu){
        return helyesBetu.equals(valaszbetu);
    }
    
}
