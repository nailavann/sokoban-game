public enum Direction {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4),
    INVALID(5);
    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
