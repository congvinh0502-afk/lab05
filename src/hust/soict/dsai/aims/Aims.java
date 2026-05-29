package hust.soict.dsai.aims;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.screen.StoreScreen;
import hust.soict.dsai.aims.store.Store;

public class Aims {

    public static void main(String[] args) {

        Store store = new Store();

        DigitalVideoDisc dvd1 =
                new DigitalVideoDisc(
                        "Batman",
                        "Movie",
                        "Nolan",
                        120,
                        20f
                );

        DigitalVideoDisc dvd2 =
                new DigitalVideoDisc(
                        "Avengers",
                        "Movie",
                        "Marvel",
                        150,
                        25f
                );

        Book book1 =
                new Book(
                        1,
                        "Java Programming",
                        "Education",
                        15f
                );

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);

        new StoreScreen(store);
    }
}