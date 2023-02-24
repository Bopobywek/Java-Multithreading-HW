import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class SharedData {
    private final Queue<Integer> data = new ArrayDeque<>();
    private final Object lock = new Object();
    private final int maxSize;

    SharedData(int size) {
        maxSize = size;

    }
    public void add(Integer number) throws InterruptedException {
        synchronized (lock) {
            while (data.size() == maxSize) {
                System.out.println("Data is full, producer is waiting...");
                lock.wait();
            }

            data.add(number);
            System.out.println("Producer push number " + number);
            lock.notifyAll();
        }
    }

    public void extract() throws InterruptedException {
        synchronized (lock) {
            while (data.isEmpty()) {
                System.out.println("Consumer is waiting for new numbers...");
                lock.wait();
            }

            int result = data.poll();
            System.out.println("Consumer extract number " + result);
            lock.notifyAll();
        }
    }
}
