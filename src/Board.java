import java.util.ArrayList;

public class Board {
    private Guard guard;
    private ArrayList<Box> boxes;
    private ArrayList<Goal> goals;
    private ArrayList<Floor> floors;
    private ArrayList<Wall> walls;
    public static int score = 0;

    private int[][] matrix;

    public Board() {
        boxes = new ArrayList<>();
        floors = new ArrayList<>();
        walls = new ArrayList<>();
        goals = new ArrayList<>();
    }

    public void showBoard() {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
    }

    public void loadBoard(int[][] board) {
        matrix = new int[board.length][board[0].length];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (board[x][y] == Location.GUARD.getValue()) {
                    guard = new Guard(x, y);
                } else if (board[x][y] == Location.GOAL.getValue()) {
                    goals.add(new Goal(x, y));
                } else if (board[x][y] == Location.BOX.getValue()) {
                    boxes.add(new Box(x, y));
                } else if (board[x][y] == Location.WALL.getValue()) {
                    walls.add(new Wall(x, y));
                } else {
                    floors.add(new Floor(x, y));
                }
                matrix[x][y] = board[x][y];
            }
        }
    }

    public void moveGuard(Direction move) {
        int guardX = this.guard.getX();
        int guardY = this.guard.getY();

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

        this.guardAndBoxPositionChange(guardX, guardY);
    }


    private void guardAndBoxPositionChange(int newGuardX, int newGuardY) {
        if (matrix[newGuardX][newGuardY] == Location.WALL.getValue()) {
            return;
        }

        for (Box box : this.boxes) {
            if (box.getX() == newGuardX && box.getY() == newGuardY) {
                int newBoxX = box.getX() + (newGuardX - guard.getX());
                int newBoxY = box.getY() + (newGuardY - guard.getY());

                if (matrix[newBoxX][newBoxY] != Location.WALL.getValue() && matrix[newBoxX][newBoxY] != Location.BOX.getValue()) {
                    matrix[newBoxX][newBoxY] = Location.BOX.getValue();
                    matrix[box.getX()][box.getY()] = Location.FLOOR.getValue();
                    box.move(newBoxX, newBoxY);
                    break;
                } else {
                    return;
                }
            }
        }

        matrix[this.guard.getX()][this.guard.getY()] = Location.FLOOR.getValue();
        matrix[newGuardX][newGuardY] = Location.GUARD.getValue();
        guard.move(newGuardX, newGuardY);
        Board.score++;
    }


    public boolean isFinish() {
        int counter = 0;
        for (Box box : boxes) {
            for (Goal goal : goals) {
                if (box.getX() == goal.getX() && box.getY() == goal.getY()) {
                    counter = counter + 1;
                }
            }
        }
        if (counter == 2) {
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
