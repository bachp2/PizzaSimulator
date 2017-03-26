import javax.management.RuntimeOperationsException;
import java.util.IllegalFormatException;

/*
 * This class should be a simple abstraction (i.e., a small class) that represents the ratio of two numbers.  
 *  There will be only two data elements, and only a few methods.  
 *  Note that it is required for your Fraction class to store your ratio in reduced form,
 *  so this is a feature to implement in your software.
 * @author Bach Phan
 * @date 01/1/2017
 */
public class Fraction implements Comparable {
    private int numerator;
    private int denominator;
    //indicates if the fraction is in its correct format(no / by 0)

    /**
     * Empty constructor
     */
    public Fraction() {
        numerator = 1;
        denominator = 1;
    }

    /*
     * if the denominator is zero the print error
     *  otherwise assign the instance variables after divide
     *  the inputs with found gcd value.
     *  also convert to positive fraction if both num and denom are less than zero
     * @param int a,b : numerator,denominator
     * @return Fraction constructor
     */
    public Fraction(int a, int b) throws ArithmeticException{
        try {
            if (b != 0) {
                if (a < 0 && b < 0) {
                    a *= -1;
                    b *= -1;
                }
                int gcd = gcd(a, b);
                this.numerator = a / gcd;
                this.denominator = b / gcd;
            } else {
                throw new ArithmeticException();
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    /**
     * copy constructor
     *
     * @param f
     */
    public Fraction(Fraction f) {
        this.numerator = f.numerator;
        this.denominator = f.denominator;
    }

    /*
     * check to see if two fraction are equal : this.equals(that)
     * @param Fraction other
     * @return boolean
     */
    public boolean equals(Object o) {
        if( !(o instanceof Fraction) ) return false;
        Fraction f = (Fraction) o;
        int a = this.numerator * f.getDenominator();
        int b = this.denominator * f.getNumerator();
        return a==b;
    }

    /**
     * @return numerator
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * @return denominator
     */
    public int getDenominator() {
        return this.denominator;
    }

    /*
     * @return String string form of a fraction 
     * eg. '1/2'
     * @Overload
     */
    public String toString() {
        if (this.getFraction() == 1) return "1";
        return String.format("%d/%d", numerator, denominator);
    }

    /*
     * Euler'gcd algo --wiki
     * work to find the greates common denominator
     * @param int a,b numerator and denominatior
     * @return int gcd
     */
    private int gcd(int a, int b) {
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    /**
     * arithmatic operation (addition and subtraction)
     *
     * @param f Fraction
     * @return
     */
    public Fraction add(Fraction f) {
        if(f.denominator == 0)
            throw new ArithmeticException();
        int numerator = this.numerator * f.denominator + this.denominator * f.numerator;
        int denominator = this.denominator * f.denominator;
        return new Fraction(numerator, denominator);
    }

    public Fraction sub(Fraction f) {
        if(f.denominator == 0)
            throw new ArithmeticException();
        int numerator = this.numerator * f.denominator - this.denominator * f.numerator;
        int denominator = this.denominator * f.denominator;
        return new Fraction(numerator, denominator);
    }

    /**
     * implement comparable interface
     *
     * @param o Object
     * @return void
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Fraction) {
            Fraction f = (Fraction) o;
            int f_num = f.numerator * this.denominator;
            int this_num = f.denominator * this.numerator;
            if (this_num > f_num) return 1;
            else if (this_num < f_num) return -1;
            else return 0;
        } else
            throw new RuntimeException("can not compare" +
                    " object of different type");
    }

    /**
     * @return fraction :: double
     */
    public double getFraction() {
        return numerator * 1.0 / denominator;
    }

    public static void main(String[] args) {

        try {
            Fraction f = new Fraction(3, 7);
            Fraction f2 = new Fraction(-6, 2);
            Fraction f6 = new Fraction(4, 0);
            Fraction f3 = new Fraction();
            System.out.println(f3.equals(f3));
            System.out.println(f3.equals(f));
            System.out.println(f3.equals(f2));
            System.out.println(f2.sub(f));
            System.out.println(f2.add(f));
            System.out.println(f2.compareTo(f));
            System.out.println(f3.compareTo(f3));
            //done
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
