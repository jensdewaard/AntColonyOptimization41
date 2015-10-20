import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Maze {
    private final double EVAPORATION_CONSTANT = 0.1;
    private final boolean[][] grid;
    private int[][] pheromones;
    private final Point startPoint;
    private final Point endPoint;
    private final int width;
    private final int height;
    private HashMap<Move, Integer> allMoves;

    public Maze(final File mazeFile, final File coordinateFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(mazeFile);
        width = scanner.nextInt();
        height = scanner.nextInt();
        grid = new boolean[width][height];
        pheromones = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[j][i] = scanner.nextInt() == 1;
                pheromones[j][i] = 0;
            }
        }
        scanner = new Scanner(coordinateFile);
        startPoint = new Point(scanner.nextLine());
        endPoint = new Point(scanner.nextLine());
    }

    public Point getStartPoint() {
        return this.startPoint;
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public int getPheromones(Point p) {
        return pheromones[p.getX()][p.getY()];
    }

    public boolean isPassable(Point p) {
        try {
            return grid[p.getX()][p.getY()];
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public ArrayList<Point> getPossibleMoves(Point p) {
        ArrayList<Point> points = new ArrayList<>();

        if (isPassable(new Point(p.getX() + 1, p.getY())))
            points.add(new Point(p.getX() + 1, p.getY()));
        if (isPassable(new Point(p.getX() - 1, p.getY())))
            points.add(new Point(p.getX() - 1, p.getY()));
        if (isPassable(new Point(p.getX(), p.getY() + 1)))
            points.add(new Point(p.getX(), p.getY() + 1));
        if (isPassable(new Point(p.getX(), p.getY() - 1)))
            points.add(new Point(p.getX(), p.getY() - 1));
        return points;
    }
    
    public void addPossibleMove(Point p) {
        ArrayList<Point> points = new ArrayList<>();

        if (isPassable(new Point(p.getX() + 1, p.getY())))
            allMoves.put(new Move(p, Route.Direction.EAST), 0);
        if (isPassable(new Point(p.getX() - 1, p.getY())))
        	allMoves.put(new Move(p, Route.Direction.WEST), 0);
        if (isPassable(new Point(p.getX(), p.getY() + 1)))
        	allMoves.put(new Move(p, Route.Direction.SOUTH), 0);
        if (isPassable(new Point(p.getX(), p.getY() - 1)))
        	allMoves.put(new Move(p, Route.Direction.NORTH), 0);
    }

    public Point getNextPosition(Point p, Route.Direction d) {
        Point nextPoint;
        switch (d) {
            case NORTH:
                nextPoint = new Point(p.getX(), p.getY() - 1);
                if (isPassable(nextPoint)) {
                    return nextPoint;
                } else {
                    throw new IllegalArgumentException("Can't move in given direction.");
                }
            case EAST:
                nextPoint = new Point(p.getX() + 1, p.getY());
                if (isPassable(nextPoint)) {
                    return nextPoint;
                } else {
                    throw new IllegalArgumentException("Can't move in given direction.");
                }
            case SOUTH:
                nextPoint = new Point(p.getX(), p.getY() + 1);
                if (isPassable(nextPoint)) {
                    return nextPoint;
                } else {
                    throw new IllegalArgumentException("Can't move in given direction.");
                }
            case WEST:
                nextPoint = new Point(p.getX() - 1, p.getY());
                if (isPassable(nextPoint)) {
                    return nextPoint;
                } else {
                    throw new IllegalArgumentException("Can't move in given direction.");
                }
            default:
                throw new IllegalArgumentException("Direction is not handled.");
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Start: ").append(startPoint.toString()).append(System.getProperty("line.separator"));
        builder.append("End: ").append(endPoint.toString()).append(System.getProperty("line.separator"));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append(grid[j][i] ? "1" : "0");
                builder.append(" ");
            }
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    public void addPheromone(Point point, int amount) {
        pheromones[point.getX()][point.getY()] += amount;
    }

    public void evaporatePheromones() {
        for(int i = 0; i < height; i++)  {
            for(int j = 0; j <width; j++) {
                pheromones[j][i] -= pheromones[j][i] > 0 ? 3 : 0;
            }
        }
    }
    
    public int getHeight() {
    	return height;
    }
    
    public int getWidth() {
    	return width;
    }
}
