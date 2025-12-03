import swiftbot.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class CelebrationController {

    private SwiftBotAPI swiftBot;

    // Constructor
    public CelebrationController() {
        // Initialize the robot API
        try {
            swiftBot = new SwiftBotAPI();
        } catch (Exception e) {
            System.out.println("Error initializing SwiftBot in CelebrationController: " + e.getMessage());
        }
    }

    // Main method called by GameController
    public void performCelebration(int score) {
        System.out.println("\n*** CELEBRATION MODE ACTIVATED! ***");
        System.out.println("Final Score: " + score);

        // 1. Calculate Speed based on score
        int speed = calculateSpeed(score);
        System.out.println("Celebration Speed set to: " + speed + "%");

        // 2. Blink LEDs in random order
        System.out.println("Blinking lights...");
        blinkRandomColors();

        // 3. Perform the 'V' shape movement
        System.out.println("Performing Victory Dive...");
        performVictoryDive(speed);

        // 4. Blink LEDs again
        System.out.println("Final light show...");
        blinkRandomColors();
        
        System.out.println("*** Celebration Complete ***");
    }

    // Logic to calculate speed as per requirements
    private int calculateSpeed(int score) {
        if (score < 5) {
            return 40; // Default safety speed, though celebration shouldn't trigger < 5
        } else if (score >= 5 && score < 10) {
            return score * 10; // e.g., Score 6 = 60% speed
        } else {
            return 100; // Cap speed at 100% for scores >= 10
        }
    }

    // Logic to perform the physical 'V' shape movement
    private void performVictoryDive(int speed) {
        try {
            // Note: Calibration is needed to know exactly how long 30cm takes.
            // Assumption: At 100% speed, 30cm takes approx 1 second (1000ms).
            // Formula: time = (BaseTime / (speed/100.0))
            // This ensures lower speed = longer time to cover same distance.
            
            int baseTimeFor30cm = 1000; // Adjust this value after testing with your robot!
            double speedFactor = (double) speed / 100.0;
            int moveTime = (int) (baseTimeFor30cm / speedFactor);
            
            // Turn Left to start first arm of 'V'
            // Assumes ~500ms is a 45 degree turn. Adjust as needed.
            swiftBot.move(-50, 50, 500); 
            
            // Move Forward (Arm 1)
            swiftBot.move(speed, speed, moveTime);
            
            // Reverse back to center
            swiftBot.move(-speed, -speed, moveTime);
            
            // Turn Right to face second arm of 'V'
            // Needs to turn 90 degrees total (45 back to center + 45 to right)
            swiftBot.move(50, -50, 1000); 
            
            // Move Forward (Arm 2)
            swiftBot.move(speed, speed, moveTime);
            
            // Reverse back to center
            swiftBot.move(-speed, -speed, moveTime);
            
            // Re-center the robot (Turn Left 45 degrees)
            swiftBot.move(-50, 50, 500);
            
        } catch (Exception e) {
            System.out.println("Error during movement: " + e.getMessage());
        }
    }

    // Logic to blink 4 colors in random order
    private void blinkRandomColors() {
        // Define RGB values for Red, Green, Blue, Yellow
        int[] red = {255, 0, 0};
        int[] green = {0, 255, 0};
        int[] blue = {0, 0, 255};
        int[] yellow = {255, 255, 0};

        // Put them in a list to shuffle
        ArrayList<int[]> colors = new ArrayList<>();
        colors.add(red);
        colors.add(green);
        colors.add(blue);
        colors.add(yellow);

        // Shuffle to get random order
        Collections.shuffle(colors);

        try {
            // Iterate through the randomized colors
            for (int[] color : colors) {
                swiftBot.fillUnderlights(color); // Turn on lights
                Thread.sleep(500); // Wait 0.5 seconds
                swiftBot.disableUnderlights(); // Turn off
                Thread.sleep(200); // Short pause between flashes
            }
        } catch (InterruptedException e) {
            System.out.println("Light show interrupted.");
        }
    }
}