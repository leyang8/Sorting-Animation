package se2203b.assignment1;

import javafx.application.Platform;

public class SelectionSort implements SortingStrategy {

    private final int[] list;
    private final SortingHubController sortingHubController;

    public SelectionSort(int[] list, SortingHubController controller) {
        this.list = list;
        this.sortingHubController = controller;
    }

    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //swap the positions of i and the smallest minindex
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;

            Platform.runLater(()->{
                sortingHubController.updateGraph(a);
            });
            try {
                Thread.sleep(30);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
            sort(list);
    }
}
