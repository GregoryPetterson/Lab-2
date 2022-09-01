import java.util.Arrays;
class quicksort{
    public static void main(String[] args) {
        //Sets length of testing array
        int testLength = 5;
        int[] TestIntegers = new int[testLength];
        for(int x=0;x<testLength;x++)
            TestIntegers[x] = (int)Math.random();
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
