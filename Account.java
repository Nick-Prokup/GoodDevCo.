package CSE201;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Account extends JFrame implements ActionListener {
	// global variables
	String name, email, dob, accType;
	private String pswd;

	public static void main(String[] args) {
		new LoginPage();

	}

	/**
	 * This is the full constructor for the account class
	 * 
	 * @param name,  String variable representing account name
	 * @param email, String variable representing account email
	 * @param pswd,  String variable representing account pswd
	 * @param dob,   String variable representing account date of birth
	 * @param type,  String variable representing account type
	 */
	Account(String name, String email, String pswd, String dob, String type) {
		this.name = name;
		this.email = email;
		this.pswd = pswd;
		this.dob = dob;
		this.accType = type;
	}

	/**
	 * Getter method of the name variable
	 * 
	 * @return name, String of current name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter method of the email variable
	 * 
	 * @return email, String of current email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Getter method of the pswd variable
	 * 
	 * @return pswd, String of current pswd
	 */
	public String getPswd() {
		return this.pswd;
	}

	/**
	 * Getter method of the date of birth variable
	 * 
	 * @return dob, String of current date of birth
	 */
	public String getDob() {
		return this.dob;
	}

	/**
	 * Getter method of the type variable
	 * 
	 * @return type, String of current type
	 */
	public String getAccType() {
		return this.accType;
	}

	/**
	 * Overrides the toString method of string class to give a singular line
	 * containing account info for text file
	 */
	@Override
	public String toString() {
		return this.name + "," + this.email + "," + this.pswd + "," + this.dob + "," + this.accType;
	}

	/**
	 * Base home page method
	 */
	public static void HomePage() {
		// declaring the Java swing attributes used for the GUI
		JFrame homePage;
		JPanel acctPanel = new JPanel(new GridLayout(5, 1)), panel = new JPanel(),
				searchPanel = new JPanel(new FlowLayout()), tempSearchPanel = new JPanel(), songsPanel = new JPanel(),
				mainPanel = new JPanel(new BorderLayout());
		JLabel genre, era, songs, account, search;
		JButton about = new JButton("About us/ help");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					aboutUsPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// defines account for the current user
		Account currUser = LoginPage.currUser;

		homePage = new JFrame();

		homePage.add(mainPanel);

		// display labels
		genre = new JLabel("Search by Genre:");
		era = new JLabel("Search by Era:");
		songs = new JLabel("Liked Songs: \n");
		account = new JLabel("Your Account: ");
		search = new JLabel("Search results: ");

		// button for account info, followed by the action listener for the click to
		// have functionality
		JButton profile = new JButton("Account information");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountPanel(currUser);
			}
		});

		// button for deciding which account page to pull up, followed by the action
		// listener for the click to have functionality
		JButton addSong = new JButton();
		if (currUser.getAccType().equals("Artist")) {
			account = new JLabel(currUser.name + ":  Artist");
			addSong = new JButton("Add Songs");
			addSong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					songPanel();
				}

			});
		} else if (currUser.getAccType().equals("Listener")) {
			account = new JLabel(currUser.name + ":  Listener");
			addSong = new JButton("Playlist");

			addSong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						playlistPanel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		// buttons for viewing playlists and albums NOT YET IMPLEMENTED
		JButton songList = new JButton("Song List");

		songList = new JButton("Song List");

		songList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					songList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton albums = new JButton("Albums");
		albums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					albumPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// define options for the search from boxes
		String[] genres = { "Alernative", "Country", "Hip Hop / Rap", "Rock" };
		final JComboBox<String> genreBox = new JComboBox<String>(genres);

		String[] eras = { "70s", "80s", "90s", "2000's", "2010s", "Current" };
		final JComboBox<String> eraBox = new JComboBox<String>(eras);

		// add box
		genreBox.setVisible(true);

		// add boxes and panels
		eraBox.setVisible(true);

		// search button box
		JButton searchBox = new JButton("Search b");

		// action listener to take the selected box options and search for results
		searchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gSelection = (String) genreBox.getSelectedItem();
				String eSelection = (String) eraBox.getSelectedItem();
				try {
					findSong(gSelection, eSelection);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		// search button
		JButton searchB = new JButton("Search");

		// defining the search box functions and panels
		JTextField searchBar = new JTextField(60);
		searchPanel.add(searchBar);
		searchPanel.add(searchB);
		searchPanel.add(tempSearchPanel, BorderLayout.CENTER);

		// testing JLabel to print search functionality
		JLabel test = new JLabel(searchBar.getText());

		searchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPanel.add(test);
				String searchQuery;
				searchQuery = searchBar.getText();
				// send the search into database find method
			}

		});

		// add the search button
		tempSearchPanel.add(search);

		// declaring a table for results to be generated
		String rows[][] = { { " Name ", " Artist ", " Year ", " Genre " },
				{ " info ", " display ", " here ", " after " }, { " info ", " display ", " here ", " after " },
				{ " info ", " display ", " here ", " after " } };
		String columns[] = { " Name ", " Artist ", " Year ", " Genre " };
		JTable displayBox = new JTable(rows, columns);
		tempSearchPanel.add(displayBox);

		// add the variables to the panels
		panel.add(genre);
		panel.add(genreBox);
		panel.add(searchBox);
		panel.add(era);
		panel.add(eraBox);

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
		acctPanel.add(addSong);
		acctPanel.add(songList);
		acctPanel.add(albums);

		// homePage panel adding
		mainPanel.add(panel, BorderLayout.NORTH);
		mainPanel.add(songsPanel, BorderLayout.EAST);
		mainPanel.add(acctPanel, BorderLayout.WEST);
		mainPanel.add(searchPanel, BorderLayout.CENTER);
		mainPanel.add(about, BorderLayout.SOUTH);

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

	/**
	 * Helper method to compute the era that a particular song date was made
	 * 
	 * @param era, String of the exact year a song was made
	 * @return String of the era the year falls under
	 */
	public static String computeEra(String era) {
		int newEra = Integer.parseInt(era);

		if (newEra < 1980) {
			return "70s";
		} else if (newEra >= 1980 && newEra < 1990) {
			return "80s";
		} else if (newEra >= 1990 && newEra < 2000) {
			return "90s";
		} else if (newEra >= 2000 && newEra < 2010) {
			return "2000s";
		} else if (newEra >= 2010 && newEra < 2020) {
			return "2010s";
		} else {
			return "Current";
		}
	}

	/**
	 * Method to find the song from the list of songs in the database according to
	 * the search boxes
	 * 
	 * @param genre, string
	 * @param era
	 * @throws FileNotFoundException
	 */
	public static void findSong(String genre, String era) throws FileNotFoundException {
		// check file for songs of these parameters
		File songFile = new File("SongList.txt");
		Scanner in = new Scanner(songFile);
		// loop through list to acquire the song info as a string
		while (in.hasNextLine()) {
			String line = in.nextLine();

			// loop through song to find the era
			// && line.contains(computeEra(era)

			// if statement to check if the song fits the criteria
			if (line.contains(genre)) {
				// printing song for now, *** later need to attach to the display table on main
				// page
				System.out.println(line);
			}
		}
		// close the file reader connection
		in.close();
	}

	/**
	 * Method that will write the song info to the song list file
	 * 
	 * @param song, Song that will be added to the list
	 * @throws FileNotFoundException
	 */
	public static void writeSongToFile(Song song) throws FileNotFoundException {
		try (FileWriter fw = new FileWriter("SongList.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(song.toString());

			fw.close();
			bw.close();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Method that will add the new song by using the attributes listed from the
	 * creation page
	 * 
	 * @param name
	 * @param artist
	 * @param album
	 * @param year
	 * @param genre
	 * @throws FileNotFoundException
	 */
	public static void addSong(String name, String artist, String album, int year, String genre)
			throws FileNotFoundException {
		Song song = new Song(name, artist, album, year, genre);
		writeSongToFile(song);
	}

	public static void replaceAccInfo(Account acc, Account newAcc) throws FileNotFoundException {
		try (FileWriter fw = new FileWriter("AccountList.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw);
				BufferedReader in = new BufferedReader(new FileReader("AccountList.txt"));) {

			StringBuffer inputBuffer = new StringBuffer();
			String line;
			// while there is still data in the account list
			while ((line = in.readLine()) != null) {
				// reading each account by commas to find email and password as an array element
				if (line.equals(acc.toString())) {
					line = newAcc.toString();
				} else {

					inputBuffer.append(line);
					inputBuffer.append("\n");
				}
			}
			in.close();
			FileOutputStream outFile = new FileOutputStream("AccountList.txt");
			outFile.write(inputBuffer.toString().getBytes());
			fw.write(newAcc.toString());

			outFile.close();
			fw.close();
			bw.close();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error appending new account information");
		}
	}

	public static void accountPanel(Account currUser) {
		JFrame accFrame;
		JPanel info, panel;
		JLabel info_label, email_label, pswd_label, name_label, dob_label, sub_button;
		JTextField email_text, dob_text, name_text, pswd_text;
		JButton update;

		info_label = new JLabel(
				"Edit any changes you want in the text field and update using the button at the bottom");
		info = new JPanel();
		info.add(info_label);

		name_label = new JLabel("Listed name: ");
		email_label = new JLabel("Listed email: ");
		pswd_label = new JLabel("Listed password: ");
		dob_label = new JLabel("Listed birthday: ");

		name_text = new JTextField(currUser.getName());
		email_text = new JTextField(currUser.getEmail());
		pswd_text = new JTextField(currUser.getPswd());
		dob_text = new JTextField(currUser.getDob());

		sub_button = new JLabel("Update information");
		update = new JButton("Update");

		panel = new JPanel(new GridLayout(6, 2));
		panel.add(name_label);
		panel.add(name_text);
		panel.add(email_label);
		panel.add(email_text);
		panel.add(pswd_label);
		panel.add(pswd_text);
		panel.add(dob_label);
		panel.add(dob_text);

		panel.add(sub_button);
		panel.add(update);

		accFrame = new JFrame();
		accFrame.add(info);
		accFrame.add(panel);

		accFrame.setTitle("Account Information");
		accFrame.setSize(650, 450);
		accFrame.setVisible(true);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account update = new Account(name_text.getText(), email_text.getText(), pswd_text.getText(),
						dob_text.getText(), currUser.getAccType());
				try {
					replaceAccInfo(currUser, update);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				accFrame.dispose();
			}

		});
		accFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void songPanel() {
		JFrame addSong;
		JPanel info;
		JLabel name_label, artist_label, album_label, year_label, genre_label, sub_label;
		JTextField name_text, artist_text, album_text, year_text, genre_text;
		JButton submit;

		name_label = new JLabel("Enter name of song: ");
		artist_label = new JLabel("Enter artist name: ");
		album_label = new JLabel("Album name: ");
		year_label = new JLabel("Enter year song was made: ");
		genre_label = new JLabel("Enter song genre: ");
		sub_label = new JLabel();

		name_text = new JTextField();
		artist_text = new JTextField();
		album_text = new JTextField();
		year_text = new JTextField();
		genre_text = new JTextField();

		submit = new JButton("Add song to database");

		info = new JPanel(new GridLayout(7, 2));
		info.add(name_label);
		info.add(name_text);
		info.add(artist_label);
		info.add(artist_text);
		info.add(album_label);
		info.add(album_text);
		info.add(year_label);
		info.add(year_text);
		info.add(genre_label);
		info.add(genre_text);

		info.add(sub_label);
		info.add(submit);

		addSong = new JFrame();
		addSong.add(info);

		addSong.setTitle("GoodTunez Add Song");
		addSong.setSize(650, 450);
		addSong.setExtendedState(JFrame.MAXIMIZED_BOTH);
		addSong.setVisible(true);
		addSong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addSong(name_text.getText(), artist_text.getText(), album_text.getText(),
							Integer.parseInt(year_text.getText()), genre_text.getText());
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

	public static Song searchBarReturn(String song) {
		return null;
	}

	public static void songList() throws IOException {
		JFrame listFrame;
		JPanel listPanel;
		JTable songTable;
		DefaultTableModel model = new DefaultTableModel();
		JScrollPane scrollSongs;

		songTable = new JTable(model);
		model.addColumn("Song");
		model.addColumn("Artist");
		model.addColumn("Album");
		model.addColumn("Year");
		model.addColumn("Genre");

		listFrame = new JFrame();
		listPanel = new JPanel();

		scrollSongs = new JScrollPane(songTable);
		scrollSongs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSongs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		try {
			BufferedReader in = new BufferedReader(new FileReader("SongList.txt"));
			String inStr;
			String[] songInfo;
			while ((inStr = in.readLine()) != null) {
				songInfo = inStr.split(",");
				model.addRow(songInfo);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listPanel.add(scrollSongs);
		listFrame.add(listPanel);

		listFrame.setTitle("GoodTunez Song List");
		listFrame.setSize(650, 450);
		listFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		listFrame.setVisible(true);
		listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void playlistPanel() throws IOException {
		JFrame playFrame = new JFrame();
		JPanel panel = new JPanel(new BorderLayout());
		JPanel inner = new JPanel(new GridLayout(0, 2));

		JButton newPlay = new JButton("Create new playlist");

		try {
			BufferedReader in = new BufferedReader(new FileReader("PlaylistList.txt"));
			String inStr;
			String[] songInfo;
			int i = 0;
			while ((inStr = in.readLine()) != null) {
				songInfo = inStr.split(",");
				JButton view = new JButton("View playlist");
				inner.add(view);
				JLabel playInfo = new JLabel(songInfo[0] + "'s playlist: " + songInfo[1]);
				inner.add(playInfo);
				panel.add(inner, BorderLayout.CENTER);

			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel.add(newPlay, BorderLayout.NORTH);
//		panel.add(inner, BorderLayout.CENTER);
		playFrame.add(panel);

		playFrame.setTitle("GoodTunez Song List");
		playFrame.setSize(650, 450);
		playFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		playFrame.setVisible(true);
		playFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void aboutUsPanel() throws IOException {
		JFrame aboutFrame = new JFrame();
		JPanel panel = new JPanel();
		JTextArea aboutUs = new JTextArea();
		aboutUs.setEditable(false);

		try (BufferedReader in = new BufferedReader(new FileReader("AboutUs.txt"));) {
			String inStr;
			while ((inStr = in.readLine()) != null) {
				aboutUs.append(inStr);
				aboutUs.append("\n");

			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(aboutUs);
		aboutFrame.add(panel);
		aboutFrame.setTitle("About GoodTunez");
		aboutFrame.setSize(650, 450);
		aboutFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		aboutFrame.setVisible(true);
		aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public static void albumPanel() throws IOException {
		JFrame album = new JFrame();
		JPanel panel = new JPanel();
		ArrayList<String> albumList = new ArrayList<>();

		JTable albumTable;
		DefaultTableModel model = new DefaultTableModel();
		JScrollPane scrollPane;

		albumTable = new JTable(model);
		model.addColumn("Artist");
		model.addColumn("Album");
		model.addColumn("Select");

		scrollPane = new JScrollPane(albumTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		try {
			BufferedReader in = new BufferedReader(new FileReader("SongList.txt"));
			String inStr;
			String[] songInfo;
			String[] albumInfo = new String[2];
			int count = 0;
			while ((inStr = in.readLine()) != null) {

				songInfo = inStr.split(",");
				String albumName = songInfo[2];
				if (count == 0) {
					albumList.add(albumName);
					albumInfo[0] = songInfo[1];
					albumInfo[1] = albumName;
					model.addRow(albumInfo);
				}
				count++;
				for (int i = 0; i < albumList.size(); i++) {
					if (!albumName.equals(albumList.get(i))) {
						albumList.add(albumName);
						albumInfo[0] = songInfo[1];
						albumInfo[1] = albumName;
						model.addRow(albumInfo);
					}
				}

			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel.add(scrollPane);
		album.add(panel);
		album.setTitle("Albums");
		album.setSize(650, 450);
		album.setExtendedState(JFrame.MAXIMIZED_BOTH);
		album.setVisible(true);
		album.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}
