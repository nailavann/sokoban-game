public class SprintFeature implements FeatureBehavior {

    @Override
    public void applyFeature(Board board, Direction move) {
        board.featureScore++;
        if (board.featureScore == 10) {
            board.hasSprint = false;
            board.featureScore = 0;
        }
        int guardX = board.getGuard().getX() + move.getDeltaX() * 2;
        int guardY = board.getGuard().getY() + move.getDeltaY() * 2;

        board.guardAndBoxPositionChange(guardX, guardY);
    }
}
