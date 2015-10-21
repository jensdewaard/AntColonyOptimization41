import java.util.ArrayList;

public class AntManager {
    private Maze maze;
    private ArrayList<Ant> ants;

    private final int MAX_ANTS = 10;

    public AntManager(Maze maze) {
        this.maze = maze;
        this.ants = new ArrayList<>();
        for (int i = 0; i < MAX_ANTS; i++) {
            ants.add(new Ant(maze.getStartPoint()));
        }
        initiateMoves();
    }

    public void update() {
        for (Ant a : ants) {
            a.update(maze);
        }
    }

    public Route getShortestRoute() {
        Route shortest = ants.get(0).getShortestPathFound();
        for (Ant a : ants) {
            if (shortest == null || a.getRoute().compareTo(shortest) == -1) {
                shortest = a.getShortestPathFound();
            }
        }
        return shortest;
    }

    public void printAllRoutes() {
        for (Ant a : ants) {
            System.out.println(a.getRoute());
        }
    }
    
    public void initiateMoves() {
    	for(int i = 0; i < maze.getWidth(); i++) {
    		for(int j = 0; j < maze.getHeight(); j++) {
    			Point p = new Point(i, j);
    			maze.initiatePoints(p);
    			maze.addPossibleMove(p);
    		}
    	}
    }
}
