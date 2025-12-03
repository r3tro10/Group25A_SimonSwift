
import java.util.Scanner;

public class RoundManager {
	
	public void displayRoundInfo(int round, int score) {
		System.out.println("Round: " + round + "\nScore: " + score);
	}
	
	public boolean shouldOfferQuit(int round) {
		if((round % 5) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean askQuitOrContinue() {
		Scanner scanner = new Scanner(System.in);
	    
	    System.out.print("Do you want to continue? (y/n): ");
	    String input = scanner.nextLine().trim().toLowerCase();

	    return input.equals("n");
	}
}
