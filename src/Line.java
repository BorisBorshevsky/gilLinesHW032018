/**
 * Line program will create a line between two points.
 * the program can calculate the line length, return 'middle' 'start' and 'end' points,
 * and can check if two lines intersect or overlap.
 *
 * @author Gil kagan.
 * @since 08.04.18
 */
public class Line {
    private Point p1;
    private Point p2;

    /**
     * constructor.
     *
     * @param start 'x' and 'y' values of the start of the line point.
     * @param end   'x' and 'y' values of the end of the line point.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * constructor.
     *
     * @param x1 first point 'x' value.
     * @param y1 first point 'y' value.
     * @param x2 second point 'x' value.
     * @param y2 second point 'y' value.
     */
    public Line(double x1, double y1,
                double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * calculate the length of the line.
     *
     * @return length of the line.
     */
    public double length() {
        // used distance method from Point class.
        return this.p1.distance(p2);
    }

    /**
     * calculates the mid point of the line, and returns it.
     *
     * @return middle point of the line.
     */
    public Point middle() {
        double midY = (this.p1.getY() + this.p2.getY()) / 2.0;
        double midX = (this.p1.getX() + this.p2.getX()) / 2.0;
        // returns the mid point.
        return new Point(midX, midY);
    }

    /**
     * returns the point where the line starts.
     *
     * @return first point.
     */
    public Point start() {
        return this.p1;
    }

    /**
     * returns the point where the line ends.
     *
     * @return last point.
     */
    public Point end() {
        return this.p2;
    }

    /**
     * checks if two lines intersect, using the intersectingWith method.
     *
     * @param other Line object.
     * @return 'true' if the lines intersect, 'false' otherwise.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * returns the max value of 'x' or 'y' of the line.
     *
     * @param x1 'x' or 'y' value of the first point.
     * @param x2 'x' or 'y' value pf the second point.
     * @return max value of 'x' or 'y'.
     */
    public double maxVal(double x1, double x2) {
        if (x1 > x2) {
            return x1;
        } else {
            return x2;
        }
    }

    /**
     * returns min value of the line -'x' or 'y'.
     *
     * @param x1 'x' or 'y' value of the first point.
     * @param x2 'x' or 'y' value pf the second point.
     * @return min value.
     */
    public double minVal(double x1, double x2) {
        if (x1 < x2) {
            return x1;
        } else {
            return x2;
        }
    }

    /**
     * calculates the slope of the line.
     *
     * @param line Line object.
     * @return slope of the line.
     */
    public double calcSlope(Line line) {
        return (line.p1.getY() - line.p2.getY()) / (line.p1.getX() - line.p2.getX());
    }


    public Point intersectionWith(Line other) {
        // both lines are vertical-no slope, and they are parallel.
        if (this.p1.getX() == this.p2.getX() && other.p1.getX() == other.p2.getX()) {
            return null;
        }
        if (vertical(this, other) != null) {
            new Point(vertical(this, other).getX(), vertical(this, other).getY());
        } else if (this.p1.getX() != this.p2.getX() && other.p1.getX() != other.p2.getX()) {
            Point p = findIntersection(other);
            if (this.isDotOnLine(p) != null && other.isDotOnLine(p) != null) {
                return p;
            }
        }
        return null;
    }

    public Point isDotOnLine(Point p) {
        // if the 'x' value is in range of the first segment, proceed.
        if (minVal(this.p1.getX(), this.p2.getX()) <= p.getX() && p.getX()
                <= maxVal(this.p1.getX(), this.p2.getX())) {
            if (minVal(this.p1.getY(), this.p2.getY()) <= p.getY() && p.getY()
                    <= maxVal(this.p1.getY(), this.p2.getY())) {
                return p;
            }
        }
        return null;
    }

    public Point vertical(Line l1, Line l2) {
        if (l1.p1.getX() == l1.p2.getX()) {
            // calculate slope of the second line.
            double slope2 = calcSlope(l2);
            // y = ax + b(equation of the other line).
            double b2 = l2.p1.getY() - (slope2 * l2.p1.getX());
            // point of intersection - y value.
            double intersectionY = (slope2 * this.p1.getX()) + b2;
            Point inter = new Point(l1.p1.getX(), intersectionY);
            if (l2.isDotOnLine(inter) != null) {
                return inter;
            }
            // if only the second line is vertical.
        } else if (l2.p1.getX() == l2.p2.getX()) {
            // calculates the slope of the first line.
            double slope1 = calcSlope(l1);
            double b1 = l1.p1.getY() - (slope1 * l1.p1.getX());
            double intersectionY = (slope1 * l2.p1.getX()) + b1;
            Point inter = new Point(l2.p1.getX(), intersectionY);
            if (l1.isDotOnLine(inter) != null) {
                return inter;
            }
        }
        return null;
    }

    public Point findIntersection(Line other) {
        double slope1 = calcSlope(this);
        double slope2 = calcSlope(other);
        if (slope1 == slope2) {
            return null;
        }
        // y = ax + b --> b = y - ax.
        double b1 = this.p1.getY() - (slope1 * this.p1.getX());
        double b2 = other.p1.getY() - (slope2 * other.p1.getX());
         /*
        slope1 * x0 + b1 = slope2 * x0 + b2
        slope1 * x0 - slope2 * x0 = b2 - b1
        x0(slope1 - slope2) = b2 - b1
        x0 = (b2 - b1) / (slope1 - slope2)
        */
        double x0 = (b2 - b1) / (slope1 - slope2);
        double intersectionY = (slope1 * x0) + b1;
        return new Point(x0, intersectionY);
    }


    /**
     * checks if the lines are equal.
     *
     * @param other Line object.
     * @return 'true', if the lines are equal, 'false' otherwise.
     */
    public boolean equals(Line other) {
        return (this.p1.equals(p2) && other.p1.equals(p2));
    }
}

