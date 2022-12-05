import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sort {
	
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	
	public Sort() {
		System.out.println("Enter a number for the input file.");
		System.out.println("1: input1.txt  2: input2.txt  3: input3.txt  4: input4.txt");
		input = consoleInput.nextLine();
		if (input.length() != '1' && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3' && input.charAt(0) != '4') {
			System.out.println("Enter 1, 2, 3, or 4");
			while (input.length() != '1' && input.charAt(0) != '1' && input.charAt(0) != '2'
					&& input.charAt(0) != '3' && input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}
		}
		try {
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		String infile = fileInput.nextLine();
		String[] inputStringArray = infile.split(",");
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			System.out.println(inputArray[i]);
		}
		
		System.out.println("Enter a number for the sort you want to use.");
		System.out.println("1: Bubble  2: Selection  3: Table");
		input = consoleInput.nextLine();
		if (input.length() != '1' && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3') {
			System.out.println("Enter 1, 2, or 3");
			while (input.length() != '1' && input.charAt(0) != '1' && input.charAt(0) != '2'
					&& input.charAt(0) != '3') {
				input = consoleInput.nextLine();
			}
		}
		if (input.equals("1")) {
			inputArray = bubbleSort(inputArray);
		}
		for (int i = 0; i < inputStringArray.length; i++) {
			System.out.println(inputArray[i]);
		}
	}
	
	//Compare each pair of numbers and move the larger to the right
	int[] bubbleSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			
		}
			for (int i = 0; i < array.length - 1; i++) {
				//if the one on the left is larger
				if (array[i] > array[i+1]) {
					//swap!
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = array[i];
				}
			}
		
		return array;
	}

	public static void main(String[] args) {
		new Sort();
	}

}
