package hust.soict.dsai.test.Store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
public class StoreTest {

    public static void main(String[] args) {

        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin");

        // Test add
       store.addMedia(dvd1);
    store.addMedia(dvd2);
        store.addMedia(dvd3);

        // Test remove
        store.removeMedia(dvd2);
        store.removeMedia(dvd3);
    }
}
