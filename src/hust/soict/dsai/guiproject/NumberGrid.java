package hust.soict.dsai.guiproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberGrid extends JFrame {

    private JTextField display;

    public NumberGrid() {
        setTitle("Number Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.BOLD, 22));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(4, 3, 4, 4));
        buttons.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (String lbl : new String[]{"7","8","9","4","5","6","1","2","3","C","0","DEL"}) {
            JButton btn = new JButton(lbl);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(new ButtonListener());
            buttons.add(btn);
        }
        add(buttons, BorderLayout.CENTER);

        setSize(250, 320);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String current = display.getText();
            switch (cmd) {
                case "C":
                    display.setText("0");
                    break;
                case "DEL":
                    if (current.length() > 1) {
                        display.setText(current.substring(0, current.length() - 1));
                    } else {
                        display.setText("0");
                    }
                    break;
                default:
                    display.setText(current.equals("0") ? cmd : current + cmd);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGrid::new);
    }
}
