public class Main {

    public static void main(String[] args) {

        System.out.println("1: Floor");
        System.out.println("2: Wall");
        System.out.println("3: Guard");
        System.out.println("4: Box");
        System.out.println("5: Goal");

        Game game = new Game(ReadCharacters.getInstance().getMatrix());
        game.start();

    }

}
