public class Main {

    public static void main(String[] args) {

        Game game = new Game(ReadCharacters.getInstance().getMatrix());
        game.start();
    }

}
