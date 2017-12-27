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
    private Queue<Operation> ops = null;
    private int[] data = null;
    private Timer timer = null;
    private int swaps = 0;
    private int lookups = 0;
    private int comparisons = 0;
    private int totalOperations = 0;

    public void init(int delay, int[] data, Queue<Operation> ops) {
        this.data = data;
        this.ops = ops;
        timer = new Timer(delay, this);
        timer.start();

        swaps = lookups = comparisons = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing code goes here
        // graph the data[] array basically
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