public class SprintFeature implements FeatureBehavior {

    @Override
    public void applyFeature(GameManager manager, Direction move) {
        manager.featureScore++;
        if (manager.featureScore == 10) {
            manager.hasSprint = false;
            manager.featureScore = 0;
        }
        int guardX = manager.board.getGuard().getX() + move.getDeltaX() * 2;
        int guardY = manager.board.getGuard().getY() + move.getDeltaY() * 2;

        manager.matrixPositionChange(guardX, guardY);
    }
}
