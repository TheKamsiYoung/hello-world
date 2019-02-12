import java.util.*;

class DotCom {
	 private ArrayList<String> locationCells;
	// int numOfHits;

	String checkUserValue(String userValue) {
		int coordinate = Integer.parseInt(userValue);
		String guess = "miss";
        
			if (locationCells.contains(userValue)) {
                locationCells.remove(userValue);
				if (locationCells.isEmpty()) {
					guess = "kill";
				}else {
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
		DotCom dot = new DotCom();
		GameHelper helper = new GameHelper();
		int randomNumber = (int) (Math.random()*5);
        
        ArrayList<String> randomNumberList = new ArrayList<>(Arrays.asList(String.valueOf(randomNumber),
                                                               String.valueOf(randomNumber + 1), String.valueOf(randomNumber + 2)));

		//int[] location = {randomNumber, randomNumber + 1, randomNumber + 2};
		dot.setLocationCells(randomNumberList);
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
