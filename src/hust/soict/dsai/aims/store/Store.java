package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {

    private ArrayList<Media> itemsInStore =
            new ArrayList<Media>();

    // Add media
    public void addMedia(Media media) {

        if (!itemsInStore.contains(media)) {

            itemsInStore.add(media);

            System.out.println("The media has been added to store");

        } else {

            System.out.println("The media already exists in store");
        }
    }

    // Remove media
    public void removeMedia(Media media) {

        if (itemsInStore.contains(media)) {

            itemsInStore.remove(media);

            System.out.println("The media has been removed from store");

        } else {

            System.out.println("The media is not found in store");
        }
    }

    // Print store
    public void printStore() {

        System.out.println("****************STORE****************");

        for (Media media : itemsInStore) {
            System.out.println(media.toString());
        }

        System.out.println("*************************************");
    }

    // Getter
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}