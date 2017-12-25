package org.bluefire2;

import org.bluefire2.Operations.Lookup;
import org.bluefire2.Operations.Swap;
import java.util.ArrayList;

public abstract class Sort {
    // some helper methods

    /**
     * Swaps two array elements, and updates operations. Swapping involves two array reads and two writes (four
     * lookups), and one swap (lookups and swaps are not mutually exclusive).
     *
     * @param data The input array.
     * @param a The index of the first element.
     * @param b The index of the second element.
     * @param ops The list of operations to add to.
     */
    protected void swap(int[] data, int a, int b, ArrayList<Operation> ops) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;

        ops.add(new Swap(a, b));
        ops.add(new Lookup(a));
        ops.add(new Lookup(b));
        ops.add(new Lookup(a));
        ops.add(new Lookup(b));
    }

    /**
     * Reads and returns an array element, updating operations with one lookup.
     *
     * @param data The input array.
     * @param i The index to look up.
     * @param ops The list of operations to add to.
     * @return The element of `data` at index `i`.
     */
    protected int access(int[] data, int i, ArrayList<Operation> ops) {
        ops.add(new Lookup(i));
        return data[i];
    }

    /**
     * Writes a value to an array element, updating operations with one lookup.
     *
     * @param data The input array.
     * @param i The index to write to.
     * @param value The value to write.
     * @param ops The list of operations to add to.
     */
    protected void set(int[] data, int i, int value, ArrayList<Operation> ops) {
        ops.add(new Lookup(i));
        data[i] = value;
    }

    public abstract ArrayList<Operation> run(int[] data);
}
