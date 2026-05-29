package hust.soict.dsai.aims.media;

public class Track implements Playable {

    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

@Override
public void play() {

    if (this.getLength() <= 0) {

        System.out.println(
                "ERROR: Track length is non-positive");

        return;
    }

    System.out.println(
            "Playing Track: " + this.getTitle());

    System.out.println(
            "Track length: " + this.getLength());
}

@Override
public boolean equals(Object obj) {

    if (this == obj) {
        return true;
    }

    if (!(obj instanceof Track)) {
        return false;
    }

    Track other = (Track) obj;

    return this.title.equals(other.title)
            && this.length == other.length;
}

public static void main(String[] args) {

    Track t1 = new Track("Song", 200);
    Track t2 = new Track("Song", 200);

    CompactDisc cd = new CompactDisc(
            1,
            "Best Hits",
            "Music",
            20f,
            0,
            "Director",
            "Artist"
    );

    cd.addTrack(t1);
    cd.addTrack(t2);

    cd.play();
    System.out.println(t1.equals(t2));
}
}