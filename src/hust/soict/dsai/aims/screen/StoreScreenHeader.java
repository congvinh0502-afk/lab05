package hust.soict.dsai.aims.screen;

import javax.swing.*;

import hust.soict.dsai.aims.cart.Cart;

import java.awt.*;

public class StoreScreenHeader extends JPanel {
    private Cart cart;
    public StoreScreenHeader(Cart cart) {
        this.cart = cart ;
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("AIMS");

        title.setFont(new Font("Arial", Font.BOLD, 50));

        title.setForeground(Color.CYAN);

        // Cart button
        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> {

            new CartScreen(cart);
        });

        JPanel rightPanel = new JPanel();

        rightPanel.add(cartButton);

        add(title, BorderLayout.WEST);

        add(rightPanel, BorderLayout.EAST);
    }
}