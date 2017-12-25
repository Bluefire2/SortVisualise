package org.bluefire2;

import org.bluefire2.Sorts.BubbleSort;
import org.bluefire2.Sorts.InsertionSort;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InvalidOperationException {
        int[] data = new int[] {5, 4, 3, 2, 1};
        Sort sort = new InsertionSort();
        ArrayList<Operation> ops = sort.run(data);
        for(Operation op : ops) {
            System.out.println(op);
            op.apply(data);
        }

        System.out.println("Done");
    }
}
