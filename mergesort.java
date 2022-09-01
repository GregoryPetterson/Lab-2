
class mergesort{
    public static void main(String[] args) {
        int[] TestIntegers = new int[10000];
        for(int x=0;x<10000;x++)
            TestIntegers[x] = (int)Math.random();
    }
}
class TestInteger implements Comparable<TestInteger>{
    private int value;
    static public int counter;
    public int compareTo(TestInteger other){
        ++counter;
        return counter;
    }

}
