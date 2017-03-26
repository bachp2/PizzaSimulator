import java.awt.*;

public class Square extends Shape implements Cloneable {

    private int sideLength;

    /**
     * constructor
     *
     * @param x position
     * @param y position
     */
    public Square(int x, int y) {
        super(x, y);
    }

    /**
     * getSide:  Add this getter method
     *
     * @return sideLength
     */
    public int getSide() {
        return sideLength;
    }

    /**
     * setSide:  Add this setter method
     *
     * @param side
     */
    public void setSide(int side) {
        this.sideLength = side;
    }

    /**
     * @return name of shape String
     */
    public String toString() {
        return "Square";
    }

    /**
     * get area of the shape
     *
     * @return area double
     */
    public double getArea() {
        return sideLength * sideLength;
    }

    /**
     * return deep clone of this object
     *
     * @return Square
     */
    public Square clone() {
        Square square = new Square(getX(), getY());
        square.setSide(getSide());
        return square;
    }

    /**
     * draw graphics
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.fill3DRect(getX(), getY(), sideLength, sideLength, false);
    }
}
