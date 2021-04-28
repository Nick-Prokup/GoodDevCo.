package CSE201;

/*
 * This class contains the account types as well as depending on which account is in use, 
 * the home page that will be displayed to handle any function provided the home page.
 * 
 *  @author, GooDevelopment Co. (C) 2021
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// global variables
	String name, email, dob, accType;
	private String pswd;

	// main method where our login page is called
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
				mainPanel = new JPanel(new BorderLayout()), secPanel = new JPanel(new BorderLayout());
		JLabel genre, era, songs, account, search;

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

		// Creates about us button and calls for the panel when clicked
		JButton about = new JButton("About us/ help");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clearComponent(mainPanel);
					JPanel about = aboutUsPanel();
					mainPanel.add(about, BorderLayout.CENTER);
					homePage.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// button for account info, followed by the action listener for the click to
		// have functionality
		JButton profile = new JButton("Account information");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearComponent(mainPanel);
				JPanel acc = accountPanel(currUser);
				mainPanel.add(acc, BorderLayout.CENTER);
				homePage.setVisible(true);
			}
		});

		// button for deciding which account page to pull up, followed by the action
		// listener for the click to have functionality
		JButton add = new JButton();
		if (currUser.getAccType().equals("Artist")) { // Displays appropriate information for artists
			account = new JLabel(currUser.name + ":  Artist");
			add = new JButton("Add Songs");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearComponent(mainPanel);
					JPanel song = songPanel();
					mainPanel.add(song, BorderLayout.CENTER);
					homePage.setVisible(true);
				}

			});
		} else if (currUser.getAccType().equals("Listener")) { // Displays appropriate information for listeners
			account = new JLabel(currUser.name + ":  Listener");
			add = new JButton("Playlist");
			// Adding functionality to the play list button
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						clearComponent(mainPanel);
						JPanel play = playlistPanel();
						mainPanel.add(play, BorderLayout.CENTER);
						homePage.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}

		// buttons for viewing song list
		JButton songList = new JButton("Song List");
		songList = new JButton("Song List");
		songList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clearComponent(mainPanel);
					JPanel song = songList();
					mainPanel.add(song, BorderLayout.CENTER);
					homePage.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Button for viewing albums in database
		JButton albums = new JButton("Albums");
		albums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clearComponent(mainPanel);
					JPanel center = albumPanel();
					mainPanel.add(center, BorderLayout.CENTER);
					homePage.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		// define options for the search from boxes
		String[] genres = { "Alernative", "Country", "Hip Hop / Rap", "Pop", "Rock" };
		final JComboBox<String> genreBox = new JComboBox<String>(genres);

		String[] eras = { "70s", "80s", "90s", "2000's", "2010s", "Current" };
		final JComboBox<String> eraBox = new JComboBox<String>(eras);

		// add box
		genreBox.setVisible(true);

		// add boxes and panels
		eraBox.setVisible(true);

		// search button box
		JButton searchBox = new JButton("Search");

		// action listener to take the selected box options and search for results
		searchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gSelection = (String) genreBox.getSelectedItem();
				String eSelection = (String) eraBox.getSelectedItem();
				try {
					findSong(gSelection, eSelection);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}

		});

		// search bar button
		JButton searchB = new JButton("Search");

		// add a label to the search bar
		JLabel searchMSG = new JLabel("Search a song attribute (RAPSTAR) or (1995) :");
		searchPanel.add(searchMSG);

		// defining the search box functions and panels
		JTextField searchBar = new JTextField(60);
		searchPanel.add(searchBar);
		searchPanel.add(searchB);
		searchPanel.add(tempSearchPanel, BorderLayout.CENTER);

		// testing JLabel to print search functionality
		JLabel test = new JLabel(searchBar.getText());

		// search functionality for the search bar
		searchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery;
				searchQuery = searchBar.getText();
				try {
					searchBarReturn(searchQuery);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
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
		// tempSearchPanel.add(displayBox);

		// add the variables to the panels
		panel.add(genre);
		panel.add(genreBox);
		panel.add(era);
		panel.add(eraBox);
		panel.add(searchBox);

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
		acctPanel.add(add);
		acctPanel.add(songList);
		acctPanel.add(albums);

		// homePage panel adding
		secPanel.add(panel, BorderLayout.NORTH);
		secPanel.add(songsPanel, BorderLayout.EAST);
		secPanel.add(acctPanel, BorderLayout.WEST);
		secPanel.add(searchPanel, BorderLayout.CENTER);
		mainPanel.add(secPanel, BorderLayout.NORTH);
		mainPanel.add(about, BorderLayout.SOUTH);

		homePage.add(mainPanel);
		homePage.setTitle("GoodTunez Home");
		homePage.setSize(650, 450);
		homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
		homePage.setVisible(true);

		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Required unimplemented method for ActionListener class (unused)
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	/**
	 * Helper method to compute the era that a particular song date was made
	 * 
	 * @param era, String of the exact year a song was made
	 * @return String of the era the year falls under
	 */
	public static String computeEra(int year) {
		if (year < 1980) {
			return "70s";
		} else if (year >= 1980 && year < 1990) {
			return "80s";
		} else if (year >= 1990 && year < 2000) {
			return "90s";
		} else if (year >= 2000 && year < 2010) {
			return "2000s";
		} else if (year >= 2010 && year < 2020) {
			return "2010s";
		} else {
			return "Current";
		}
	}

	/**
	 * Method to find the song from the list of songs in the database according to
	 * the search boxes
	 * 
	 * @param genre, String selected from the combo box
	 * @param era,   String selected from the combo box
	 * @throws FileNotFoundException
	 */
	public static void findSong(String genre, String era) throws FileNotFoundException {
		// check file for songs of these parameters
		File songFile = new File("SongList.txt");
		Scanner in = new Scanner(songFile);
		// loop through list to acquire the song info as a string
		while (in.hasNextLine()) {
			String line = in.nextLine();
			// create a song with the data and use getYear OR just take the year from the
			// file
			String tempYear = "";
			tempYear = line.substring(line.indexOf(','), line.length());
			for (int i = 0; i < 3; i++) {
				tempYear = tempYear.substring(tempYear.indexOf(',') + 1, tempYear.length());
			}
			// now that line is from the comma before the year to the end of line, just take
			// next 4 letters
			tempYear = tempYear.substring(0, 4);

			// parse into integer
			int year = Integer.parseInt(tempYear);

			// if statement to check if the song fits the criteria
			if (line.contains(genre) && era.equals(computeEra(year))) {
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

			// Closing files
		//	fw.close();
			bw.close();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Method that will add the new song by using the attributes listed from the
	 * creation page
	 * 
	 * @param name,   String attribute of the song object
	 * @param artist, String attribute of the song object
	 * @param album,  String attribute of the song object
	 * @param year,   integer attribute of the song object
	 * @param genre,  String attribute of the song object
	 * @throws FileNotFoundException
	 */
	public static void addSong(String name, String artist, String album, int year, String genre)
			throws FileNotFoundException {
		Song song = new Song(name, artist, album, year, genre);
		writeSongToFile(song);
	}

	/**
	 * Method adds function to the account and changes information in the text file
	 * given user input
	 * 
	 * @param acc
	 * @param newAcc
	 * @throws FileNotFoundException
	 */
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
			fw.write(newAcc.toString()); // Updating the text file with new information

			// Closing files
			outFile.close();
			fw.close();
			bw.close();
			out.close();
		} catch (IOException e1) {
			System.out.println("Error appending new account information");
		}
	}

	/**
	 * Method creates the panel that will allow the user to edit and update their
	 * account information
	 * 
	 * @return JFrame
	 * @param currUser, Account object to update the account information
	 */
	public static JPanel accountPanel(Account currUser) {
		// declare the variables
		JPanel info, panel, mainPanel;
		JLabel info_label, email_label, pswd_label, name_label, dob_label, sub_button;
		JTextField email_text, dob_text, pswd_text;
		JButton update;

		info_label = new JLabel(
				"Edit any changes you want in the text field and update using the button at the bottom (changes will not be displayed until database is reloaded)");
		info = new JPanel();
		info.add(info_label);

		// Listing the current data that is in each variable
		name_label = new JLabel("Listed name: ");
		email_label = new JLabel("Listed email: ");
		pswd_label = new JLabel("Listed password: ");
		dob_label = new JLabel("Listed birthday: ");

		JLabel display_name = new JLabel(currUser.getName());
		email_text = new JTextField(currUser.getEmail());
		pswd_text = new JTextField(currUser.getPswd());
		dob_text = new JTextField(currUser.getDob());

		sub_button = new JLabel("Update information");
		update = new JButton("Update");

		// editing the layout of the update panel
		panel = new JPanel(new GridLayout(6, 2));
		panel.add(name_label);
		panel.add(display_name);
		panel.add(email_label);
		panel.add(email_text);
		panel.add(pswd_label);
		panel.add(pswd_text);
		panel.add(dob_label);
		panel.add(dob_text);

		panel.add(sub_button);
		panel.add(update);

		// action listener to actually change the data
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account update = new Account(currUser.getName(), email_text.getText(), pswd_text.getText(),
						dob_text.getText(), currUser.getAccType());
				try {
					replaceAccInfo(currUser, update);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}

		});

		// Add labels and buttons to the panel to be returned
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(info, BorderLayout.NORTH);
		mainPanel.add(panel, BorderLayout.CENTER);
		return mainPanel;
	}

	/**
	 * Method creates and displays the panel to input a song's data
	 * 
	 * @return JFrame
	 */
	public static JPanel songPanel() {
		// declare the variables
		JPanel info;
		JLabel name_label, artist_label, album_label, year_label, genre_label, sub_label;
		JTextField name_text, album_text, year_text, genre_text;
		JButton submit;

		// creating labels for input
		name_label = new JLabel("Enter name of song: ");
		artist_label = new JLabel("Enter artist name: ");
		album_label = new JLabel("Album name: ");
		year_label = new JLabel("Enter year song was made: ");
		genre_label = new JLabel("Enter song genre: ");
		sub_label = new JLabel();

		// text fields for the input of the song info
		name_text = new JTextField();
		JLabel artist = new JLabel(LoginPage.currUser.getName());
		album_text = new JTextField();
		year_text = new JTextField();
		genre_text = new JTextField();

		// button to submit this information
		submit = new JButton("Add song to database");

		// adding the information to the panel
		info = new JPanel(new GridLayout(7, 2));
		info.add(name_label);
		info.add(name_text);
		info.add(artist_label);
		info.add(artist);
		info.add(album_label);
		info.add(album_text);
		info.add(year_label);
		info.add(year_text);
		info.add(genre_label);
		info.add(genre_text);

		info.add(sub_label);
		info.add(submit);

		// submit button that will take the data entered and add it to song list
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					addSong(name_text.getText(), LoginPage.currUser.getName(), album_text.getText(),
							Integer.parseInt(year_text.getText()), genre_text.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		return info;
	}

	/**
	 * Method will take the input from the search bar and check if it is anywhere in
	 * the database
	 * 
	 * @param searchQuery, String of the search bar input
	 * @throws FileNotFoundException
	 */
	public static void searchBarReturn(String searchQuery) throws FileNotFoundException {
		// flag for search found
		boolean flag = false;
		// send the search into database find method
		File songFile = new File("SongList.txt");
		Scanner in = new Scanner(songFile);
		// loop through list to find line containing
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.contains(searchQuery)) {
				// add to the table
				System.out.println(line);
				flag = true;
			}
		}
		in.close();

		if (!flag)
			System.out.println("Song not in database");
	}

	/**
	 * Method will make a table of the songs in the list
	 * 
	 * @return JFrame
	 * @throws IOException
	 */
	public static JPanel songList() throws IOException {
		// declare variables
		JPanel listPanel;
		JTable songTable;
		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};;
		JScrollPane scrollSongs;

		// creates table
		songTable = new JTable(model);
		model.addColumn("Song");
		model.addColumn("Artist");
		model.addColumn("Album");
		model.addColumn("Year");
		model.addColumn("Genre");

		listPanel = new JPanel();
		// Create and set defaults to the scroll table
		scrollSongs = new JScrollPane(songTable);
		scrollSongs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSongs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// try catch to read the info from the song list to put into the table
		try (BufferedReader in = new BufferedReader(new FileReader("SongList.txt"))) {
			String inStr;
			String[] songInfo;
			while ((inStr = in.readLine()) != null) {
				songInfo = inStr.split(",");
				model.addRow(songInfo);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		listPanel.add(scrollSongs);
		return listPanel;
	}

	/**
	 * Method returns the panel that displays playist and playlist information
	 * 
	 * @return JPanel
	 * @throws IOException
	 */
	public static JPanel playlistPanel() throws IOException {
		// Initialize panels and panel components
		JPanel panel = new JPanel(new BorderLayout());
		JPanel inner = new JPanel(new GridLayout(1, 2));
		JTable songList;
		DefaultTableModel sModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};;
		JScrollPane scrollText;

		JTable playTable;
		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};;
		JScrollPane scrollPane;
		String[] playInfo = new String[2];

		playTable = new JTable(model);
		model.addColumn("User");
		model.addColumn("Playlist");

		JButton newPlay = new JButton("Create new playlist");

		// Read from the text file to get play list components to be added to the table
		try (BufferedReader in = new BufferedReader(new FileReader("PlaylistList.txt"))) {

			String inStr;
			String[] songInfo;
			while ((inStr = in.readLine()) != null) {
				songInfo = inStr.split(",");
				playInfo[0] = songInfo[0];
				playInfo[1] = songInfo[1];
				model.addRow(playInfo);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Creating table header
		songList = new JTable(sModel);
		sModel.addColumn("Song");
		sModel.addColumn("Artist");
		sModel.addColumn("Album");
		sModel.addColumn("Year");
		sModel.addColumn("Genre");

		// Adding mouse listener to the song table
		playTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				// When left clicked add clicked song to play list table
				if (event.getButton() == MouseEvent.BUTTON1) {
					sModel.setRowCount(0);
					int row = playTable.rowAtPoint(event.getPoint());
					String selecName = playTable.getValueAt(row, 1).toString();
					String selecUser = playTable.getValueAt(row, 0).toString();

					try (BufferedReader in = new BufferedReader(new FileReader("PlaylistList.txt"));) {
						String inStr;
						String[] songInfo;

						while ((inStr = in.readLine()) != null) {
							songInfo = inStr.split(",");
							String playUser = songInfo[0];
							String playName = songInfo[1];
							String[] song = new String[5];
							if (playName.equals(selecName) && playUser.equals(selecUser)) {
								for (int i = 2; i < songInfo.length - 1; i = i + 5) {
									song[0] = songInfo[i];
									song[1] = songInfo[i + 1];
									song[2] = songInfo[i + 2];
									song[3] = songInfo[i + 3];
									song[4] = songInfo[i + 4];
									sModel.addRow(song);
								}
							}
						}
						in.close();
					} catch (IOException e) {
						e.printStackTrace();

						System.out.println(selecName);
					}
				}
			}
		});

		// Create and initialize play list table
		scrollPane = new JScrollPane(playTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Create and initialize song list table
		scrollText = new JScrollPane(songList);
		scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Add tables and labels to appropriate panels
		inner.add(scrollPane);
		inner.add(scrollText);
		panel.add(newPlay, BorderLayout.NORTH);
		panel.add(inner, BorderLayout.CENTER);

		// Add action listener to button to call createPlaylist() method
		newPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createPlaylist();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return panel;
	}

	/**
	 * Method will frame the about us panel
	 * 
	 * @return JPanel
	 * @throws IOException
	 */
	public static JPanel aboutUsPanel() throws IOException {
		// Create and initialize JPanel and it's components
		JPanel panel = new JPanel();
		JTextArea aboutUs = new JTextArea();
		aboutUs.setEditable(false);

		// Read information from the text file and append it to the textArea
		try (BufferedReader in = new BufferedReader(new FileReader("AboutUs.txt"));) {
			String inStr;
			while ((inStr = in.readLine()) != null) {
				aboutUs.append(inStr);
				aboutUs.append("\n");

			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		panel.add(aboutUs);
		return panel;
	}

	/**
	 * Method provides the table of the albums
	 * 
	 * @return JPanel
	 * @throws IOException
	 */
	public static JPanel albumPanel() throws IOException {
		// Initialize and create JPanel and it's components
		JPanel panel = new JPanel(new GridLayout(1, 2));
		ArrayList<String> albumList = new ArrayList<>();
		JTextArea songList;
		JScrollPane scrollText;

		JTable albumTable;
		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		JScrollPane scrollPane;

		// Add header to album table
		albumTable = new JTable(model);
		model.addColumn("Artist");
		model.addColumn("Album");

		songList = new JTextArea();
		songList.setEditable(false);

		// When desired album is pressed it displays album information
		albumTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON1) {
					int row = albumTable.rowAtPoint(event.getPoint());
					String selecName = albumTable.getValueAt(row, 1).toString();
					String selecArtist = albumTable.getValueAt(row, 0).toString();
					songList.append("Songs in '" + selecName + "' by artist '" + selecArtist + "'\n");

					try (BufferedReader in = new BufferedReader(new FileReader("SongList.txt"));) {
						String inStr;
						String[] songInfo;
						while ((inStr = in.readLine()) != null) {
							songInfo = inStr.split(",");
							String albumName = songInfo[2];
							if (albumName.equals(selecName)) {
								songList.append(songInfo[0] + ", " + songInfo[3] + ", " + songInfo[4] + "\n");
							}
						}
						songList.append("\n");
						in.close();
					} catch (IOException e) {
						e.printStackTrace();

						System.out.println(selecName);
					}
				}
			}
		});

		// Create and add defaults to songList and albumTable
		scrollText = new JScrollPane(songList);
		scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane = new JScrollPane(albumTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Adds information to the album table from SongList.txt
		try (BufferedReader in = new BufferedReader(new FileReader("SongList.txt"))) {
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

				// Determines if album is already in the table and updates if not
				for (int i = 0; i < albumList.size(); i++) {
						if (!albumList.contains(albumName)) {
							albumList.add(albumName);
							albumInfo[0] = songInfo[1];
							albumInfo[1] = albumName;
							model.addRow(albumInfo);
						}
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Add and return JPanel elements
		panel.add(scrollPane);
		panel.add(scrollText);
		return panel;
	}

	/*
	 * Method creates a new JFrame that displays the create play list tables and
	 * functions
	 * 
	 * @throws IOException
	 */
	public static void createPlaylist() throws IOException {
		// Declare and initialize JPanel and it's components
		JPanel panel = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(2, 2));
		JPanel songSelec = new JPanel(new GridLayout(1, 2));
		JPanel inner = new JPanel(new BorderLayout());
		JLabel info = new JLabel("Left click song to add to playlist, right click to remove from current playlist");
		JTable songTable, currTable;
		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};;
		DefaultTableModel currModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};;
		JScrollPane scrollSongs, scrollText;

		JLabel nameLabel = new JLabel("Enter name of playlist: ");
		JTextField nameText = new JTextField();

		// Create table and add header for list of songs
		JLabel song = new JLabel("Songlist");
		songTable = new JTable(model);
		model.addColumn("Song");
		model.addColumn("Artist");
		model.addColumn("Album");
		model.addColumn("Year");
		model.addColumn("Genre");

		// Create table and add header for songs in play list
		JLabel curr = new JLabel("Current songs in playlist");
		currTable = new JTable(currModel);
		currModel.addColumn("Song");
		currModel.addColumn("Artist");
		currModel.addColumn("Album");
		currModel.addColumn("Year");
		currModel.addColumn("Genre");

		// Initialize and set defaults for tables
		scrollSongs = new JScrollPane(songTable);
		scrollSongs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSongs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollText = new JScrollPane(currTable);
		scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JFrame newPlay = new JFrame();

		// Add a mouse listener to song table to add information
		// to the current play list
		songTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON1) {
					int row = songTable.rowAtPoint(event.getPoint());
					String selecSong = songTable.getValueAt(row, 0).toString();
					String selecArtist = songTable.getValueAt(row, 1).toString();
					String selecAlbum = songTable.getValueAt(row, 2).toString();
					String selecYear = songTable.getValueAt(row, 3).toString();
					String selecGenre = songTable.getValueAt(row, 4).toString();
					String[] addTo = { selecSong, selecArtist, selecAlbum, selecYear, selecGenre };
					currModel.addRow(addTo);
				}
			}
		});

		// Adds mouse listener to remove information from
		// the current play list table
		currTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON3) {
					int row = songTable.rowAtPoint(event.getPoint());
					currModel.removeRow(row);
				}
			}
		});
		// try catch to read the info from the song list to put into the table
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
			e.printStackTrace();
		}

		// Add action listener to button to submit created play list
		JButton sub = new JButton("Create Playlist");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (FileWriter fw = new FileWriter("PlaylistList.txt", true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw);
						BufferedReader in = new BufferedReader(new FileReader("PlaylistList.txt"));) {

					Account currUser = LoginPage.currUser;

					fw.write("\n" + currUser.getName() + ",");
					fw.write(nameText.getText() + ",");
					// Gets information from play list table and adds it to PlaylistList.txt
					for (int i = 0; i < currTable.getRowCount(); i++) {
						String song = currTable.getValueAt(i, 0).toString();
						String artist = currTable.getValueAt(i, 1).toString();
						String album = currTable.getValueAt(i, 2).toString();
						String year = currTable.getValueAt(i, 3).toString();
						String genre = currTable.getValueAt(i, 4).toString();

						if (i != currTable.getRowCount()) {
							fw.write(song + "," + artist + "," + album + "," + year + "," + genre + ",");
						} else {
							fw.write(song + "," + artist + "," + album + "," + year + "," + genre);
						}
					}

					// Closes appropriate files
					in.close();
					fw.close();
					bw.close();
					out.close();

					newPlay.dispose();
				} catch (IOException e1) {
					System.out.println("Error appending new playlist information");
				}

			}
		});

		// Add to panels and JFrame
		labels.add(nameLabel);
		labels.add(nameText);
		labels.add(song);
		labels.add(curr);
		songSelec.add(scrollSongs);
		songSelec.add(scrollText);

		inner.add(labels, BorderLayout.NORTH);
		inner.add(songSelec, BorderLayout.CENTER);

		panel.add(info, BorderLayout.NORTH);
		panel.add(inner, BorderLayout.CENTER);
		panel.add(sub, BorderLayout.SOUTH);

		newPlay.add(panel);
		newPlay.setTitle("CreatePlaylist");
		newPlay.setSize(650, 450);
		newPlay.setExtendedState(JFrame.MAXIMIZED_BOTH);
		newPlay.setVisible(true);
		newPlay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	/*
	 * Method clears the center component of a JPanel with BorderLayout
	 * 
	 */
	public static void clearComponent(JPanel panel) {
		BorderLayout layout = (BorderLayout) panel.getLayout();
		if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
			panel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		}

	}
}
