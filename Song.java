package CSE201;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Song {
	private String name, artist, genre, album;
	private int year;
	public Song(String name, String artist, String album, int year, String genre) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.genre = genre;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	public int getYear() {
		return this.year;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public String toString() {
		return this.name + "," + this.artist + "," + this.year + "," + this.genre;
	}
	
}
