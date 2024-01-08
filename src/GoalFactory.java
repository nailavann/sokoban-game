public class GoalFactory implements BaseCellFactory {
    @Override
    public Goal create(int x, int y) {
        return new Goal(x, y);
    }
}
