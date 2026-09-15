package loim;

import java.io.Serializable;

public class Jatekos implements Serializable{
    private String nev;
    private long ido;
    private int nyeremeny;

    public Jatekos(String name, long time, int nyeremeny){
        this.nev=name;
        this.ido=time;
        this.nyeremeny=nyeremeny;
    }

    public String getNev(){
        return nev;
    }

    public long getIdo(){
        return ido;
    }

    public int getNyeremeny(){
        return nyeremeny;
    }
}
