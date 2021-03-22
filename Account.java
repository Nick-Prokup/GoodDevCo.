package CSE201;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Account {
	String name, email, dob;
	private String pswd;
	
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
}

class Artist {
	
	
}

class Listener {
	
}

class Admin {
	
	public void editAccount() {
		
	}
}
