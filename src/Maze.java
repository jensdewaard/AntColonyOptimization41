import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private final boolean[][] grid;
    private final Point startPoint;
    private final Point endPoint;

    public Maze(final File mazeFile, final File coordinateFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(mazeFile);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        grid = new boolean[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[j][i] = scanner.nextInt() == 1;
            }
        }
        scanner = new Scanner(coordinateFile);
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();
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
}
