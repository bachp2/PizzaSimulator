import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList-like Interface structure that mimic original Java
 * ArrayList class.
 * List of methods available to use are :
 * void insert(Object, int index):  Add the object at the specified index.
 * Object remove(int index):  Remove and return the object at the specified index.
 * (This should behave like the corresponding method in Java's built-in ArrayList class; see Savitch's description.)
 * int size()
 * String toString()
 * boolean isEmpty()
 * int indexOf(Object):  Returns -1 if not found
 * boolean equals(Object):  Compare sizes and elements in the data structure.
 * Object get(int index):  Returns the object at index specified.
 * Created by bachp on 1/20/2017.
 */
public class ArrayList<T> implements Iterable<T> {
    protected Object[] arr;
    protected int counter = 0;

    /**
     * empty constructor
     */
    public ArrayList() {
        arr = new Object[100];
    }

    /**
     * constructor to set capacity of array
     *
     * @param capacity
     */
    public ArrayList(int capacity) {
        arr = new Object[capacity];
    }

    /**
     * size of ArrayList
     *
     * @return int
     */
    public int size() {
        return this.counter;
    }

    /**
     * self-explanatory, check if the List is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (counter == 0);
    }

    /**
     * get T object from a given index
     *
     * @param index
     * @return T
     * @throws NullPointerException      if the list is empty
     * @throws IndexOutOfBoundsException if arg's index exceed counter
     */
    public T get(int index) {
        try {
            if (this.isEmpty()) throw new NullPointerException();
            if (index < counter && index >= 0) return (T) arr[index];
            else throw new IndexOutOfBoundsException();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    normal insert method to insert object with a given index
    the index passing through the parameter have to be equal or smaller than the instance counter
    @param Object a, int i : object element and index
    @return void
     */
    public void insert(T a, int i) {
        try {
            if (i <= counter && i >= 0) {
                if (counter == arr.length - 1)
                    expand(5);
                //change the number inside () to change the sizing factor
                arr[i] = a;
                if (i == counter)
                    counter++;
            } else throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /*
     * Remove an object and shift subsequent objects to the left to refill the array
     * @param int index : index in the array
     * @return Object : whatever object in the array
     */
    public T remove(int index) {
        try {
            if(isEmpty()) throw new IndexOutOfBoundsException();
            if (index <= counter && index >= 0) {
                Object[] new_arr = new Object[this.arr.length];

                int j = 0;
                for (int i = 0; i < counter; i++) {
                    if (i == index) {
                        j += 1;
                    }
                    new_arr[i] = arr[j];
                    j++;
                }
                arr = new_arr;
                counter--;
                return (T) arr[index];
            } else
                throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * compare ArrayList with conditions that:
     * arg is of the same type
     * arg has the same size
     * arg has the same items via 'equals' method
     *
     * @param other
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ArrayList))
            return false;
        ArrayList AL_other = (ArrayList) other;
        if (this.size() == AL_other.size()) {
            for (int i = 0; i < this.counter; i++) {
                if (this.get(i).equals(AL_other.get(i)))
                    return false;
            }
        } else return false;
        return true;
    }

    /**
     * return index of a given Object in the list
     *
     * @param a
     * @return int
     */
    public int indexOf(T a) {
        for (int i = 0; i < this.counter; i++) {
            if (arr[i].equals(a))
                return i;
        }
        return -1;
    }

    /**
     * toString method of this class
     *
     * @return String
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < counter; i++) {
            output += String.format("%s, ", arr[i]);
        }
        if (output.length() != 0)
            output = "[ " + output.substring(0, output.length() - 2) + " ]";
        else output = "[ ]";
        return output;
    }

    /*
     *  a helper method to dynamically expand the instance array to accommodate the size of data
     *  user can set the factor of multiple by passing integer into the parameter
     */
    private void expand(int factor) {
        Object[] new_arr = new Object[arr.length * factor];
        int i = 0;
        while (i < this.counter) {
            new_arr[i] = arr[i];
            i++;
        }
        this.arr = new_arr;
    }

    /**
     * Implement Iterable
     *
     * @return
     */
    @Override
    public ArrayListIterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Inner class implement Iterator interface
     * implement Iterator
     */
    public class ArrayListIterator<T> implements Iterator {
        private int cnt;

        /**
         * empty constructor
         */
        public ArrayListIterator() {
            this.cnt = 0;
        }

        /**
         * return true is there is next element in the chain
         *
         * @return boolean
         */
        public boolean hasNext() {
            return this.cnt < counter;
        }

        /**
         * return next object
         *
         * @return T
         */
        public T next() {
            if (this.hasNext()) {
                return (T) arr[cnt++];
            }
            throw new NoSuchElementException();
        }

        /**
         * not implemented for this class
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * swap method to be used for insertion sort
     * @param i int
     */
    public void swap(int i) {
        T temp = this.get(i);
        this.insert(this.get(i - 1), i);
        this.insert(temp, i - 1);
    }

    public static void main(String[] args){
        ArrayList<String> arr = new ArrayList<>();
        arr.insert("asd", 0);
        arr.insert("asfasf", 1);
        arr.insert("keth", 2);
        arr.insert("random", 3);
        arr.insert("asd123", 4);
        arr.insert("asfas32f", 5);
        arr.insert("ketgfdgdfh", 6);
        //arr.insert("random", -17);
        //arr.insert("random", 17);
        System.out.println(arr);
        System.out.println(arr.size());
        System.out.println("index of asd : "+arr.indexOf("asd"));
        System.out.println("index of asdasasfasf : "+arr.indexOf("asdasasfasf"));
        arr.remove(2);
        System.out.println(arr);
        while(!arr.isEmpty()){
            arr.remove(0);
        }
        System.out.println(arr);
        System.out.println(arr.size());
        //arr.remove(0);
        System.out.println(arr);
        ArrayList<String> arr1 = new ArrayList<>();
        arr1.insert("asd",0);
        System.out.println(arr1);
        System.out.println(arr1.equals(arr));

    }
}
