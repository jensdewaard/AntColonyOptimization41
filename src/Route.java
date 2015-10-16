import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Route {
    private Point start;
    private Point end;
    private ArrayList<Direction> steps;

    public Route(Point start) {
        steps = new ArrayList<>();
        this.start = start;
        this.end = start;
    }

    public void takeStep(Direction dir) {
        steps.add(dir);
        switch (dir) {
            case NORTH:
                end = new Point(end.getX(), end.getY() - 1);
                break;
            case EAST:
                end = new Point(end.getX() + 1, end.getY());
                break;
            case SOUTH:
                end = new Point(end.getX(), end.getY() + 1);
                break;
            case WEST:
                end = new Point(end.getX() - 1, end.getY());
                break;
        }
    }

    public Route.Direction popFirst() {
        Route.Direction dir = steps.get(0);
        steps.remove(0);
        return dir;
    }

    public Route.Direction peekLast() {
        if(steps.size() > 0)
            return steps.get(steps.size() -1);
        return null;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(steps.size()).append(";").append(System.getProperty("line.separator"));
        builder.append(start.toString()).append(System.getProperty("line.separator"));
        for (Direction dir : steps) {
            builder.append(dir.ordinal()).append(";");
        }
        return builder.toString();
    }

    public enum Direction {
        EAST,
        NORTH,
        WEST,
        SOUTH
    }

    public Route reverse() {
        Route reversePath = new Route(end);
        for(int i = (steps.size() - 1); i >= 0; i--) {
            Direction dir = steps.get(i);
            switch (dir) {
                case NORTH:
                    reversePath.takeStep(Direction.SOUTH);
                    break;
                case EAST:
                    reversePath.takeStep(Direction.WEST);
                    break;
                case SOUTH:
                    reversePath.takeStep(Direction.NORTH);
                    break;
                case WEST:
                    reversePath.takeStep(Direction.EAST);
                    break;
            }
        }
        return reversePath;
    }

    public int compareTo(Object o) {
        if (!(o instanceof Route)) {
            throw new IllegalArgumentException("Object not of type Route");
        }
        if (this.steps.size() < ((Route) o).steps.size()) {
            return -1;
        }
        if (this.steps.size() > ((Route) o).steps.size()) {
            return 1;
        }
        return 0;
    }

    public Route copy() {
        Route route = new Route(start);
        route.steps.addAll(steps.stream().collect(Collectors.toList()));
        route.end = end;
        return route;
    }

    public static Direction invertDirection(Direction dir) {
        if(dir == null) return null;
        switch(dir) {
            case NORTH:
                return Direction.SOUTH;
            case EAST:
                return Direction.WEST;
            case SOUTH:
                return Direction.NORTH;
            case WEST:
                return Direction.EAST;
        }
        return null;
    }

    public Point getStart() {
        return this.start;
    }
}


