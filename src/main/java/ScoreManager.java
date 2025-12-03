import java.util.ArrayList;

public class ScoreManager {

	    
	    public boolean isSequenceCorrect(ArrayList<String> target, ArrayList<String> attempt) {

	        // its a size check, if its different sizes then it will be false
	        if (target.size() != attempt.size()) {
	            return false;
	        }

	        // compares elements
	        for (int i = 0; i < target.size(); i++) {
	            if (!target.get(i).equals(attempt.get(i))) {
	                return false; // if there is a mismatch then it will be false
	            }
	        }

	        return true; // all elements matched in order
	    }

	   
	    public int updateScore(boolean correct, int currentScore) {
	        if (correct) {
	            return currentScore + 1; // reward one point
	        } else {
	            return currentScore;     // no change if wrong
	        }
	    }

	    
	     //this prints the quit message when the player wants to exit
	     // i also added the empty print lines so the text isnt jumbled up
	    
	    public void printQuitMessage(int finalScore, int rounds) {
	        System.out.println("");
	        System.out.println("  Goodbye! ");
	        System.out.println("  Total rounds played: " + rounds);
	        System.out.println("  Final score : " + finalScore);
	        System.out.println("");
	    }

	   
	     //it prints the game over message when the player fails minigame, it also shows the score and round number
	     
	    public void printGameOver(int finalScore, int rounds) {
	        System.out.println("");
	        System.out.println("  GAME OVER ");
	        System.out.println("  You reached round: " + rounds);
	        System.out.println("  Final score: " + finalScore);
	        System.out.println("");
	    }

	    
}

