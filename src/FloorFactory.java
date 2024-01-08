public class FloorFactory implements BaseCellFactory {
    @Override
    public Floor create(int x, int y) {
        return new Floor(x, y);
    }
}
