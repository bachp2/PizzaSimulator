import java.awt.*;

/**
 * Created by bachp on 3/5/2017.
 */
public class Vegetable extends Ingredient {
    private Color myColor;

    /**
     * constructor
     *
     * @param desc String
     * @param cost Money
     * @param cal  int
     */
    Vegetable(String desc, Money cost, int cal, Color c) {
        super(desc, cost, cal);
        myColor = c;
    }

    /**
     * constructor
     *
     * @param desc String
     * @param cost Money
     * @param cal  int
     */
    Vegetable(String desc, Money cost, int cal) {
        super(desc, cost, cal);
    }

    /**
     * @return myColor Color
     */
    Color getColor() {
        return myColor;
    }

    /**
     * @param c Color
     */
    void setColor(Color c) {
        myColor = c;
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString(){
        return desc+" Color: "+myColor.toString();
    }

    /**
     * equals method
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Vegetable){
            Vegetable v = (Vegetable) o;
            return this.myColor.equals(v.myColor);
        }
        return false;
    }

}
