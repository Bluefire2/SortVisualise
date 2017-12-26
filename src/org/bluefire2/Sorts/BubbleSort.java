package org.bluefire2.Sorts;

import org.bluefire2.Operations.Operation;

import java.util.ArrayList;

public class BubbleSort extends Sort {
    @Override
    public ArrayList<Operation> run(int[] data) {
        final ArrayList<Operation> ops = new ArrayList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        boolean swapped;

        do {
            swapped = false;
            for(int i = 1; i < dataCopy.length; i++) {
                int previous = access(dataCopy, i - 1, ops);
                int current = access(dataCopy, i, ops);
                if(gt(previous, current, ops)) {
                    swap(dataCopy, i - 1, i, ops);
                    swapped = true;
                }
            }
        } while(swapped); // stop when no elements need swapping (i.e. array is sorted)

        return ops;
    }
}
