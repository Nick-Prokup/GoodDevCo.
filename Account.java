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
		JPanel acctPanel = new JPanel(new GridLayout(5, 1)), panel = new JPanel(),
				searchPanel = new JPanel(new FlowLayout()), tempSearchPanel = new JPanel(), songsPanel = new JPanel(),
				mainPanel = new JPanel(new BorderLayout());
		JLabel genre, era, songs, account, search;

		Account currUser = LoginPage.currUser;

		homePage = new JFrame();

		homePage.add(mainPanel);

		// display labels
		genre = new JLabel("Search by Genre:");
		era = new JLabel("Search by Era:");
		songs = new JLabel("Liked Songs: \n");
		account = new JLabel("Your Account: ");
		search = new JLabel("Search results: ");

		JButton profile = new JButton("Account information");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountPanel(currUser);
			}
		});

		JButton type = new JButton();
		if (currUser.getAccType().equals("Artist")) {
			account = new JLabel(currUser.name + ":  Artist");
			type = new JButton("Add Songs");
			type.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					songPanel();
				}

			});
		} else if (currUser.getAccType().equals("Listener")) {
			account = new JLabel(currUser.name + ":  Listener");
			type = new JButton("Song List");

			type.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						songList();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

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

		String gSelection = (String) genreBox.getSelectedItem();
		String eSelection = (String) eraBox.getSelectedItem();

		// search button
		JButton searchB = new JButton("Search");
		searchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSong(gSelection, eSelection);
			}

		});

		tempSearchPanel.add(search);
		String rows[][] = { { " Name ", " Artist ", " Year ", " Genre " },
				{ " info ", " display ", " here ", " after " }, { " info ", " display ", " here ", " after " },
				{ " info ", " display ", " here ", " after " } };
		String columns[] = { " Name ", " Artist ", " Year ", " Genre " };
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

	public static Song searchBarReturn(String song) {
		return null;
	}
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
		accFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		addSong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

	public static void songList() throws IOException {
		JFrame listFrame;
		JPanel listPanel;
		JTextArea songs;
		JScrollPane scrollSongs;

		listFrame = new JFrame();
		listPanel = new JPanel();
		songs = new JTextArea(200, 100);
		scrollSongs = new JScrollPane(songs);
		scrollSongs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSongs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		songs.setEditable(false);

		try {
			BufferedReader in = new BufferedReader(new FileReader("SongList.txt"));
			String inStr;
			String[] songInfo;
			String outStr;
			while ((inStr = in.readLine()) != null) {
				songInfo = inStr.split(",");
				outStr = "Song: " + songInfo[0] + " Artist: " + songInfo[1] + " Album: " + songInfo[2] + " Year: "
						+ songInfo[3] + " Genre: " + songInfo[4];
				songs.append(outStr);
				songs.append("\n");
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
		listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Admin {

	public void editAccount() {

	}
}
