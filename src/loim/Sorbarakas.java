package loim;

import java.util.*;

public class Sorbarakas extends Kerdes{
    private String helyesSorrend;

    public Sorbarakas(String question, List<String> answers, String order){
        this.kerd=question;
        this.valaszok=answers;
        this.helyesSorrend=order;
    }

    @Override
    public boolean helyesValasz(String sorrend){
        return helyesSorrend.equals(sorrend);
    }

    public String getHelyesSorrend(){
        return helyesSorrend;
    }
    
}
