package loim;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Dicsoseglista {
    private List<Jatekos> tkTop10;
    private List<Jatekos> srTop10;

    public Dicsoseglista() {
        tkTop10=new ArrayList<>();
        srTop10=new ArrayList<>();
    }

    public List<Jatekos> getTKtop10(){
        return tkTop10;
    }

    public List<Jatekos> getSRtop10(){
        return srTop10;
    }

    public void ujrekordhozzaad(Jatekos player, String type) {
        List<Jatekos> targetList;
        if (type.equals("Tesztkerdes")) {
            targetList=tkTop10;
        }else{
            targetList=srTop10;
        }

        int beszurIdx=0;
        for (int i=0; i<targetList.size(); i++) {
            Jatekos current=targetList.get(i);
            if (player.getNyeremeny()>current.getNyeremeny()
            || (player.getNyeremeny()==current.getNyeremeny() && player.getIdo()<current.getIdo())) {
                break;
            }
            beszurIdx++;
        }

        if (targetList.size()<10) {
            targetList.add(beszurIdx, player);
        } else if (beszurIdx<10) {
            targetList.add(beszurIdx, player);
            targetList.remove(10);
        }
    }

    public void elment() {
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/resources/tkTop10.txt"))) {
            oos.writeObject(tkTop10);
        } catch (IOException e) {
            System.out.println("Hiba a tkTop10 mentésekor: "+e.getMessage());
        }
    
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/resources/srTop10.txt"))) {
            oos.writeObject(srTop10);
        } catch (IOException e) {
            System.out.println("Hiba a srTop10 mentésekor: "+e.getMessage());
        }
    }

    public void beolvas() {
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/resources/tkTop10.txt"))) {
            tkTop10=(List<Jatekos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hiba a tkTop10 beolvasásakor: "+e.getMessage());
        }
    
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/resources/srTop10.txt"))) {
            srTop10=(List<Jatekos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hiba a srTop10 beolvasásakor: "+e.getMessage());
        }
    }

}
