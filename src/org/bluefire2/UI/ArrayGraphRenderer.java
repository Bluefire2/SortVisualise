package org.bluefire2.UI;

import org.bluefire2.InvalidOperationException;
import org.bluefire2.Operations.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class ArrayGraphRenderer extends JComponent implements ActionListener {
    private Queue<Operation> ops = null;
    private int[] data = null;
    private Timer timer = null;

    public void init(int delay, int[] data, Queue<Operation> ops) {
        this.data = data;
        this.ops = ops;
        this.timer = new Timer(delay, this);
        this.timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing code goes here
    }

    private void update() {
        // do stuff
        if(!ops.isEmpty()) {
            try {
                Operation current = ops.remove();
                current.apply(data);
                // this.ops.remove().apply(this.data);
            } catch(InvalidOperationException e) {
                e.printStackTrace();
            }
        } else {
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