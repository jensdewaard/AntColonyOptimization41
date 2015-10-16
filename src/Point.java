import java.util.StringTokenizer;

public final class Point {
    private final int x, y;
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        String nextToken = tokenizer.nextToken();
        this.x = Integer.parseInt(nextToken.substring(0, nextToken.length() - 1));
        nextToken = tokenizer.nextToken();
        this.y = Integer.parseInt(nextToken.substring(0, nextToken.length() - 1));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Route.Direction getDirection(Point p) {
        if(p.equals(new Point(x, y-1))) return Route.Direction.NORTH;
        if(p.equals(new Point(x, y+1))) return Route.Direction.SOUTH;
        if(p.equals(new Point(x-1, y))) return Route.Direction.WEST;
        if(p.equals(new Point(x+1, y))) return Route.Direction.EAST;
        throw new IllegalArgumentException("Points are not adjacent.");
    }

    public String toString() {
        return String.format("%d, %d;", x, y);
    }

    public boolean equals(Object o) {
        return o instanceof Point && this.x == ((Point) o).x && this.y == ((Point) o).y;
    }
}
