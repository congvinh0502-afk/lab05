package hust.soict.dsai.test.Disc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
public class TestPassingParameter {

    public static void main(String[] args) {

        DigitalVideoDisc jungleDVD =
                new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD =
                new DigitalVideoDisc("Cinderella");

        System.out.println("Before swap:");
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
        System.out.println("Cinderella DVD title: " + cinderellaDVD.getTitle());

        swap(jungleDVD, cinderellaDVD);

        System.out.println("\nAfter swap:");
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
        System.out.println("Cinderella DVD title: " + cinderellaDVD.getTitle());

        changeTitle(jungleDVD, cinderellaDVD.getTitle());

        System.out.println("\nAfter changeTitle:");
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
    }

    public static void swap(DigitalVideoDisc o1, DigitalVideoDisc o2) {
        DigitalVideoDisc temp = o1;
        o1 = o2;
        o2 = temp;
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        dvd.setTitle(title);
    }
}