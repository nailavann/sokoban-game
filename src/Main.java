public class Main {

    public static void main(String[] args) {

        int[][] matrix =
                {
                        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                        {2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2},
                        {2, 1, 4, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 2},
                        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 2},
                        {2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2},
                        {2, 1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2},
                        {2, 1, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1, 1, 2},
                        {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2},
                        {2, 1, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1, 1, 2},
                        {2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2},
                        {2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2},
                        {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2},
                        {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2},
                        {2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2},
                };

        Game game = new Game(matrix);
        game.start();
    }

}
