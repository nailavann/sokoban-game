public class CreateFactory {
    public GameObject createCell(int x, int y, int type) {
        if (type == 1) {
            return new FloorFactory().create(x, y);
        } else if (type == 2) {
            return new WallFactory().create(x, y);
        } else if (type == 3) {
            return new GuardFactory().create(x, y);
        } else if (type == 4) {
            return new BoxFactory().create(x, y);
        } else if (type == 5) {
            return new GoalFactory().create(x, y);
        }
        return null;
    }
}
