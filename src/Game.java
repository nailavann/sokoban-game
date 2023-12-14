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
            this.board.moveGuard(move);
            System.out.println("Score: " + Board.score);
        } while (!this.board.isFinish());
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


}