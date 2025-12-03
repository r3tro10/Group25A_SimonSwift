// UtilsAndValidation.java
import java.util.ArrayList;

public class UtilsAndValidation {

    // keeps score above 0
    public static int fixScore(int score) {
        if (score < 0) {
            return 0;
        }
        return score;
    }

    // limits speed between 0 and 100
    public static int clampSpeed(int speed) {
        if (speed < 0) {
            return 0;
        }
        if (speed > 100) {
            return 100;
        }
        return speed;
    }

    // simple length check for user input
    public static boolean hasExpectedLength(ArrayList<String> seq, int expected) {
        if (seq == null) {
            return false;
        }
        return seq.size() == expected;
    }
}
