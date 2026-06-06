package hust.soict.dsai.test.Cart;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
public class CartTest {
    public static void main(String[] args) {

        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", "John Musker", 90, 18.99f);

        // Add
        try {
            cart.addMedia(dvd1);
            cart.addMedia(dvd2);
            cart.addMedia(dvd3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Print
        cart.printCart();

        // Search by ID
        System.out.println("Search by ID:");
        cart.searchById(dvd2.getId());

        // Search by Title
        System.out.println("Search by Title:");
        cart.searchByTitle("Star Wars");
    }
}
