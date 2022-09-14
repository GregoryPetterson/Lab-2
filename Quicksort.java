import java.util.Arrays;
import java.util.Random;

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
            if(A[j].compareTo(pivot) != 1){
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
            if(A[j].compareTo(pivot) != 1){
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
        TestInteger pivot = medianPivot(A,high,low);
        int i = low-1;
        for(int j = low; j < high; j++){
            if(A[j].compareTo(pivot) != 1){
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
    TestInteger medianPivot(TestInteger[] A, int left, int right) {
        int center = (left + right) / 2;
        if (A[left].compareTo(A[center]) != 1) {
            TestInteger temp = A[left];
            A[left] = A[center];
            A[center] = temp;
        }
        if (A[left].compareTo(A[right]) != 1){
            TestInteger temp = A[left];
            A[left] = A[right];
            A[right] = temp;
        }
        if (A[center].compareTo(A[right]) != 1){
            TestInteger temp = A[center];
            A[center] = A[right];
            A[right] = temp;
        }
        TestInteger temp = A[center];
        A[center] = A[right];
        A[right] = temp;
        return A[right];
    }

    void switchAtEndQuicksort(TestInteger A, int low, int high){

    }


}

class TestInteger implements Comparable<TestInteger>{
    public TestInteger(int v){
        this.value = v;
    }
    private final int value;
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
