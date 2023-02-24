import java.util.Random;

public class Producer implements Runnable {
    private final SharedData sharedData;
    private final int target;
    private final Random random = new Random();

    Producer(SharedData data, int target) {
        sharedData = data;
        this.target = target;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter != target) {
            int number = random.nextInt(100);
            try {
                // Имитируем сложное "производство" числа задержкой, а затем добавляем его
                Thread.sleep(random.nextInt(130,200));
                sharedData.add(number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            ++counter;
        }
    }
}
