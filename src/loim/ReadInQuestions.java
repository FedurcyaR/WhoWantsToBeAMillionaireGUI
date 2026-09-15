package loim;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ReadInQuestions {

	public void beolvasTesztkerdes(String filename1, List<Kerdes> adatstrukt1) throws IOException{
		List<String[]> tordelnivaloSor1=new ArrayList<>();
		try(BufferedReader br1=new BufferedReader(new InputStreamReader(new FileInputStream(filename1), StandardCharsets.UTF_8))){
			String line1;
			while ((line1=br1.readLine())!=null){
				tordelnivaloSor1.add(line1.split(";"));
			}
		}
		for (int i=1; i<tordelnivaloSor1.size(); i++){
			List<String> valaszok1=new ArrayList<>();
			valaszok1.add(tordelnivaloSor1.get(i)[2]);
			valaszok1.add(tordelnivaloSor1.get(i)[3]);
			valaszok1.add(tordelnivaloSor1.get(i)[4]);
			valaszok1.add(tordelnivaloSor1.get(i)[5]);
			Kerdes temp1=new Tesztkerdes(tordelnivaloSor1.get(i)[1], valaszok1, tordelnivaloSor1.get(i)[6]);
			adatstrukt1.add(temp1);
		}
	}

	public void beolvasSorbarakas(String filename2, List<Kerdes> adatstrukt2) throws IOException{
		List<String[]> tordelnivaloSor2=new ArrayList<>();
		try(BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(filename2), StandardCharsets.UTF_8))){
			String line2;
			while ((line2=br2.readLine())!=null){
				tordelnivaloSor2.add(line2.split(";"));
			}
		}
		for (int j=1; j<tordelnivaloSor2.size(); j++){
			List<String> valaszok2=new ArrayList<>();
			valaszok2.add(tordelnivaloSor2.get(j)[1]);
			valaszok2.add(tordelnivaloSor2.get(j)[2]);
			valaszok2.add(tordelnivaloSor2.get(j)[3]);
			valaszok2.add(tordelnivaloSor2.get(j)[4]);
			Kerdes temp2=new Sorbarakas(tordelnivaloSor2.get(j)[0], valaszok2, tordelnivaloSor2.get(j)[5]);
			adatstrukt2.add(temp2);
		}
	}
}
