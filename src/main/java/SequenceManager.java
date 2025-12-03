import java.util.ArrayList;
import java.util.Random;

public class SequenceManager {

    // list stores the sequence of colours
    private ArrayList<String> sequence;

    // runs when the class is first used and sets up the ArrayList
    // so it’s ready to store colours
    public SequenceManager() {
        sequence = new ArrayList<String>();
    }

    // method adds a new random colour and returns the whole list
    public ArrayList<String> extendSequence() {

        Random rand = new Random();

        // get a random number between 1 and 4
        int randomNumber = rand.nextInt(4) + 1;

        String newColour = "";

        // match the number to a colour
        if (randomNumber == 1) {
            newColour = "Red";
        }
        else if (randomNumber == 2) {
            newColour = "Yellow";
        }
        else if (randomNumber == 3) {
            newColour = "Green";
        }
        else if (randomNumber == 4) {
            newColour = "Blue";
        }

        // add the new colour to the sequence
        sequence.add(newColour);

        // return our newly generated sequence back to game controller
        return sequence;
    }
}

