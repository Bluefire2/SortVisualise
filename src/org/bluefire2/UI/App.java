package org.bluefire2.UI;

import org.bluefire2.InvalidOperationException;
import org.bluefire2.Operations.Operation;
import org.bluefire2.Operations.Swap;
import org.bluefire2.Sorts.BubbleSort;
import org.bluefire2.Sorts.Sort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class App {

    private JPanel mainPanel;
    private Renderer canvas;
    private JButton startBtn;
    private JLabel sortAlgoLabel;
    private JComboBox<String> sortAlgoComboBox;
    private JLabel arraySizeLabel;
    private JTextField arraySizeTextField;

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
                JOptionPane.showMessageDialog(frame, "Array size must be a number.");
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

            // generate random data
            int[] data = Sort.randomArray(arraySize, 0, arraySize);

            // sort!
            ArrayList<Operation> ops = sort.run(data);
            int swaps = 0;
            for(Operation op : ops) {
                System.out.println(op);
                try {
                    op.apply(data);
                } catch(InvalidOperationException e) {
                    e.printStackTrace();
                    return;
                }
                if(op instanceof Swap) {
                    swaps++;
                }
            }

            System.out.println(Arrays.toString(data));
            System.out.println(String.format("Total swaps: %d", swaps));
        });
    }
}
