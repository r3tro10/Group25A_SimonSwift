import java.util.ArrayList;
import swiftbot.*;

public class GameController {

    private SwiftBotAPI robot;
    private SequenceManager sequenceManager;
    private DisplayManager displayManager;
    private InputManager inputManager;
    private RoundManager roundManager;
    private ScoreManager scoreManager;
    private CelebrationController celebrationController;

    public GameController() {
        robot = new SwiftBotAPI();
        sequenceManager = new SequenceManager();
        displayManager = new DisplayManager();
        inputManager = new InputManager(robot);
        roundManager = new RoundManager();
        scoreManager = new ScoreManager();
        celebrationController = new CelebrationController(robot);
    }

    public void startGame() {

        System.out.println("Welcome to Simon Swift!");

        int currentRound = 1;
        int currentScore = 0;
        boolean gameRunning = true;

        while (gameRunning == true) {

            roundManager.displayRoundInfo(currentRound, currentScore);

            ArrayList<String> gameSequence = sequenceManager.extendSequence();

            displayManager.showSequence(gameSequence);

            ArrayList<String> playerAttempt = inputManager.getUserSequence(gameSequence.size());

            boolean isCorrect = scoreManager.isSequenceCorrect(gameSequence, playerAttempt);

            if (isCorrect == true) {
                System.out.println("Correct!");

                currentScore = scoreManager.updateScore(true, currentScore);

                if (roundManager.shouldOfferQuit(currentRound) == true) {
                    boolean quit = roundManager.askQuitOrContinue();

                    if (quit == true) {
                        gameRunning = false;
                        scoreManager.printQuitMessage(currentScore, currentRound);
                    }
                }

                currentRound = currentRound + 1;

            } else {
                gameRunning = false;
                scoreManager.printGameOver(currentScore, currentRound);
            }
        }

        if (currentScore >= 5 && true) {
            celebrationController.performCelebration(currentScore);
        }

        System.out.println("Game finished.");
        System.exit(0);
    }

    public static void main(String[] args) {
        GameController game = new GameController();
        game.startGame();
    }
}
