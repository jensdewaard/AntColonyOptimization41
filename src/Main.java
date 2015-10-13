import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("usage: mazefile coordinatefile");
            return;
        }

        try {
            Maze maze = new Maze(new File(args[0]), new File(args[1]));
            System.out.print(maze.toString());
        } catch (FileNotFoundException e) {
            System.err.println("Files not found");
        }
    }
}
