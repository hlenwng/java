import java.util.Scanner;

public class Details {
	
	public Details() {
		String binary = "01011011";
		
		int decimal = Integer.parseInt(binary, 2);
		System.out.println(binary + " in binary = " + decimal + " in decimal.");
		
	}

	public static void main(String[] args) {
		new Details();
	}
}
