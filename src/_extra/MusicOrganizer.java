/**Old code here just in case
 * NOT IN USE ANYMORE
 */
/*
import static org.junit.Assert.*;

public class MusicOrganizer {
    Album rootAlbum = new Album("All Sound Clips"); // Skapar rot-albumet automatiskt då MusicOrganizer skapas

    // Metod för att skapa nytt album
    public void newAlbum(String albumName, Album parentAlbum) {
        new Album(albumName, parentAlbum);
    }

    // Metod för att lägga till ett nytt soundclip
    // Går igenom alla parentAlbum tills det når rootAlbum och lägger till soundclippet i dem
    public void newSoundClip(SoundClip newSoundClip, Album album) {
        // While loopen håller på tills den når rootAlbum som har parentAlbum = null
        while (album != null) {
            // Om soundclippet inte redan finns i albumet läggs det till
            if (!album.getSoundClips().contains(newSoundClip)) {
                album.getSoundClips().add(newSoundClip);
            }
            album = album.getParentAlbum(); // album uppdateras till nästa parentAlbum
        }
    }

    // Metod för att ta bort ett soundclip
    public void removeSoundClip(SoundClip soundClip, Album album) {
        // Kollar om soundclippet finns i albumet och tar bort det. Om det inte finns avbryts metoden
        if (album.getSoundClips().contains(soundClip)) {
            album.getSoundClips().remove(soundClip);
        } else {
            return;
        }
        assertFalse(album.getSoundClips().contains(soundClip));

        // Går igenom alla sub-album till albumet och tar bort soundclippet från dem också
        for (Album subAlbum : album.getSubAlbums()) {
            // Om soundclippet inte finns i sub-albumet avbryts loopen
            if (!subAlbum.getSoundClips().contains(soundClip)) {
                break;
            }
            // Om det finns i sub-albumet tas det bort
            removeSoundClip(soundClip, subAlbum);
            assertFalse(subAlbum.getSoundClips().contains(soundClip));
        }
    }

    // Metod för att ta bort ett album
    public void removeAlbum(Album album) {
        assertNotNull(album.getParentAlbum()); //Kollar att albumet inte är rootAlbum
        // Går igenom alla sub-album till albumet och tar bort dem
        for (Album subAlbum : album.getSubAlbums()) {
            removeAlbum(subAlbum);
        }
        // Tar till slut bort albumet från dess parentAlbums sub-albums lista
        album.getParentAlbum().getSubAlbums().remove(album);
    }
}
*/