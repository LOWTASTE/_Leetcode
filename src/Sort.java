import java.util.Scanner;

public class Sort {
    static int partition(int[] arr, int low, int high, boolean reserve) {
        //以高位为轴
        /*int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;
                int temp  = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp  = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;*/

        //以低位为轴
        if (low == high)
            return 0;
        int pivot = arr[low];
        int pos = low;
        int temp;
        if (!reserve)
        {
            for (int i = low + 1;i <= high;i++){
                if (arr[i] < pivot){
                    pos++;
                    temp = arr[i];
                    arr[i] = arr[pos];
                    arr[pos] = temp;
                }
            }
            temp = arr[low];
            arr[low] = arr[pos];
            arr[pos] = temp;
            return pos;
        }
        else {
            for (int i = low + 1;i <= high;i++){
                if (arr[i] > pivot){
                    pos++;
                    temp = arr[i];
                    arr[i] = arr[pos];
                    arr[pos] = temp;
                }
            }
            temp = arr[low];
            arr[low] = arr[pos];
            arr[pos] = temp;
            return pos;
        }

    }

    static void quickSort(int[] arr, int low, int high, boolean reserve)
    {
        if (low < high)
        {
            int mid = partition(arr, low, high, reserve);
            quickSort(arr, low, mid - 1, reserve);  // Before pi
            quickSort(arr, mid + 1, high, reserve); // After pi
        }
    }

    static void bubbleSort(int[] arr)
    {
        for (int i = arr.length - 1;i >= 0;i--){
            for (int j = i - 1;j >= 0;j--){
                if (arr[i]<arr[j]){
                    int temp  = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    public static void shellSort(int[] arr)
    {
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    public static void insertionSort(int[] arr)
    {
        if (arr.length == 0)
            return;
        int current;
        for (int i = 0; i < arr.length - 1; i++) {
            current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }


    public static void main(String[] args) {
        int[] arr_q = {20,85,30,68,57,46,81,54,18,99};
        quickSort(arr_q,0,arr_q.length-1,false);
        System.out.println("\n快排排序如下F：");
        for (int j : arr_q) {
            System.out.print(j);
        }
        quickSort(arr_q,0,arr_q.length-1,true);
        System.out.println("\n快排排序如下T：");
        for (int j : arr_q) {
            System.out.print(j);
        }
        int[] arr_b = {20,85,30,68,57,46,81,54,18,99};
        bubbleSort(arr_b);
        System.out.println("\n冒泡排序如下：");
        for (int j : arr_b) {
            System.out.print(j);
        }
        int[] arr_i = {20,85,30,68,57,46,81,54,18,99};
        insertionSort(arr_i);
        System.out.println("\n插入排序如下：");
        for (int j : arr_i) {
            System.out.print(j);
        }
        Scanner scanner =new Scanner(System.in);
        int output = scanner.nextInt();
        System.out.println(output);
        int[] arr_input = new int[5];
        for (int i = 0; i < 5;i++){
            arr_input[i]=scanner.nextInt();
        }
        quickSort(arr_input,0,arr_input.length-1,false);
        for (int j : arr_input) {
            System.out.print(j);
        }
    }
}
