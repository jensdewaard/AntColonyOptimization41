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
		return o instanceof Point && this.point == ((Move) o).point && this.dir == ((Move) o).dir;
	}
	
	public int hashCode() {
        int result = point;
        result = 31 * result + y;
        return result;
    }
}
