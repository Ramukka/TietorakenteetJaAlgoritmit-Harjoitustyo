import java.util.Random;
import java.util.Arrays;

public class Program {

/*
    ###Timer utility###
*/
    public class TimerUtil { // Class for making the timer usage more modular.
        static long startTime; 
        static long endTime;

        // When calling TimerUtil.Start(), the start time is saved
        public static void Start() {
                startTime = System.nanoTime();
        }

        // When calling TimerUtil.Stop(), the stop time is saved and the time is returned in seconds.
        public static double Stop() {
            endTime = System.nanoTime();
            return (endTime-startTime)/1000000.0;
        }
    }

/*
    ###Sort methods start###
*/

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }

    public static void BubbleSort(int[] arr) {
        int n = arr.length;
        int i, j, temp;
        boolean swapped;

        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
    }

    public static void ShellSort(int[] arr) {
        int n = arr.length;

        for(int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i<n; i++) {
                int temp = arr[i];
                int j = i;

                while(j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
            }
        }
    }

    static int partition(int[] arr, int low, int high)  {
        
        int mid = low + (high -low)/2;

    if (arr[mid] < arr[low]) swap(arr, low, mid);
    if (arr[high] < arr[low]) swap(arr, low, high);
    if (arr[high] < arr[mid]) swap(arr, mid, high);

    swap(arr, mid, high);

        int pivot = arr[high];
        int i = low-1;

        for(int j = low; j <= high -1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return i+1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void QuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            QuickSort(arr, low, pi -1);
            QuickSort(arr, pi+1, high);
        }
    }

    static void Merge(int arr[], int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for(int i = 0; i < n1; i++) {
            L[i] = arr[l+i];
        }
        for(int j = 0; j < n2; j++) {
            R[j] = arr[m+1+j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if(L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while(j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void MergeSort(int arr[], int l, int r) {
        
        if (l < r) {
            int m = l + (r-l) / 2;

            MergeSort(arr, l, m);
            MergeSort(arr, m+1, r);

            Merge(arr, l, m, r);
        }
    }

    public static void OptimizedMergeSort(int[] arr) {
        if (arr.length <= 1) return;

        int[] aux = new int[arr.length];
        recursiveMergeSort(arr, aux, 0, arr.length -1);
    }

    private static void recursiveMergeSort(int[] arr, int[] aux, int l, int r) {
        if (r-l <15){
            insertionSortRange(arr, l,r);
            return;
        }

        if(l < r) {
            int m = l+(r-l)/2;
            recursiveMergeSort(arr, aux, l, m);
            recursiveMergeSort(arr, aux, m+1, r);
            mergeOptimized(arr, aux, l, m,r);
        }
    }

    private static void mergeOptimized(int[] arr, int[] aux, int l, int m, int r) {
        for(int i = l; i <= r; i++) {
            aux[i] = arr[i];
        }

        int i = l;
        int j = m+1;
        int k = l;

        while (i <= m && j <= r) {
            if(aux[i] <= aux[j]) {
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
            }
        }

        while (i <= m) {
            arr[k++] = aux[i++];
        }
    }

    // for optimized mergesort
    public static void insertionSortRange(int[] data, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
        int key = data[i];
        int j = i - 1;
        while (j >= low && data[j] > key) {
            data[j + 1] = data[j];
            j--;
        }
        data[j + 1] = key;
    }
}

/*
    ###Sort methods end###
*/


    // Random int array generation.
    public static int[] createRandomData(int n) {
        int[] data = new int[n];

        Random gen = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = gen.nextInt(n);
        }

        return data;
    }

    // Asc. int array generation.
    public static int[] createAscData(int n) {
        int[] data = new int[n];

        for(int i = 0; i<data.length; i++) {
            data[i] = i;
        }

        return data;
    }

    // Desc. int array generation.
    public static int[] createDescData(int n) {
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = n - i; 
        }

        return data;
    }

    // interface for function pointer.
    private interface FunctionPointer {
        void methodSignature(int[] arr);
    }

    //record for sort entries, takes method name and Function Pointer
    record SortEntry(String name, FunctionPointer method) {}

    // Method for testing sort methods and printing their time.
    public static void SortMethodTester(FunctionPointer method, String methodName, int[] arr) {

        TimerUtil.Start(); // Start the timer utility

        method.methodSignature(arr); // Run the method

        double time = TimerUtil.Stop(); // Stop the timer and save to variable.

        System.out.printf("%s : %d ms%n", methodName, Math.round(time)); // Print the function name and time.
    }

/*
    ###Main###
*/
    public static void main(String[] args) {

        int n = 500000;

        // Differrent sorting methods and their names in a SortEntry array.
        SortEntry[] sorts = {
            new SortEntry("InsertionSort", Program::insertionSort),
            new SortEntry("Arrays.sort", Arrays::sort),
            new SortEntry("BubbleSort", Program::BubbleSort),
            new SortEntry("ShellSort", Program::ShellSort),
            new SortEntry("QuickSort", (arr) -> QuickSort(arr, 0, arr.length - 1)),
            new SortEntry("MergeSort", (arr) -> MergeSort(arr, 0, arr.length-1) ),
            new SortEntry("MergeSort(Optimized)", Program::OptimizedMergeSort)
        };
        System.out.println("========================================");
        System.out.printf("Testing the speed of different sort methods.\nArray length: %d\n", n);
        System.out.println("========================================");

        // Loop for testing the sort methods.
        System.out.println("--- Random data ---");
        for (SortEntry sort : sorts) {
            SortMethodTester(sort.method(), sort.name(), createRandomData(n));
        }
        System.out.println("\n--- Ascending data ---");
        for (SortEntry sort : sorts) {
            SortMethodTester(sort.method(), sort.name(), createAscData(n));
        }
        System.out.println("\n--- Descending data ---");
        for (SortEntry sort : sorts) {
            SortMethodTester(sort.method(), sort.name(), createDescData(n));
        }


    }
}