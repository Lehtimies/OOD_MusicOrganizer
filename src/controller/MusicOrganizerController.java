package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Album;
import model.SoundClip;
import model.SoundClipBlockingQueue;
import model.SoundClipLoader;
import model.SoundClipPlayer;
import view.MusicOrganizerWindow;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	/**
	 * Adds an album to the Music Organizer
	 */

	public MusicOrganizerController() {

		// TODO: Create the root album for all sound clips
		root = new Album();

		// Create the blocking queue
		queue = new SoundClipBlockingQueue();

		// Create a separate thread for the sound clip player and start it

		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album

		for (SoundClip clip : clips) {
			root.addSoundClip(clip);
		}

		return clips;
	}

	public void registerView(MusicOrganizerWindow view) {
		this.view = view;
	}

	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}

	public void addNewAlbum(){ //TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		String albumName = view.promptForAlbumName();
		if(albumName != null) {
			Album parentAlbum = view.getSelectedAlbum();
			Album newAlbum = new Album(albumName, parentAlbum);
			if (parentAlbum != null) {
				parentAlbum.addAlbum(newAlbum);
				view.onAlbumAdded(newAlbum);
			}
		}
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ //TODO Update parameters if needed
		// TODO: Add your code here
		Album selectedAlbum = view.getSelectedAlbum();
		selectedAlbum.getParentAlbum().removeAlbum(selectedAlbum);
		view.onAlbumRemoved();
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		List<SoundClip> selectedClips = view.getSelectedSoundClips();
		Album selectedAlbum = view.getSelectedAlbum();
		if (selectedAlbum != null) {
			selectedAlbum.addSoundClips(new HashSet<>(selectedClips));
		}
		view.onClipsUpdated();
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		List<SoundClip> selectedClips = view.getSelectedSoundClips();
		Album selectedAlbum = view.getSelectedAlbum();
		selectedAlbum.removeSoundClips(new HashSet<>(selectedClips));
		view.onClipsUpdated();
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		queue.enqueue(l);
		for(int i=0;i<l.size();i++) {
			view.displayMessage("Playing " + l.get(i));
		}
	}
}
