import java.util.List;
import java.util.Arrays;
import java.util.Collections;

// SwiftBot API - used to control LEDs
import swiftbot.SwiftBotAPI;
import swiftbot.Underlight;

public class DisplayManager {

    // Colour constants for easy reference
    public static final int COLOUR_RED = 0;
    public static final int COLOUR_BLUE = 1;

    // SwiftBot instance to control the hardware
    private final SwiftBotAPI swiftBot = SwiftBotAPI.INSTANCE;

    // Constructor - just confirms setup
    public DisplayManager() {
        System.out.println("DisplayManager initialized with SwiftBot.");
    }

    // Converts a colour ID to a human-readable name
    private String getColourName(int colourId) {
        if (colourId == COLOUR_RED) {
            return "Red";
        } else if (colourId == COLOUR_BLUE) {
            return "Blue";
        } else {
            return "Unknown";
        }
    }

    // Returns the label for the LED position (for logging/debugging)
    private String getLedLabel(int colourId) {
        if (colourId == COLOUR_RED) {
            return "Front LED";
        } else if (colourId == COLOUR_BLUE) {
            return "Back LED";
        } else {
            return "Unknown LED";
        }
    }

    // Maps the colour ID to the corresponding LED position
    private Underlight getLedPosition(int colourId) {
        if (colourId == COLOUR_RED) {
            return Underlight.FRONT_LEFT;
        } else if (colourId == COLOUR_BLUE) {
            return Underlight.BACK_RIGHT;
        } else {
            return Underlight.FRONT_LEFT; // Default fallback
        }
    }

    // Provides RGB values based on the selected colour
    private int[] getRgbForColour(int colourId) {
        if (colourId == COLOUR_RED) {
            return new int[] {255, 0, 0}; // Red
        } else if (colourId == COLOUR_BLUE) {
            return new int[] {0, 0, 255}; // Blue
        } else {
            return new int[] {0, 0, 0}; // Off / default
        }
    }

    // Lights up one LED with a specific colour briefly
    public void showSingleColour(int colourId) {
        String colourName = getColourName(colourId);
        String ledName = getLedLabel(colourId);

        System.out.println("Lighting " + colourName + " on " + ledName);

        Underlight led = getLedPosition(colourId);
        int[] rgb = getRgbForColour(colourId);

        try {
            swiftBot.setUnderlight(led, rgb); // Turn LED on
            Thread.sleep(500);               // Keep it on briefly
            swiftBot.disableUnderlight(led); // Then turn it off
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt flag
        }
    }

    // Goes through a list of colours and displays each one
    public void showColourSequence(List<Integer> colourSequence) {
        for (int colourId : colourSequence) {
            showSingleColour(colourId);

            try {
                Thread.sleep(500); // Pause between colours
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Blinks all known colours in a random order
    public void blinkColoursRandomly() {
        List<Integer> allColours = Arrays.asList(COLOUR_RED, COLOUR_BLUE);

        Collections.shuffle(allColours); // Randomize order

        System.out.println("Randomized colour order: " + allColours);

        showColourSequence(allColours); // Reuse the sequence method
    }
}
