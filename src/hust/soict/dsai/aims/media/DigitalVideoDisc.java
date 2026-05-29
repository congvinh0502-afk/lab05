package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title,
        String category,
        String director,
        int length,
        float cost) {

    super(++nbDigitalVideoDiscs,
            title,
            category,
            cost,
            length,
            director);
}
    public DigitalVideoDisc(String title) {
        this(title, null, null, 0, 0);
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        this(title, category, null, 0, cost);
    }

    public DigitalVideoDisc(String director, String category,
            String title, float cost) {

        this(title, category, director, 0, cost);
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - "
                + getCategory() + " - "
                + director + " - "
                + length + ": "
                + getCost() + " $";
    }

    public boolean isMatch(String title) {
        return this.getTitle()
                .toLowerCase()
                .contains(title.toLowerCase());
    }
    @Override
public void play() {

    if (this.getLength() <= 0) {

        System.out.println(
                "ERROR: DVD length is non-positive");

        return;
    }

    System.out.println(
            "Playing DVD: " + this.getTitle());

    System.out.println(
            "DVD length: " + this.getLength());
}
}