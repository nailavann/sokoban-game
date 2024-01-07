public class SkateBoardFeature implements FeatureBehavior {
    @Override
    public void applyFeature(Board board, Direction move) {
        int guardX = board.getGuard().getX();
        int guardY = board.getGuard().getY();
        while (!board.matrixLocationCheck(guardX + move.getDeltaX(), guardY + move.getDeltaY(), Location.WALL)
                && !board.matrixLocationCheck(guardX + move.getDeltaX(), guardY + move.getDeltaY(), Location.BOX)) {
            guardX += move.getDeltaX();
            guardY += move.getDeltaY();
        }
        board.hasSkateBoard = false;

        board.guardAndBoxPositionChange(guardX, guardY);
    }
}
