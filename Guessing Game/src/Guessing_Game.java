import java.util.Scanner;

public class Guessing_Game {
	
	public Guessing_Game() {
		boolean stillPlaying = true;
		
		while (stillPlaying == true) {
			Scanner scanner = new Scanner(System.in);
			int randomNum = (int)(Math.random() * 51);
			int guess = -1;
			int numGuess = 0;
			
			System.out.println("Guess a number between 0-50...");
			
			while (guess != randomNum) {
				String input = scanner.nextLine();
				System.out.println("You typed: " + input);
				guess = Integer.parseInt(input);
				System.out.println(guess);
				
				numGuess++;
				
				if (guess == randomNum) {
					System.out.println("You got it right! It took you " + numGuess + " tries.");
				} 
				else if (guess < randomNum) {
					System.out.println("Too low.");
				} 
				else if (guess > randomNum) {
					System.out.println("Too high.");
				} 
			}
					
			System.out.println("Play again? (Yes or No?)");
			String yesno = scanner.nextLine();
			if (yesno.equals("yes") || yesno.equals("Yes")){
				stillPlaying =  true;
			} else {
				stillPlaying = false;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Guessing_Game();
	}
}