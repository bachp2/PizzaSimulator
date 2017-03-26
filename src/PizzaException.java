/**
 * Exception class for Pizza class
 * @author Bach Phan
 * @version 02/16/2017
 */
public class PizzaException extends RuntimeException{
    /**
     * @param msg
     */
    public PizzaException(String msg){
        super(msg);
    }

    /**
     * empty constructor
     */
    public PizzaException(){
        super("this pizza is already sold");
    }
}
