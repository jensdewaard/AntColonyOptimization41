public class Ant {
    private Point position;
    private Route shortestPathFound;
    private Route routeTaken;
    private Route returnPath;
    private MovementStrategyInterface strategy;
    private boolean reachedEnd = false;
    private boolean stopped = false ;
    private int pathLength;

    private static final int PHEROMONE_DROPPED = 50;

    public Ant(Point start) {
        position = start;
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
        if (!reachedEnd) {
            Route.Direction direction = strategy.decideDirection(
                    position,
                    maze.getPossibleMoves1(position),
                    maze,
                    Route.invertDirection(routeTaken.peekLast())
            );
            routeTaken.takeStep(direction);
            position = maze.getNextPosition(position, direction);
            if (position.equals(maze.getEndPoint())) {
                reachedEnd = true;
                returnPath = routeTaken.reverse();
                pathLength = routeTaken.length();
            }
        } else { // hij is op de terugweg
        	Route.Direction returnDir = returnPath.popFirst();
        	Route.Direction initialDir = Route.invertDirection(returnDir);
            Move move = new Move(maze.getNextPosition(position, returnDir), initialDir);
        	position = maze.getNextPosition(position, returnDir);
            maze.addMovePheromone(position, move, 1000/pathLength);
            if (position.equals(maze.getStartPoint())) { // hij is weer bij het begin
                if (shortestPathFound == null || routeTaken.compareTo(shortestPathFound) < 0)
                    shortestPathFound = routeTaken.copy();
                routeTaken = new Route(position);
                reachedEnd = false;
            }
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
