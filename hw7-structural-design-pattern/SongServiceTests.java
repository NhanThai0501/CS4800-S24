import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class SongServiceTests {
    private SongService songService;

    @BeforeEach
    void setup() {
        songService = new SongServiceProxy();
    }

    @Test
    void testSearchById() {
        Song song = songService.searchById(1);
        assertNotNull(song, "Song should not be null.");
        assertEquals("Shape of You", song.getTitle(), "Song title should match.");
    }

    @Test
    void testSearchByTitle() {
        List<Song> songs = songService.searchByTitle("Believer");
        assertFalse(songs.isEmpty(), "Should find at least one song.");
        assertEquals(1, songs.size(), "Should find exactly one song.");
        assertEquals("Believer", songs.get(0).getTitle(), "Song title should match.");
    }

    @Test
    void testSearchByAlbum() {
        List<Song> songs = songService.searchByAlbum("Evolve");
        assertFalse(songs.isEmpty(), "Should find songs from 'Evolve' album.");
        assertTrue(songs.stream().allMatch(song -> song.getAlbum().equalsIgnoreCase("Evolve")),
                "All songs should be from the 'Evolve' album.");
    }

    @Test
    void testCachingMechanismById() {
        Song song1 = songService.searchById(1); // First call, caches the result
        Song song2 = songService.searchById(1); // Second call, should hit the cache

        assertSame(song1, song2, "Both song instances should be the same (cached result).");
    }

    @Test
    void testCachingMechanismByTitle() {
        List<Song> songs1 = songService.searchByTitle("Happier"); // First call, caches the result
        List<Song> songs2 = songService.searchByTitle("Happier"); // Second call, should hit the cache

        assertSame(songs1, songs2, "Both song lists should be the same (cached result).");
    }

    @Test
    void testCachingMechanismByAlbum() {
        List<Song> songs1 = songService.searchByAlbum("Different World"); // First call, caches the result
        List<Song> songs2 = songService.searchByAlbum("Different World"); // Second call, should hit the cache

        assertSame(songs1, songs2, "Both song lists should be the same (cached result).");
    }
}
