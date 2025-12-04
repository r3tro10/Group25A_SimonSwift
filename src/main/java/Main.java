package DisplayManager;

public class Main {

    public static void main(String[] args) {

        // Creating the DisplayManager so I can control the lights on the SwiftBot
        DisplayManager displayManager = new DisplayManager();

        // Testing the random blinking feature twice just to make sure it works
        displayManager.blinkColoursRandomly();
        displayManager.blinkColoursRandomly();
    }
}