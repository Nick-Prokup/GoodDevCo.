package CSE201;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoodTunezTester {

	@Test
	void testListner() {
		Listener l1 = new Listener("Owen Campbell", "yes.com", "pswd", "05/02/2002");
		
		System.out.println(l1.getClass());
		
		assertEquals(l1.getName(), "Owen Campbell");
		assertEquals(l1.getEmail(), "yes.com");
		assertEquals(l1.getPswd(), "pswd");
		assertEquals(l1.getDob(), "05/02/2002");
		
	}
	
	@Test
	void testArtist() {
		Artist a1 = new Artist("John Mayer", "jMayer.com", "pswd", "12/01/1994");
		System.out.println(a1.getClass());
		assertEquals(a1.getName(), "John Mayer");
		assertEquals(a1.getEmail(), "jMayer.com");
		assertEquals(a1.getPswd(), "pswd");
		assertEquals(a1.getDob(), "12/01/1994");
	}
	
	@Test
	void loginVerificationTest() {
		LoginPage log = new LoginPage();
		assertTrue(log.loginVerification("jMayer.com", "pswd"));
		assertFalse(log.loginVerification("jMayer.com", "badPswd"));
	}
	
	@Test 
	void pswdVerificationTest() {
		CreateAccount acc = new CreateAccount();
		
		assertTrue(acc.verifyPswd("pswd", "pswd", "Listener"));
		assertFalse(acc.verifyPswd("pswd", "badPswd", "false"));
	}
}
