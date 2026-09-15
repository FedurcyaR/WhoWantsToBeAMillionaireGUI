package loim;

public class GUISegedfuggvenyek {

    public static boolean joInputTextField(String input2){
		input2=input2.replace(" ", "");
		input2=input2.toUpperCase();
		return ((input2.length()==4) && (input2.contains("A") && input2.contains("B") && input2.contains("C") && input2.contains("D")));
	}

}
