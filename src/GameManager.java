import java.util.Random;
import java.util.Scanner;

public class GameManager {

    public Board board;

    public static int score = 0;

    public int featureScore = 0;

    public boolean hasSprint = false; //sprint feature

    public boolean hasSkateBoard = false; //skateboard feature

    private FeatureBehavior featureBehavior;

    public GameManager(Board board) {
        this.board = board;
    }

    public Direction userSelectedChoice(Scanner console) {
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

    public void moveGuard(Direction move) {
        if (this.hasSprint) {
            //sprint feature *2 step
            this.featureBehavior = new SprintFeature();
        } else if (this.hasSkateBoard) {
            //skateboard feature
            this.featureBehavior = new SkateBoardFeature();
        } else {
            //not feature
            this.matrixPositionChange(this.board.getGuard().getX() + move.getDeltaX(), this.board.getGuard().getY() + move.getDeltaY());
        }

        if (this.featureBehavior != null) {
            this.featureBehavior.applyFeature(this, move);
            this.featureBehavior = null;
        }
    }


    public void matrixPositionChange(int newGuardX, int newGuardY) {
        if (this.board.matrixLocationCheck(newGuardX, newGuardY, Location.WALL)) {
            return;
        }

        if (this.board.matrixLocationCheck(newGuardX, newGuardY, Location.FEATURE)) {
            Random rnd = new Random();
            int randomFeature = rnd.nextInt(1, 3);
            if (randomFeature == 1) {
                System.out.println("Sprint feature active.");
                this.hasSprint = true;
            } else {
                System.out.println("Skateboard feature active.");
                this.hasSkateBoard = true;
            }
        }

        for (Box box : this.board.getBoxes()) {
            if (box.getX() == newGuardX && box.getY() == newGuardY) {
                int newBoxX = box.getX() + (newGuardX - this.board.getGuard().getX());
                int newBoxY = box.getY() + (newGuardY - this.board.getGuard().getY());

                if (!this.board.matrixLocationCheck(newBoxX, newBoxY, Location.WALL) && !this.board.matrixLocationCheck(newBoxX, newBoxY, Location.BOX)) {
                    this.board.setMatrixLocation(newBoxX, newBoxY, Location.BOX);
                    this.board.setMatrixLocation(box.getX(), box.getY(), Location.FLOOR);
                    box.move(newBoxX, newBoxY);
                    break;
                } else {
                    return;
                }
            }
        }
        this.board.setMatrixLocation(this.board.getGuard().getX(), this.board.getGuard().getY(), Location.FLOOR);
        this.board.setMatrixLocation(newGuardX, newGuardY, Location.GUARD);
        this.board.getGuard().move(newGuardX, newGuardY);
        GameManager.score++;
        this.featureCheck();
    }

    public void featureCheck() {
        if (GameManager.score % 5 == 0) {
            Random rnd = new Random();
            int rndX = rnd.nextInt(14);
            int rndY = rnd.nextInt(14);
            if (this.board.matrixLocationCheck(rndX, rndY, Location.FLOOR)) {
                this.board.setMatrixLocation(rndX, rndY, Location.FEATURE);
                System.out.println("evet floor");
            }
        }
    }


    public int isFinish() {
        int counter = 0;
        for (Box box : this.board.getBoxes()) {
            for (Goal goal : this.board.getGoals()) {
                if (box.getX() == goal.getX() && box.getY() == goal.getY()) {
                    counter = counter + 1;
                }
            }
        }
        return counter;
    }


    public void gameEndMessage() {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("Congratulations!");
        System.out.println("Score: " + GameManager.score);
    }
}
