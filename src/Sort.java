

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

    public static void quickSort(int[] arr, int low, int high, boolean reserve) {
        if (low < high)
        {
            int mid = partition(arr, low, high, reserve);
            quickSort(arr, low, mid - 1, reserve);  // Before pi
            quickSort(arr, mid + 1, high, reserve); // After pi
        }
    }

    public static void bubbleSort(int[] arr) {
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

    public static void shellSort(int[] arr) {
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

    public static void insertionSort(int[] arr) {
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

    /**
     * 堆筛选，除了start之外，start~end均满足大顶堆的定义。
     * 调整之后start~end称为一个大顶堆。
     * @param arr 待调整数组
     * @param start 起始指针
     * @param end 结束指针
     */
    public static void heapAdjust(int[] arr, int start, int end) {
        int temp = arr[start];

        for(int i= 2 * start + 1; i<=end; i*=2) {
            //左右孩子的节点分别为2*i+1,2*i+2

            //选择出左右孩子较小的下标
            if(i < end && arr[i] < arr[i+1]) {
                i ++;
            }
            if(temp >= arr[i]) {
                break; //已经为大顶堆，=保持稳定性。
            }
            arr[start] = arr[i]; //将子节点上移
            start = i; //下一轮筛选
        }

        arr[start] = temp; //插入正确的位置
    }


    public static void heapSort(int[] arr) {
        if(arr == null || arr.length == 0)
            return ;

        //建立大顶堆
        for(int i = arr.length / 2; i>=0; i--) {
            heapAdjust(arr, i, arr.length-1);
        }

        for(int i=arr.length-1; i>=0; i--) {
            swap(arr, 0, i);
            heapAdjust(arr, 0, i-1);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




    public static void main(String[] args) {
//        int[] arr_q = {20,85,30,68,57,46,81,54,18,99};
//        quickSort(arr_q,0,arr_q.length-1,false);
//        System.out.println("\n快排排序如下F：");
//        for (int j : arr_q) {
//            System.out.print(j);
//        }
//        quickSort(arr_q,0,arr_q.length-1,true);
//        System.out.println("\n快排排序如下T：");
//        for (int j : arr_q) {
//            System.out.print(j);
//        }
        int[] arr_b = {20,85,30,68,57,46,81,54,18,99};
//        bubbleSort(arr_b);
//        System.out.println("\n冒泡排序如下：");
//        for (int j : arr_b) {
//            System.out.print(j);
//        }
//        int[] arr_i = {20,85,30,68,57,46,81,54,18,99};
//        insertionSort(arr_i);
//        System.out.println("\n插入排序如下：");
//        for (int j : arr_i) {
//            System.out.print(j);
//        }
//        Scanner scanner =new Scanner(System.in);
//        int output = scanner.nextInt();
//        System.out.println(output);
//        int[] arr_input = new int[5];
//        for (int i = 0; i < 5;i++){
//            arr_input[i]=scanner.nextInt();
//        }
//        quickSort(arr_input,0,arr_input.length-1,false);
//        for (int j : arr_input) {
//            System.out.print(j);
//        }

        heapSort(arr_b);
    }
}
