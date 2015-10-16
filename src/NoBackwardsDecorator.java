import java.util.ArrayList;

public class NoBackwardsDecorator extends AbstractMovementDecorator {
    private MovementStrategyInterface parent;

    public NoBackwardsDecorator(MovementStrategyInterface movementStrategyInterface) {
        this.parent = movementStrategyInterface;
    }

    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Point> possibilities, Maze m, Route.Direction previous) {
        if(previous != null) {
            Point previousPoint = m.getNextPosition(current, previous);
            possibilities.remove(previousPoint);
        }
        return parent.decideDirection(current, possibilities, m, previous);
    }
}
