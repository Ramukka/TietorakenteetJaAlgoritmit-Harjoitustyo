import java.util.Random;
import java.util.Arrays;

public class SortTest {

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

// TODO: Quicksort MergeSort. optimoi quicksort ja/tai mergesort.

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

    // interface for function pointer.
    private interface FunctionPointer {
        void methodSignature(int[] arr);
    }

    //record for sort entries, takes method name and Function Pointer
    record SortEntry(String name, FunctionPointer method) {}

    // Method for testing sort methods and printing their time.
    public static void SortMethodTester(FunctionPointer method, String methodName, int n) {
        int[] arr = createRandomData(n);

        TimerUtil.Start(); // Start the timer utility

        method.methodSignature(arr); // Run the method

        double time = TimerUtil.Stop(); // Stop the timer and save to variable.

        System.out.printf("%s : %d ms%n", methodName, Math.round(time)); // Print the function name and time.
    }

/*
    ###Main###
*/
    public static void main(String[] args) {

        int n = 100000;

        // Differrent sorting methods and their names in a SortEntry array.
        SortEntry[] sorts = {
            new SortEntry("Insertion Sort", SortTest::insertionSort),
            new SortEntry("Arrays.sort", Arrays::sort),
            new SortEntry("Bubble Sort", SortTest::BubbleSort),
            new SortEntry("Shell Sort", SortTest::ShellSort),
        };

        // Loop for testing the sort methods.
        for (SortEntry sort : sorts) {
            SortMethodTester(sort.method(), sort.name(), n);
        }

    }
}