package se2203b.assignment1;

import javafx.application.Platform;

public class MergeSort implements SortingStrategy {

    private final int[] list;
    private final SortingHubController sortingHubController;

    public MergeSort(int[] list, SortingHubController controller) {
        this.list = list;
        this.sortingHubController = controller;
    }
    @Override
    public void sort(int[] numbers) {
        mergeSort(numbers, 0, numbers.length - 1);
    }

    @Override
    public void run() {
        sort(list);
    }

    /* l is for left index and r is right index of the
   sub-array of arr to be sorted */
    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {

            // Same as (l + r) / 2, but avoids overflow
            // for large l and r
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(int[] arr, int start, int mid, int end) {
        int start2 = mid + 1;

        // If the direct merge is already sorted
        if (arr[mid] <= arr[start2]) {
            return;
        }

        // Two pointers to maintain start
        // of both arrays to merge
        while (start <= mid && start2 <= end) {

            // If element 1 is in right place
            if (arr[start] <= arr[start2]) {
                start++;
            } else {
                int value = arr[start2];
                int index = start2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != start) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[start] = value;

                // Update all the pointers
                start++;
                mid++;
                start2++;
            }
            Platform.runLater(() -> {
                sortingHubController.updateGraph(arr);
            });
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

