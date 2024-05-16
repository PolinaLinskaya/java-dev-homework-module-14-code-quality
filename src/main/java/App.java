import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            game.displayBoard();

            if (game.GameOver()) {
                game.Result();
                break;
            }

            game.playerMove(scan);
            if (game.Winner(Game.PLAYER_MARK)) {
                game.Winner(1);
                continue;
            }

            if (game.isFilled()) {
                game.Winner(3);
                continue;
            }

            game.computerMove();
            if (game.Winner(Game.COMPUTER_MARK)) {
                game.Winner(2);
                continue;
            }
        }
    }
}

class Game {
    private List<Character> board;
    private static final char EMPTY = ' ';
    public static final char PLAYER_MARK = 'X';
    public static final char COMPUTER_MARK = 'O';
    private int winner;

    public Game() {
        board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            board.add(EMPTY);
        }
        winner = 0;
    }

    public void displayBoard() {
        System.out.println("\n\n " + board.get(0) + " | " + board.get(1) + " | " + board.get(2) + " ");
        System.out.println("-----------");
        System.out.println(" " + board.get(3) + " | " + board.get(4) + " | " + board.get(5) + " ");
        System.out.println("-----------");
        System.out.println(" " + board.get(6) + " | " + board.get(7) + " | " + board.get(8) + " \n");
    }

    public void playerMove(Scanner scan) {
        while (true) {
            System.out.print("Enter a number between 1 and 9: ");
            byte input = scan.nextByte();
            if (input > 0 && input <= 9 && board.get(input - 1) == EMPTY) {
                board.set(input - 1, PLAYER_MARK);
                break;
            } else {
                System.out.println("Invalid input or box already taken. Try again.");
            }
        }
    }

    public void computerMove() {
        while (true) {
            int randomIndex = (int) (Math.random() * 9);
            if (board.get(randomIndex) == EMPTY) {
                board.set(randomIndex, COMPUTER_MARK);
                break;
            }
        }
    }

    private boolean checkLine(char mark, int i, int j, int k) {
        return board.get(i) == mark && board.get(j) == mark && board.get(k) == mark;
    }

    public boolean Winner(char mark) {
        return (checkLine(mark, 0, 1, 2) || checkLine(mark, 3, 4, 5) || checkLine(mark, 6, 7, 8) ||
                checkLine(mark, 0, 3, 6) || checkLine(mark, 1, 4, 7) || checkLine(mark, 2, 5, 8) ||
                checkLine(mark, 0, 4, 8) || checkLine(mark, 2, 4, 6));
    }

    public boolean isFilled() {
        for (char c : board) {
            if (c == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void Result() {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }
    public boolean GameOver() {
        return winner != 0;
    }

    public void Winner(int winner) {
        this.winner = winner;
    }
}
