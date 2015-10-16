import java.util.ArrayList;

/**
 * This strategy will make the ant move in the first direction given.
 */
public class SimpleMovement implements MovementStrategyInterface {
    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Point> possibilities, Maze m, Route.Direction previous) {
        return current.getDirection(possibilities.get(0));
    }
}
