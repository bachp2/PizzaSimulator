/**
 * Created by bachp on 3/27/17.
 */
public class MergeSort {
    static int[] B = {2,-4};
    //implements bottom up approach
    public static void mergeSort(int[] B){
        int[] A = new int[B.length];
        for(int i = 0; i < B.length; i++){
            A[i] = B[i];
        }
        sort(0, B.length - 1, A, B);
    }
    private static void sort(int low, int high, int[] A, int[] B){
        if(low < high){
            int middle = (low+high)/2;
            sort(low, middle, A, B);
            sort(middle + 1, high, A, B);
            merge(low, middle, high, A, B);
        }
    }
    private static void merge(int begin, int middle, int end, int[] A, int[] B){
        int i = begin;
        int j = middle + 1;
        for(int k = begin; k <= end; k++){
            if(i <= middle && (j > end || A[i] <= A[j])){
                B[k] = A[i];
                i++;
            }
            else{
                B[k] = A[j];
                j++;
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
