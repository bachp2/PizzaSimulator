/**
 * Created by bachp on 3/5/2017.
 * A super class for this class ingredient
 */
public class Ingredient extends Object{
    final String desc;
    private Money cost;
    private int calories;

    /**
     * constructor
     * @param desc String
     * @param cost  Money
     * @param calories  int
     */
    Ingredient(String desc, Money cost, int calories) {
        this.desc = desc;
        this.cost = cost;
        this.calories = calories;
    }

    /**
     * @return cost Money
     */
    Money getCost(){
        final Money money = new Money(cost);
        return money;
    }

    /**
     * @return calories int
     */
    int getCalories(){
        final int cal = this.calories;
        return cal;
    }
}
