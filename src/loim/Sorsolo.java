package loim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorsolo {
    private static Random rand=new Random();

    public static List<Kerdes> sorsol(String type, int nehezseg, List<Kerdes> forras){
        int kezdoSorszam=0;
        List<Kerdes> sorsoltKerdesek=new ArrayList<>(15);
        if (type.equals("Tesztkerdes")){
            if (nehezseg==1){
                kezdoSorszam=rand.nextInt(4957)+1;
                while (kezdoSorszam%3!=1){
                    kezdoSorszam=rand.nextInt(4957)+1;
                }
            }
            if (nehezseg==2){
                kezdoSorszam=rand.nextInt(4958)+1;
                while (kezdoSorszam%3!=2) {
                    kezdoSorszam=rand.nextInt(4958)+1;
                }
            }
            if (nehezseg==3){
                kezdoSorszam=rand.nextInt(4956)+1;
                while (kezdoSorszam%3!=0) {
                    kezdoSorszam=rand.nextInt(4956)+1;
                }
            }
        }else{
            if (nehezseg==1){
                kezdoSorszam=rand.nextInt(457)+1;
                while (kezdoSorszam%3!=1) {
                    kezdoSorszam=rand.nextInt(457)+1;
                }
            }
            if (nehezseg==2){
                kezdoSorszam=rand.nextInt(458)+1;
                while (kezdoSorszam%3!=2) {
                    kezdoSorszam=rand.nextInt(458)+1;
                }
            }
            if (nehezseg==3){
                kezdoSorszam=rand.nextInt(456)+1;
                while (kezdoSorszam%3!=0) {
                    kezdoSorszam=rand.nextInt(456)+1;
                }
            }   
        }
        for (int iter=0; iter<15; iter++){
            sorsoltKerdesek.add(forras.get(kezdoSorszam-1+(iter*3)));
        }
        return sorsoltKerdesek;
    }
}
