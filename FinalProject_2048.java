/*  ========== Final Project 2048 ==========
 * 
 *  Game:
 *      4) Check for lose condition
 *      5) Repeat until step 4 is true
 *      6) (Optional) Track and display score
 */

import java.util.Scanner;

public class FinalProject_2048 {
    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
        };

        generateRanTile(grid);
        generateRanTile(grid);

        boolean winCon = true;

        while (winCon == true) {
            displayGrid(grid);
            moveMerge(grid, userInput());
            generateRanTile(grid);
            
            winCon = checkWinCon(grid, winCon);
        }
        displayGrid(grid);
 
    }

    public static void displayGrid(int[][] grid) {
        System.out.print("\n---------------------\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Create the leftmost border
                if (j == 0)
                    System.out.print("|");
 
                // Displays tiles' number and aligns grid properly; creates borders
                if (grid[i][j] == 0)
                    System.out.print("    |");
                else if (grid[i][j] <= 9) {
                    System.out.print(grid[i][j] + "   |");
                }
                else if (grid[i][j] <= 99) {
                    System.out.print(grid[i][j] + "  |");
                }
                else if (grid[i][j] <= 999) {
                    System.out.print(grid[i][j] + " |");
                }
                else if (grid[i][j] <= 9999) {
                    System.out.print(grid[i][j] + "|");
                }
            }

            System.out.print("\n---------------------\n");
        }

        return;
    }

    public static void generateRanTile(int[][] grid) {
        int counter = 0;

        // Counter and assign all empty cells to later select
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
                if (grid[i][j]== 0)
                    grid[i][j] = counter--;

        // Create random number that will select a cell later
        int selectTile = (int)(Math.random() * counter);

        // Generates a value to enter into the selected cell
        int selectTileValue = (int)(Math.random() * 4);
        if (selectTileValue < 3)
            selectTileValue = 2;
        else 
            selectTileValue = 4;

        // Assigns generated value to the selected cell and unassigns all previous empty cells.
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == selectTile)
                    grid[i][j] = selectTileValue;
                else if (grid[i][j] < 0)
                    grid[i][j] = 0;
            }
 
    }

    public static int userInput() {
        Scanner input = new Scanner(System.in);
 
        System.out.print("Enter move: ");
        int userChoice = input.nextInt();

        while (userChoice != 2 && userChoice != 4 && userChoice != 6 && userChoice != 8) {
            System.out.println("That was an invalid choice.");
            System.out.println("2 for down, 4 for left, 6 for right, 8 for up.");
            System.out.print("Enter move: ");
            userChoice = input.nextInt();
        }
 
        return userChoice;
    }

    public static int[][] moveMerge(int[][] grid, int userChoice) {
        // It is possible to combine right/left and up/down.
        // I.e. if {userChoice == 4} (int rightLeft = -1) else if (userchoice == 6) {int rightLeft = 1}
        // Then grid[i][j + rightLeft]
        int counter = 0;

        // Move/Merge Left
        if (userChoice == 4) {
            while (counter < 3) {
                for (int i = 0; i < grid.length; i++)
                    for (int j = 1; j < grid[i].length; j++) {

                        // Tile -> Empty Cell; If the cell is empty, move the tile left
                        if (grid[i][j - 1] == 0) {
                            grid[i][j - 1] = grid[i][j];
                            grid[i][j] = 0;
                        }
                        // Tile -> Equivalant Tile; If the tile is equal to the left tile, merge
                        // Currerntly merges all equal tiles instead of stopping at one merge. I.e {5, 5, 5, 5} is {625, 0, 0, 0} instead of {25, 25, 0, 0}. It is because the counter loops throught the process 3 times.
                        else if (grid[i][j - 1] == grid[i][j]) {
                            grid[i][j - 1] *= 2;
                            grid[i][j] = 0;
                        }
                    }

                counter++;
            }
        }
        // Move/Merge Right
        else if (userChoice == 6) {
            while (counter < 3) {
                for (int i = 0; i < grid.length; i++)
                    for (int j = 2; j > -1; j--) {

                        // Tile -> Empty Cell; If the cell is empty, move the tile right
                        if (grid[i][j + 1] == 0) {
                            grid[i][j + 1] = grid[i][j];
                            grid[i][j] = 0; 
                        }
                        // Tile -> Equivalant Tile; If the tile is equal to the right tile, merge
                        // Currerntly merges all equal tiles instead of stopping at one merge. I.e {5, 5, 5, 5} is {0, 0, 0, 625} instead of {0, 0, 25, 25}. It is because the counter loops throught the process 3 times.
                        else if (grid[i][j + 1] == grid[i][j]) {
                            grid[i][j + 1] *= 2;
                            grid[i][j] = 0;
                        }
                    }

                counter++;
            }
        }
        // Move/merge Down
        else if (userChoice == 2) {
            while (counter < 3) {
                for (int i = grid.length - 2; i > -1; i--)
                    for (int j = 0; j < grid[i].length; j++) {

                        // Tile -> Empty Cell; If the cell is empty, move the tile down
                        if (grid[i + 1][j] == 0) {
                            grid[i + 1][j] = grid[i][j];
                            grid[i][j] = 0;
                        }
                        // Tile -> Equivalant Tile; If the tile is equal to the lower tile, merge
                        // Currerntly merges all equal tiles instead of stopping at one merge. I.e {5, 5, 5, 5} is {0, 0, 0, 625} instead of {0, 0, 25, 25}. It is because the counter loops throught the process 3 times.
                        else if (grid[i + 1][j] == grid[i][j]) {
                            grid[i + 1][j] *= 2;
                            grid[i][j] = 0;
                        }
                    }

                counter++;
            }
        }
        // Move/merge Up
        else {
            while (counter < 3) {
                for (int i = 1; i < grid.length; i++)
                    for (int j = 0; j < grid[i].length; j++) {

                        // Tile -> Empty Cell; If the cell is empty, move the tile down
                        if (grid[i - 1][j] == 0) {
                            grid[i - 1][j] = grid[i][j];
                            grid[i][j] = 0;
                        }
                        // Tile -> Equivalant Tile; If the tile is equal to the lower tile, merge
                        // Currerntly merges all equal tiles instead of stopping at one merge. I.e {5, 5, 5, 5} is {0, 0, 0, 625} instead of {0, 0, 25, 25}. It is because the counter loops throught the process 3 times.
                        else if (grid[i - 1][j] == grid[i][j]) {
                            grid[i - 1][j] *= 2;
                            grid[i][j] = 0;
                        }
                    }

                counter++;
            }
        }

        return grid;
    }

    public static boolean checkWinCon(int[][] grid, boolean winCon) {
        winCon = true;
        int maxValue = 0;
        
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] > maxValue)
                    maxValue = grid[i][j];

        if (maxValue >= 2048) {
            winCon = false;
            System.out.println("You Win!");
        }

        return winCon;
    }
}
