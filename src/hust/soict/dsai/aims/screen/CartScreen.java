package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CartScreen extends JFrame {

    private Cart cart;
    private Store store;

    public CartScreen(Cart cart, Store store) {

        super();
        this.cart  = cart;
        this.store = store;

        setTitle("Cart");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);

        setVisible(true);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("cart.fxml"));

                CartScreenController controller =
                        new CartScreenController(cart, store, this);
                loader.setController(controller);

                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
