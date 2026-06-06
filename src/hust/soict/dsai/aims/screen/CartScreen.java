package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class CartScreen extends JFrame {

    private Cart cart;
    private Store store;
    private JLabel totalLabel;
    private JPanel itemsPanel;

    public CartScreen(Cart cart, Store store) {

        this.cart = cart;
        this.store = store;

        setTitle("Cart");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());
        add(createNorth(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);
        add(createEast(), BorderLayout.EAST);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {

        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        smUpdateStore.add(new JMenuItem("Add Book"));
        smUpdateStore.add(new JMenuItem("Add CD"));
        smUpdateStore.add(new JMenuItem("Add DVD"));
        menu.add(smUpdateStore);

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            new StoreScreen(store, cart);
            dispose();
        });
        menu.add(viewStore);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> {
            new CartScreen(cart, store);
            dispose();
        });
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createNorth() {

        JLabel header = new JLabel("CART");
        header.setFont(new Font("Arial", Font.PLAIN, 50));
        header.setForeground(Color.CYAN);
        header.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));

        JPanel north = new JPanel(new BorderLayout());
        north.add(header, BorderLayout.WEST);
        return north;
    }

    private JScrollPane createCenter() {

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        refreshItems();

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    private JPanel createEast() {

        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
        east.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        JPanel totalRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel totalStatic = new JLabel("Total: ");
        totalStatic.setFont(new Font("Arial", Font.BOLD, 24));

        totalLabel = new JLabel(String.format("%.2f $", cart.totalCost()));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalLabel.setForeground(Color.CYAN);

        totalRow.add(totalStatic);
        totalRow.add(totalLabel);
        east.add(totalRow);

        east.add(Box.createVerticalStrut(20));

        JButton placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.setFont(new Font("Arial", Font.BOLD, 18));
        placeOrderBtn.setForeground(Color.WHITE);
        placeOrderBtn.setBackground(Color.RED);
        placeOrderBtn.setOpaque(true);
        placeOrderBtn.setBorderPainted(false);
        placeOrderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeOrderBtn.addActionListener(e -> {
            if (cart.getItemsOrdered().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Your cart is empty!",
                        "Place Order",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Order placed! Total: "
                        + String.format("%.2f $", cart.totalCost()),
                        "Order Confirmed",
                        JOptionPane.INFORMATION_MESSAGE);
                cart.getItemsOrdered().clear();
                refreshItems();
                updateTotal();
            }
        });
        east.add(placeOrderBtn);

        return east;
    }

    private void refreshItems() {

        if (itemsPanel == null) return;

        itemsPanel.removeAll();

        for (Media media : cart.getItemsOrdered()) {

            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

            JLabel info = new JLabel(
                    "<html><b>" + media.getTitle() + "</b>"
                    + "  &nbsp; <i>" + media.getCategory() + "</i>"
                    + "  &nbsp; " + media.getCost() + " $</html>"
            );
            info.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            if (media instanceof Playable) {
                JButton playBtn = new JButton("Play");
                playBtn.addActionListener(e -> {
                    try {
                        ((Playable) media).play();
                        JOptionPane.showMessageDialog(this,
                                "Playing: " + media.getTitle());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                ex.getMessage(),
                                "Player Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });
                buttons.add(playBtn);
            }

            JButton removeBtn = new JButton("Remove");
            removeBtn.addActionListener(e -> {
                cart.removeMedia(media);
                refreshItems();
                updateTotal();
            });
            buttons.add(removeBtn);

            row.add(info, BorderLayout.CENTER);
            row.add(buttons, BorderLayout.EAST);
            itemsPanel.add(row);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void updateTotal() {

        if (totalLabel != null) {
            totalLabel.setText(String.format("%.2f $", cart.totalCost()));
        }
    }
}
