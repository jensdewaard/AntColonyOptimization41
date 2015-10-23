import java.util.ArrayList;

public class Ant {
    private Point position;
    private Route shortestPathFound;
    private ArrayList<Point> points;
    private Route routeTaken;
    private Route returnPath;
    private MovementStrategyInterface strategy;
    private boolean reachedEnd = false;
    private boolean stopped = false ;
    private int pathLength;

    private static final int PHEROMONE_DROPPED = 50;

    public Ant(Point start) {
        position = start;
        points = new ArrayList();
        points.add(position);
        routeTaken = new Route(start);
        strategy =
                new OneOptionDecorator(
                        new NoBackwardsDecorator(
                                new WeightedPheromonesDecorator(
                                        new SimpleMovement()
                                )

                        )
                )
        ;
    }

    public void update(Maze maze) {
    	
            Route.Direction direction = strategy.decideDirection(
                    position,
                    maze.getPossibleMoves1(position),
                    points,
                    maze,
                    Route.invertDirection(routeTaken.peekLast())
            );
            routeTaken.takeStep(direction);
            position = maze.getNextPosition(position, direction);
            points.add(position);
            if (position.equals(maze.getEndPoint())) {
            	if (shortestPathFound == null || routeTaken.compareTo(shortestPathFound) < 0) {
                    shortestPathFound = routeTaken.copy();
            	}
                pathLength = routeTaken.length();
                for(int i = 0; i < points.size() - 1; i++) {
                	Move move = new Move(points.get(i), points.get(i).getDirection(points.get(i + 1)));
                	maze.addMovePheromone(points.get(i), move, 200/pathLength);
                }
                routeTaken = new Route(maze.getStartPoint());
                position = maze.getStartPoint();
                points = new ArrayList<Point>();
                points.add(maze.getStartPoint());
            }
    }

    public Route getRoute() {
        return routeTaken;
    }

    public Route getShortestPathFound() {
        return shortestPathFound;
    }

    public Point getPosition() {
        return position;
    }

    public boolean getStopped() {
        return stopped;
    }

    public void reset() {
        stopped = false;
        reachedEnd = false;
        routeTaken = new Route(position);
    }
}
