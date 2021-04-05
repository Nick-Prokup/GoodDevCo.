package CSE201;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

public class Account extends JFrame implements ActionListener {
	String name, email, dob;
	private String pswd;

	public static void main(String[] args) {
		new LoginPage();

	}

	Account(String name, String email, String pswd, String dob) {
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
	
	/**
	 * Base home page method
	 */
	public static void HomePage() {
		JFrame homePage;
		JPanel acctPanel = new JPanel(new GridLayout(5,1)), panel = new JPanel(), searchPanel = new JPanel(new GridLayout(3,1)),
				songsPanel = new JPanel(), mainPanel = new JPanel(new BorderLayout());
		JLabel genre, era, songs, account, search;
		
		homePage = new JFrame();
		
		homePage.add(mainPanel);

		// display labels 
		genre = new JLabel("Search by Genre:");
		era = new JLabel("Search by Era:");
		songs = new JLabel("Liked Songs: \t\t\t");
		account = new JLabel("Your Account: ");
		search = new JLabel("Search for song: ");
		
		// define options
		String[] genres = { "Alernative", "Country", "Hip Hop / Rap", "Rock" };
		final JComboBox<String> genreBox = new JComboBox<String>(genres);
		
		JButton profile = new JButton("Name");
		JButton type = new JButton("User Type");
		JButton playlists = new JButton("Playlists");
		JButton albums = new JButton("Albums?");
		JButton displayBox = new JButton("Display Data Base Info Here");
		
		// add box
		genreBox.setVisible(true);
				
		JButton searchB = new JButton("Search");
		
		
		String[] eras = { "70s", "80s", "90s", "2000's", "2010s", "Current" };
		final JComboBox<String> eraBox = new JComboBox<String>(eras);
		
		// add boxes and panels
		eraBox.setVisible(true);
		
		// add the variables to the panels
		panel.add(genre);
		panel.add(genreBox);
		panel.add(searchB);
		panel.add(era);
		panel.add(eraBox);
		panel.add(searchB);
		
		songsPanel.add(songs);
		
		acctPanel.add(account);
		acctPanel.add(profile);
		acctPanel.add(type);
		acctPanel.add(playlists);
		acctPanel.add(albums);
		
		searchPanel.add(search);
		searchPanel.add(new JTextField());
		searchPanel.add(displayBox);

		
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
}

class Artist {

}

class Listener {

}

class Admin {

	public void editAccount() {

	}

}

}
