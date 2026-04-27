import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 LoginPage.java
 * Where we sign in to the program
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/22/2026
 */

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();


    JLabel userIDLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> logging = new HashMap<String, String>();

    LoginPage(HashMap<String, String> loginInfoOriginal) {
        logging = loginInfoOriginal;

        userIDLabel.setBounds(50, 80, 75, 25);
        userIDField.setBounds(125, 80, 200, 25);

        userPasswordLabel.setBounds(50, 130, 75, 25);
        userPasswordField.setBounds(125, 130, 200, 25);

        messageLabel.setBounds(125, 220, 250, 25);
        messageLabel.setFont(new Font(null, Font.ITALIC,20));

        loginButton.setBounds(125, 180, 100, 25);
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 180, 100, 25);
        resetButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == resetButton){
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if(e.getSource() == loginButton){

            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(logging.containsKey(userID)){

                if (logging.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.blue);
                    messageLabel.setText("Login Successful");

                    Welcome welcomeTemporary = new Welcome(userID);
                    frame.dispose();
                }
                else {
                    messageLabel.setForeground((Color.red));
                    messageLabel.setText("Wrong password");
                }
            }
            else {
                messageLabel.setForeground((Color.red));
                messageLabel.setText("Username not found");
            }

        }

    }
}
