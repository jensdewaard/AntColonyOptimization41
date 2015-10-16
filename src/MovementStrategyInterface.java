import java.util.ArrayList;

public interface MovementStrategyInterface
{
    Route.Direction decideDirection(Point current, ArrayList<Point> possibilities, Maze m, Route.Direction previous);
}
