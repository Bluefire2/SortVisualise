package org.bluefire2.UI;

import org.bluefire2.InvalidOperationException;
import org.bluefire2.Operations.Comparison;
import org.bluefire2.Operations.Lookup;
import org.bluefire2.Operations.Operation;
import org.bluefire2.Operations.Swap;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Queue;

public class ArrayGraphRenderer extends JComponent {
    private boolean initDone = false;

    private int refreshDelay = 0;

    private Queue<Operation> ops = null;
    private int[] data = null;
    private int swaps = 0,
            lookups = 0,
            comparisons = 0,
            totalOperations = 0;

    private JLabel swapLabel;
    private JLabel lookupLabel;
    private JLabel compareLabel;
    private JLabel opsLabel;

    /**
     * Initialisation method for renderer.
     *
     * @param refreshDelay The time delay between each array/graph update.
     * @param data The array data.
     * @param ops The operations to be applied to the array.
     * @param opsDataLabel The data label for the total number of operations.
     * @param swapsDataLabel The data label for the number of swaps.
     * @param lookupsDataLabel The data label for the number of array lookups.
     * @param comparisonsDataLabel The data label for the number of comparisons.
     */
    public void init(int refreshDelay, int[] data, Queue<Operation> ops,
                     JLabel opsDataLabel, JLabel swapsDataLabel, JLabel lookupsDataLabel, JLabel comparisonsDataLabel) {
        initDone = true;

        this.refreshDelay = refreshDelay;

        this.data = data;
        this.ops = ops;

        swaps = lookups = comparisons = 0;

        // pass in the labels that we need to modify when data updates
        this.swapLabel = swapsDataLabel;
        this.lookupLabel = lookupsDataLabel;
        this.compareLabel = comparisonsDataLabel;
        this.opsLabel = opsDataLabel;

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // only draw if init has been called, otherwise we get null pointers...
        if(initDone) {
            // first update the data labels
            swapLabel.setText(Integer.toString(swaps));
            lookupLabel.setText(Integer.toString(lookups));
            compareLabel.setText(Integer.toString(comparisons));
            opsLabel.setText(Integer.toString(totalOperations));

            // drawing code goes here
            // graph the data[] array basically
            int width = getWidth(),
                    height = getHeight(),
                    xMargin = width / 50,
                    yMargin = height / 50;


            // try to update data, if successful then repaint
            if(updateData()) {
                try {
                    Thread.sleep(refreshDelay);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            } else {
                System.out.println("done!");
                System.out.println(Arrays.toString(data));
            }
        }
    }

    /**
     * Method for updating the array based on the operations specified.
     *
     * @return true if array has been updated, false if it has not (no more operations or exception thrown).
     */
    private boolean updateData() {
        if(!ops.isEmpty()) {
            // there are more operations left
            try {
                Operation current = ops.remove();
                current.apply(data);

                // log the type of operation
                if(current instanceof Swap) {
                    swaps++;
                } else if(current instanceof Lookup) {
                    lookups++;
                } else if(current instanceof Comparison) {
                    comparisons++;
                }
                totalOperations++;

            } catch(InvalidOperationException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        } else {
            // stop repainting when we're done
            return false;
        }
    }
}