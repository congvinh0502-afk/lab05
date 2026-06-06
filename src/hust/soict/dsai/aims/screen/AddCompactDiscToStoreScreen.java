package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {

        super(store, cart);
        setTitle("Add CD");

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField tfTitle    = new JTextField(20);
        JTextField tfCategory = new JTextField(20);
        JTextField tfArtist   = new JTextField(20);
        JTextField tfDirector = new JTextField(20);
        JTextField tfCost     = new JTextField(20);

        form.add(createFormRow("Title:",    tfTitle));
        form.add(createFormRow("Category:", tfCategory));
        form.add(createFormRow("Artist:",   tfArtist));
        form.add(createFormRow("Director:", tfDirector));
        form.add(createFormRow("Cost ($):", tfCost));

        JButton btnAdd = new JButton("Add CD to Store");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.addActionListener(e -> {
            try {
                String title    = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String artist   = tfArtist.getText().trim();
                String director = tfDirector.getText().trim();
                float cost      = Float.parseFloat(tfCost.getText().trim());

                CompactDisc cd = new CompactDisc(
                        store.getItemsInStore().size() + 1,
                        title, category, cost, 0, director, artist
                );
                store.addMedia(cd);
                JOptionPane.showMessageDialog(this,
                        "CD \"" + title + "\" added to store!");
                tfTitle.setText(""); tfCategory.setText("");
                tfArtist.setText(""); tfDirector.setText(""); tfCost.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid cost value.",
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
