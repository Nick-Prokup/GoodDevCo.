package CSE201;

import java.awt.*;
import java.awt.event.*;
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
		panel = new JPanel (new GridLayout(4,2));
		
		panel.add(email_label);
		panel.add(email_text);
		panel.add(pswd_label);
		panel.add(pswd_text);
		
		panel.add(out);
		panel.add(submit);
		panel.add(empty);
		panel.add(newAcc);
		
		panel.setBorder(new EmptyBorder(10,10,10,10));
		submit.addActionListener(this);
		
		logFrame = new JFrame();
		logFrame.add(panel);
		
		logFrame.setTitle("GoodTunez Login");
		logFrame.setSize(650, 450);
		logFrame.setVisible(true);
		
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		new LoginPage();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String email = email_text.getText();
		String pswd = pswd_text.getText();
		
		if (email.equals("admin") && pswd.equals("admin")) {
			out.setText("Login confirmed");
		} else {
			out.setText("Invalid login info");
		}
	}
}
