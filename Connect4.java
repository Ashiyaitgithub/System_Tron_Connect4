import java.util.Objects;
import java.util.Scanner;

public class Connect4 {

    /**
     * Global variables
     */
    private String[][] board;
    private int winningPlayer;
    private int currentPlayer;

    /**
     * Constructor
     */
    public Connect4() {
        winningPlayer = 0;
        currentPlayer = 1;
        board = new String[6][7];
        initializeBoard();
        displayBoard();
    }

    /**
     * Initialize the game board
     */
    private void initializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = " - ";
            }
        }
    }

    /**
     * Display the game board
     */
    private void displayBoard() {
        System.out.println("\n    *** CONNECT 4 ***\n");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Check if the input is valid
     */
    private boolean isValidInput(String input) {
        return Objects.equals(input, "1") ||
               Objects.equals(input, "2") ||
               Objects.equals(input, "3") ||
               Objects.equals(input, "4") ||
               Objects.equals(input, "5") ||
               Objects.equals(input, "6") ||
               Objects.equals(input, "7");
    }

    /**
     * Check if the selected column is full
     */
    private boolean isColumnFull(int column) {
        return !board[0][column].equals(" - ");
    }

    /**
     * Get the next available row in a column
     */
    private int getNextAvailableRow(int column) {
        int row = 5;
        while (row >= 0 && !board[row][column].equals(" - ")) {
            row--;
        }
        return row;
    }

    /**
     * Place a piece on the board
     */
    private void placePiece(int column) {
        int row = getNextAvailableRow(column);
        board[row][column] = (currentPlayer == 1) ? " X " : " O ";
        displayBoard();
    }

    /**
     * Check for a winner
     */
    private boolean checkForWinner() {
        // Horizontal check
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (!board[i][j].equals(" - ") &&
                    board[i][j].equals(board[i][j+1]) &&
                    board[i][j].equals(board[i][j+2]) &&
                    board[i][j].equals(board[i][j+3])) {
                    return true;
                }
            }
        }
        // Vertical check
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 3; i++) {
                if (!board[i][j].equals(" - ") &&
                    board[i][j].equals(board[i+1][j]) &&
                    board[i][j].equals(board[i+2][j]) &&
                    board[i][j].equals(board[i+3][j])) {
                    return true;
                }
            }
        }
        // Diagonal check (from top-left to bottom-right)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (!board[i][j].equals(" - ") &&
                    board[i][j].equals(board[i+1][j+1]) &&
                    board[i][j].equals(board[i+2][j+2]) &&
                    board[i][j].equals(board[i+3][j+3])) {
                    return true;
                }
            }
        }
        // Diagonal check (from top-right to bottom-left)
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (!board[i][j].equals(" - ") &&
                    board[i][j].equals(board[i+1][j-1]) &&
                    board[i][j].equals(board[i+2][j-2]) &&
                    board[i][j].equals(board[i+3][j-3])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the board is full (draw)
     */
    private boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j].equals(" - ")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Main game loop
     */
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (winningPlayer == 0) {
            System.out.println("Player " + currentPlayer + ", enter the column number (1-7): ");
            String input = scanner.nextLine();
            if (isValidInput(input)) {
                int column = Integer.parseInt(input) - 1;
                if (!isColumnFull(column)) {
                    placePiece(column);
                    if (checkForWinner()) {
                        winningPlayer = currentPlayer;
                        System.out.println("Player " + currentPlayer + " wins!");
                    } else if (isBoardFull()) {
                        System.out.println("It's a draw!");
                        break;
                    } else {
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    }
                } else {
                    System.out.println("Column is full. Please choose another column.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }
        scanner.close();
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        Connect4 game = new Connect4();
        game.playGame();
    }
}

