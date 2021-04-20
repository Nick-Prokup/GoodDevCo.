package CSE201;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoodTunezTester {

	// Tester method to identify that the attributes of the account (Grey)
	@Test
	void testListner() {
		Account l1 = new Account("Owen Campbell", "yes.com", "pswd", "05/02/2002", "Listener");
		
		System.out.println(l1.getClass());
		
		assertEquals(l1.getName(), "Owen Campbell");
		assertEquals(l1.getEmail(), "yes.com");
		assertEquals(l1.getPswd(), "pswd");
		assertEquals(l1.getDob(), "05/02/2002");
		
	}
	
	// Tester method to identify that the attributes of the artists (Grey) 
	@Test
	void testArtist() {
		Account a1 = new Account("John Mayer", "jMayer.com", "pswd", "12/01/1994", "Artist");
		System.out.println(a1.getClass());
		assertEquals(a1.getName(), "John Mayer");
		assertEquals(a1.getEmail(), "jMayer.com");
		assertEquals(a1.getPswd(), "pswd");
		assertEquals(a1.getDob(), "12/01/1994");
	}
	
	// Tester method to verify that the login is functional (Black)
	@Test
	void loginVerificationTest() {
		LoginPage log = new LoginPage();
		assertTrue(log.loginVerification("jMayer.com", "pswd"));
		assertFalse(log.loginVerification("jMayer.com", "badPswd"));
	}
	
	// Tester method to verify password (Black)
	@Test 
	void pswdVerificationTest() {
		CreateAccount acc = new CreateAccount();
		
		assertTrue(acc.verifyPswd("pswd", "pswd", "Listener"));
		assertFalse(acc.verifyPswd("pswd", "badPswd", "false"));
	}
	
	// Tester method to prove that user is able to login
	@Test
	void loginAbility() {
		
		
	}
	
	@Test
	void searchBoxTest() {
		// when the box is selected, get the item from combo box when search is hit
		// return the information within the boxes. 
	}
	
	@ Test
	void searchBarTest() {
		String song1 = "Bohemian Rhapsody,Queen,Album,1975,Rock";
		String song2 = null;
		
		Song s1 = new Song("Bohemian Rhapsody", "Queen", "Album", 1975, "Rock");
		Song s2 = null;
		
		assertTrue(s1.equals(Account.searchBarReturn(song1)));
		assertFalse(s2.equals(Account.searchBarReturn(song1)));
		assertEquals(song1.toString(), Account.searchBarReturn(song1).toString());
		assertEquals("Song not in database", Account.searchBarReturn(song2).toString());
	}
}
