import java.util.*;

public class StringSorter {

    public static void selectionSort(String[] arr) {
        for(int i=0; i< arr.length ; i++)
        {
            String min = arr[i];
            int index = i;
            for(int j= i+1 ; j< arr.length ; j++)
            {
                if( compare(min, arr[j]) > 0)
                {
                    min = arr[j];
                    index = j;
                }
            }
            swap(arr, i, index);
        }
    }
    public static void insertionSort(String[] arr) {
        for(int i= 1 ; i<arr.length ; i++)
        {
            for(int j = i - 1; j>=0 && compare(arr[j],arr[j+1]) > 0; j--)
            {
                swap(arr, j, j+1);
            }
        }
    }
    public static void bubbleSort(String[] arr) {
        for(int i=0; i<arr.length-1 ; i++)
        {
            for(int j= 0; j<arr.length -i -1 ; j++)
            {
                if(compare(arr[j],arr[j+1]) > 0)
                    swap(arr,j,j+1);
            }
        }
    }
    public static void mergeSort(String[] arr) {
        String[] temp = MergeSort(arr);
        for(int i=0; i<arr.length; i++)
        {
            arr[i] = temp[i];
        }
    }

    public static String[] MergeSort(String[] arr)
    {
        if(arr.length <= 1 )
            return arr;
        else
        {
            return	Merge(MergeSort(Arrays.copyOfRange(arr,0, arr.length/2)),MergeSort(Arrays.copyOfRange(arr,arr.length/2 ,arr.length)));
        }

    }
    public static String[] Merge(String[] value1, String[] value2)
    {
        int length = value1.length + value2.length;
        String[] res = new String[length];
        int i = 0,j =0;
        while(i+j <length)
        {
            if(i == value1.length){
                while(j < value2.length)
                    res[i+j] = value2[j++];
                break;
            }
            else if(j == value2.length){
                while (i<value1.length)
                    res[i+j] = value1[i++];
                break;
            }

            if(compare(value1[i], value2[j]) <= 0)
                res[i+j] = value1[i++];
            else
                res[i+j] = value2[j++];
        }

        return res;
    }

    public static int compare(String a, String b)
    { // a is bigger
        for(int i = 0 ; i< a.length() && i< b.length() ; i++)
        {
            if(a.charAt(i) == b.charAt(i))
                continue;
            return a.charAt(i)-b.charAt(i);
        }
        return a.length() - b.length() ;
    }

    public static void swap(String[] arr, int i, int j)
    {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}