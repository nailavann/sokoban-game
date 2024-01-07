import java.util.Scanner;

public class GameManager {

    private Board board;

    public static int score = 0;

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
        int newGuardX = this.board.getGuard().getX() + move.getDeltaX();
        int newGuardY = this.board.getGuard().getY() + move.getDeltaY();

        this.matrixPositionChange(newGuardX, newGuardY);
    }

    private void matrixPositionChange(int newGuardX, int newGuardY) {
        if (this.board.matrixLocationCheck(newGuardX,newGuardY,Location.WALL)) {
            return;
        }

        for (Box box : this.board.getBoxes()) {
            if (box.getX() == newGuardX && box.getY() == newGuardY) {
                int newBoxX = box.getX() + (newGuardX - this.board.getGuard().getX());
                int newBoxY = box.getY() + (newGuardY - this.board.getGuard().getY());

                if (!this.board.matrixLocationCheck(newBoxX,newBoxY,Location.WALL) && !this.board.matrixLocationCheck(newBoxX,newBoxY,Location.BOX)) {
                    this.board.setMatrixLocation(newBoxX,newBoxY,Location.BOX);
                    this.board.setMatrixLocation(box.getX(),box.getY(),Location.FLOOR);
                    box.move(newBoxX, newBoxY);
                    break;
                } else {
                    return;
                }
            }
        }
        this.board.setMatrixLocation(this.board.getGuard().getX(),this.board.getGuard().getY(),Location.FLOOR);
        this.board.setMatrixLocation(newGuardX,newGuardY,Location.GUARD);
        this.board.getGuard().move(newGuardX, newGuardY);
        GameManager.score++;
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


    public void gameEndMessage(){
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("Congratulations!");
        System.out.println("Score: " + GameManager.score);
    }
}
