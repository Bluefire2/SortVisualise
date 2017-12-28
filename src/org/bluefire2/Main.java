package org.bluefire2;

import org.bluefire2.Operations.Operation;
import org.bluefire2.Operations.Swap;
import org.bluefire2.Sorts.Sort;
import org.bluefire2.UI.App;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static void testSort(Class sortImplementation) throws IllegalAccessException, InstantiationException, InvalidOperationException {
        int[] data = new int[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        }
        System.out.println(Arrays.toString(data));

        Sort sort = (Sort)sortImplementation.newInstance();
        Queue<Operation> ops = sort.run(data);

        int swaps = 0;
        Operation op = ops.remove();
        while(true) {
            op.apply(data);
            if(op instanceof Swap) {
                swaps++;
            }
            if(ops.isEmpty()) {
                break;
            } else {
                op = ops.remove();
            }
        }

        System.out.println(Arrays.toString(data));
        System.out.println(String.format("Total swaps: %d", swaps));
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvalidOperationException, InstantiationException {
        App app = new App();
        // test like this:
        // testSort(Class.forName("org.bluefire2.Sorts.QuickSort"));
    }
}
