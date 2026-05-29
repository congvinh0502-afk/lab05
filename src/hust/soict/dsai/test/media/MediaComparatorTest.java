package hust.soict.dsai.test.media;

import hust.soict.dsai.aims.media.*;
import java.util.ArrayList;
import java.util.Collections;

public class MediaComparatorTest {

    public static void main(String[] args) {

        ArrayList<Media> mediae =
                new ArrayList<Media>();

        mediae.add(new Book(
                1,
                "Java",
                "Programming",
                20f));

        mediae.add(new Book(
                2,
                "C++",
                "Programming",
                15f));

        mediae.add(new DigitalVideoDisc(
                "Batman",
                "Movie",
                "Nolan",
                120,
                25f));

        mediae.add(new DigitalVideoDisc(
                "Avengers",
                "Movie",
                "Marvel",
                150,
                30f));

        System.out.println("===== Sort by title =====");

        Collections.sort(
                mediae,
                Media.COMPARE_BY_TITLE_COST
        );

        for (Media media : mediae) {
            System.out.println(media);
        }

        System.out.println("\n===== Sort by cost =====");

        Collections.sort(
                mediae,
                Media.COMPARE_BY_COST_TITLE
        );

        for (Media media : mediae) {
            System.out.println(media);
        }
    }
}