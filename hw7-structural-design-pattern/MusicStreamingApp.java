import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Interface for song service
interface SongService {
    Song searchById(Integer songID);
    List<Song> searchByTitle(String title);
    List<Song> searchByAlbum(String album);
}

// Song class to hold metadata
class Song {
    private Integer songID;
    private String title;
    private String artist;
    private String album;
    private int duration; // in seconds

    public Song(Integer songID, String title, String artist, String album, int duration) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public Integer getSongID() {
        return songID;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Song [" +
               "ID=" + songID +
               ", title='" + title + '\'' +
               ", artist='" + artist + '\'' +
               ", album='" + album + '\'' +
               ", duration=" + duration +
               ']';
    }
}


// Real service implementation
class SongServiceImpl implements SongService {
    private Map<Integer, Song> songs;

    public SongServiceImpl() {
        songs = new HashMap<>();
        // Simulate database
        songs.put(1, new Song(1, "Shape of You", "Ed Sheeran", "Divide", 240));
        songs.put(2, new Song(2, "Believer", "Imagine Dragons", "Evolve", 216));
        songs.put(3, new Song(3, "Thunder", "Imagine Dragons", "Evolve", 187));
        songs.put(4, new Song(4, "Whatever It Takes", "Imagine Dragons", "Evolve", 203));
        songs.put(5, new Song(5, "Perfect", "Ed Sheeran", "Divide", 263));
        songs.put(6, new Song(6, "Happier", "Marshmello", "Happier", 214));
        songs.put(7, new Song(7, "Faded", "Alan Walker", "Different World", 212));
        songs.put(8, new Song(8, "Alone", "Marshmello", "Alone", 214));
        songs.put(9, new Song(9, "On My Way", "Alan Walker", "Different World", 213));
        songs.put(10, new Song(10, "Unity", "Alan Walker", "Different World", 249));
        
    }

    private void simulateNetworkLatency() {
        try {
            Thread.sleep(1000); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Song searchById(Integer songID) {
        simulateNetworkLatency();
        return songs.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        simulateNetworkLatency();
        List<Song> result = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                result.add(song);
            }
        }
        return result;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        simulateNetworkLatency();
        List<Song> result = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                result.add(song);
            }
        }
        return result;
    }
}

// Proxy for caching song data
class SongServiceProxy implements SongService {
    private SongServiceImpl realService;
    private Map<Integer, Song> cacheById;
    private Map<String, List<Song>> cacheByTitle;
    private Map<String, List<Song>> cacheByAlbum;

    public SongServiceProxy() {
        realService = new SongServiceImpl();
        cacheById = new HashMap<>();
        cacheByTitle = new HashMap<>();
        cacheByAlbum = new HashMap<>();
    }

    @Override
    public Song searchById(Integer songID) {
        if (!cacheById.containsKey(songID)) {
            cacheById.put(songID, realService.searchById(songID));
        }
        return cacheById.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        if (!cacheByTitle.containsKey(title.toLowerCase())) {
            cacheByTitle.put(title.toLowerCase(), realService.searchByTitle(title));
        }
        return cacheByTitle.get(title.toLowerCase());
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        if (!cacheByAlbum.containsKey(album.toLowerCase())) {
            cacheByAlbum.put(album.toLowerCase(), realService.searchByAlbum(album));
        }
        return cacheByAlbum.get(album.toLowerCase());
    }
}

// Test driver
public class MusicStreamingApp {
    public static void main(String[] args) {
        SongService songService = new SongServiceProxy();

        // Fetching songs
        System.out.println("Fetching song by ID:");

        for (int i = 1; i <= 10; i++) {
            System.out.println(songService.searchById(i));
        }

        System.out.println("\nFetching songs by title:");
        songService.searchByTitle("Believer").forEach(System.out::println);
        songService.searchByTitle("Happier").forEach(System.out::println);
        songService.searchByTitle("Faded").forEach(System.out::println);

        System.out.println("\nFetching songs by album:");
        songService.searchByAlbum("Evolve").forEach(System.out::println);
        songService.searchByAlbum("Happier").forEach(System.out::println);
        songService.searchByAlbum("Different World").forEach(System.out::println);

    }
}
