package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class StoreScreen extends JFrame {

    private Store store;

    public StoreScreen(Store store) {

        this.store = store;

        setTitle("AIMS Store");

        setSize(1024, 768);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View Store");

        JMenuItem updateStore = new JMenuItem("Update Store");

        JMenuItem viewCart = new JMenuItem("View Cart");

        menu.add(viewStore);

        menu.add(updateStore);

        menu.add(viewCart);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        // Center
        JLabel label = new JLabel("AIMS Store", SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 30));

        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}