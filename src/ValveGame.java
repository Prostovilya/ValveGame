

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ValveGame {

        private final int rows;
        private final int cols;
        private char[][] grid;
        private final Random random = new Random();

        public ValveGame(int rows, int cols){
            this.rows = rows;
            this.cols = cols;
            this.grid = new char [rows][cols];
            initializeGrid();
        }

        private void initializeGrid(){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = random.nextBoolean() ? '|' : '-';
                }
            }
        }

        public void printGrid(){
            System.out.println("Current Valve Configuration");
            for (char[] row : grid){
                System.out.println(Arrays.toString(row));
            }
        }

        public void turnValve(int row, int col){
            if (row < 0 || row >= rows || col < 0 || col >= cols){
                System.out.println("Invalid valve position");
                return;
            }
            grid[row][col] = (grid[row][col] == '|') ? '-' : '|';
            System.out.println("Turned valve at (" + row + ", " + col + ")");
            changeAdjacentValves(row, col);
        }

        private void changeAdjacentValves( int row, int col){
            int[][] directions = {{0,1}, {1,0}, {0, -1}, {-1,0}};
            for (int[] dir : directions){
                int newRow = row+dir[0];
                int newCol = col + dir[1];
                if (isValidPosition(newRow, newCol)){
                    grid[newRow][newCol] = (grid[newRow][newCol] == '|') ? '-' : '|';
                }
            }
        }
        private boolean isValidPosition(int row, int col){
            return row >= 0 && row < rows && col >=0 && col < cols;
        }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ValveGame game = new ValveGame (5, 5) ;
            game.printGrid();

            while (true){
                System.out.print("Enter row and column to turn valve (or -1 -1 to exit): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (row == -1 && col == -1){
                    break;
                }
                game.turnValve(row, col);
                game.printGrid();
            }

            scanner.close();

    }
    }
