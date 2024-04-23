package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album {
    private Album parentAlbum; // Final värde för parentAlbum, är final eftersom parent-albumet inte ska kunna ändras
    private String name; // String för albumets namn
    private Set<SoundClip> SoundClips; // Lista för soundclips
    private List<Album> subAlbums; // Lista för sub-albumen

    // Constructor som skapar rot-albumet med namnet "All Sound Clips"
    /**OBS! ANVÄND ENDAST FÖR ATT SKAPA ROT-ALBUMET**/
    public Album(String albumName) {
        this(albumName, null);
    }

    // Constructor som skapar sub-album
    public Album(String albumName, Album parent) {
        name = albumName;
        parentAlbum = parent;
        subAlbums = new ArrayList<>();
        SoundClips = new HashSet<>();

        /* Kollar om det implementeras senare
        // lägger till sub-albumet i parentAlbums sub-albums lista
        if (parentAlbum != null) {
            parentAlbum.getSubAlbums().add(this);
        }*/
    }

    public void addAlbum(Album a) {
        subAlbums.add(a);
        a.parentAlbum = this;
    }

    public void removeAlbum(Album a) {
        removeAlbums(Set.of(a));
    }

    public void removeAlbums(Set<Album> albums) {
        subAlbums.removeAll(albums);
        for (Album a : albums) {
            a.parentAlbum = null;
        }
    }

    public void addSoundClip(SoundClip s) {
        addSoundClips(Set.of(s));
    }

    public void addSoundClips(Set<SoundClip> clips) {
        SoundClips.addAll(clips);
        if (parentAlbum != null) {
            parentAlbum.addSoundClips(clips);
        }
    }

    public void removeSoundClip(SoundClip s) {
        removeSoundClips(Set.of(s));
    }

    public void removeSoundClips(Set<SoundClip> clips) {
        SoundClips.removeAll(clips);
        for (Album a : subAlbums) {
            a.removeSoundClips(clips);
        }
    }

    /** @return - albumets namn */
    public String toString() {
        return name;
    }

    /** @return - parentAlbum */
    public Album getParentAlbum() {
        return parentAlbum;
    }


    /** @return - listan av SoundClips */
    public Set<SoundClip> getSoundClips() {
        return SoundClips;
    }

    /** @return - listan av sub-album */
    public List<Album> getSubAlbums() {
        return subAlbums;
    }
}
