package org.bluefire2.UI;

import org.bluefire2.InvalidOperationException;
import org.bluefire2.Operations.Comparison;
import org.bluefire2.Operations.Lookup;
import org.bluefire2.Operations.Operation;
import org.bluefire2.Operations.Swap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class ArrayGraphRenderer extends JComponent implements ActionListener {
    private boolean initDone = false;

    private Queue<Operation> ops = null;
    private int[] data = null;
    private Timer timer = null;
    private int swaps = 0,
            lookups = 0,
            comparisons = 0,
            totalOperations = 0;

    private JLabel swapLabel;
    private JLabel lookupLabel;
    private JLabel compareLabel;
    private JLabel opsLabel;

    public void init(int delay, int[] data, Queue<Operation> ops,
                     JLabel opsDataLabel, JLabel swapsDataLabel, JLabel lookupsDataLabel, JLabel comparisonsDataLabel) {
        initDone = true;

        this.data = data;
        this.ops = ops;
        timer = new Timer(delay, this);
        timer.start();

        swaps = lookups = comparisons = 0;

        // pass in the labels that we need to modify when data updates
        this.swapLabel = swapsDataLabel;
        this.lookupLabel = lookupsDataLabel;
        this.compareLabel = comparisonsDataLabel;
        this.opsLabel = opsDataLabel;
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
        }
    }

    private void update() {
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
            }
        } else {
            // stop the timer when we're done
            System.out.println("Done!");
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
}