package hust.soict.dsai.test.media;
import hust.soict.dsai.aims.media.*;

import java.util.ArrayList;

public class PolymorphismTest {

    public static void main(String[] args) {

        ArrayList<Media> mediae =
                new ArrayList<Media>();

        Book book = new Book(
                1,
                "Java",
                "Programming",
                20f
        );

        DigitalVideoDisc dvd =
                new DigitalVideoDisc(
                        "Batman",
                        "Movie",
                        "Nolan",
                        120,
                        15f
                );

        CompactDisc cd =
                new CompactDisc(
                        2,
                        "Best Hits",
                        "Music",
                        25f,
                        0,
                        "Director",
                        "Taylor Swift"
                );

        mediae.add(book);
        mediae.add(dvd);
        mediae.add(cd);

        for (Media media : mediae) {
            System.out.println(media.toString());
        }
    }
}