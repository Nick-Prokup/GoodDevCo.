package CSE201;
/*
 * Song class contains the constructor, getter, and toString methods for the song object
 * 
 * @author GooDevelopment Co. (C) 2021
 */
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Song {
	// declare the variables
	private String name, artist, album, genre;
	private int year;
	
	/**
	 * Full constructor to create the song object complete with all attributes
	 * 
	 * @param name, String to define the name attribute 
	 * @param artist, String to define the artist attribute 
	 * @param album, String to define the album attribute 
	 * @param year, int to define the year attribute 
	 * @param genre, String to define the genre attribute 
	 */
	public Song(String name, String artist, String album, int year, String genre) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.genre = genre;
	}
	
	/**
	 * Accessor method to get the name of song
	 * @return name, String of name 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Accessor method to get the artist of song
	 * @return artist, String of artist 
	 */
	public String getArtist() {
		return this.artist;
	}
	
	/**
	 * Accessor method to get the album of song
	 * @return album, String of album 
	 */
	public String getAlbum() {
		return this.album;
	}
	
	/**
	 * Accessor method to get the year of song
	 * @return year, int of year 
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * Accessor method to get the genre of song
	 * @return genre, String of genre 
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * Method will return the attributes of song separated by commas
	 * 
	 * @return String of song info
	 */
	@Override
	public String toString() {
		return this.name + "," + this.artist + "," + this.album + "," + this.year + "," + this.genre;
	}
	
}
