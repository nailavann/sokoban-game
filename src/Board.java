
public class Board extends BaseBoard{

    public static int score = 0;

    public Board(int[][] matrix) {
        super();
        super.loadBoard(matrix);
    }

    public void moveGuard(Direction move) {
        int guardX = this.getGuard().getX() + move.getDeltaX();
        int guardY = this.getGuard().getY() + move.getDeltaY();

        this.guardAndBoxPositionChange(guardX, guardY);
    }


    private void guardAndBoxPositionChange(int newGuardX, int newGuardY) {
        if (this.matrixLocationCheck(newGuardX,newGuardY,Location.WALL)) {
            return;
        }

        for (Box box : this.getBoxes()) {
            if (box.getX() == newGuardX && box.getY() == newGuardY) {
                int newBoxX = box.getX() + (newGuardX - this.getGuard().getX());
                int newBoxY = box.getY() + (newGuardY - this.getGuard().getY());

                if (!this.matrixLocationCheck(newBoxX,newBoxY,Location.WALL) && !this.matrixLocationCheck(newBoxX,newBoxY,Location.BOX)) {
                    this.setMatrixLocation(newBoxX,newBoxY,Location.BOX);
                    this.setMatrixLocation(box.getX(),box.getY(),Location.FLOOR);
                    box.move(newBoxX, newBoxY);
                    break;
                } else {
                    return;
                }
            }
        }
        this.setMatrixLocation(this.getGuard().getX(),this.getGuard().getY(),Location.FLOOR);
        this.setMatrixLocation(newGuardX,newGuardY,Location.GUARD);
        this.getGuard().move(newGuardX, newGuardY);
        Board.score++;
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
