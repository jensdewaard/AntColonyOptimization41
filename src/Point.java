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

    public String toString() {
        return String.format("%d, %d;", x, y);
    }
}
