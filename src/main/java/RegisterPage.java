import javax.swing.*;
import java.awt.*;

/**
 RegisterPage.java
 * Where we first create an account, entering a username and password
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/25/2026
 */

public class RegisterPage {

    JFrame frame = new JFrame();
    JLabel titleLabel = new JLabel("Welcome!");

    public RegisterPage (){

        titleLabel.setBounds(0, 0, 200, 35);
        titleLabel.setFont(new Font(null,Font.BOLD, 25));
        titleLabel.setText("Page not yet implemented");

        titleLabel.setBounds(25, 100, 500, 30);
        frame.add(titleLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}
