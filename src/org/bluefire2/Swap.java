package org.bluefire2;

public class Swap implements Operation {
    private final int a;
    private final int b;

    public Swap(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int[] apply(int[] data) throws InvalidOperationException {
        try {
            int temp = data[a];
            data[a] = data[b];
            data[b] = temp;
        } catch(ArrayIndexOutOfBoundsException e) {
            // swap cannot be applied due to invalid index
            throw new InvalidOperationException("Invalid swap index.");
        }

        return data;
    }

    @Override
    public String toString() {
        return String.format("Swapping index %d with index %d", a, b);
    }
}
