import java.util.HashMap;


public class Move {
	private Point point;
	private Route.Direction dir;
	
	public Move(Point p, Route.Direction d) {
		point = p;
		dir = d;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public Route.Direction getDirection(){
		return dir;
	}
	
	public String toString() {
		String res = point.toString() + "   " + dir.toString();
		return res;
	}
	
	public boolean equals(Object o) {
		return ((o instanceof Move) && (point.equals(((Move) o).getPoint())) && (this.dir == ((Move) o).getDirection()));
	}
	
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + point.hashCode();
		hash = 31 * hash + (null == dir ? 0 : dir.hashCode());
		return hash;
    }
}
