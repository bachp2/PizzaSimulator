/**
 * Created by bachp on 3/27/17.
 */
public class MergeSort {
    static int[] B = {2,-4,5,3,56,124,123,56756,567,123,45,234,546,435,345,24,23,24,26,54,64};
    //implements bottom up approach
    public static void mergeSort(int[] B){
        sort(0, B.length, B);
    }
    private static void sort(int low, int high, int[] B){
        if((high-low)>1){
            int middle = (low+high)/2;
            sort(low, middle, B);
            sort(middle, high, B);
            merge(low, middle, high, B);
        }
    }
    private static void merge(int low, int middle, int high, int[] B){
        int i = low;
        int j = middle;
        int k = 0;
        int[] temp = new int[high - low];
        while(i < middle && j < high){
            if(B[i] < B[j]){
                temp[k++] = B[i++];
            }
            else{
                temp[k++] = B[j++];
            }
        }
        while(i < middle){
            temp[k] = B[i++];
            k++;
        }
        while(j < high){
            temp[k] = B[j++];
            k++;
        }
        for(k = 0; k < high-low; k++){
            B[low + k] = temp[k];
        }
    }
    public static void main(String[] args){
        mergeSort(B);
        for(int i  : B){
            System.out.print(i+",");
        }
    }
}
