import java.util.*;

class SimpleDotCom {
	int[] locationCells;
	int numOfHits;
	
	String checkUserValue(String value) {
		int coordinate = Integer.parseInt(value);
		String guess = "miss";
		for (int count : locationCells) {
			if (coordinate == count) {
				numOfHits++;
				if (numOfHits == 3) {
					guess = "kill"; 
				}
				else {
					guess = "hit";
				}
				break;
			}
		}
		System.out.println(guess);
		return guess;
	}
	void setLocationCells(int[] array) {
		locationCells = array;
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

public class SimpleDotComTestDrive1 {
	public static void main (String[] args) {
		int numOfGuesses = 0;
		SimpleDotCom dot = new SimpleDotCom();
		GameHelper helper = new GameHelper();
		int randomNumber = (int) (Math.random()*5);
		int[] location = {randomNumber, randomNumber + 1, randomNumber + 2};
		dot.setLocationCells(location);
		boolean isAlive = true;
		while (isAlive == true) {
			String userGuess = helper.userInput("Enter a number(0-6): ");
			String result = dot.checkUserValue(userGuess);
			numOfGuesses++;
			if (result.equals("kill")) {
				isAlive = false;
				helper.outputTotalGuess(numOfGuesses);
			}
		}
	}
}
