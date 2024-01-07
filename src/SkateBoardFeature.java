public class SkateBoardFeature implements FeatureBehavior {
    @Override
    public void applyFeature(GameManager manager, Direction move) {
        int guardX = manager.board.getGuard().getX();
        int guardY = manager.board.getGuard().getY();
        while (!manager.board.matrixLocationCheck(guardX + move.getDeltaX(), guardY + move.getDeltaY(), Location.WALL)
                && !manager.board.matrixLocationCheck(guardX + move.getDeltaX(), guardY + move.getDeltaY(), Location.BOX)) {
            guardX += move.getDeltaX();
            guardY += move.getDeltaY();
        }
        manager.hasSkateBoard = false;

        manager.matrixPositionChange(guardX, guardY);
    }
}
