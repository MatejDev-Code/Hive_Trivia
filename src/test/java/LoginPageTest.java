import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        HashMap<String, String> loginInfo = new HashMap<>();
        loginInfo.put("John", "password123");
        loginPage = new LoginPage(loginInfo);
    }

    @Test
    void successfulLogin() {

        loginPage.userIDField.setText("John");
        loginPage.userPasswordField.setText("password123");

        loginPage.loginButton.doClick();

        // I am struggling to get the tests to function. (Maybe GUI issues)
        assertTrue(loginPage.successfulLogin);

    }

    @Test
    void unSuccessfulLogin() {

        loginPage.userIDField.setText("John");
        loginPage.userPasswordField.setText("secretPassword");

        loginPage.loginButton.doClick();

        assertFalse(loginPage.successfulLogin);

    }
}