package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {

    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {

        this.media = media;
        this.cart = cart;

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

    try {

        cart.addMedia(media);

        JOptionPane.showMessageDialog(
                null,
                media.getTitle() + " added to cart"
        );

    } catch (LimitExceededException ex) {

        JOptionPane.showMessageDialog(
                null,
                ex.getMessage(),
                "Cart Limit Exceeded",
                JOptionPane.ERROR_MESSAGE
        );
    }
});

        container.add(addToCartButton);

        // Play button only if playable
if (media instanceof Playable) {

    JButton playButton =
            new JButton("Play");

    playButton.addActionListener(e -> {

        try {

            Playable playable =
                    (Playable) media;

            playable.play();

            JOptionPane.showMessageDialog(
                    null,
                    "Playing " + media.getTitle()
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Player Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    });

    container.add(playButton);
}

add(container);
}
}