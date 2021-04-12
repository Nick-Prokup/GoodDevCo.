package CSE201;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Account extends JFrame implements ActionListener {
	String name, email, dob, accType;
	private String pswd;

	public static void main(String[] args) {
		new LoginPage();

	}

	Account(String name, String email, String pswd, String dob, String type) {
		this.name = name;
		this.email = email;
		this.pswd = pswd;
		this.dob = dob;
		this.accType = type;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPswd() {
		return this.pswd;
	}

	public String getDob() {
		return this.dob;
	}
	
	public String getAccType() {
		return this.accType;
	}
	
	public String toString() {
		return this.name + "," + this.email + "," + this.pswd + "," + this.dob + "," + this.accType;
	}
	
	/**
	 * Base home page method
	 */
	public static void HomePage() {
		JFrame homePage;
		JPanel acctPanel = new JPanel(new GridLayout(5,1)), panel = new JPanel(), searchPanel = new JPanel(new FlowLayout()),
				tempSearchPanel = new JPanel(), songsPanel = new JPanel(), mainPanel = new JPanel(new BorderLayout());
		JLabel genre, era, songs, account, search;
		
		homePage = new JFrame();
		
		homePage.add(mainPanel);

		// display labels 
		genre = new JLabel("Search by Genre:");
		era = new JLabel("Search by Era:");
		songs = new JLabel("Liked Songs: \n");
		account = new JLabel("Your Account: ");
		search = new JLabel("Search results: ");
		
		JButton profile = new JButton("Name");
		JButton type = new JButton("Add Songs");
		type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				songPanel();
			}
			
		});
		
		JButton playlists = new JButton("Playlists");
		JButton albums = new JButton("Albums");
		
		// define options
		String[] genres = { "Alernative", "Country", "Hip Hop / Rap", "Rock" };
		final JComboBox<String> genreBox = new JComboBox<String>(genres);

		String[] eras = { "70s", "80s", "90s", "2000's", "2010s", "Current" };
		final JComboBox<String> eraBox = new JComboBox<String>(eras);
		
		// add box
		genreBox.setVisible(true);

		// add boxes and panels
		eraBox.setVisible(true);
		
		String gSelection = (String)genreBox.getSelectedItem();
		String eSelection = (String)eraBox.getSelectedItem();

		// search button
		JButton searchB = new JButton("Search");
		searchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSong(gSelection, eSelection);
			}

		});
		
		tempSearchPanel.add(search);		
		String rows[][] = { {" Name ", " Artist ", " Year ", " Genre "},
				{" info ", " display ", " here ", " after "},
				{" info ", " display ", " here ", " after "}, 
				{" info ", " display ", " here ", " after "}}; 
		String columns[] = {" Name ", " Artist ", " Year ", " Genre "};
		JTable displayBox = new JTable(rows, columns);		 
		tempSearchPanel.add(displayBox);
		
		JTextField tf1 = new JTextField("Search Bar\t\t\t\t\t\t\t\t\t\t\t\t");
		searchPanel.add(tf1);
		searchPanel.add(tempSearchPanel, BorderLayout.CENTER);
		
		// add the variables to the panels
		panel.add(genre);
		panel.add(genreBox);
		panel.add(searchB);
		panel.add(era);
		panel.add(eraBox);
		panel.add(searchB);
		
		// songs list (liked songs)
		songsPanel.add(songs);
		DefaultListModel<String> liked = new DefaultListModel<>();
		liked.addElement("\n Song Name 1 \n");
		liked.addElement("Song Name 2 \n");
		liked.addElement("Song Name 3 \n");
		liked.addElement("Song Name 4 \n");
		JList<String> list = new JList<>(liked);
		songsPanel.add(list);
		
		// account panel info addition
		acctPanel.add(account);
		acctPanel.add(profile);
		acctPanel.add(type);
		acctPanel.add(playlists);
		acctPanel.add(albums);

		
		// homePage panel adding
		mainPanel.add(panel, BorderLayout.NORTH);
		mainPanel.add(songsPanel, BorderLayout.EAST);
		mainPanel.add(acctPanel, BorderLayout.WEST);
		mainPanel.add(searchPanel, BorderLayout.CENTER);
		
		homePage.setTitle("GoodTunez Home");
		homePage.setSize(650, 450);
		homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
		homePage.setVisible(true);

		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void findSong(String genre, String era) {
		// check file for songs of these parameters
		Scanner in = new Scanner("SongList.txt");
		while (in.hasNextLine()) {
			String line = in.nextLine();

			if (line.contains(genre) && line.contains(era)) {
				System.out.println(line);
			}
		}

		in.close();
		
		// populate variables with the song info to be in displayBox
		System.out.println(genre + " " + era);
	}
	
	public static void writeSongToFile(Song song) throws FileNotFoundException {
		try (FileWriter fw = new FileWriter("SongList.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(song.toString());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void addSong(String name, String artist, int year, String genre) throws FileNotFoundException {
		Song song = new Song(name, artist, year, genre);
		writeSongToFile(song);
	}
	
	public static void songPanel() {
		JFrame addSong;
		JPanel info;
		JLabel name_label, artist_label, year_label, genre_label, sub_label;
		JTextField name_text, artist_text, year_text, genre_text;
		JButton submit;
		
		name_label = new JLabel("Enter name of song: ");
		artist_label = new JLabel("Enter artist name: ");
		year_label = new JLabel("Enter year song was made: ");
		genre_label = new JLabel("Enter song genre: ");
		sub_label = new JLabel();
		
		name_text = new JTextField();
		artist_text = new JTextField();
		year_text = new JTextField();
		genre_text = new JTextField();
		
		submit = new JButton("Add song to database");
		
		info = new JPanel(new GridLayout(6,2));
		info.add(name_label);
		info.add(name_text);
		info.add(artist_label);
		info.add(artist_text);
		info.add(year_label);
		info.add(year_text);
		info.add(genre_label);
		info.add(genre_text);
		
		info.add(sub_label);
		info.add(submit);
		
		addSong = new JFrame();
		addSong.add(info);
		
		addSong.setTitle("GoodTunez Create Account");
		addSong.setSize(650, 450);
		addSong.setExtendedState(JFrame.MAXIMIZED_BOTH);
		addSong.setVisible(true);
		addSong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addSong(name_text.getText(), artist_text.getText(), Integer.parseInt(year_text.getText()), genre_text.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				addSong.dispose();
			}
		});
	}
}

class Artist {
	String name, email, dob;
	private String pswd;
	Artist(String name, String email, String pswd, String dob) {
		this.name = name;
		this.email = email;
		this.pswd = pswd;
		this.dob = dob;
	}
	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPswd() {
		return this.pswd;
	}

	public String getDob() {
		return this.dob;
	}
	

}

class Listener {
	String name, email, dob;
	private String pswd;
	Listener(String name, String email, String pswd, String dob) {
		this.name = name;
		this.email = email;
		this.pswd = pswd;
		this.dob = dob;
		}
	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPswd() {
		return this.pswd;
	}

	public String getDob() {
		return this.dob;
	}
 }

class Admin {

	public void editAccount() {

	}
}
