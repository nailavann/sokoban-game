public class BoxFactory implements BaseCellFactory {
    @Override
    public Box create(int x, int y) {
        return new Box(x, y);
    }
}
