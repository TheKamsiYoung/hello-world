import java.util.*;

class DotCom {
	 private ArrayList<String> locationCells;
	// int numOfHits;

	String checkUserValue(String userValue) {
		int coordinate = Integer.parseInt(userValue);
		String guess = "miss";
		boolean isMember = locationCells.contains(userValue);
			if (isMember = true) {
				if (locationCells.isEmpty() == true) {
					guess = "kill";
				}
				else {
					guess = "hit";
				}
			}
		System.out.println(guess);
		return guess;
	}
	void setLocationCells(ArrayList<String> arrayLocation) {
		locationCells = arrayLocation;
	}
}

class GameHelper {
	Scanner scan = new Scanner(System.in);
	String userInput(String display) {
		System.out.print(display);
		String input = scan.nextLine();
		return input;
	}
	void outputTotalGuess(int guess) {
		System.out.println("Total number of guesses: " + guess);
	}
}

public class DotComTestDrive {
	public static void main (String[] args) {
		int numOfGuesses = 0;
		SimpleDotCom dot = new SimpleDotCom();
		GameHelper helper = new GameHelper();
		int randomNumber = (int) (Math.random()*5);
		int[] location = {randomNumber, randomNumber + 1, randomNumber + 2};
		dot.setLocationCells(location);
		boolean isAlive = true;
		while (isAlive == true) {
			String userGuess = helper.userInput("Enter a number: ");
			String result = dot.checkUserValue(userGuess);
			numOfGuesses++;
			if (result.equals("kill")) {
				isAlive = false;
				helper.outputTotalGuess(numOfGuesses);
			}
		}
	}
}
