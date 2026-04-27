import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;

public class BackButtonExampleTest {

    @Test
    public void testFirstWindowTitle() {
        JFrame firstWindow = BackButtonExample.createFirstWindow();

        assertEquals("First Window", firstWindow.getTitle());
    }

    @Test
    public void testSecondWindowTitle() {
        JFrame secondWindow = BackButtonExample.createSecondWindow();

        assertEquals("Second Window", secondWindow.getTitle());
    }

    @Test
    public void testFirstWindowHasNextButton() {
        JFrame firstWindow = BackButtonExample.createFirstWindow();
        JButton nextButton = BackButtonExample.findButton(firstWindow, "Next");

        assertNotNull(nextButton);
    }

    @Test
    public void testSecondWindowHasBackButton() {
        JFrame secondWindow = BackButtonExample.createSecondWindow();
        JButton backButton = BackButtonExample.findButton(secondWindow, "Back");

        assertNotNull(backButton);
    }
}