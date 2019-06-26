/**
 * A class implementing the Dining Philosphers
 */
public class Philosopher extends Thread {

    private static Semaphore lockForks = new Semaphore(1);
    private static final int numberOfPhilosophers = 5;
    private int philosopherId;                // number for trace
    private static int numberOfMeals = 0;
    private Semaphore leftMutex;
    private Semaphore rightMutex;


    private Philosopher(int id, Semaphore leftChopstick,
                        Semaphore rightChopstick) {
        this.philosopherId = id;
        this.leftMutex = leftChopstick;
        this.rightMutex = rightChopstick;
    }

    /**
     * philosopher's body: think and eat 5 times
     */
    public void run() {
        while (numberOfMeals <= 100000) {
            System.out.println(philosopherId + " thinks");
            System.out.println(philosopherId + " is trying to eat");
            lockForks.acquire();// Necessary to deal with a condition where
            // every philosopher wants to eat and picks up his right fork
            leftMutex.acquire();
            rightMutex.acquire();
            lockForks.release();
            System.out.println("\t" + philosopherId + " eats");
            System.out.println("\t" + philosopherId + " leaves");
            leftMutex.release();
            rightMutex.release();
            numberOfMeals++;

        }
    }

    void sort(){

    }

    /**
     * sets up for 5 philosophers
     */
    public static void main(String args[]) throws InterruptedException {

        Semaphore mutexForkArray[] = new Semaphore[numberOfPhilosophers];

        for (int i = 0; i < mutexForkArray.length; i++) {
            mutexForkArray[i] = new Semaphore(1);
        }
        Philosopher philosophers[] = new Philosopher[numberOfPhilosophers];
        philosophers[0] =
                new Philosopher(0,
                        mutexForkArray[mutexForkArray.length - 1],
                        mutexForkArray[0]);
        for (int n = 1; n < numberOfPhilosophers; ++n) {
            philosophers[n] =
                    new Philosopher(n, mutexForkArray[n - 1],
                            mutexForkArray[n]);
        }
        for (int n = 0; n < numberOfPhilosophers; ++n) philosophers[n].start();
        for (int n = 0; n < numberOfPhilosophers; ++n) philosophers[n].join();

    }
}