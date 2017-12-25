package org.bluefire2.Sorts;

import org.bluefire2.Operation;
import org.bluefire2.Sort;

import java.util.ArrayList;

public class InsertionSort extends Sort {

    @Override
    public ArrayList<Operation> run(int[] data) {
        final ArrayList<Operation> ops = new ArrayList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        for(int i = 1; i < dataCopy.length; i++) {
            for(int j = i; j > 0; j--) {
                if(access(dataCopy, j - 1, ops) < access(dataCopy, j, ops)) {
                    break;
                } else {
                    swap(dataCopy, j - 1, j, ops);
                }
            }
        }

        return ops;
    }
}
