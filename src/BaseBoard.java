import java.util.ArrayList;

public class BaseBoard {
    private Guard guard;
    private ArrayList<Box> boxes;
    private ArrayList<Goal> goals;
    private ArrayList<Floor> floors;
    private ArrayList<Wall> walls;

    private int[][] matrix;

    public BaseBoard() {
        boxes = new ArrayList<>();
        floors = new ArrayList<>();
        walls = new ArrayList<>();
        goals = new ArrayList<>();
    }


    public void showBoard() {
        for (int x = 0; x < this.getMatrix().length; x++) {
            for (int y = 0; y < this.getMatrix()[x].length; y++) {
                System.out.print(this.getMatrix()[x][y] + " ");
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

    public int[][] getMatrix() {
        return matrix;
    }

    public Guard getGuard() {
        return guard;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setMatrixLocation(int x ,int y,Location location) {
        this.matrix[x][y] = location.getValue();
    }

    public boolean matrixLocationCheck(int x, int y, Location location){
        return this.matrix[x][y] == location.getValue();
    }

}
