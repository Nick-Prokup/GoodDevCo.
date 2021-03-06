package CSE201;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreateAccount extends JFrame implements ActionListener {

	CreateAccount() {
		JFrame createAcc;
		JPanel info;
		JLabel email_label, pswd_label, pswd_label2, name_label, dob_label, type_label, sub_label;
		JTextField email_text, dob_text, name_text, type_text;
		JPasswordField pswd_text, pswd_text2;

		JButton submit;

		name_label = new JLabel("Enter full name: ");
		name_text = new JTextField();

		email_label = new JLabel("Register email: ");
		email_text = new JTextField();

		pswd_label = new JLabel("Register password: ");
		pswd_text = new JPasswordField();
		pswd_label2 = new JLabel("Verify password: ");
		pswd_text2 = new JPasswordField();

		dob_label = new JLabel("Enter date of birth (mm/dd/yyyy): ");
		dob_text = new JTextField("mm/dd/yyyy");

		type_label = new JLabel("Type of account: ");
		type_text = new JTextField("Listener or Artist");

		sub_label = new JLabel("Create account: ");
		submit = new JButton("Submit");

		info = new JPanel(new GridLayout(10, 2));

		info.add(name_label);
		info.add(name_text);

		info.add(email_label);
		info.add(email_text);

		info.add(pswd_label);
		info.add(pswd_text);
		info.add(pswd_label2);
		info.add(pswd_text2);

		info.add(dob_label);
		info.add(dob_text);

		info.add(type_label);
		info.add(type_text);

		info.add(sub_label);
		info.add(submit);

		createAcc = new JFrame();
		createAcc.add(info);

		createAcc.setTitle("GoodTunez Create Account");
		createAcc.setSize(650, 450);
		createAcc.setExtendedState(JFrame.MAXIMIZED_BOTH);
		createAcc.setVisible(true);
		createAcc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = name_text.getText();
				String email = email_text.getText();
				String pswd = pswd_text.getText();
				String pswd2 = pswd_text2.getText();
				String dob = dob_text.getText();
				String type = type_text.getText();

				if (verifyPswd(pswd, pswd2, type)) {
					Account newUser = new Account(name, email, pswd, dob, type);
					try {
						writeToFile(newUser);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					createAcc.dispose();
				} else {
					sub_label.setText("Invalid password or account type try again");
				}
			}
		});
	}

	public boolean verifyPswd(String pswd, String pswd2, String type) {
		boolean match = false;

		if (pswd.equals(pswd2) && pswd.length() >= 8) {
			match = true;
		}

		if (type.equals("Listener") || type.equals("Artist")) {
			match = true;
		} else {
			match = false;
		}
		return match;
	}

	public void writeToFile(Account acc) throws FileNotFoundException {

		try (FileWriter fw = new FileWriter("AccountList.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(acc.toString());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
