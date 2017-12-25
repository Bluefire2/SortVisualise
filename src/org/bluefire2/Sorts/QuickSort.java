package org.bluefire2.Sorts;

import org.bluefire2.Operation;
import org.bluefire2.Sort;

import java.util.ArrayList;

public class QuickSort extends Sort {
    @Override
    public ArrayList<Operation> run(int[] data) {
        final ArrayList<Operation> ops = new ArrayList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        quicksort(dataCopy, 0, dataCopy.length - 1, ops);


        return ops;
    }

    private void quicksort(int[] data, int start, int end, ArrayList<Operation> ops) {
        if(start < end) {
            int pIndex = partition(data, start, end, ops);
            quicksort(data, start, pIndex, ops);
            quicksort(data, pIndex + 1, end, ops);
        }
    }

    private int partition(int[] data, int start, int end, ArrayList<Operation> ops) {
        int pivot = data[start];
        int i = start - 1;
        int j = end + 1;

        while(true) {
            int current;

            do {
                i++;
                current = access(data, i, ops);
            } while(lt(current, pivot, ops));

            do {
                j--;
                current = access(data, j, ops);
            } while(gt(current, pivot, ops));

            if(i >= j) {
                return j;
            }

            swap(data, i, j, ops);
        }
    }
}
