package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import javax.swing.*;
import java.awt.*;
public class StoreScreen extends JFrame {

    private Store store;
    private Cart cart;

    public StoreScreen(Store store, Cart cart) {

        this.store = store;
        this.cart = cart;

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

        StoreScreenHeader header =
        new StoreScreenHeader(cart, store);

        add(header, BorderLayout.NORTH);

        // Center
        JPanel center = new JPanel();

        center.setLayout(new GridLayout(0, 3, 20, 20));

        for (Media media : store.getItemsInStore()) {

        MediaStore mediaStore =
            new  MediaStore(media, cart);

        center.add(mediaStore);
}

        JScrollPane scrollPane =
        new JScrollPane(center);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}