import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		String[][] grid = {
				{" ", " ", "|", " ",  " ", " ", "|", " ",  " "},
				{"-", "-", "-", "-", "-", "-", "-", "-", "-"},
				{" ", " ", "|", " ",  " ", " ", "|", " ",  " "},
				{"-", "-", "-", "-", "-", "-", "-", "-", "-"},
				{" ", " ", "|", " ",  " ", " ", "|", " ",  " "}
		};
		
		System.out.print("Welcome to Tic-Tac-Toe.\n"
				+ "1) Two Player\n2) Easy AI\n3) Hard AI\n> ");
		Scanner input = new Scanner(System.in);
		int playerType = input.nextInt();

		while (playerType <= 0 || 4 <= playerType || playerType == 3) {
			if (playerType == 3)
				System.out.println("This feature is unavaliable right now. Try another option.\n> ");
			else
				System.out.println("Input out of range. Try again.\n" +
					"1) Two Player\n2) Easy AI\n3) Hard AI\n> ");
			
			playerType = input.nextInt();
		}
		
		String userToken;
		String compToken;
		int startPlayer = (int)(Math.random() * 2);
		
		if (startPlayer % 2 == 0) {
			userToken = "X";
			compToken = "O";
			System.out.println("Player 1 Starts\n");
		}
		else {
			userToken = "O";
			compToken = "X";
			System.out.println("Player 2 Starts\n");
		}
		
		int counter = 0;
		
		// Game Loop
		while (counter < 4) {
			// Player 1 Starts
			if (startPlayer % 2 == 0) {
				displayGrid(grid);
				userToken(grid, userToken);
				
				displayGrid(grid);
				compToken(grid, compToken, playerType);
				
				// The last available cell is unfilled without this code. This runs through the process one last time.
				if (counter == 3) {
					displayGrid(grid);
					userToken(grid, userToken);	
				}
				
			}
			// Player 2 starts
			else {
				// Shows grid for the second human player. Otherwise don't show grid, keeps the terminal cleaner.
				if (playerType == 1)
					displayGrid(grid);
				compToken(grid, compToken, playerType);
				
				displayGrid(grid);
				userToken(grid, userToken);
				
				// The last available cell is unfilled without this code. This runs through the process one last time.
				if (counter == 3) {
					displayGrid(grid);
					compToken(grid, compToken, playerType);
				}		
			}
			
			counter++;
		}

		System.out.println("\nEnd Game\n");
		displayGrid(grid);
	}

	public static void displayGrid(String[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
			System.out.print(grid[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	// Player 1 turn handled here, everything but displaying the grid[][].
	public static String[][] userToken(String[][] grid, String userToken) {
		System.out.println("Player 1 Turn\n");
		Scanner input = new Scanner(System.in);
		
		int row;
		boolean exit = false;

		while (exit != true) {
				do {
				System.out.print("Enter a row (1, 2, 3): ");
				row = input.nextInt();
				if (row <= 0 ||  row >= 4) {
					System.out.println("Input outside of range, try again.");
				}
			}
			while (row <= 0 ||  row >= 4);

			// Changes row to match grid[][]. I.e. row = 1 -> (row = 0) = grid[0]
			if (row == 1)
				row = 0;
			else if (row == 3)
				row = 4;

			int column;
			do {
				System.out.print("Enter a column (1, 2, 3): ");
				column = input.nextInt();
				if (column <= 0 ||  column >= 4) {
					System.out.println("Input outside of range, try again.");
				}
			}
			while (column <= 0 ||  column >= 4);

			// Changes column to match grid[][]. I.e. column = 1 -> (column = 0) = grid[][0]
			if (column == 1)
				column = 0;
			else if (column == 2)
				column = 4;
			else if (column == 3)
				column = 8;

			if (grid[row][column].compareTo(" ") == 0) {
				grid[row][column] = userToken;
				exit = true;
			}
			else {
				System.out.println("\nCell (" + row + "," + column
					+ ") is already taken, try again.\n");
				displayGrid(grid);
			}
		}
		return grid;
	}

	// Player 2 (Human & Easy/Hard AI's) turn handled here, everything but displaying the grid[][].
	public static String[][] compToken(String[][] grid, String compToken, int playerType) {
		System.out.println("Player 2 Turn\n");
		// Player 2
		if (playerType == 1) {
			userToken(grid, compToken);
		}
		
		// Easy AI
		else if (playerType == 2){
			int counter = 0;

			// Gives each empty cell a number that will be chosen by "select"
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid[i].length; j++)
					if (grid[i][j].compareTo(" ") == 0 && j % 2 == 0)
						grid[i][j] = "" + counter++;

			int select = (int)(Math.random() * counter);

			// Erases the numbers from the empty cells. If the cell number matches "select", place "compToken"
			for (int i = 0; i < grid.length; i++) {
				if (i == 1 || i == 3)
					continue;
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j].compareTo(("" + select)) == 0)
						grid[i][j] = compToken;
					else if (j % 4 == 0 && !(grid[i][j].compareTo("X") == 0
						|| grid[i][j].compareTo("O") == 0)) {
						grid[i][j] = " ";
					}
				}
			}
		}
		
		// Hard AI
		// Not implemented yet.
		else if (playerType == 3) {
			System.exit(0);
		}

		return grid;
	}
}