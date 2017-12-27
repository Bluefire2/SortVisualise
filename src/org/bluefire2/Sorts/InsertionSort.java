package org.bluefire2.Sorts;

import org.bluefire2.Operations.Operation;

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
                int previous = access(dataCopy, j - 1, ops);
                int current = access(dataCopy, j, ops);
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
