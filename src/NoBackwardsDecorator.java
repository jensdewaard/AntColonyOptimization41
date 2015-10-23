import java.util.ArrayList;

public class NoBackwardsDecorator extends AbstractMovementDecorator {
    private MovementStrategyInterface parent;

    public NoBackwardsDecorator(MovementStrategyInterface movementStrategyInterface) {
        this.parent = movementStrategyInterface;
    }

    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Move> possibilities, ArrayList<Point> points, Maze m, Route.Direction previous) {
        if(previous != null) {
        	for(int i = 0; i < possibilities.size(); i++) {
        		if(possibilities.get(i).getDirection() == previous) {
        			possibilities.remove(i);
        			break;
        		}
        	}
        }
        return parent.decideDirection(current, possibilities, points, m, previous);
    }
}
