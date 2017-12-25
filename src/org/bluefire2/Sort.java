package org.bluefire2;

import org.bluefire2.Operations.Comparison;
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

    // comparison operators:

    /**
     * Checks if two integers are equal, updating operations with one comparison.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @param ops The list of operations to add to.
     * @return True if the two integers are equal, false if they are not equal.
     */
    protected boolean equal(int a, int b, ArrayList<Operation> ops) {
        ops.add(new Comparison("eq"));
        return a == b;
    }

    /**
     * Checks if the first integer is less than or equal to the second integer, updating operations with one comparison.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @param ops The list of operations to add to.
     * @return True if the first integer is less than or equal to the second integer, false if it is not.
     */
    protected boolean le(int a, int b, ArrayList<Operation> ops) {
        ops.add(new Comparison("le"));
        return a <= b;
    }

    /**
     * Checks if the first integer is less than the second integer, updating operations with one comparison.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @param ops The list of operations to add to.
     * @return True if the first integer is less than the second integer, false if it is not.
     */
    protected boolean lt(int a, int b, ArrayList<Operation> ops) {
        ops.add(new Comparison("lt"));
        return a < b;
    }

    /**
     * Checks if the first integer is greater than the second integer, updating operations with one comparison.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @param ops The list of operations to add to.
     * @return True if the first integer is greater than the second integer, false if it is not.
     */
    protected boolean gt(int a, int b, ArrayList<Operation> ops) {
        ops.add(new Comparison("gt"));
        return a > b;
    }


    /**
     * Checks if the first integer is greater than or equal to the second integer, updating operations with one
     * comparison.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @param ops The list of operations to add to.
     * @return True if the first integer is greater than or equal to the second integer, false if it is not.
     */
    protected boolean ge(int a, int b, ArrayList<Operation> ops) {
        ops.add(new Comparison("ge"));
        return a >= b;
    }

    public abstract ArrayList<Operation> run(int[] data);
}
