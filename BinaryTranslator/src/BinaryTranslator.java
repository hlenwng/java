import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BinaryTranslator {
	
	public BinaryTranslator() {
		System.out.println("Please enter \"file\" to enter a file or \"input\" to use the console");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String numberInput = "";
		
		boolean readFile = false;
		
		if(input.equals("file")) { //input from a file
			try {
				System.out.println("Enter your file name.");
				input = scanner.nextLine();
				Scanner fileScanner = new Scanner(new File(input));
				numberInput = fileScanner.nextLine();
				
				readFile = true;
				
			} catch (IOException ex) {
				System.out.println("File not found.");
				scanner.close();
				System.exit(1);
			}
		}
		//input from the console or use value from file
						
			System.out.println("If you are translating from decimal to binary, type \"d-b\".");
			System.out.println("If you are translating from binary to decimal, type \"b-d\".");
			
			input = scanner.nextLine();
			
			if(input.equals("d-b")) { //decimal to binary
				int num = 0;
				Scanner decimalConvert = new Scanner(System.in);
				
				if (readFile == true) {
					num = Integer.parseInt(numberInput);
				}
				else {
				System.out.println("Please enter the decimal number...");
				
				String answer = decimalConvert.nextLine();
			    num = Integer.parseInt(answer);
				}

				int binary[] = new int[40];
			    int index = 0;
			   
			    while(num > 0){
			    	binary[index++] = num%2;
			    	num = num/2;
		        }
			    
		        for(int i = index-1; i >= 0; i--){
		        	System.out.print(binary[i]);
		        }
			    
			}    
			
			else { //binary to decimal
				int number, decimal = 0, a = 0;
			    Scanner binaryConvert = new Scanner(System.in);
						
			    if (readFile == true) {
					number = Integer.parseInt(numberInput);
				}
			    else {
				System.out.println("Please enter the binary number...");
			
			    String strBinary = binaryConvert.nextLine();
			    number = Integer.parseInt(strBinary);
			    }
			   
			    while(number != 0){
			    	decimal += (number % 10) * Math.pow(2, a);
			    	number = number / 10;
			    	a++;				
			    }
			    
		    	System.out.println("Decimal number: " + decimal);
			}
		
			scanner.close();
	}
	
	public static void main(String[] args) {
		new BinaryTranslator();
	}
}
