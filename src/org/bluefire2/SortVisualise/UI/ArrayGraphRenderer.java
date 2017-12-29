package org.bluefire2.SortVisualise.UI;

import org.bluefire2.SortVisualise.InvalidOperationException;
import org.bluefire2.SortVisualise.Operations.Comparison;
import org.bluefire2.SortVisualise.Operations.Lookup;
import org.bluefire2.SortVisualise.Operations.Operation;
import org.bluefire2.SortVisualise.Operations.Swap;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Queue;

public class ArrayGraphRenderer extends JComponent {
    private boolean initDone = false;

    private int refreshDelay = 0;

    private Queue<Operation> ops = null;
    private int[] data = null;
    private int swaps,
            lookups,
            comparisons,
            totalOperations;

    private int width;
    private int height;
    private int xMargin;
    private int yMargin;
    private float xStep;
    private float yStep;

    private JLabel swapLabel;
    private JLabel lookupLabel;
    private JLabel compareLabel;
    private JLabel opsLabel;

    enum UpdateResult {
        Delay, NoDelay, Fail
    }

    /**
     * Initialisation method for renderer.
     * @param data The array data.
     * @param ops The operations to be applied to the array.
     * @param refreshDelay The time delay between each array/graph update.
     * @param opsDataLabel The data label for the total number of operations.
     * @param swapsDataLabel The data label for the number of swaps.
     * @param lookupsDataLabel The data label for the number of array lookups.
     * @param comparisonsDataLabel The data label for the number of comparisons.
     */
    public void init(int[] data, Queue<Operation> ops, int refreshDelay,
                     JLabel opsDataLabel, JLabel swapsDataLabel, JLabel lookupsDataLabel, JLabel comparisonsDataLabel) {
        initDone = true;

        this.refreshDelay = refreshDelay;

        this.data = data;
        this.ops = ops;

        int maxValue = 0;

        for(int elem : data) {
            if(elem > maxValue) {
                maxValue = elem;
            }
        }

        swaps = lookups = comparisons = totalOperations = 0;

        // pass in the labels that we need to modify when data updates
        this.swapLabel = swapsDataLabel;
        this.lookupLabel = lookupsDataLabel;
        this.compareLabel = comparisonsDataLabel;
        this.opsLabel = opsDataLabel;

        // reset values
        swapLabel.setText("0");
        lookupLabel.setText("0");
        compareLabel.setText("0");
        opsLabel.setText("0");

        // initialise values
        width = getWidth();
        height = getHeight();
        xMargin = 0;
        yMargin = 0; // 0 margins for now

        xStep = ((float)width - 2 * (float)xMargin) / (float)data.length;
        yStep = ((float)height - 2 * (float)yMargin) / (float) maxValue;

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
            int currX = xMargin;

            for(int elem : data) {
                int h = (int)Math.floor(elem * yStep);
                g.fillRect(currX, height - h, (int)xStep, h);
                currX += xStep;
            }


            // try to update data, if successful then repaint
            UpdateResult update = updateData();
            if(update != UpdateResult.Fail) {
                try {
                    if(update == UpdateResult.Delay) { // only delay if we need to
                        Thread.sleep(refreshDelay);
                    }
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
    private UpdateResult updateData() {
        if(!ops.isEmpty()) {
            // there are more operations left
            try {
                Operation current = ops.remove();
                current.apply(data);

                // log the type of operation
                // delay if it's a swap, if it's a lookup or comparison then don't
                if(current instanceof Swap) {
                    swaps++; // don't increment total operations if it's a swap
                    return UpdateResult.Delay;
                } else if(current instanceof Lookup) {
                    lookups++;
                    totalOperations++;
                    return UpdateResult.NoDelay;
                } else if(current instanceof Comparison) {
                    comparisons++;
                    totalOperations++;
                    return UpdateResult.NoDelay;
                } else {
                    // should never happen
                    return UpdateResult.Fail;
                }

            } catch(InvalidOperationException e) {
                e.printStackTrace();
                return UpdateResult.Fail;
            }
        } else {
            // stop repainting when we're done
            return UpdateResult.Fail;
        }
    }
}