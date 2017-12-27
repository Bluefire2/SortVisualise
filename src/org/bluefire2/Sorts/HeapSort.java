package org.bluefire2.Sorts;

import org.bluefire2.Operations.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class HeapSort extends Sort {
    @Override
    public Queue<Operation> run(int[] data) {
        final Queue<Operation> ops = new LinkedList<>();
        int[] dataCopy = new int[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);

        heapify(dataCopy, ops);

        int end = dataCopy.length - 1;
        while(end > 0) {
            // pop from the heap to the end of the array
            swap(dataCopy, 0, end, ops);

            // as a result the heap gets smaller by 1
            end--;

            // repair the heap
            siftDown(dataCopy, 0, end, ops);
        }

        return ops;
    }

    // turns the array into a heap, in-place
    private void heapify(int[] data, Queue<Operation> ops) {
        int end = data.length - 1;
        int start = parent(end); // start from the parent of the last element

        while(start >= 0) { // repeat until the end of the array
            siftDown(data, start, end, ops);
            start--;
        }
    }

    // repair the heap whose root is at `start`
    private void siftDown(int[] data, int start, int end, Queue<Operation> ops) {
        int root = start; // root of the current heap

        while(leftChild(root) <= end) { // repeat until the end of the array
            int child = leftChild(root);
            int swap = root;

            int swapElem = access(data, swap, ops);
            int childElem = access(data, child, ops);
            if(lt(swapElem, childElem, ops)) {
                swap = child;
            }

            // right child is child + 1
            if(child + 1 <= end) {
                swapElem = access(data, swap, ops);
                childElem = access(data, child + 1, ops);
                if(lt(swapElem, childElem, ops)) {
                    swap = child + 1;
                }
            }

            if(swap == root) {
                // we're done
                return;
            } else {
                // otherwise switch the root and the largest child element
                swap(data, root, swap, ops);
                root = swap;
            }
        }

    }

    private int parent(int i) {
        return Math.floorDiv(i - 1, 2);
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }
}
