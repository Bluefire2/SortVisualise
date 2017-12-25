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
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);
        try {
            int temp = dataCopy[a];
            dataCopy[a] = dataCopy[b];
            dataCopy[b] = temp;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new InvalidOperationException("Invalid swap index.");
        }

        return dataCopy;
    }
}
