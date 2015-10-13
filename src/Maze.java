import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private final boolean[][] grid;
    private final Point startPoint;
    private final Point endPoint;
    private final int width;
    private final int height;

    public Maze(final File mazeFile, final File coordinateFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(mazeFile);
        width = scanner.nextInt();
        height = scanner.nextInt();
        grid = new boolean[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[j][i] = scanner.nextInt() == 1;
            }
        }
        scanner = new Scanner(coordinateFile);
        // We require the substrings because each number is followed
        // by either a , or a ;
        String nextToken = scanner.next();
        int startX = Integer.parseInt(nextToken.substring(0, nextToken.length() - 1));
        nextToken = scanner.next();
        int startY = Integer.parseInt(nextToken.substring(0, nextToken.length() - 1));
        nextToken = scanner.next();
        int endX = Integer.parseInt(nextToken.substring(0, nextToken.length() - 1));
        nextToken = scanner.next();
        int endY = Integer.parseInt(nextToken.substring(0, nextToken.length() - 1));

        startPoint = new Point(startX, startY);
        endPoint = new Point(endX, endY);
    }

    public Point getStartPoint() {
        return this.startPoint;
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public boolean isPassable(Point p) {
        return grid[p.getX()][p.getY()];
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
}
