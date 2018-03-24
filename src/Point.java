/**
 * Point program will create a point. can calculate distance to other point,
 * and check if they are equal.
 *
 * @author Gil Kagan.
 * @since 08.04.18
 */
public class Point {
    private double x;           // x value of the point.
    private double y;           // y value of the point.

    /**
     * constructor.
     *
     * @param x 'x' value of the point.
     * @param y 'y' value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getX will return the 'x' value.
     *
     * @return 'x' value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * getY will return the 'y' value.
     *
     * @return 'y' value of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * distance func will calculate the distance between two points.
     *
     * @param other some Point.
     * @return distance between the two points.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        // ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)).
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * equals func will check if two points are equal.
     *
     * @param other some Point.
     * @return 'true' if the points are equal, 'false' otherwise.
     */
    public boolean equals(Point other) {
        // checks if both x and y values are the same.
        return (this.x == other.getX() && this.y == other.getY());
    }
}
