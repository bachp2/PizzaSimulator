import java.io.Serializable;


/**
 * This class is used to track a USD amount consisting of two integers to manage dollars and cents.
 * All dollars and cents will be positive or 0, and cents will never exceed 99
 *
 * @author Bach Phan
 * @version 1/29/2017
 */
public class Money implements Comparable, Cloneable, Serializable {
    private int cents;//invariants range[0-99], positive at all time
    private int dollars;// invariants positive at all time

    /**
     * empty constructor
     */
    public Money() {
    }

    /**
     * constructor
     *
     * @param dollars amount
     * @param cents   amount less than 100
     */
    public Money(int dollars, int cents) {
        if (isValid(dollars, cents)) {
            this.cents = cents;
            this.dollars = dollars;
        } else throw new IllegalArgumentException("cents can not exceed 99 and " +
                "dollars and cents can't be negative");
    }

    /**
     * constructor for dollar only
     *
     * @param dollars amount
     */
    public Money(int dollars) {
        // 50 is a dummy value that make the return boolean solely depend
        // on the condition of 'dollars'
        if (isValid(dollars, 50))
            this.dollars = dollars;
        else throw new IllegalArgumentException("dollars can not be negative");
    }

    /**
     * copy constructor
     *
     * @param other Money
     */
    public Money(Money other) {
        this.cents = other.getCents();
        this.dollars = other.getDollars();
    }

    /**
     * setter for Money object
     *
     * @param dollars int
     * @param cents   int
     */
    public void setMoney(int dollars, int cents) {
        if (isValid(dollars, cents)) {
            setCents(cents);
            setDollars(dollars);
        } else
            throw new IllegalArgumentException("invalid dollars " +
                            "and cents amount");
    }

    /**
     * getter for instance variable dollars
     *
     * @return cents
     */
    public int getDollars() {
        return this.dollars;
    }

    /**
     * setter for instance variable dollars
     *
     * @param dollars int
     */
    public void setDollars(int dollars) {
        if (isValid(dollars, 50))
            this.dollars = dollars;
        else
            throw new IllegalArgumentException("Check " +
                                "for illegal input");
    }

    /**
     * getter for instance variable cents
     *
     * @return cents int
     */
    public int getCents() {
        return this.cents;
    }

    /**
     * setter for instance variable cents
     *
     * @param cents int
     */
    public void setCents(int cents) {
        if (isValid(2, cents))
            this.cents = cents;
        else
            throw new IllegalArgumentException
                    ("check for wrong input");
    }

    /**
     * get Money object
     *
     * @return double       money amount
     */
    public double getMoney() {
        return (double) (dollars + cents / 100.0);
    }

    /**
     * addition to dollars amount
     *
     * @param dollars int
     */
    public void add(int dollars) {
        if (dollars >= 0)
            this.dollars += dollars;
        else throw new IllegalArgumentException("dollars added has to be a positive number");
    }

    /**
     * addition method for Money class
     *
     * @param other Money
     * @return void
     */
    public void add(Money other) {
        int c = other.getCents();
        int d = other.getDollars();
        this.add(d, c);
    }

    /**
     * addition method for Money class
     *
     * @param dollars
     * @param cents
     * @return void
     */
    public void add(int dollars, int cents) {
        if (isValid(dollars, cents)) {
            this.dollars += dollars;
            this.cents += cents;
            if (this.cents > 99) {
                this.dollars += this.cents / 100;
                this.cents = this.cents % 100;
            }
        }
    }

    /**
     * check if input parameters dollars and cents are valid
     *
     * @param d int
     * @param c int
     * @return boolean
     */
    private boolean isValid(int d, int c) {
        return d >= 0 && c < 100 && c >= 0;
    }

    /**
     * compare Object type to a Money type
     *
     * @param other Object
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Money))
            return false;
        else if (this.cents == ((Money) other).getCents() && this.dollars == ((Money) other).getDollars())
            return true;
        return false;
    }

    /**
     * override toString method to apt with displaying Money class
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("$%.2f", getMoney());
    }

    /**
     * compare Money
     * @param o
     * @return int (positive, negative, zero)
     */
    @Override
    public int compareTo(Object o) {
        try {
            if (o instanceof Money) {
                if (this.getMoney() < ((Money) o).getMoney())
                    return -1;
                else if (this.getMoney() > ((Money) o).getMoney())
                    return 1;
                else return 0;
            }
            throw new IllegalArgumentException("Can not " +
                                "compare object of other type");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * clone Money to a new Object
     * @return
     */
    @Override
    public Money clone() {
        return new Money(this.dollars, this.cents);
    }

    public static void main(String[] args){
        try{
            //done
            Money money1 = new Money(8);
            System.out.println(money1);
            Money money2 = new Money(8);
            //Money money2 = money1.clone();
            System.out.println(money2);
            //#################################################################
            //Construct some money
            //Money money1 = new Money(-10);
            //Money money1 = new Money(10, 170);

            Money money3 = new Money(10);
            Money money4 = new Money(money1);
            money1.setMoney(30, 50);

            System.out.println("Money objects output:");
            System.out.println(money1);
            System.out.println(money2);

            //add method
            //money1.add(2, 10);
            money1.add(1, 89);
            //money2.add(money2);
            money2.add(money1);
            System.out.println(money2);

            //equals method
            Money money5 = new Money(money2);
            System.out.println(money3.equals("we came we saw we conquer"));
            System.out.println(money3.equals(money1));
            System.out.println(money3.equals(money2));
            System.out.println(money3.equals(money3));
            //Done!
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
