package model.domains;


/**
 * Point Class - simple class that contains 2 x,y ints which represent position of a point.
 */
public class Point {
	
	public int x,y;

	/**
	 * Point C'tor - Sets the data members according to the given parameters:
	 * @param x (int)
	 * @param y (int)
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Point C'tor that gets a point and sets x and y to the point.x and point.y
	 * @param p
	 */
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
}
