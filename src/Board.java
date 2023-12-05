public class Board {
    private int guardX = 0, guardY = 0;
    private int boxX = 0, boxY = 0;
    private int goalX = 0, goalY = 0;

    public static int score = 0;

    private int[][] matrix;

    public Board() {
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
                    guardX = x;
                    guardY = y;
                }
                if (board[x][y] == Location.GOAL.getValue()) {
                    goalX = x;
                    goalY = y;
                }
                if (board[x][y] == Location.BOX.getValue()) {
                    boxX = x;
                    boxY = y;
                }
                matrix[x][y] = board[x][y];
            }
        }
    }

    public void guardAndBoxPositionChange(int guardX, int guardY) {
        if (matrix[guardX][guardY] == Location.WALL.getValue()) {
            return;
        }

        int newGuardX = this.guardX;
        int newGuardY = this.guardY;

        if (matrix[guardX][guardY] == Location.FLOOR.getValue() || matrix[guardX][guardY] == Location.BOX.getValue()) {
            newGuardX = guardX;
            newGuardY = guardY;
        }

        if (matrix[guardX][guardY] == Location.BOX.getValue()) {
            int newBoxX = boxX + (guardX - this.guardX);
            int newBoxY = boxY + (guardY - this.guardY);

            if (matrix[newBoxX][newBoxY] != Location.WALL.getValue() && matrix[newBoxX][newBoxY] != Location.BOX.getValue()) {
                matrix[newBoxX][newBoxY] = Location.BOX.getValue();
                matrix[boxX][boxY] = Location.FLOOR.getValue();
                boxX = newBoxX;
                boxY = newBoxY;
            } else {
                return;
            }
        }

        matrix[this.guardX][this.guardY] = Location.FLOOR.getValue();
        matrix[newGuardX][newGuardY] = Location.GUARD.getValue();
        this.guardX = newGuardX;
        this.guardY = newGuardY;
        Board.score++;
    }

    public int getGuardX() {
        return guardX;
    }


    public int getGuardY() {
        return guardY;
    }


    public int getGoalX() {
        return goalX;
    }

    public int getGoalY() {
        return goalY;
    }


    public int getBoxX() {
        return boxX;
    }

    public int getBoxY() {
        return boxY;
    }


}
