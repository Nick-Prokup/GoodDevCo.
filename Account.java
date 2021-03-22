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

class HomePage {
	
	HomePage() {
		JFrame homePage;

		homePage = new JFrame();

		homePage.setTitle("GoodTunez Home");
		homePage.setSize(650, 450);
		homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
		homePage.setVisible(true);

		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
