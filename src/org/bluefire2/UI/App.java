package org.bluefire2.UI;

import javax.swing.*;

public class App {

    private JPanel mainPanel;
    private Renderer canvas;
    private JButton startBtn;
    private JLabel sortAlgoLabel;
    private JComboBox sortAlgoComboBox;
    private JLabel arraySizeLabel;
    private JTextField arraySizeTextField;

    public App() {
        JFrame frame = new JFrame("Sort visualiser");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
