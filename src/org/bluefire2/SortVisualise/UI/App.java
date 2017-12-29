package org.bluefire2.SortVisualise.UI;

import org.bluefire2.SortVisualise.Operations.Operation;
import org.bluefire2.SortVisualise.Sorts.Sort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Objects;
import java.util.Queue;

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

    public App() {
        String sortImplementationsPackage = "org.bluefire2.SortVisualise.Sorts";

        HashMap<String, String> sortImplementations = new HashMap<>();
        sortImplementations.put("Bubble sort", "BubbleSort");
        sortImplementations.put("Selection sort", "SelectionSort");
        sortImplementations.put("Insertion sort", "InsertionSort");
        sortImplementations.put("Heap sort", "HeapSort");
        sortImplementations.put("Quick sort", "QuickSort");

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
            if(arraySize < 0) {
                JOptionPane.showMessageDialog(frame, "Array size must be non-negative.");
                return;
            }

            // retrieve implementation for the specified sort
            String sortType = Objects.requireNonNull(sortAlgoComboBox.getSelectedItem()).toString();
            String sortImplementationClassName = sortImplementations.get(sortType);
            Class sortImplementation = null;
            try {
                sortImplementation = Class.forName(sortImplementationsPackage + "." + sortImplementationClassName);
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }

            Sort sort;
            try {
                sort = (Sort) Objects.requireNonNull(sortImplementation).newInstance();
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
            if(timeDelay < 0) {
                JOptionPane.showMessageDialog(frame, "Time delay must be non-negative, unfortunately that technology doesn't exist yet.");
                return;
            }

            // generate random data, uniformly
            int[] data = Sort.randomUniformArray(arraySize);

            // sort!
            Queue<Operation> ops = sort.run(data);

            canvas.init(data, ops, timeDelay, operationsCountValueLabel, swapsCountValueLabel, lookupsCountValueLabel, comparisonsCountValueLabel);
        });
    }
}
