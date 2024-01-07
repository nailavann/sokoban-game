import java.util.Random;

public class Board extends BaseBoard {

    public static int score = 0;

    public int featureScore = 0;

    public boolean hasSprint = false; //sprint feature

    public boolean hasSkateBoard = false; //skateboard feature

    private FeatureBehavior featureBehavior;


    public Board(int[][] matrix) {
        super();
        super.loadBoard(matrix);
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
            this.guardAndBoxPositionChange(this.getGuard().getX() + move.getDeltaX(), this.getGuard().getY() + move.getDeltaY());
        }

        if (this.featureBehavior != null) {
            this.featureBehavior.applyFeature(this, move);
        }
    }

    public void guardAndBoxPositionChange(int newGuardX, int newGuardY) {
        if (this.matrixLocationCheck(newGuardX, newGuardY, Location.WALL)) {
            return;
        }

        if (this.matrixLocationCheck(newGuardX, newGuardY, Location.FEATURE)) {
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

        for (Box box : this.getBoxes()) {
            if (box.getX() == newGuardX && box.getY() == newGuardY) {
                int newBoxX = box.getX() + (newGuardX - this.getGuard().getX());
                int newBoxY = box.getY() + (newGuardY - this.getGuard().getY());

                if (!this.matrixLocationCheck(newBoxX, newBoxY, Location.WALL) && !this.matrixLocationCheck(newBoxX, newBoxY, Location.BOX)) {
                    this.setMatrixLocation(newBoxX, newBoxY, Location.BOX);
                    this.setMatrixLocation(box.getX(), box.getY(), Location.FLOOR);
                    box.move(newBoxX, newBoxY);
                    break;
                } else {
                    return;
                }
            }
        }
        this.setMatrixLocation(this.getGuard().getX(), this.getGuard().getY(), Location.FLOOR);
        this.setMatrixLocation(newGuardX, newGuardY, Location.GUARD);
        this.getGuard().move(newGuardX, newGuardY);
        Board.score++;

        this.featureCheck();
    }

    public void featureCheck() {
        if (Board.score % 5 == 0) {
            Random rnd = new Random();
            int rndX = rnd.nextInt(14);
            int rndY = rnd.nextInt(14);
            if (this.matrixLocationCheck(rndX, rndY, Location.FLOOR)) {
                this.setMatrixLocation(rndX, rndY, Location.FEATURE);
                System.out.println("evet floor");
            }
        }
    }

    public int isFinish() {
        int counter = 0;
        for (Box box : this.getBoxes()) {
            for (Goal goal : this.getGoals()) {
                if (box.getX() == goal.getX() && box.getY() == goal.getY()) {
                    counter = counter + 1;
                }
            }
        }
        return counter;
    }

}
