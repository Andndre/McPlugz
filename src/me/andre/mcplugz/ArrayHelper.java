package me.andre.mcplugz;

public class ArrayHelper {

    public static void selSort(int[] arr){
        int i = 0;
        while(i < arr.length - 1){
            int j = locOfSmallest(arr, i, arr.length - 1);
            swap(arr, i, j);
            i++;
        }
    }

    public static int locOfSmallest(int[] arr, int from, int to){
        int i = from;
        int j = i;

        while(i <= to){
            if(arr[i] < arr[j]){
                j = i;
            }
            i++;
        }
        return j;
    }

    public static void swap(int[] arr, int a, int b){
        if(a >= arr.length || b >= arr.length){
            System.out.println("out of bounds for index " + a + " and " + b);
            return;
        }
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
