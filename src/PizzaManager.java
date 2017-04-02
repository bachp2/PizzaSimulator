import java.util.Random;
import java.util.Scanner;

/**
 * PizzaManager Skeleton File
 * CSS 162, Final Project
 * <p>
 * This class is a starting point for your final project and is incomplete.
 * Note that if there are any inconsistencies between this skeleton and
 * the assignment description, the assignment description controls.
 *
 * @author BachPhan
 * @version 3/4/2017
 *          Author: Rob Nash with edits by Johnny Lin
 */
public class PizzaManager {
    /**
     * No need to edit functions below this line, unless extending the menu or
     * changing the instructions
     */
    private static final String instructions = "-----------------------\nWelcome to PizzaManager\n---------" +
            "--------------\n(A)dd a random pizza\nAdd a (H)undred random pizzas\n(E)at a fraction of a pizza\nSort pizzas " +
            "by (P)rice\nSort pizzas by (S)ize \nSort pizzas by (C)alories\n(B)inary Search pizzas by calories\n(Q)uit\n";
    /*
     *  TODO: Data definitions here.
     */
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private Random rand = new Random();
    private boolean isSort = false;

    /*
     * Notice the one-line main function.
     */
    public static void main(String[] args) {
        new PizzaManager().start();
    }

    /**
     * The console interface is defined in the start method
     * You can exit or extend the code below to accomplish all of
     * the outcomes defined in the homework document
     */
    public void start() {
        char selection = 'q';

        Scanner foo = new Scanner(System.in);

        while (true) {
            System.out.println("======================================");
            displayAllPizzas();
            System.out.println("======================================");
            System.out.println();
            displayInstructions();

            //accept string
            String select = foo.next();
            if (select.length() == 1)
                selection = select.charAt(0);
            else selection = 'k';
            //intentionally choose a wrong input
            //foo.nextChar() doesn't exist, so now what?

            switch (selection) {
                case 'A':
                case 'a':
                    System.out.println("Adding a random pizza to the ArrayList<Pizza>.");
                    //todo:
                    addRandomPizza();
                    break;
                case 'H':
                case 'h':
                    System.out.println("Adding one hundred random pizzas to the ArrayList<Pizza>.");
                    //todo:
                    int j = 0;
                    while (j < 100) {
                        addRandomPizza();
                        j++;
                    }
                    break;
                case 'E':
                case 'e':
                    System.out.println("Eating a fraction of a pizza. How much? (a/b)");
                    //todo:pizza eating code here
                    eatSomePizza(foo);
                    break;
                case 'P':
                case 'p':
                    System.out.println("Sorting pizzas by (P)rice");
                    //todo:
                    long startTime = System.nanoTime();
                    mergeSortByPrice(pizzas);
                    long estimatedTime = System.nanoTime() - startTime;
                    System.out.println("Time elapsed : " + estimatedTime + "ns");
                    break;
                case 'S':
                case 's':
                    System.out.println("Sorting pizzas by (S)ize");
                    //todo:
                    startTime = System.nanoTime();
                    mergeSortBySize(pizzas);
                    estimatedTime = System.nanoTime() - startTime;
                    System.out.println("Time elapsed : " + estimatedTime + "ns");
                    break;
                case 'C':
                case 'c':
                    System.out.println("Sorting pizzas by (C)alories");
                    //todo
                    startTime = System.nanoTime();
                    mergeSortByCalories(pizzas);
                    estimatedTime = System.nanoTime() - startTime;
                    System.out.println("Time elapsed : " + estimatedTime + "ns");
                    break;
                case 'B':
                case 'b':
                    System.out.println("(B)inary search over pizzas by calories(int).  Sorting first.  What calorie count are you looking for?");
                    //todo:
                    int cals = foo.nextInt();
                    if (!isSort) sortByCalories();
                    int a = binarySearchByCalories(cals);
                    if (a != -1) {
                        System.out.println("======================================");
                        System.out.println("index : " + a + " in ArrayList<Pizza>");
                        System.out.println("======================================");
                    } else System.out.println("Pizza not found");
                    break;
                case 'Q':
                case 'q':
                    System.out.println("(Q)uitting!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unrecognized input - try again");
            }
        }

    }

    /**
     * eat a random pizza in the pizzas list
     *
     * @param keys Scanner
     */
    private void eatSomePizza(Scanner keys) {
        //todo:
        try {
            if (pizzas.isEmpty())
                throw new PizzaException("NO pizzas currently on counter");
            String[] s = keys.next().split("/");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int r = rand.nextInt(pizzas.counter);
            Pizza p = pizzas.get(r);
            p.eatSomePizza(new Fraction(a, b));
            if (p.getFractionRemaining().getFraction() == 0) {
                pizzas.remove(r);
            }
        } catch (PizzaException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
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
        while (i < pizzas.counter) {
            System.out.println(i + 1 + ". " + pizzas.get(i++).toString());
        }
    }

    //==============================================================================
    //implements bottom up approach

    /**
     * merge sort implementation
     *
     * @param pizzas
     */
    public void mergeSortByPrice(ArrayList<Pizza> pizzas) {
        sortByPrice(0, pizzas.size(), pizzas);
        isSort = true;
    }

    /**
     * helper split method
     *
     * @param low
     * @param high
     * @param pizzas
     */
    private void sortByPrice(int low, int high, ArrayList<Pizza> pizzas) {
        if ((high - low) > 1) {
            int middle = (low + high) / 2;
            sortByPrice(low, middle, pizzas);
            sortByPrice(middle, high, pizzas);
            mergeSortByPrice(low, middle, high, pizzas);
        }
    }

    /**
     * helper merge method
     *
     * @param low
     * @param middle
     * @param high
     * @param pizzas
     */
    private void mergeSortByPrice(int low, int middle, int high, ArrayList<Pizza> pizzas) {
        int i = low;
        int j = middle;
        int k = 0;
        Pizza[] temp = new Pizza[high - low];
        while (i < middle && j < high) {
            if (pizzas.get(i).compareTo(pizzas.get(j)) < 0) {
                temp[k++] = pizzas.get(i++);
            } else {
                temp[k++] = pizzas.get(j++);
            }
        }
        while (i < middle) {
            temp[k] = pizzas.get(i++);
            k++;
        }
        while (j < high) {
            temp[k] = pizzas.get(j++);
            k++;
        }
        for (k = 0; k < high - low; k++) {
            pizzas.insert(temp[k], low + k);
        }
    }

    /**
     * sort pizzas by price using insertion sort
     */
    private void sortByPrice() {
        //todo:
        //implement merge sort
        for (int index = 1; index < pizzas.counter; index++) {
            int j = index;
            while (j > 0 &&
                    pizzas.get(j - 1).compareTo(pizzas.get(j)) > 0) {
                pizzas.swap(j);
                j--;
            }
        }
    }

    //==============================================================================
    //implements bottom up approach

    /**
     * merge sort by size
     *
     * @param pizzas ArrayList<Pizza>
     */
    public void mergeSortBySize(ArrayList<Pizza> pizzas) {
        sortBySize(0, pizzas.size(), pizzas);
        isSort = true;
    }

    /**
     * helper split method
     *
     * @param low    int
     * @param high   int
     * @param pizzas ArrayList<Pizza>
     */
    private void sortBySize(int low, int high, ArrayList<Pizza> pizzas) {
        if ((high - low) > 1) {
            int middle = (low + high) / 2;
            sortBySize(low, middle, pizzas);
            sortBySize(middle, high, pizzas);
            mergeSortBySize(low, middle, high, pizzas);
        }
    }

    /**
     * helper merge method
     *
     * @param low    int
     * @param middle int
     * @param high   int
     * @param pizzas ArrayList
     */
    private void mergeSortBySize(int low, int middle, int high, ArrayList<Pizza> pizzas) {
        int i = low;
        int j = middle;
        int k = 0;
        Pizza[] temp = new Pizza[high - low];
        while (i < middle && j < high) {
            if (pizzas.get(i).compareToBySize(pizzas.get(j)) < 0) {
                temp[k++] = pizzas.get(i++);
            } else {
                temp[k++] = pizzas.get(j++);
            }
        }
        while (i < middle) {
            temp[k] = pizzas.get(i++);
            k++;
        }
        while (j < high) {
            temp[k] = pizzas.get(j++);
            k++;
        }
        for (k = 0; k < high - low; k++) {
            pizzas.insert(temp[k], low + k);
        }
    }

    /**
     * sort pizzas by size using insertion sort
     */
    private void sortBySize() {
        //todo: implement merge sort
        //todo:
        for (int index = 1; index < pizzas.counter; index++) {
            int j = index;
            while (j > 0 &&
                    pizzas.get(j - 1).compareToBySize(pizzas.get(j)) > 0) {
                pizzas.swap(j);
                j--;
            }
        }
    }

    //==============================================================================
    //implements bottom up approach
    //todo check for work

    /**
     * merge sort by calories
     * @param pizzas ArrayList
     */
    public void mergeSortByCalories(ArrayList<Pizza> pizzas) {
        sortByCalories(0, pizzas.size(), pizzas);
        isSort = true;
    }

    /**
     * helper split method
     * @param low int
     * @param high int
     * @param pizzas ArrayList
     */
    private void sortByCalories(int low, int high, ArrayList<Pizza> pizzas) {
        if ((high - low) > 1) {
            int middle = (low + high) / 2;
            sortByCalories(low, middle, pizzas);
            sortByCalories(middle, high, pizzas);
            mergeSortByCalories(low, middle, high, pizzas);
        }
    }

    /**
     * helper merge method
     * @param low int
     * @param middle int
     * @param high int
     * @param pizzas ArrayList
     */
    private void mergeSortByCalories(int low, int middle, int high, ArrayList<Pizza> pizzas) {
        int i = low;
        int j = middle;
        int k = 0;
        Pizza[] temp = new Pizza[high - low];
        while (i < middle && j < high) {
            if (pizzas.get(i).compareToByCalories(pizzas.get(j)) < 0) {
                temp[k++] = pizzas.get(i++);
            } else {
                temp[k++] = pizzas.get(j++);
            }
        }
        while (i < middle) {
            temp[k] = pizzas.get(i++);
            k++;
        }
        while (j < high) {
            temp[k] = pizzas.get(j++);
            k++;
        }
        for (k = 0; k < high - low; k++) {
            pizzas.insert(temp[k], low + k);
        }
    }

    /**
     * ort pizzas by calories using insertion sort
     */
    private void sortByCalories() {
        //todo:
        for (int index = 1; index < pizzas.counter; index++) {
            int j = index;
            while (j > 0 &&
                    pizzas.get(j - 1).compareToByCalories(pizzas.get(j)) > 0) {
                pizzas.swap(j);
                j--;
            }
        }
        isSort = true;
    }
    //==============================================================================

    /**
     * binary search by calories
     *
     * @param cals
     * @return
     */
    private int binarySearchByCalories(int cals) {
        //todo:
        int lo = 0;
        int hi = pizzas.counter - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (cals < pizzas.get(mid).getCalories())
                hi = mid - 1;
            else if (cals > pizzas.get(mid).getCalories())
                lo = mid + 1;
                //return index
            else return mid;
        }
        //not found
        return -1;
    }

    private void displayInstructions() {
        System.out.println(instructions);
    }

    class MergeSort {
        //implements bottom up approach
        public void mergeSort(ArrayList<Pizza> pizzas) {
            sort(0, pizzas.size(), pizzas);
        }

        private void sort(int low, int high, ArrayList<Pizza> pizzas) {
            if ((high - low) > 1) {
                int middle = (low + high) / 2;
                sort(low, middle, pizzas);
                sort(middle, high, pizzas);
                mergeByCalories(low, middle, high, pizzas);
            }
        }

        private void mergeByCalories(int low, int middle, int high, ArrayList<Pizza> pizzas) {
            int i = low;
            int j = middle;
            int k = 0;
            Pizza[] temp = new Pizza[high - low];
            while (i < middle && j < high) {
                if (pizzas.get(i).compareToByCalories(pizzas.get(j)) > 0) {
                    temp[k++] = pizzas.get(i++);
                } else {
                    temp[k++] = pizzas.get(j++);
                }
            }
            while (i < middle) {
                temp[k] = pizzas.get(i++);
                k++;
            }
            while (j < high) {
                temp[k] = pizzas.get(j++);
                k++;
            }
            for (k = 0; k < high - low; k++) {
                pizzas.insert(temp[k], low + k);
            }
        }

        public void main(String[] args) {
            mergeSort(pizzas);
            for (Pizza i : pizzas) {
                System.out.print(i + ",");
            }
        }
    }
}
