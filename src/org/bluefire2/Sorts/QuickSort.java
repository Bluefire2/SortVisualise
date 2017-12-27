package org.bluefire2.Sorts;

import org.bluefire2.Operations.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class QuickSort extends Sort {
    @Override
    public Queue<Operation> run(int[] data) {
        final Queue<Operation> ops = new LinkedList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        quicksort(dataCopy, 0, dataCopy.length - 1, ops);


        return ops;
    }

    private void quicksort(int[] data, int start, int end, Queue<Operation> ops) {
        if(start < end) {
            int pIndex = partition(data, start, end, ops);
            quicksort(data, start, pIndex, ops);
            quicksort(data, pIndex + 1, end, ops);
        }
    }

    private int partition(int[] data, int start, int end, Queue<Operation> ops) {
        int pivot = data[start];
        int i = start - 1;
        int j = end + 1;

        while(true) {
            int current;

            do {
                i++;
                current = lookup(data, i, ops);
            } while(lt(current, pivot, ops));

            do {
                j--;
                current = lookup(data, j, ops);
            } while(gt(current, pivot, ops));

            if(i >= j) {
                return j;
            }

            swap(data, i, j, ops);
        }
    }
}
