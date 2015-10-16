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
    public Route.Direction decideDirection(Point current, ArrayList<Point> possibilities, Maze m, Route.Direction previous) {
        for(Point p : possibilities) {
            int pheromones = m.getPheromones(p);

            if(pheromones == 0)
                return current.getDirection(p);
        }
        return decorated.decideDirection(current, possibilities, m, previous);
    }
}
