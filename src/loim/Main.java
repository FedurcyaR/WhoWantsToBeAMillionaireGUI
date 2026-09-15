package loim;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Kerdes> osszesTesztes=new ArrayList<>();
		List<Kerdes> osszesSorbarakos=new ArrayList<>();
		ReadInQuestions reader=new ReadInQuestions();
		try {
			reader.beolvasTesztkerdes("src/resources/loim_tesztkerdesek.csv", osszesTesztes);
			reader.beolvasSorbarakas("src/resources/loim_sorbarakas.csv", osszesSorbarakos);
		} catch (Exception e){
			System.out.println("Hiba!\n"+e.getMessage());
		}
	    GraphicalUI gui=new GraphicalUI();
	    gui.start(osszesTesztes, osszesSorbarakos);
	}
}
