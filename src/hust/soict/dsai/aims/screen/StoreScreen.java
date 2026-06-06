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

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> new AddBookToStoreScreen(store, cart));
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> new AddCompactDiscToStoreScreen(store, cart));
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store, cart));
        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            new StoreScreen(store, cart);
            dispose();
        });
        menu.add(viewStore);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> new CartScreen(cart, store));
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