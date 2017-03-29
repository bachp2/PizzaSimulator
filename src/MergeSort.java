/**
 * Created by bachp on 3/27/17.
 */
public class MergeSort {
    static int[] B = {2,3,6,4,1,23,42,1234,345,1,234,3,5};
    //implements bottom up approach
    public static void mergeSort(int[] B){
        int[] A = new int[B.length];
        for(int i = 0; i < B.length; i++){
            A[i] = B[i];
        }
        sort(0, B.length, A, B);
    }
    private static void sort(int low, int high, int[] A, int[] B){
        if(high - low < 2)                       // if run size == 1
            return;
        if(low <= high){
            int middle = (low+high)/2;
            sort(low, middle, A, B);
            sort(middle, high, A, B);
            merge(low, middle, high, A, B);
        }
    }
    private static void merge(int begin, int high, int end, int[] A, int[] B){
        int i = begin;
        int j = high;
        for(int k = 0; k < end; k++){
            if(i < high && (A[i] <= A[j] || j >= end)){
                B[k] = A[i++];
            }
            else{
                B[k] = A[j++];
            }
        }
    }
    public static void main(String[] args){
        mergeSort(B);
        for(int i  : B){
            System.out.println(i+" ");
        }
    }
}
