package org.bluefire2.Sorts;

import org.bluefire2.Operation;
import org.bluefire2.Sort;

import java.util.ArrayList;

public class SelectionSort extends Sort {
    @Override
    public ArrayList<Operation> run(int[] data) {
        final ArrayList<Operation> ops = new ArrayList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        for(int i = 0; i < dataCopy.length; i++) {
            int smallestIndex = -1;
            int smallestValue = Integer.MAX_VALUE;

            for(int j = i; j < dataCopy.length; j++) {
                int current = access(dataCopy, j, ops);
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
