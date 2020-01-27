package sample;

public class ThreadTestMain {
    public static void main(String[] args)
    {
        int n = 8; // Number of threads
        for (int i=0; i<3; i++)
        {
            ThreadTest object = new ThreadTest();
            object.start();
            switch (i){
                case 0:
                    TestCase1();
                    break;
                case 1:
                    System.out.println("Hello Thread 2!");
                    break;
                case 2:
                    System.out.println("Hello Thread 3!");
                    break;
            }
        }
    }
    public static void TestCase1(){
        System.out.println("Hello world!");
    }
}
