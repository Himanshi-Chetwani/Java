public class Thread_1 extends Thread {
    public static void main(String[] args) {
        Thread TH1 = new Thread_1();
        Thread TH2 = new Thread_1();
        TH1.start();
        TH2.start();
        System.out.println(TH1);
        System.out.println(TH2);
    }
}
