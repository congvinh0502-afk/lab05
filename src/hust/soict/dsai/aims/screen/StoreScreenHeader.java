package hust.soict.dsai.aims.screen;

import javax.swing.*;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import java.awt.*;

public class StoreScreenHeader extends JPanel {

    private Cart cart;
    private Store store;

    public StoreScreenHeader(Cart cart, Store store) {

        this.cart = cart;
        this.store = store;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.CYAN);

        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> new CartScreen(cart, store));

        JPanel rightPanel = new JPanel();
        rightPanel.add(cartButton);

        add(title, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
}