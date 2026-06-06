package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;

    private ArrayList<Media> itemsOrdered =
        new ArrayList<Media>();

    public void addMedia(Media media) throws LimitExceededException {

        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {

            throw new LimitExceededException(
                "ERROR: The number of media has reached its limit of "
                + MAX_NUMBERS_ORDERED
            );
        }

        if (!itemsOrdered.contains(media)) {

            itemsOrdered.add(media);

            System.out.println("The media has been added");

        } else {

            System.out.println("The media already exists");
        }
    }
    

   public void removeMedia(Media media) {

    if (itemsOrdered.contains(media)) {

        itemsOrdered.remove(media);

        System.out.println("The media has been removed");

    } else {

        System.out.println("The media is not found");
    }
}

    // Total cost
    public float totalCost() {

    float total = 0;

    for (Media media : itemsOrdered) {
        total += media.getCost();
    }

    return total;
}
    
    //    printCart
    public void printCart() {

    System.out.println("***********************CART***********************");

    System.out.println("Ordered Items:");

    for (int i = 0; i < itemsOrdered.size(); i++) {

        System.out.println((i + 1) + ". "
                + itemsOrdered.get(i).toString());
    }

    System.out.println("Total cost: " + totalCost());

    System.out.println("***************************************************");
}
    //    search theo id
    public void searchById(int id) {

    for (int i = 0; i < itemsOrdered.size(); i++) {

        if (itemsOrdered.get(i).getId() == id) {

            System.out.println("Found: "
                    + itemsOrdered.get(i));

            return;
        }
    }

    System.out.println("Not found");
}
    // search theo title
    public void searchByTitle(String title) {

    boolean found = false;

    for (int i = 0; i < itemsOrdered.size(); i++) {

        if (itemsOrdered.get(i)
                .getTitle()
                .toLowerCase()
                .contains(title.toLowerCase())) {

            System.out.println(itemsOrdered.get(i));

            found = true;
        }
    }

    if (!found) {
        System.out.println("Not found");
    }
}
public ArrayList<Media> getItemsOrdered() {
    return itemsOrdered;
}

}
