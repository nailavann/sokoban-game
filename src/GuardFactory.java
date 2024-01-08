public class GuardFactory implements BaseCellFactory {
    @Override
    public Guard create(int x, int y) {
        return new Guard(x, y);
    }
}
