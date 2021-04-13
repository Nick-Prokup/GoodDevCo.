package CSE201;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame implements ActionListener {
	// declare JFrame component variables
	JFrame logFrame;
	JPanel panel;
	JLabel email_label, pswd_label, out, empty;
	JTextField email_text;
	JPasswordField pswd_text;
	JButton submit, newAcc;

	static Account currUser;

	// login page declaration, upon opening the class this frame will be displayed
	LoginPage() {
		// labels and text fields for email and password entry
		email_label = new JLabel("Enter email: ");
		email_text = new JTextField();
		pswd_label = new JLabel("Enter password: ");
		pswd_text = new JPasswordField();

		// ***** not sure what JLabel does *****
		//
		out = new JLabel();
		// JButton to attempt a login
		submit = new JButton("Log in");
		empty = new JLabel("");
		// JButton to create new account
		newAcc = new JButton("Create Account");
		// creates the spaces and format for the login page panel layout
		panel = new JPanel(new GridLayout(4, 2));

		// add the variables to the display panel
		panel.add(email_label);
		panel.add(email_text);
		panel.add(pswd_label);
		panel.add(pswd_text);

		// adds the labels and buttons to panel
		panel.add(out);
		panel.add(submit);
		panel.add(empty);
		panel.add(newAcc);

		// sets border around panel
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// adds button functionality to the login button
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = email_text.getText();
				String pswd = pswd_text.getText();

				// verification of the login credentials
				if (email.equals("admin") && pswd.equals("admin") || loginVerification(email, pswd)) {
					out.setText("Login confirmed");
					logFrame.dispose();
					Account.HomePage();
				} else {
					out.setText("Invalid login info");
				}
			}
		});

		// adds button functionality to the create account button
		newAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// goes to account creation page
				new CreateAccount();
			}
		});

		// entire login frame
		logFrame = new JFrame();
		logFrame.add(panel);

		logFrame.setTitle("GoodTunez Login");
		logFrame.setSize(650, 450);
		logFrame.setVisible(true);

		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * method to verify login credentials
	 * 
	 * @param email, String entered by the user which is the email
	 * @param pswd,  String entered by the user which is the password
	 * @return boolean, true if account exists and is correct
	 */
	public boolean loginVerification(String email, String pswd) {
		// ***** Not sure about bufferedReader
		try (BufferedReader in = new BufferedReader(new FileReader("AccountList.txt"))) {
			String line;
			String[] accInfo;
			// while there is still data in the account list
			while ((line = in.readLine()) != null) {
				// reading each account by commas to find email and password as an array element
				accInfo = line.split(",");

				if (accInfo[4].equals("Listener")) {
					currUser = new Account(accInfo[0], accInfo[1], accInfo[2], accInfo[3], "Listener");
				} else if (accInfo[4].equals("Artist")) {
					currUser = new Account(accInfo[0], accInfo[1], accInfo[2], accInfo[3], "Artist");
				}

				if (accInfo[1].equals(email) && accInfo[2].equals(pswd)) {

					return true;
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
