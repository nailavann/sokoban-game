public class WallFactory implements BaseCellFactory {
    @Override
    public Wall create(int x, int y) {
        return new Wall(x, y);
    }
}
