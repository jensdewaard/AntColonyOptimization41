import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WeightedPheromonesDecorator extends AbstractMovementDecorator {
    MovementStrategyInterface decorated;

    public WeightedPheromonesDecorator(MovementStrategyInterface parent) {
        this.decorated = parent;
    }

    @Override
    public Route.Direction decideDirection(Point current, ArrayList<Point> possibilities, Maze m, Route.Direction previous) {
        int sum = 0;
        HashMap<Point, Integer> chances = new HashMap<>();
        for(Point p : possibilities) {
            int chance = m.getPheromones(p)+1;
            chances.put(p, chance);
            sum += chance;
        }
        Random randomGenerator = new Random();
        float randomDouble = randomGenerator.nextFloat();
        for(Point p : possibilities) {
            float chance = ((float) chances.get(p)) / (float) sum;
            if(randomDouble < chance) return current.getDirection(p);
            randomDouble -= chance;
        }
        return decorated.decideDirection(current, possibilities, m, previous);
    }
}
