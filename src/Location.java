public enum Location {
    FLOOR(1),
    WALL(2),
    GUARD(3),
    BOX(4),
    GOAL(5);
    private final int value;

    Location(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
