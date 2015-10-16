public class Ant {
    private Point position;
    private Route shortestPathFound;
    private Route routeTaken;
    private Route returnPath;
    private MovementStrategyInterface strategy;
    private boolean reachedEnd = false;
    private boolean stopped = false;

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
        if (stopped) return;
        if (!reachedEnd) {
            Route.Direction direction = strategy.decideDirection(
                    position,
                    maze.getPossibleMoves(position),
                    maze,
                    Route.invertDirection(routeTaken.peekLast())
            );
            routeTaken.takeStep(direction);
            position = maze.getNextPosition(position, direction);
            if (position.equals(maze.getEndPoint())) {
                reachedEnd = true;
                returnPath = routeTaken.reverse();
            }
        } else { // hij is op de terugweg
            position = maze.getNextPosition(position, returnPath.popFirst());
            maze.addPheromone(position, PHEROMONE_DROPPED);
            if (position.equals(maze.getStartPoint())) { // hij is weer bij het begin
                if (shortestPathFound == null || routeTaken.compareTo(shortestPathFound) < 0)
                    shortestPathFound = routeTaken.copy();
                routeTaken = new Route(position);
                reachedEnd = false;
                stopped = true;
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