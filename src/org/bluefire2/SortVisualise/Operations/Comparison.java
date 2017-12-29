package org.bluefire2.SortVisualise.Operations;

public class Comparison implements Operation {
    private final String type;

    public Comparison(String type) {
        this.type = type;
    }

    @Override
    public void apply(int[] data) {
        // do nothing
    }

    @Override
    public String toString() {
        return String.format("Comparing: %s", type);
    }
}
