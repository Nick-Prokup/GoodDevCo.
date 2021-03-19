package CSE201;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame implements ActionListener {
	JFrame logFrame;
	JPanel panel;
	JLabel email_label, pswd_label, out, empty;
	JTextField email_text;
	JPasswordField pswd_text;
	JButton submit, newAcc;

	LoginPage() {
		email_label = new JLabel("Enter email: ");
		email_text = new JTextField();
		pswd_label = new JLabel("Enter password: ");
		pswd_text = new JPasswordField();

		out = new JLabel();
		submit = new JButton("Log in");
		empty = new JLabel("");
		newAcc = new JButton("Create Account");
		panel = new JPanel(new GridLayout(4, 2));

		panel.add(email_label);
		panel.add(email_text);
		panel.add(pswd_label);
		panel.add(pswd_text);

		panel.add(out);
		panel.add(submit);
		panel.add(empty);
		panel.add(newAcc);

		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = email_text.getText();
				String pswd = pswd_text.getText();

				if (email.equals("admin") && pswd.equals("admin") || loginVerification(email, pswd)) {
					out.setText("Login confirmed");
					new HomePage();
				} else {
					out.setText("Invalid login info");
				}
			}
		});

		newAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateAccount();
			}
		});

		logFrame = new JFrame();
		logFrame.add(panel);

		logFrame.setTitle("GoodTunez Login");
		logFrame.setSize(650, 450);
		logFrame.setVisible(true);

		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public boolean loginVerification(String email, String pswd) {
		boolean match = false;
		try (BufferedReader in = new BufferedReader(new FileReader("AccountList.txt"))) {
			String str;
			String[] accInfo;
			while ((str = in.readLine()) != null) {
				accInfo = str.split(",");
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
