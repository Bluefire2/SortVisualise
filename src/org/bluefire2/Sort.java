package org.bluefire2;

import org.bluefire2.Operations.Lookup;
import org.bluefire2.Operations.Swap;
import java.util.ArrayList;

public abstract class Sort {
    // some helper methods
    protected void swap(int[] data, int a, int b, ArrayList<Operation> ops) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;

        ops.add(new Lookup(a));
        ops.add(new Lookup(b));
        ops.add(new Lookup(a));
        ops.add(new Lookup(b));
        ops.add(new Swap(a, b));
    }

    protected int access(int[] data, int i, ArrayList<Operation> ops) {
        ops.add(new Lookup(i));
        return data[i];
    }

    protected void set(int[] data, int i, int value, ArrayList<Operation> ops) {
        ops.add(new Lookup(i));
        data[i] = value;
    }

    public abstract ArrayList<Operation> run(int[] data);
}
