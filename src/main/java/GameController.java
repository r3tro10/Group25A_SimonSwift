import java.util.ArrayList;
import swiftbot.*; // Need to import SwiftBotAPI---------------------------------
public class GameController {    // CLASS DEFINITION

        private SwiftBotAPI robot;
        private SequenceManager sequenceManager;
        private DisplayManager displayManager;
        private InputManager inputManager;
        private RoundManager roundManager;
        private ScoreManager scoreManager;
        private CelebrationController celebrationController;

    public GameController() {    // CONSTRUCTOR
        
        robot = new SwiftBotAPI();
        sequenceManager = new SequenceManager();
        displayManager = new DisplayManager();
        inputManager = new InputManager();
        roundManager = new RoundManager();
        scoreManager = new ScoreManager();
        celebrationController = new CelebrationController();
    }
    public void startGame() {    // MAIN GAME LOOP
        System.out.println("Welcome to Simon Swift!");

        int currentRound = 1;
        int currentScore = 0;
        boolean gameRunning = true;

        while(gameRunning == true) { 
            roundManager.displayRoundInfo(currentRound, currentScore);                                   //  STEP 1: START ROUND 
            ArrayList<String> gamesequence = sequenceManager.extendSequence();                            //  STEP 2: PREPARE SEQUENCE 
            displayManager.showSequence(gamesequence);                                                  //  STEP 3: DISPLAY 
            ArrayList<String> playerAttempt = inputManager.getUserSequence(gamesequence.size());            //  STEP 4: PLAYER INPUT 
            boolean isCorrect = scoreManager.isSequenceCorrect(gamesequence, playerAttempt);               //  STEP 5: CHECK ATTEMPT 
            
            if (isCorrect == true) {                             // SUCCESS LOGIC
                System.out.println("Correct!");
                currentScore = scoreManager.updateScore(true, currentScore);
                

                if (roundManager.shouldOfferQuit(currentRound) == true) {                //  STEP 6: QUIT CHECK
                    boolean userWantsToQuit = roundManager.askQuitOrContinue();

                    if (userWantsToQuit == true) {
                        gameRunning = false;
                        scoreManager.printQuitMessage(currentScore, currentRound);
                    }
                }
                currentRound ++;
            }
            else {
                gameRunning = false;                // FAILURE LOGIC
                scoreManager.printGameOver(currentScore, currentRound);
            }
        }
        if (currentScore >= 5) {        // STEP 7: CELEBRATION
            celebrationController.performCelebration(currentScore);
        }
        System.out.println("Game finished.");
        System.exit(0);
    }
    public static void main(String[] args) {    // 4. MAIN METHOD
        GameController game = new GameController();
        game.startGame();
    }
}