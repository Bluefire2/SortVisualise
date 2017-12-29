package org.bluefire2.SortVisualise.Sorts;

import org.bluefire2.SortVisualise.Operations.Comparison;
import org.bluefire2.SortVisualise.Operations.Lookup;
import org.bluefire2.SortVisualise.Operations.Operation;
import org.bluefire2.SortVisualise.Operations.Swap;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

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
    void swap(int[] data, int a, int b, Queue<Operation> ops) {
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
    int lookup(int[] data, int i, Queue<Operation> ops) {
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
    void set(int[] data, int i, int value, Queue<Operation> ops) {
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
    boolean equal(int a, int b, Queue<Operation> ops) {
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
    boolean le(int a, int b, Queue<Operation> ops) {
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
    boolean lt(int a, int b, Queue<Operation> ops) {
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
    boolean gt(int a, int b, Queue<Operation> ops) {
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
    boolean ge(int a, int b, Queue<Operation> ops) {
        ops.add(new Comparison("ge"));
        return a >= b;
    }

    public abstract Queue<Operation> run(int[] data);

    public static int[] randomArray(int size, int min, int max) {
        int[] data = new int[size];
        for (int i = 0; i < data.length; i++) {
            data[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        return data;
    }

    public static int[] randomUniformArray(int length) {
        // build uniform array, then shuffle it
        int[] out = new int[length];
        for(int i = 0; i < length; i++) {
            out[i] = i + 1;
        }

        shuffle(out);
        return out;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int n = ThreadLocalRandom.current().nextInt(1, i + 1);
            swap(array, i, n);
        }
    }
}
