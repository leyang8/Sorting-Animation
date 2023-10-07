package se2203b.assignment1;

public interface SortingStrategy extends Runnable {
    void sort(int[] numbers) throws InterruptedException;

    @Override
    void run();

}
