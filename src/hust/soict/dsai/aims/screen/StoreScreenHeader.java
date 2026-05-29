package hust.soict.dsai.aims.screen;

import javax.swing.*;
import java.awt.*;

public class StoreScreenHeader extends JPanel {

    public StoreScreenHeader() {

        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("AIMS");

        title.setFont(new Font("Arial", Font.BOLD, 50));

        title.setForeground(Color.CYAN);

        // Cart button
        JButton cartButton = new JButton("View Cart");

        JPanel rightPanel = new JPanel();

        rightPanel.add(cartButton);

        add(title, BorderLayout.WEST);

        add(rightPanel, BorderLayout.EAST);
    }
}