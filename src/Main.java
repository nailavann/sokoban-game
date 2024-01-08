public class Main {

    public static void main(String[] args) {

        ReadGame.getInstance().readInitialize();
        Game game = new Game(ReadGame.getInstance().getMatrix());
        game.start();

    }

}
