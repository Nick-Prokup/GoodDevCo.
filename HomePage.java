package CSE201;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HomePage extends JFrame implements ActionListener {

	HomePage() {
		JFrame homePage;
		
		homePage = new JFrame();
		
		homePage.setTitle("GoodTunez Home");
		homePage.setSize(650, 450);
		homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
		homePage.setVisible(true);
		
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new LoginPage();
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
