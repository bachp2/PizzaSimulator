import java.util.Random;
import java.util.Scanner;

/** PizzaManager Skeleton File
 *  CSS 162, Final Project
 * 
 *  This class is a starting point for your final project and is incomplete.
 *  Note that if there are any inconsistencies between this skeleton and
 *  the assignment description, the assignment description controls.
 * @author BachPhan
 * @version 3/4/2017
 *  Author: Rob Nash with edits by Johnny Lin
 */
public class PizzaManager {
    /*
     *  TODO: Data definitions here.  
     */
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private Random rand = new Random();
    private boolean isSortByCalories = false;
    /** 
     * The console interface is defined in the start method 
     * You can exit or extend the code below to accomplish all of 
     * the outcomes defined in the homework document
     */
    public void start() {
        char selection='q';
        
        Scanner foo = new Scanner(System.in);
        
        while(true) {
            System.out.println("======================================");
            displayAllPizzas();
            System.out.println("======================================");
            System.out.println();
            displayInstructions();

            //accept string
            String select = foo.next();
            if(select.length() == 1)
                selection = select.charAt(0);
            else selection = 'k';
                //intentionally choose a wrong input
             //foo.nextChar() doesn't exist, so now what?
            
            switch(selection) {
                case 'A':    
                case 'a':    System.out.println("Adding a random pizza to the ArrayList<Pizza>.");
                                //todo:
                                addRandomPizza();
                                break;
                case 'H':    
                case 'h':    System.out.println("Adding one hundred random pizzas to the ArrayList<Pizza>.");
                                //todo:
                                int j = 0;
                                while(j < 100){
                                    addRandomPizza();
                                    j++;
                                }
                                break;                    
                case 'E':    
                case 'e':    System.out.println("Eating a fraction of a pizza. How much? (a/b)");
                                //todo:pizza eating code here
                                eatSomePizza(foo);
                                break;            
                case 'P':    
                case 'p':     System.out.println("Sorting pizzas by (P)rice");
                                //todo:
                                sortByPrice();
                                  break;    
                case 'S':    
                case 's':     System.out.println("Sorting pizzas by (S)ize");
                                 //todo:
                                sortBySize();
                                 break;          
                case 'C':    
                case 'c':      System.out.println("Sorting pizzas by (C)alories");
                                  //todo
                                sortByCalories();
                                  break;
                case 'B':
                case 'b':    System.out.println("(B)inary search over pizzas by calories(int).  Sorting first.  What calorie count are you looking for?");
                                //todo:
                                int cals = foo.nextInt();
                                if(!isSortByCalories) sortByCalories();
                                int a = binarySearchByCalories(cals);
                                if(a != -1) {
                                    System.out.println("======================================");
                                    System.out.println("index : " + a + " in ArrayList<Pizza>");
                                    System.out.println("======================================");
                                }
                                else System.out.println("Pizza not found");
                                break;
                case 'Q':
                case 'q':    System.out.println("(Q)uitting!" );
                                System.exit(0);
                                break;
                default:    System.out.println("Unrecognized input - try again");
            }
        }

    }

    /**
     * eat a random pizza in the pizzas list
     * @param keys Scanner
     */
    private void eatSomePizza(Scanner keys) {
        //todo:
        try {
            if(pizzas.isEmpty())
                throw new PizzaException("NO pizzas currently on counter");
            String[] s = keys.next().split("/");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int r = rand.nextInt(pizzas.counter);
            Pizza p = pizzas.get(r);
            p.eatSomePizza(new Fraction(a, b));
            if(p.getFractionRemaining().getFraction() == 0) {
                pizzas.remove(r);
            }
        }catch(PizzaException e){
            e.printStackTrace();
        }
         catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    /**
     * add pizza using randomize method
     */
    private void addRandomPizza() {
        //todo:
        pizzas.insert(new Pizza(), pizzas.counter++);
    }

    /**
     * display all pizzas in the counter
     */
    private void displayAllPizzas() {
        //todo:
        int i = 0;
        while(i < pizzas.counter){
            System.out.println(i+1+". "+pizzas.get(i++).toString());
        }
    }

    /**
     * sort pizzas by price using insertion sort
     */
    private void sortByPrice() {  
        //todo:
        for(int index = 1; index < pizzas.counter; index++){
            int j = index;
            while(j > 0 &&
                    pizzas.get(j-1).compareTo(pizzas.get(j)) > 0){
                pizzas.swap(j);
                j--;
            }
        }
    }

    /**
     * sort pizzas by size using insertion sort
     */
    private void sortBySize() {
        //todo:
        for(int index = 1; index < pizzas.counter; index++){
            int j = index;
            while(j > 0 &&
                    pizzas.get(j-1).compareToBySize(pizzas.get(j)) > 0){
                pizzas.swap(j);
                j--;
            }
        }
    }

    /**
     * sort pizza by calories using insertion sort
     */
    private void sortByCalories() {
        //todo:
        for(int index = 1; index < pizzas.counter; index++){
            int j = index;
            while(j > 0 &&
                    pizzas.get(j-1).compareToByCalories(pizzas.get(j)) > 0){
                pizzas.swap(j);
                j--;
            }
        }
        isSortByCalories = true;
    }

    /**
     * binary search by calories
     * @param cals
     * @return
     */
    private int binarySearchByCalories(int cals) {
        //todo:
        int lo = 0;
        int hi = pizzas.counter -1;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            if(cals < pizzas.get(mid).getCalories())
                hi = mid-1;
            else if(cals > pizzas.get(mid).getCalories())
                lo = mid+1;
            //return index
            else return mid;
        }
        //not found
        return -1;
    }
    
    /**
     * No need to edit functions below this line, unless extending the menu or
     * changing the instructions
     */
    private static final String instructions = "-----------------------\nWelcome to PizzaManager\n---------" +
            "--------------\n(A)dd a random pizza\nAdd a (H)undred random pizzas\n(E)at a fraction of a pizza\nSort pizzas " +
            "by (P)rice\nSort pizzas by (S)ize \nSort pizzas by (C)alories\n(B)inary Search pizzas by calories\n(Q)uit\n";

    private void displayInstructions() {
        System.out.println(instructions);    
    }

    /*
     * Notice the one-line main function.
     */
    public static void main(String[] args) {
        new PizzaManager().start();
    }
}
