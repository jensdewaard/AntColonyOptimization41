import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static final int ITERATIONS = 5000;

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("usage: mazefile coordinatefile");
            return;
        }

        Maze maze;
        try {
            maze = new Maze(new File(args[0]), new File(args[1]));
        } catch (FileNotFoundException e) {
            System.err.println("Files not found");
            return;
        }
        AntManager manager = new AntManager(maze);
        manager.initiateMoves();
        for(int i = 0; i < ITERATIONS; i++) {
            manager.update();
            maze.evaporatePheromones();
        }
        System.out.println("Shortest route:");
        System.out.println(manager.getShortestRoute());

        manager.printAllRoutes();
    }
}
