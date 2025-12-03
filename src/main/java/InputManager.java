import java.util.ArrayList;
import swiftbot.*;
// I've been in-able to test this because i can't get the Swiftbot to work at home, i honestly have no clue if it works or not.
public class InputManager {
	static SwiftBotAPI swiftBot;
	
	public ArrayList<String> getUserSequence(int length) 
	{
		int n = length;
		// can be changed to whatever gives the needed length
		ArrayList<String> result = new ArrayList<>();
		
			swiftBot.enableButton(Button.A, () -> {
				if (result.size() < n) {
					// the following code should only run if the size of result is less then length
				result.add("Red");
				// adds the corresponding colour to "result"
			}});

			swiftBot.enableButton(Button.B, () -> {
				if (result.size() < n) {
					// the following code should only run if the size of result is less then length
				result.add("Blue");
				// adds the corresponding colour to "result"
			}});

			swiftBot.enableButton(Button.X, () -> {
				if (result.size() < n) {
					// the following code should only run if the size of result is less then length
				result.add("Green");
				// adds the corresponding colour to "result"
			}});

			swiftBot.enableButton(Button.Y, () -> {
				if (result.size() < n) {
					// the following code should only run if the size of result is less then length
				result.add("Yellow");
				// adds the corresponding colour to "result"
			}});
			
			while (result.size() < length) {
		        try { Thread.sleep(10); } catch (InterruptedException e) {}
		        // waits until the user has pressed buttons enough times
			}
		return result;
		// returns the list of colours. "result" can be changed if needed
	}
}
