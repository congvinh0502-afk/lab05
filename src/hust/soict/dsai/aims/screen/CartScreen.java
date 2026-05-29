package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;

import javax.swing.*;
import java.awt.*;

public class CartScreen extends JFrame {

    private Cart cart;

    public CartScreen(Cart cart) {

        this.cart = cart;

        setTitle("Cart");

        setSize(800, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "Current Cart",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();

        center.setLayout(
                new GridLayout(0, 1, 10, 10)
        );

        for (Media media : cart.getItemsOrdered()) {

            JLabel mediaLabel =
                    new JLabel(media.toString());

            center.add(mediaLabel);
        }

        JScrollPane scrollPane =
                new JScrollPane(center);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}