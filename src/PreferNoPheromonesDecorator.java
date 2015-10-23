import java.util.ArrayList;

/**
 * This decorator will make a strategy prefer directions that lead to positions
 * without pheromones.
 */
public class PreferNoPheromonesDecorator extends AbstractMovementDecorator {
    private MovementStrategyInterface decorated;

    public PreferNoPheromonesDecorator(MovementStrategyInterface parent) {
        this.decorated = parent;
    }

    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Move> possibilities, ArrayList<Point> points, Maze m, Route.Direction previous) {
        for(Move move : possibilities) {
            int pheromones = m.getMovePheromones(current, move);

            if(pheromones == 0)
                return move.getDirection();
        }
        return decorated.decideDirection(current, possibilities, points, m, previous);
    }
}
