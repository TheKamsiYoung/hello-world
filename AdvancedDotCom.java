import java.util.*;
import java.io.*;

class AdvancedDotCom {
	public static void main(String[] args) {
		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlayingGame();
	}
}

class DotComBust {
	GameHelper helper = new GameHelper();
	private int numOfGuesses = 0;
	private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	private DotCom[] initialDotComList = new DotCom[3];
	
	public void setUpGame() {
		//Create three dotComs
		for (int x = 0; x < 3; x++) {
			initialDotComList[x] = new DotCom();
		}
		
		//Add to the main dotComList
		for (int x = 0; x < 3; x++) {
			dotComList.add(initialDotComList[x]);
		}
	
		//Name the dotComs
		String[] dotComNameList = {"Pets.com", "Ask.com", "Google.com"};
		int counter = 0;
		for (DotCom dotComToSetName : dotComList){
			dotComToSetName.setName(dotComNameList[counter]);
			counter++;
		}
		
		//Set locationCells
		for (DotCom dotComToSetLocation : dotComList) {
			ArrayList<String> tempLocation = helper.placeDotCom(3);
			dotComToSetLocation.setLocationCells(tempLocation);
		}
	}
	
	public void startPlayingGame() {
		while (!dotComList.isEmpty()) {
			String userGuess = helper.userInput("Enter a cell location (E.g 'A3'): ");
			this.checkUserGuess(userGuess);
		} 					//close while
		this.finishGame();
	} 					//close startPlayingGame method
	
	private void checkUserGuess(String userGuess) {
		numOfGuesses++;					//Increment guess every time checkUserGuess() is called
		String result = "miss";						//default is a 'miss'
		for (DotCom dotComToCheck : dotComList) {				//repeat with all dotComs in the dotComList
			result = dotComToCheck.checkYourself(userGuess);					//check for hit, miss or kill
			if (result.equals("hit")) {
				break;
			}
			if (result.equals("kill")) {
				dotComList.remove(dotComToCheck);					//Remove dotCom when thee is a kill
				break;
			}
		}
		System.out.println(result);					//display result
	}
	
	private void finishGame() {
		System.out.println("Game Over! You sunk all dotComs");
		if (numOfGuesses <= 20) {
			System.out.println("Hmmm... Not bad. Took you only " + numOfGuesses + " tries.");
		}
		else {
			System.out.println("Took you long enough!");
		}
	}
}

class DotCom {
	private ArrayList<String> locationCells;
	private String name;
	
	public void setLocationCells(ArrayList<String> loc) {
		locationCells = loc;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String checkYourself(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);
		if (index >= 0) {
			locationCells.remove(index);
			if (locationCells.isEmpty()) {
				result = "kill";
			}
			else {
				result = "hit";
			}
		}
		return result;
	}
}

class GameHelper {
	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = 49;
	private int[] grid = new int[gridSize];
	private int comCount = 0;
	
	public String userInput(String prompt) {
		String inputLine = null;
		System.out.println(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if (inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine.toLowerCase();
	}
	
	public ArrayList<String> placeDotCom(int comSize) {
		ArrayList<String> alphaCells = new ArrayList<String>();
		String[] alphacoords = new String [comSize];
		String temp = null;
		int[] coords = new int[comSize];
		int attempts = 0;
		boolean success = false;
		int location = 0;
		
		comCount++;
		int incr = 1;
		if ((comCount % 2) == 1) {
			incr = gridLength;
		}
		
		while (!success & attempts++ < 200) {
			location = (int) (Math.random()* gridSize);
			int x = 0;
			success = true;
			while (success && x < comSize) {
				if (grid[location] == 0) {
					coords[x++] = location;
					location += incr;
					if (location >= gridSize) {
						success = false;
					}
					if (x > 0 && (location % gridLength == 0)) {
						success = false;
					}
				}else {
					success = false;
				}
			}
		}
		
		int x = 0;
		int row = 0;
		int column = 0;
		
		while (x < comSize) {
			grid[coords[x]] = 1;
			row = (int) (coords[x] /gridLength);
			column = coords[x] % gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		}
		
		return alphaCells;
	}
}