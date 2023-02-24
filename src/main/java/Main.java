public class Main {
    public static void main(String[] args) {
        // target -- количество чисел, которые производитель/потребитель должен произвести/получить
        final int target = 20;

        SharedData sharedData = new SharedData(2);
        Producer producer = new Producer(sharedData, target);
        Consumer consumer = new Consumer(sharedData, target);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        producerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
