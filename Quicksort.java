import java.util.Arrays;

class Quicksort{
    public static void main(String[] args) {
        //Sets length of testing array
        int testLength = 10000;
        //Creates test and populates array for quicksort
        TestInteger[] testIntegersQ = new TestInteger[testLength];
        for(int x=0;x<testLength;x++)
            testIntegersQ[x] = new TestInteger((int)(Math.random() * 1000000));
        //Clones test array for mergesort
        TestInteger[] testIntegersM = testIntegersQ.clone();
        testIntegersM[0].resetCounter();
        //Runs mergesort and prints number of comparisons
        Arrays.sort(testIntegersM);
        System.out.println("Mergesort completed with " + testIntegersM[0].getCounter() + " comparisons.");
        //Runs quicksort and prints number of comparisons
        Quicksort q = new Quicksort();
        testIntegersQ[0].resetCounter();
        q.quickSort(testIntegersQ, 0, testIntegersQ.length-1);
        System.out.println("Quicksort completed with "  + testIntegersQ[0].getCounter() + " comparisons.");
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
        if (low<high){
            int partition = randomizedPartition(A, low, high);
        }

    }
    int randomizedPartition(TestInteger[] A, int low, int high){
        return 0;
    }

    void medianQuicksort(TestInteger A, int low, int high){

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
