package org.bluefire2.SortVisualise.Operations;

import org.bluefire2.SortVisualise.InvalidOperationException;

public class Lookup implements Operation {
    private final int i;

    public Lookup(int i) {
        this.i = i;
    }

    @Override
    public void apply(int[] data) throws InvalidOperationException {
        if(i >= data.length || i < 0) {
            throw new InvalidOperationException("Invalid lookup index.");
        }
    }

    @Override
    public String toString() {
        return String.format("Accessing element at index %d", i);
    }
}
