/*  ========== Final Project 2048 ==========
 * 
 *  Game:
 *      1) Generate a tile on the first, and after, every move
 *      2) Ask user for a movement (up, down, left, right)
 *      3) Move/merge tiles and display the grid
 *      4) Check for win/lose condition
 *      5) Repeat until step 4 is true
 *      6) (Optional) Track and display score
 * 
 *  Plan:
 *      main {
 *          Char arrayGrid[][]
 *          
 *          loop (while win condition method() isn't true) {
 *              display grid method (arrayGrid[][])
 *              generate random tile method(arrayGrid[][])
 *              userInput
 *              move/merge cells method(arrayGrid[][], userInput)
 *              check win condition method(arrayGrid[][])
 *              }
 *      }
 * 
 *      Char[][] generate random tile method(arrayGrid[][]) {
 *          //[1][2][3][4]
 *          //[5][6][7][8]
 *          //[9][10][11][12]
 *          //[13][14][15][16]
 * 
 *          loop (while cells are not filled) {
 *              give each empty cell a number
 *              counter++
 *              }
 * 
 *          randTile = Math.random() * counter
 *          randTileValue = Math.random()
 * 
 *          // Can give 2's a higher chance to appear
 *          Switch (randTileValue) {
 *              case 0 = randTilevalue = 2
 *              case 1 = randTile value = 2
 *              case 2 = randTile value = 4
 *          }
 * 
 *          arrayGrid[][randTile] = randTileValue
 *          return arrayGrid[][]
 *      }
 * 
 *      void display grid method(arrayGrid[][]) {
 *          loop (while grid isn't fully displayed)
 *              display the grid
 *      }
 * 
 *      Char[][] move/merge cells method(arrayGrid[][], userInput) {
 *          loop (for each tile) {
 *              if (cell is empty)
 *                  moveDirection = userInput
 *              else if (cell is !empty && can be merged) {
 *                  mergeDirection = userInput
 *                  moveDirection = userInput
 *              }
 *          }
 * 
 *          return arrayGrid[][]
 * 
 *      }
 * 
 *      Boolean check win condition method(arrayGrid[][]) {
 *          if (one cell has 2048) {
 *              winCon = true;
 *              print ("You win!")
 *              print ("Score is " + score) // optional
 *          }
 *          else if (can't move/merge) {
 *              wincon = true;
 *              print ("You lose.")
 *              print ("Score is " + score) // optional
 *          }
 *          else
 *              winCon = false
 *          
 *          return winCon
 *      }
 * 
 */



public class FinalProject_2048 {
    public static void main(String[] args) throws Exception {
        int[][] grid = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
        };
        for (int i = 0; i < 17; i++) {
            displayGrid(grid);
            generateRanTile(grid);
        }
    }

    public static void displayGrid(int[][] grid) {
        System.out.print("\n---------------------\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Create the leftmost border
                if (j == 0)
                    System.out.print("|");

                // Displays tiles number and aligns grid properly, creates borders
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

    public static void generateRanTile (int[][] grid) {
        int counter = -1;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
                if (grid[i][j]== 0)
                    grid[i][j] = counter--;

        int selectTile = (int)(Math.random() * counter);
        int selectTileValue = (int)(Math.random() * 4);

        if (selectTileValue < 3)
            selectTileValue = 2;
        else 
            selectTileValue = 4;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == selectTile)
                    grid[i][j] = selectTileValue;
                else if (grid[i][j] < 0)
                    grid[i][j] = 0;
            }

    }
}
