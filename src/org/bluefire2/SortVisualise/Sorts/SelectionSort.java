package org.bluefire2.SortVisualise.Sorts;

import org.bluefire2.SortVisualise.Operations.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class SelectionSort extends Sort {
    @Override
    public Queue<Operation> run(int[] data) {
        final Queue<Operation> ops = new LinkedList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        for(int i = 0; i < dataCopy.length; i++) {
            int smallestIndex = -1;
            int smallestValue = Integer.MAX_VALUE;

            for(int j = i; j < dataCopy.length; j++) {
                int current = lookup(dataCopy, j, ops);
                if(lt(current, smallestValue, ops) || smallestIndex == -1) {
                    smallestIndex = j;
                    smallestValue = current;
                }
            }

            if(i != smallestIndex) {
                swap(dataCopy, i, smallestIndex, ops);
            }
        }

        return ops;
    }
}
