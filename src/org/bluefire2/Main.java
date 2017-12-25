package org.bluefire2;

import org.bluefire2.Operations.Swap;
import org.bluefire2.Sorts.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws InvalidOperationException {
        int[] data = new int[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        }
        System.out.println(Arrays.toString(data));

        Sort sort = new QuickSort();
        ArrayList<Operation> ops = sort.run(data);

        int swaps = 0;
        for(Operation op : ops) {
            System.out.println(op);
            op.apply(data);
            if(op instanceof Swap) {
                swaps++;
            }
        }

        System.out.println(Arrays.toString(data));
        System.out.println(String.format("Total swaps: %d", swaps));
    }
}
