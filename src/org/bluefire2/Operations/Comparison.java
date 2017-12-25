package org.bluefire2.Operations;

import org.bluefire2.Operation;

public class Comparison implements Operation {
    @Override
    public int[] apply(int[] data) {
        return data;
    }

    @Override
    public String toString() {
        return "Comparing";
    }
}
