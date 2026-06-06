package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store, Cart cart) {

        super(store, cart);
        setTitle("Add Book");

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField tfTitle    = new JTextField(20);
        JTextField tfCategory = new JTextField(20);
        JTextField tfCost     = new JTextField(20);

        form.add(createFormRow("Title:",    tfTitle));
        form.add(createFormRow("Category:", tfCategory));
        form.add(createFormRow("Cost ($):", tfCost));

        JButton btnAdd = new JButton("Add Book to Store");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.addActionListener(e -> {
            try {
                String title    = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                float cost      = Float.parseFloat(tfCost.getText().trim());

                Book book = new Book(
                        store.getItemsInStore().size() + 1,
                        title, category, cost
                );
                store.addMedia(book);
                JOptionPane.showMessageDialog(this,
                        "Book \"" + title + "\" added to store!");
                tfTitle.setText("");
                tfCategory.setText("");
                tfCost.setText("");
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
