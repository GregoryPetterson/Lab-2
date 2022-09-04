import java.util.Arrays;

class quicksort{
    public static void main(String[] args) {
        //Sets length of testing array
        int testLength = 10000;
        TestInteger[] testIntegersQ = new TestInteger[testLength];
        for(int x=0;x<testLength;x++)
            testIntegersQ[x] = new TestInteger((int)(Math.random() * 1000000));
        TestInteger[] testIntegersM = testIntegersQ.clone();
        testIntegersM[0].resetCounter();
        Arrays.sort(testIntegersM);
        System.out.println("Mergesort completed with " + testIntegersM[0].getCounter() + " comparisons.");
        quicksort q = new quicksort();
        testIntegersQ[0].resetCounter();
        q.qsort(testIntegersQ, 0, testIntegersQ.length-1);
        System.out.println("Quicksort completed with "  + testIntegersQ[0].getCounter() + " comparisons.");
    }

    void qsort(TestInteger[] A, int low, int high){
        if(low<high){
            int p = partition(A, low, high);
            qsort(A, low, p-1);
            qsort(A, p+1, high);
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
