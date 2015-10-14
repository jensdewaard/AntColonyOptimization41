import java.util.ArrayList;

public class Route {
    private Point start;
    private ArrayList<Direction> steps;

    public Route() {
        steps = new ArrayList<>();
    }

    public void takeStep(Direction dir) {
        steps.add(dir);
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
}


