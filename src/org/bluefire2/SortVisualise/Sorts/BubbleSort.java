package org.bluefire2.SortVisualise.Sorts;

import org.bluefire2.SortVisualise.Operations.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class BubbleSort extends Sort {
    @Override
    public Queue<Operation> run(int[] data) {
        final Queue<Operation> ops = new LinkedList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        boolean swapped;

        do {
            swapped = false;
            for(int i = 1; i < dataCopy.length; i++) {
                int previous = lookup(dataCopy, i - 1, ops);
                int current = lookup(dataCopy, i, ops);
                if(gt(previous, current, ops)) {
                    swap(dataCopy, i - 1, i, ops);
                    swapped = true;
                }
            }
        } while(swapped); // stop when no elements need swapping (i.e. array is sorted)

        return ops;
    }
}
