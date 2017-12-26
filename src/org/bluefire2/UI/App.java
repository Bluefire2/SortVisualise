package org.bluefire2.UI;

import org.bluefire2.Sorts.BubbleSort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class App {

    private JPanel mainPanel;
    private Renderer canvas;
    private JButton startBtn;
    private JLabel sortAlgoLabel;
    private JComboBox<String> sortAlgoComboBox;
    private JLabel arraySizeLabel;
    private JTextField arraySizeTextField;

    public App() throws ClassNotFoundException {
        HashMap<String, Class> sortImplementation = new HashMap<>();
        sortImplementation.put("Bubble sort", Class.forName("org.bluefire2.Sorts.BubbleSort"));
        sortImplementation.put("Selection sort", Class.forName("org.bluefire2.Sorts.SelectionSort"));
        sortImplementation.put("Insertion sort", Class.forName("org.bluefire2.Sorts.InsertionSort"));
        sortImplementation.put("Heap sort", Class.forName("org.bluefire2.Sorts.HeapSort"));
        sortImplementation.put("Quick sort", Class.forName("org.bluefire2.Sorts.QuickSort"));

        JFrame frame = new JFrame("Sort visualiser");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String[] sortNames = sortImplementation.keySet().toArray(new String[0]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(sortNames);
        sortAlgoComboBox.setModel(model);
    }
}
