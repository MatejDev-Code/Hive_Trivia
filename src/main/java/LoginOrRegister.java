import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 LoginOrRegister.java
 * We choose to either register a new account, or log into an existing account
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/25/2026
 */

public class LoginOrRegister implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JLabel loginText = new JLabel("Log into existing account?");
    JLabel registerText = new JLabel("Create a new account?");

    LoginOrRegister(){

        loginText.setBounds(90, 70, 500, 25);
        loginButton.setBounds(125, 100, 100, 25);
        loginButton.addActionListener(this);

        registerText.setBounds(100, 170, 500, 25);
        registerButton.setBounds(125, 200, 100, 25);
        registerButton.addActionListener(this);

        frame.add(loginText);
        frame.add(loginButton);
        frame.add(registerText);
        frame.add(registerButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton){

            IDandPasswords idandPasswords = new IDandPasswords();

            LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());

            frame.dispose();

        }

        if(e.getSource() == registerButton){

            RegisterPage registerPage = new RegisterPage();

            frame.dispose();

        }

    }
}
