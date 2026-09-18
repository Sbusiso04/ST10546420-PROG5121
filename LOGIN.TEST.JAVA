import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach.
    public void setUp() {
        login = new Login("Kyle", "User");
    }

    // --- Boolean Unit Tests ---

    @Test
    public void testUsernameCorrectlyFormatted() {
        login.setUsername("kyl_1");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        login.setUsername("kyle!!!!!!");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        login.setPassword("password");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        login.setCellPhoneNumber("+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        login.setCellPhoneNumber("08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        assertFalse(login.loginUser("wrong_user", "wrong_pass"));
    }

    // --- Message / assertEquals Tests ---

    @Test
    public void testRegisterUserSuccess() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
        assertEquals("Username successfully captured.", login.registerUser());
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        login.setUsername("kyle!!!!!!");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
        assertEquals(
            "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
            login.registerUser()
        );
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        login.setUsername("kyl_1");
        login.setPassword("password");
        login.setCellPhoneNumber("+27838968976");
        assertEquals(
            "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
            login.registerUser()
        );
    }

    @Test
    public void testRegisterUserInvalidCell() {
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("08966553");
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
            login.registerUser()
        );
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String expectedMessage = "Welcome Kyle ,User it is great to see you again.";
        assertEquals(expectedMessage, login.returnLoginStatus(true));
    }
}
