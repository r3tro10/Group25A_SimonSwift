import swiftbot.*;
import java.util.ArrayList;
import java.util.Collections;

public class CelebrationController {

    private final SwiftBotAPI swiftBot;

    public CelebrationController(SwiftBotAPI robot) {
        this.swiftBot = robot;
    }

    public void performCelebration(int score) {
        System.out.println("Celebrating... Score = " + score);

        int speed = calculateSpeed(score);
        System.out.println("Speed: " + speed);

        blinkRandomColors();
        doVDive(speed);
        blinkRandomColors();

        System.out.println("Done.");
    }

    private int calculateSpeed(int score) {
        // I just followed the rule from requirements
        if (score < 5) {
            return 40;
        } else if (score < 10) {
            return score * 10;
        } else {
            return 100;
        }
    }

    private void doVDive(int speed) {
        // TODO: these timings need testing on real robot
        int base = 1000;
        double f = speed / 100.0;
        int moveTime = (int)(base / f);

        try {
            // left a bit
            swiftBot.move(-50, 50, 500);

            // forward
            swiftBot.move(speed, speed, moveTime);

            // back
            swiftBot.move(-speed, -speed, moveTime);

            // turn right more (90 deg)
            swiftBot.move(50, -50, 1000);

            // forward again
            swiftBot.move(speed, speed, moveTime);

            // back again
            swiftBot.move(-speed, -speed, moveTime);

            // center it
            swiftBot.move(-50, 50, 500);

        } catch (Exception e) {
            System.out.println("Movement error: " + e.getMessage());
        }
    }

    private void blinkRandomColors() {
        int[] red = {255, 0, 0};
        int[] green = {0, 255, 0};
        int[] blue = {0, 0, 255};
        int[] yellow = {255, 255, 0};

        ArrayList<int[]> colors = new ArrayList<>();
        colors.add(red);
        colors.add(green);
        colors.add(blue);
        colors.add(yellow);

        Collections.shuffle(colors);

        try {
            for (int[] c : colors) {
                swiftBot.fillUnderlights(c);
                Thread.sleep(500);
                swiftBot.disableUnderlights();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Light issue: " + e.getMessage());
        }
    }
}
