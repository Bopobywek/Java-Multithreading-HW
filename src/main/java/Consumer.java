import java.util.Random;

public class Consumer implements Runnable {
    private final SharedData sharedData;
    private final int target;
    private final Random random = new Random();

    Consumer(SharedData data, int target) {
        sharedData = data;
        this.target = target;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter != target) {
            try {
                sharedData.extract();
                // Имитируем обработку полученного числа задержкой
                Thread.sleep(random.nextInt(170,220));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            ++counter;
        }
    }
}
