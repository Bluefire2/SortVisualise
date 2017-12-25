package org.bluefire2;

public interface Operation {
    /**
     * This method applies the operation to an array. The input array is *not* modified; rather, a modified copy is
     * returned.
     *
     * @param data The array to apply the operation to. If the operation cannot be applied to the input, e.g. the
     *             operation needs to access index 10 but the input only has 10 elements, then, an
     *             InvalidOperationException is thrown.
     * @return A copy of the input array, with the operation applied.
     */
    int[] apply(int[] data) throws InvalidOperationException;
}
