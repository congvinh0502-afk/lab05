package hust.soict.dsai.guiproject;

import java.awt.*;
import java.awt.event.*;

public class AWTAccumulator extends Frame {

    private TextField tfInput;
    private TextField tfTotal;
    private double total = 0;

    public AWTAccumulator() {
        setTitle("AWT Accumulator");
        setLayout(new BorderLayout(5, 5));

        Panel display = new Panel(new GridLayout(2, 2, 5, 5));
        display.add(new Label("Input:"));
        tfInput = new TextField("0");
        display.add(tfInput);
        display.add(new Label("Total:"));
        tfTotal = new TextField("0");
        tfTotal.setEditable(false);
        display.add(tfTotal);
        add(display, BorderLayout.NORTH);

        Panel buttons = new Panel(new GridLayout(4, 3, 5, 5));
        for (String lbl : new String[]{"7","8","9","4","5","6","1","2","3","C","0","+"}) {
            Button btn = new Button(lbl);
            btn.addActionListener(new ButtonListener());
            buttons.add(btn);
        }
        add(buttons, BorderLayout.CENTER);

        setSize(280, 280);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { dispose(); }
        });
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
        new AWTAccumulator();
    }
}
