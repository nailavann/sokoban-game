import java.util.Scanner;

public class Game {
    private Board board;
    private GameManager gameManager;

    public Game(int[][] matrix) {
        this.board = new Board(matrix);
        this.gameManager = new GameManager(this.board);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        do {
            this.board.showBoard();
            Direction move = this.gameManager.userSelectedChoice(scanner);
            this.gameManager.moveGuard(move);
            System.out.println("Score: " + GameManager.score);
        } while (this.gameManager.isFinish() != this.board.getGoals().size());

        this.gameManager.gameEndMessage();
    }

}