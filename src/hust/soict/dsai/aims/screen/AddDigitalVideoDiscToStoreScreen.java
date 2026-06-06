package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {

        super(store, cart);
        setTitle("Add DVD");

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField tfTitle    = new JTextField(20);
        JTextField tfCategory = new JTextField(20);
        JTextField tfDirector = new JTextField(20);
        JTextField tfLength   = new JTextField(20);
        JTextField tfCost     = new JTextField(20);

        form.add(createFormRow("Title:",    tfTitle));
        form.add(createFormRow("Category:", tfCategory));
        form.add(createFormRow("Director:", tfDirector));
        form.add(createFormRow("Length:",   tfLength));
        form.add(createFormRow("Cost ($):", tfCost));

        JButton btnAdd = new JButton("Add DVD to Store");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.addActionListener(e -> {
            try {
                String title    = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String director = tfDirector.getText().trim();
                int length      = Integer.parseInt(tfLength.getText().trim());
                float cost      = Float.parseFloat(tfCost.getText().trim());

                DigitalVideoDisc dvd = new DigitalVideoDisc(
                        title, category, director, length, cost
                );
                store.addMedia(dvd);
                JOptionPane.showMessageDialog(this,
                        "DVD \"" + title + "\" added to store!");
                tfTitle.setText(""); tfCategory.setText("");
                tfDirector.setText(""); tfLength.setText(""); tfCost.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid number value.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(Box.createVerticalStrut(15));
        form.add(btnAdd);

        add(form, BorderLayout.CENTER);
        setVisible(true);
    }
}
