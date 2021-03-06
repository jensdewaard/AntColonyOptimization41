import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WeightedPheromonesDecorator extends AbstractMovementDecorator {
    MovementStrategyInterface decorated;

    public WeightedPheromonesDecorator(MovementStrategyInterface parent) {
        this.decorated = parent;
    }


    
    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Move> possibilities, ArrayList<Point> points, Maze m, Route.Direction previous) {
        int sum = 0;
        ArrayList<Move> actualPossibilities = new ArrayList<Move>();
        HashMap<Move, Integer> chances = new HashMap<>();
        for(Move move : possibilities) {
        	Point next = m.getNextPosition(current, move.getDirection());
        	if(!points.contains(next)) {
        		actualPossibilities.add(move);
        	}
        }
        if(actualPossibilities.size() < 1) {
        	actualPossibilities = possibilities;
        }
        for(Move move : actualPossibilities) {
        	int pheromones = m.getMovePheromones(current, move)+1;
        	chances.put(move, pheromones);
        	sum += pheromones;
        }
        Random randomGenerator = new Random();
        float randomDouble = randomGenerator.nextFloat();
        for(Move move : actualPossibilities) {
            float chance = ((float) chances.get(move)) / (float) sum;
            if(randomDouble < chance) return move.getDirection();
            randomDouble -= chance;
        }
        return decorated.decideDirection(current, possibilities, points, m, previous);
    }
}
