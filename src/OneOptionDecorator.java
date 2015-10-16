import java.util.ArrayList;

public class OneOptionDecorator extends AbstractMovementDecorator {
    private MovementStrategyInterface parent;

    public OneOptionDecorator(MovementStrategyInterface parent) {
        this.parent = parent;
    }

    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Point> possibilities, Maze m, Route.Direction previous) {
        if(possibilities.size() == 1) {
            System.out.println("Only one option, taking that one!");
            current.getDirection(possibilities.get(0));
        }
        return parent.decideDirection(current, possibilities, m, previous);
    }
}
