package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {

    private Media media;

    public MediaStore(Media media) {

        this.media = media;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Title
        JLabel title = new JLabel(media.getTitle());

        title.setFont(new Font("Arial", Font.BOLD, 20));

        title.setAlignmentX(CENTER_ALIGNMENT);

        add(title);

        // Cost
        JLabel cost = new JLabel(media.getCost() + " $");

        cost.setAlignmentX(CENTER_ALIGNMENT);

        add(cost);

        // Button panel
        JPanel container = new JPanel();

        JButton addToCartButton =
        new JButton("Add to cart");

        addToCartButton.addActionListener(e -> {

        JOptionPane.showMessageDialog(
            null,
            media.getTitle() + " added to cart"
    );
});

        container.add(addToCartButton);

        // Play button only if playable
        if (media instanceof Playable) {

            JButton playButton =
        new JButton("Play");

playButton.addActionListener(e -> {

    JOptionPane.showMessageDialog(
            null,
            "Playing " + media.getTitle()
    );
});

            container.add(playButton);
        }

        add(container);
    }
}