class Semaphore {
    private int n;

    Semaphore(int n) {
        this.n = n;
    }

    synchronized void acquire() {
        while (n == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        n--;
    }

    synchronized void release() {
        if (++n > 0)
            notify();        // see in object
    }
}