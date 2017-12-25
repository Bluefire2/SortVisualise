package org.bluefire2;

import org.bluefire2.Sorts.BubbleSort;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InvalidOperationException {
        int[] data = new int[] {5, 4, 3, 2, 1};
        Sort bs = new BubbleSort();
        ArrayList<Operation> ops = bs.run(data);
        for(Operation op : ops) {
            System.out.println(op);
            op.apply(data);
        }
    }
}
