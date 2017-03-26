import java.util.Random;

/**
 * Created by Bach Phan on 3/4/2017.
 * PIzza class
 * @author BachPhan
 * @version 3/4/2017
 */
public class Pizza implements PizzaComparable {
    private final Fraction whole;
    private Fraction fractionRemaining;
    private ArrayList<Ingredient> listIngredients = new ArrayList<>();
    private Money cost;
    private int calories;
    private Shape shape;
    //#########################################
    private Random rand = new Random();

    private Ingredient[] sample = new Ingredient[8];

    /**
     * assign elements
     */ {
        sample[0] = new Sausage();
        sample[1] = new Goat();
        sample[2] = new Marinara();
        sample[3] = new Alfredo();
        sample[4] = new Mozzarella();
        sample[5] = new Pepper();
        sample[6] = new Pepperoni();
        sample[7] = new Olive();
    }
    //###########################################

    /**
     * empty constructor with randomize method
     */
    public Pizza() {
        whole = new Fraction();
        fractionRemaining = whole;
        cost = new Money(2);//base cost of dough
        calories = 544;//base calories of dough
        shape = rand.nextBoolean() ? new Circle(15, 15, 16) : new Square(15, 15);
        if (shape instanceof Square)
            ((Square) shape).setSide(25);
        int j = rand.nextInt(sample.length);
        int k = j - 1;
        while (j != 0) {
            Ingredient ingredient = sample[rand.nextInt(sample.length)];
            listIngredients.insert(ingredient, k - --j);
            cost.add(ingredient.getCost());
            calories += ingredient.getCalories();
        }
    }

    /**
     * constructor
     */
    public Pizza(Shape s, Ingredient... ingredients) {
        whole = new Fraction();
        fractionRemaining = whole;
        cost = new Money(2);//base cost of dough
        calories = 544;//base calories of dough
        setShape(s);
        //for loop
        int index = 0;
        for (Ingredient i : ingredients) {
            listIngredients.insert(i, index++);
            cost.add(i.getCost());
            calories += i.getCalories();
        }
    }

    /**
     * add one ingredient to the list of ingredients
     *
     * @param i
     */
    void addIngredient(Ingredient i) {
        listIngredients.insert(i, listIngredients.counter++);
        cost.add(i.getCost());
        calories += i.getCalories();
    }

    /**
     * eat pizza by subtract the fraction remaining with a fraction amt
     *
     * @param amt
     */
    void eatSomePizza(Fraction amt) {
        try {
            if(amt.getFraction() < 0)
                throw new PizzaException("amount can not be a negative fraction");
            Fraction f = new Fraction(fractionRemaining.sub(amt));
            if (f.getNumerator() == 0) {
                fractionRemaining = f;
                throw new PizzaException();
            } else if (f.getNumerator() > 0) {
                fractionRemaining = f;
                cost = double2Money(fraction2Double(f) * cost.getMoney());
            } else
                throw new PizzaException("fraction can not be negative");
        } catch (PizzaException e) {
            e.printStackTrace();
        } catch (ArithmeticException e){
            e.printStackTrace();
        }
    }

    /**
     * convert double to Money Object
     *
     * @param amt
     * @return m Money
     */
    private Money double2Money(double amt) {
        int d = (int) amt;
        int c = (int) ((amt - d) * 100);
        return new Money(d, c);
    }

    /**
     * get calories of pizza
     *
     * @return calories int
     */
    int getCalories() {
        return this.calories;
    }

    /**
     * get cost of pizza
     *
     * @return M
     */
    Money getCost() {
        return new Money(this.cost);
    }

    /**
     * @return double
     */
    double getRemainingArea() {
        return fraction2Double(fractionRemaining) * shape.getArea();
    }

    public String toString() {
        String out = "Pizza has a price: " +
                String.format("%s and calories: %d, Fraction remaining:%s and area left: %.2f and shape: %s",
                        cost, calories, fractionRemaining, fractionRemaining.getFraction() * shape.getArea(), shape.toString()
                );
        return out;
    }

    /**
     * Fraction o -> double
     *
     * @param f Fraction
     * @return
     */
    private double fraction2Double(Fraction f) {
        return f.getNumerator() * 1.0 / f.getDenominator();
    }

    /**
     * @return fractionRemaining
     */
    Fraction getFractionRemaining() {
        return fractionRemaining;
    }

    @Override
    /**
     * compare by cost
     */
    public int compareTo(Object o) {
        if (o instanceof Pizza) {
            Pizza p = (Pizza) o;
            return cost.compareTo(p.cost);
        } else
            throw new RuntimeException("can not take different type");
    }

    /**
     * compare by size
     *
     * @param o
     * @return
     */
    @Override
    public int compareToBySize(Object o) {
        if (o instanceof Pizza) {
            Pizza p = (Pizza) o;
            double a = this.getRemainingArea();
            double b = p.getRemainingArea();
            if (a > b) return 1;
            else if (a < b) return -1;
            else return 0;
        } else
            throw new RuntimeException("can not take different type");
    }

    /**
     * compare by calories
     *
     * @param o
     * @return
     */
    @Override
    public int compareToByCalories(Object o) {
        if (o instanceof Pizza) {
            Pizza p = (Pizza) o;
            if (this.calories > p.calories) return 1;
            else if (this.calories < p.calories) return -1;
            else return 0;
        } else
            throw new RuntimeException("can not take different type");
    }

    /**
     * getter for shape
     * @return Shape
     */
    public Shape getShape(){
        return (Shape) shape.clone();
    }

    /**
     * setter for shape of the pizza
     * @param s :: Shape
     */
    public void setShape(Shape s){
        shape = (Shape) s.clone();
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {

        try {
            Pizza p = new Pizza(new Circle(16), new Goat(), new Marinara(), new Pepperoni());
            System.out.println(p.cost);
            System.out.println(p.calories);
            System.out.println(p.fractionRemaining);
            System.out.println(p.shape);
            System.out.println(p.shape.getArea());
            System.out.println(p);
            System.out.println(p.listIngredients);
            p.addIngredient(new Pepperoni());
            System.out.println(p);
            //no toString method for each ingredient so I settle with printing only the reference address
            System.out.println(p.listIngredients);
            Pizza p1 = new Pizza(new Circle(16), new Goat());
            p1.eatSomePizza(new Fraction(1, 4));
            //p1.eatSomePizza(new Fraction(-1, 4));
            System.out.println(p1.compareToBySize(p));
            System.out.println(p1.compareTo(p));
            System.out.println(p1.compareToByCalories(p));
            System.out.println(p1);
            System.out.println(p);
            Random rand = new Random();
            int r = rand.nextInt(10);
            Pizza[] ps = new Pizza[r];
            while (r > 0) {
                ps[r - 1] = new Pizza();
                System.out.println(ps[r - 1]);
                r--;
            }
            //done
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
