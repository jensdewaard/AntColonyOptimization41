import java.util.ArrayList;

/**
 * This strategy will make the ant move in the first direction given.
 */
public class SimpleMovement implements MovementStrategyInterface {
    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Move> possibilities, ArrayList<Point> points, Maze m, Route.Direction previous) {
        return possibilities.get(0).getDirection();
    }
}
