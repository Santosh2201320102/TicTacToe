import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Main game loop
    private static void playGame() {
        boolean isGameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            printBoard();
            playerMove(scanner);

            isGameOver = checkWin() || checkDraw();

            if (!isGameOver) {
                switchPlayer();
            }
        }

        printBoard();

        if (checkWin()) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else if (checkDraw()) {
            System.out.println("The game is a draw!");
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Handle the player's move
    private static void playerMove(Scanner scanner) {
        int row, col;
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column, 0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }

    // Check if there is a winner
    private static boolean checkWin() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check if there is a draw
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check all rows for a win
    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check all columns for a win
    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check the two diagonals for a win
    private static boolean checkDiagonals() {
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // Switch to the next player
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
