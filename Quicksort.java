import java.util.Arrays;
import java.util.Random;

//Authored by Luke Burdette and Gregory Petterson

class Quicksort{
    public static void main(String[] args) {
        Quicksort q = new Quicksort();
        //Sets length of testing array
        int testLength = 1000;
        //Creates test and populates array for quicksort
        TestInteger[] testIntegersQ = new TestInteger[testLength];
        for(int x=0;x<testLength;x++)
            testIntegersQ[x] = new TestInteger((int)(Math.random() * 1000000));
        //Clones test array for mergesort
        TestInteger[] testIntegersM = testIntegersQ.clone();
        TestInteger[] testIntegersR = testIntegersQ.clone();
        TestInteger[] testIntegersT = testIntegersQ.clone();
        TestInteger[] testIntegersI = testIntegersQ.clone();
        //Runs mergesort
        testIntegersM[0].resetCounter();
        Arrays.sort(testIntegersM);
        System.out.println("Mergesort completed with " + testIntegersM[0].getCounter() + " comparisons.");
        //Runs quicksort
        testIntegersQ[0].resetCounter();
        q.quickSort(testIntegersQ, 0, testIntegersQ.length-1);
        System.out.println("Quicksort completed with "  + testIntegersQ[0].getCounter() + " comparisons.");
        //Runs random quicksort
        testIntegersR[0].resetCounter();
        q.randomizedQuicksort(testIntegersR, 0, testIntegersR.length-1);
        System.out.println("Randomized quicksort completed with "  + testIntegersR[0].getCounter() + " comparisons.");
        //Runs median-of-three quicksort
        testIntegersT[0].resetCounter();
        q.medianQuicksort(testIntegersT, 0, testIntegersT.length-1);
        System.out.println("Median quicksort completed with "  + testIntegersT[0].getCounter() + " comparisons.");
        //Runs insertion sort at end
        testIntegersI[0].resetCounter();
        q.insertionQuicksort(testIntegersI,0,testIntegersT.length-1);
        q.switchAtEndQuicksort(testIntegersI);
        System.out.println("Insertion quicksort completed with "  + testIntegersI[0].getCounter() + " comparisons.");
    }

    void quickSort(TestInteger[] A, int low, int high){
        if(low<high){
            int partition = partition(A, low, high);
            quickSort(A, low, partition-1);
            quickSort(A, partition+1, high);
        }
    }

    int partition(TestInteger[] A, int low, int high){
        TestInteger temp;
        TestInteger pivot = A[high];
        int i = low-1;
        for(int j = low; j < high; j++){
            if(A[j].compareTo(pivot) > 0){
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[high];
        A[high] = temp;
        return i+1;
    }

    void randomizedQuicksort(TestInteger[] A, int low, int high){
        if(low<high){
            int partition = randomizedPartition(A, low, high);
            randomizedQuicksort(A, low, partition-1);
            randomizedQuicksort(A, partition+1, high);
        }
    }
    void randomizedExchange(TestInteger[] A, int low, int high){
        Random rand= new Random();
        int pivot = rand.nextInt(high-low)+low;
        TestInteger temp1= A[pivot];
        A[pivot]=A[high];
        A[high]=temp1;
    }
    int randomizedPartition(TestInteger[] A, int low, int high){
        randomizedExchange(A,low,high);
        TestInteger temp;
        TestInteger pivot = A[high];
        int i = (low-1);
        for (int j = low; j < high; j++)
        {
            if(A[j].compareTo(pivot) > 0){
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[high];
        A[high] = temp;
        return i+1;
    }

    void medianQuicksort(TestInteger[] A, int low, int high){
        if(low<high){
            int partition = medianPartition(A, low, high);
            medianQuicksort(A, low, partition-1);
            medianQuicksort(A, partition+1, high);
        }
    }
    int medianPartition(TestInteger[] A, int low, int high){
        TestInteger temp;
        TestInteger[] B = A.clone();
        TestInteger pivot = medianPivot(B,high,low);
        int i = low-1;
        for(int j = low; j < high; j++){
            if(A[j].compareTo(pivot) > 0){
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[high];
        A[high] = temp;
        return i+1;
    }
    TestInteger medianPivot(TestInteger[] B, int left, int right) {
        int center = (left + right) / 2;
        if (B[left].compareTo(B[center]) > 0) {
            TestInteger temp = B[left];
            B[left] = B[center];
            B[center] = temp;
        }
        if (B[left].compareTo(B[right]) > 0){
            TestInteger temp = B[left];
            B[left] = B[right];
            B[right] = temp;
        }
        if (B[center].compareTo(B[right]) > 0){
            TestInteger temp = B[center];
            B[center] = B[right];
            B[right] = temp;
        }
        TestInteger temp = B[center];
        B[center] = B[right];
        B[right] = temp;
        return B[right];
    }

    void insertionQuicksort(TestInteger[] A, int low, int high){
        if(low<high*.85){
            int partition = partition(A, low, high);
            quickSort(A, low, partition-1);
            quickSort(A, partition+1, high);
        }
    }
    void switchAtEndQuicksort(TestInteger[] A){
        int n = A.length;
        for (int i = 1; i < n; ++i) {
            TestInteger key = A[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && A[j].compareTo(key) < 0) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }


}

class TestInteger implements Comparable<TestInteger>{
    public TestInteger(int v){
        this.value = v;
    }
    public final int value;
    public static long counter;
    public int compareTo(TestInteger other){
        ++counter;
        if(value > other.value)
            return -1;
        else if (value < other.value)
            return 1;
        else
            return 0;
    }
    public long getCounter(){
        return counter;
    }
    public void resetCounter(){
        counter = 0;
    }
}
