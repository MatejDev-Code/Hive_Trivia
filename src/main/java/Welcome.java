import javax.swing.*;
import java.awt.*;

/**
 Welcome.java
 * Test window, where after successfully logging in, user is sent to this temporary welcome page.
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/22/2026
 */

public class Welcome {

    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Welcome!");

    Welcome (String userID){

        welcomeLabel.setBounds(0, 0, 200, 35);
        welcomeLabel.setFont(new Font(null,Font.BOLD, 25));
        welcomeLabel.setText("Welcome, " + userID + ", test window");

        welcomeLabel.setBounds(25, 100, 500, 25);
        frame.add(welcomeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}
