package hust.soict.dsai.aims.media;
import hust.soict.dsai.aims.exception.PlayerException;
import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {

    private String artist;

    private ArrayList<Track> tracks =
            new ArrayList<Track>();

    public CompactDisc(int id, String title,
            String category, float cost,
            int length, String director,
            String artist) {

        super(id, title, category,
                cost, length, director);

        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    // Add track
    public void addTrack(Track track) {

        if (!tracks.contains(track)) {

            tracks.add(track);

            System.out.println("Track added");

        } else {

            System.out.println("Track already exists");
        }
    }

    // Remove track
    public void removeTrack(Track track) {

        if (tracks.contains(track)) {

            tracks.remove(track);

            System.out.println("Track removed");

        } else {

            System.out.println("Track not found");
        }
    }

    // Total length of CD
    @Override
    public int getLength() {

        int totalLength = 0;

        for (Track track : tracks) {
            totalLength += track.getLength();
        }

        return totalLength;
    }

    @Override
public void play() throws PlayerException {

    if (this.getLength() <= 0) {

        throw new PlayerException(
                "ERROR: CD length is non-positive!"
        );
    }

    System.out.println("Playing CD: "
            + this.getTitle());

    System.out.println("CD length: "
            + this.getLength());

    for (Track track : tracks) {

        try {

            track.play();

        } catch (PlayerException e) {

            throw e;
        }
    }
}

    @Override
    public String toString() {

    return "CD - " + getTitle()
            + " - " + getCategory()
            + " - " + getArtist()
            + " - " + getLength()
            + ": " + getCost() + " $";
}
}