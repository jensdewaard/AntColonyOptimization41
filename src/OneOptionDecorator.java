import java.util.ArrayList;

public class OneOptionDecorator extends AbstractMovementDecorator {
    private MovementStrategyInterface parent;

    public OneOptionDecorator(MovementStrategyInterface parent) {
        this.parent = parent;
    }

    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Move> possibilities, ArrayList<Point> points, Maze m, Route.Direction previous) {
        if(possibilities.size() == 1) {
            return possibilities.get(0).getDirection();
        }
        return parent.decideDirection(current, possibilities, points, m, previous);
    }
}
