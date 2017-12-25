package org.bluefire2;

public interface Operation {
    /**
     * This method applies the operation to an array. The input data is modified.
     *
     * @param data The array to apply the operation to. If the operation cannot be applied to the input, e.g. the
     *             operation needs to access index 10 but the input only has 10 elements, then, an
     *             InvalidOperationException is thrown.
     */
    void apply(int[] data) throws InvalidOperationException;
}
