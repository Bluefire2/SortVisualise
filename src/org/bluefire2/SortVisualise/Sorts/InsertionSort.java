package org.bluefire2.SortVisualise.Sorts;

import org.bluefire2.SortVisualise.Operations.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class InsertionSort extends Sort {
    @Override
    public Queue<Operation> run(int[] data) {
        final Queue<Operation> ops = new LinkedList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        for(int i = 1; i < dataCopy.length; i++) {
            for(int j = i; j > 0; j--) {
                int previous = lookup(dataCopy, j - 1, ops);
                int current = lookup(dataCopy, j, ops);
                if(lt(previous, current, ops)) {
                    break;
                } else {
                    swap(dataCopy, j - 1, j, ops);
                }
            }
        }

        return ops;
    }
}
