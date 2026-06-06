package hust.soict.dsai.guiproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingAccumulator extends JFrame {

    private JTextField tfInput;
    private JTextField tfTotal;
    private double total = 0;

    public SwingAccumulator() {
        setTitle("Swing Accumulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel display = new JPanel(new GridLayout(2, 2, 5, 5));
        display.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        display.add(new JLabel("Input:"));
        tfInput = new JTextField("0");
        display.add(tfInput);
        display.add(new JLabel("Total:"));
        tfTotal = new JTextField("0");
        tfTotal.setEditable(false);
        display.add(tfTotal);
        add(display, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(4, 3, 5, 5));
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        for (String lbl : new String[]{"7","8","9","4","5","6","1","2","3","C","0","+"}) {
            JButton btn = new JButton(lbl);
            btn.addActionListener(new ButtonListener());
            buttons.add(btn);
        }
        add(buttons, BorderLayout.CENTER);

        setSize(280, 280);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String current = tfInput.getText();
            if (cmd.equals("C")) {
                tfInput.setText("0");
                total = 0;
                tfTotal.setText("0");
            } else if (cmd.equals("+")) {
                total += Double.parseDouble(current);
                tfTotal.setText(String.valueOf(total));
                tfInput.setText("0");
            } else {
                tfInput.setText(current.equals("0") ? cmd : current + cmd);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingAccumulator::new);
    }
}
