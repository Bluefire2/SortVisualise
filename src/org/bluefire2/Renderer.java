package org.bluefire2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Renderer extends JComponent implements ActionListener {
    private int delay = 1000 / 60;

    public Renderer() {
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        // drawing code

    }

    private void update() {
        // do stuff
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
}