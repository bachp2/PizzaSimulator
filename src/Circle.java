import java.awt.*;

/**
 * Circle
 *
 * @author Bach Phan
 * @version 02/05/2017
 */
public class Circle extends Shape implements Cloneable {
    // instance variable
    private int rad;

    /**
     * empty constructor
     */
    public Circle(){
        super(0,0);
        rad = 16;
    }
    /**
     * Constructor for objects of class Circle
     */
    public Circle(int x, int y) {
        super(x, y);
        rad = 155;
    }

    /**
     * constructor
     * @param x x position
     * @param y y position
     * @param r radius
     */
    public Circle(int x, int y, int r) {
        // initialise instance variables
        super(x, y);
        if (r > 0) this.rad = r;
        else throw new IllegalArgumentException("non positive radius");

    }

    /**
     * clone object deep copy
     * @return Circle
     */
    public Circle clone(){
        return new Circle(this.getX(), this.getY(), this.getRad());
    }
    /**
     * constructor
     *
     * @param r : radius
     */
    public Circle(int r) {
        super(0, 0);
        if (r > 0) this.rad = r;
        else throw new IllegalArgumentException("non positive radius");
    }

    /**
     * return radius
     *
     * @return int
     */
    public int getRad() {
        return rad;
    }

    /**
     * set radius
     *
     * @param rad
     */
    public void setRad(int rad) {
        if (rad > 0) this.rad = rad;
        else throw new IllegalArgumentException("non positive radius");
    }

    /**
     * set area of the circle
     *
     * @return double
     */
    public double getArea() {
        return Math.PI * Math.pow(rad, 2);
    }

    /**
     * @param g : Graphics
     * @Override draw() in Shape
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(getX(), getY(), rad, rad);
    }

    /**
     * toString method
     */
    public String toString(){
        return "Circle";
    }
}
