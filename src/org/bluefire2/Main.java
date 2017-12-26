package org.bluefire2;

import org.bluefire2.Operations.Swap;
import org.bluefire2.Sorts.*;
import org.bluefire2.UI.App;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        App app = new App();
//        int[] data = new int[1000];
//        for (int i = 0; i < data.length; i++) {
//            data[i] = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
//        }
//        System.out.println(Arrays.toString(data));
//
//        Sort sort = new QuickSort();
//        ArrayList<Operation> ops = sort.run(data);
//
//        int swaps = 0;
//        for(Operation op : ops) {
//            System.out.println(op);
//            op.apply(data);
//            if(op instanceof Swap) {
//                swaps++;
//            }
//        }
//
//        System.out.println(Arrays.toString(data));
//        System.out.println(String.format("Total swaps: %d", swaps));
    }
}
