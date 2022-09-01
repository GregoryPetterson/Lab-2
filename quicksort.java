import java.util.Arrays;

class quicksort{
    public static void main(String[] args) {
        //Sets length of testing array
        int testLength = 5;
        TestInteger[] testIntegersQ = new TestInteger[testLength];
        for(int x=0;x<testLength;x++)
            testIntegersQ[x] = new TestInteger((int)Math.random());
        TestInteger[] testIntegersM = testIntegersQ.clone();
        Arrays.sort(testIntegersM);
        qsort(testIntegersQ, 0, testIntegersQ.length-1);
    }

    public static void qsort(TestInteger[] A, int low, int high){
        if(low>=high || low<0)
            return;
        int p = partition(A, low, high);
        qsort(A, low, p-1);
        qsort(A, p+1, high);
    }

    public static int partition(TestInteger[] A, int low, int high){
        TestInteger temp;
        TestInteger pivot = A[high];
        int i = low-1;
        for(int j = low; j < high-1; j++){
            if(A[j].compareTo(pivot) != 1){
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        i++;
        temp = A[i];
        A[i] = A[high];
        A[high] = temp;
        return i;
    }
}

class TestInteger implements Comparable<TestInteger>{
    public TestInteger(int v){
        this.value = v;
    }
    private int value;
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
