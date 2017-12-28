package org.bluefire2.UI;

import org.bluefire2.InvalidOperationException;
import org.bluefire2.Operations.Operation;
import org.bluefire2.Operations.Swap;
import org.bluefire2.Sorts.Sort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class App {

    private JPanel mainPanel;
    private ArrayGraphRenderer canvas;
    private JButton startBtn;
    private JLabel sortAlgoLabel;
    private JComboBox<String> sortAlgoComboBox;
    private JLabel arraySizeLabel;
    private JTextField arraySizeTextField;
    private JLabel operationsCountLabel;
    private JLabel operationsCountValueLabel;
    private JLabel swapsCountLabel;
    private JLabel swapsCountValueLabel;
    private JLabel lookupsCountLabel;
    private JLabel lookupsCountValueLabel;
    private JLabel comparisonsCountLabel;
    private JLabel comparisonsCountValueLabel;
    private JPanel countsPanel;
    private JLabel timeDelayLabel;
    private JTextField timeDelayField;

    public App() throws ClassNotFoundException {
        HashMap<String, Class> sortImplementations = new HashMap<>();
        sortImplementations.put("Bubble sort", Class.forName("org.bluefire2.Sorts.BubbleSort"));
        sortImplementations.put("Selection sort", Class.forName("org.bluefire2.Sorts.SelectionSort"));
        sortImplementations.put("Insertion sort", Class.forName("org.bluefire2.Sorts.InsertionSort"));
        sortImplementations.put("Heap sort", Class.forName("org.bluefire2.Sorts.HeapSort"));
        sortImplementations.put("Quick sort", Class.forName("org.bluefire2.Sorts.QuickSort"));

        JFrame frame = new JFrame("Sort visualiser");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String[] sortNames = sortImplementations.keySet().toArray(new String[0]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(sortNames);
        sortAlgoComboBox.setModel(model);

        startBtn.addActionListener((ActionEvent event) -> {
            // retrieve specified array size
            int arraySize;
            try {
                arraySize = Integer.parseInt(arraySizeTextField.getText());
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Array size must be a whole number.");
                return;
            }

            // retrieve implementation for the specified sort
            String sortType = Objects.requireNonNull(sortAlgoComboBox.getSelectedItem()).toString();
            Class sortImplementation = sortImplementations.get(sortType);
            Sort sort;
            try {
                sort = (Sort)sortImplementation.newInstance();
            } catch(InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return;
            }

            // retrieve time delay
            int timeDelay;
            try {
                timeDelay = Integer.parseInt(timeDelayField.getText());
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Time delay must be a whole number.");
                return;
            }

            // generate random data
            int[] data = Sort.randomArray(arraySize, 0, arraySize);

            // sort!
            Queue<Operation> ops = sort.run(data);

            canvas.init(timeDelay, data, ops, operationsCountValueLabel, swapsCountValueLabel, lookupsCountValueLabel, comparisonsCountValueLabel);
        });
    }
}
