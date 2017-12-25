package org.bluefire2.Sorts;

import org.bluefire2.Operation;
import org.bluefire2.Swap;
import java.util.ArrayList;

public abstract class Sort {
    // some helper methods
    protected void swap(int[] data, int a, int b, ArrayList<Operation> ops) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;

        ops.add(new Swap(a, b));
    }

    public abstract ArrayList<Operation> run(int[] data);
}
