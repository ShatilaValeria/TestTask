public class Timeout extends Thread {

    public void timeout() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
