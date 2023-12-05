
import java.util.Scanner;

public class Game {
    private Board board;

    public Game(int[][] matrix) {
        board = new Board();
        board.loadBoard(matrix);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        do {
            board.showBoard();
            Direction move = userSelectedChoice(scanner);
            moveGuard(move);
            System.out.println("Score: " + Board.score);
        } while (!isFinish());
    }

    private Direction userSelectedChoice(Scanner console) {
        System.out.print("Direction (W-A-S-D) : ");
        char choice = console.next().charAt(0);

        return switch (choice) {
            case 'w', 'W' -> Direction.UP;
            case 's', 'S' -> Direction.DOWN;
            case 'a', 'A' -> Direction.LEFT;
            case 'd', 'D' -> Direction.RIGHT;
            default -> Direction.INVALID;
        };
    }

    private void moveGuard(Direction move) {
        int guardX = board.getGuardX();
        int guardY = board.getGuardY();

        switch (move) {
            case UP:
                guardX--;
                break;
            case DOWN:
                guardX++;
                break;
            case LEFT:
                guardY--;
                break;
            case RIGHT:
                guardY++;
                break;
            default:
                break;
        }

        board.guardAndBoxPositionChange(guardX, guardY);
    }


    private boolean isFinish() {
        if (board.getBoxX() == board.getGoalX() && board.getBoxY() == board.getGoalY()) {
            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println();
            System.out.println("Congratulations!");
            System.out.println("Score: " + Board.score);
            return true;
        }
        return false;
    }

}