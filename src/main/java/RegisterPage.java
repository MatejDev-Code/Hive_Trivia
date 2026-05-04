import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 RegisterPage.java
 * Where we first create an account, entering a username and password
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/25/2026
 */

public class RegisterPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton createButton = new JButton("Create");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JPasswordField userConfirmField = new JPasswordField();
    JLabel messageLabel = new JLabel("");


    JLabel userIDLabel = new JLabel("new username:");
    JLabel userPasswordLabel = new JLabel("new password:");
    JLabel userConfirmLabel = new JLabel("confirm password:");

    JLabel passwordChar = new JLabel("❌ Contains at least 8 characters");
    JLabel passwordCase = new JLabel("❌ Contains both lower and uppercase letters");
    JLabel passwordNum = new JLabel("❌ Contains at least one number");
    JLabel passwordSpec = new JLabel("❌ Contains at least one special character (!@#$%^&) ");
    JLabel passwordMatch = new JLabel("❌ Both passwords must match ");


    public RegisterPage() {

        userPasswordField.getDocument().addDocumentListener(dl);
        userConfirmField.getDocument().addDocumentListener(dl);

        userIDLabel.setBounds(50, 80, 125, 25);
        userIDField.setBounds(180, 80, 200, 25);

        userPasswordLabel.setBounds(50, 110, 200, 25);
        userPasswordField.setBounds(180, 110, 200, 25);

        passwordChar.setBounds(100, 130, 300, 25);
        passwordChar.setFont(new Font(null, Font.ITALIC,12));
        passwordChar.setForeground((Color.red));

        passwordCase.setBounds(100, 150, 300, 25);
        passwordCase.setFont(new Font(null, Font.ITALIC,12));
        passwordCase.setForeground((Color.red));

        passwordNum.setBounds(100, 170, 300, 25);
        passwordNum.setFont(new Font(null, Font.ITALIC,12));
        passwordNum.setForeground((Color.red));

        passwordSpec.setBounds(100, 190, 350, 25);
        passwordSpec.setFont(new Font(null, Font.ITALIC,12));
        passwordSpec.setForeground((Color.red));

        passwordMatch.setBounds(100, 240, 350, 25);
        passwordMatch.setFont(new Font(null, Font.ITALIC,12));
        passwordMatch.setForeground((Color.red));

        userConfirmLabel.setBounds(50, 220, 200, 25);
        userConfirmField.setBounds(180, 220, 200, 25);

        createButton.setBounds(125, 280, 100, 25);
        createButton.addActionListener(this);

        resetButton.setBounds(225, 280, 100, 25);
        resetButton.addActionListener(this);

        messageLabel.setBounds(150, 310, 300, 25);
        messageLabel.setFont(new Font(null, Font.ITALIC,12));
        messageLabel.setForeground((Color.red));


        frame.add(userIDLabel);
        frame.add(userPasswordLabel);

        frame.add(passwordChar);
        frame.add(passwordCase);
        frame.add(passwordNum);
        frame.add(passwordSpec);
        frame.add(passwordMatch);

        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(userConfirmLabel);
        frame.add(userConfirmField);
        frame.add(createButton);
        frame.add(resetButton);

        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    Color darkGreen = new Color(0, 180, 0);

    DocumentListener dl = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            check();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            check();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            check();
        }
    };

    public boolean check(){

        boolean hasLowerCase = false;
        boolean hasUpperCase = false;

        boolean hasNumber = false;

        String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
        boolean hasSpecialChar = false;

        boolean doesMatch = false;
        boolean isLong = false;

        for (char c : userPasswordField.getPassword()){
            if (Character.isLowerCase(c)){
                hasLowerCase = true;
            }
            if (Character.isUpperCase(c)){
                hasUpperCase = true;
            }
            if(Character.isDigit(c)){
                hasNumber = true;
            }
            if (specialChars.indexOf(c) >= 0){
                hasSpecialChar = true;
            }
        }

        if (Arrays.equals(userPasswordField.getPassword(), userConfirmField.getPassword()) && userPasswordField.getPassword().length > 0){
            passwordMatch.setText("✅ Both passwords must match ");
            passwordMatch.setForeground(darkGreen);
            doesMatch = true;
        } else {
            passwordMatch.setText("❌ Both passwords must match ");
            passwordMatch.setForeground(Color.red);
        }

        if(userPasswordField.getPassword().length >= 8){
            passwordChar.setText("✅ Contains at least 8 characters");
            passwordChar.setForeground(darkGreen);
            isLong = true;
        } else {
            passwordChar.setText("❌ Contains at least 8 characters");
            passwordChar.setForeground(Color.red);
        }

        if(hasUpperCase && hasLowerCase) {
            passwordCase.setText("✅ Contains both lower and uppercase letters");
            passwordCase.setForeground(darkGreen);
        } else {
            passwordCase.setText("❌ Contains both lower and uppercase letters");
            passwordCase.setForeground(Color.red);
        }

        if(hasNumber) {
            passwordNum.setText("✅ Contains at least one number");
            passwordNum.setForeground(darkGreen);
        } else {
            passwordNum.setText("❌ Contains at least one number");
            passwordNum.setForeground(Color.red);
        }

        if(hasSpecialChar) {
            passwordSpec.setText("✅ Contains at least one special character (!@#$%^&)");
            passwordSpec.setForeground(darkGreen);
        } else {
            passwordSpec.setText("❌ Contains at least one special character (!@#$%^&)");
            passwordSpec.setForeground(Color.red);
        }

        return(hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar && isLong && doesMatch);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String password = String.valueOf(userPasswordField.getPassword());
        String confirmPassword = String.valueOf(userConfirmField.getPassword());

        if(e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
            userConfirmField.setText("");
        }

        if(e.getSource() == createButton){

            if (check()){
                LoginOrRegister lor = new LoginOrRegister();
            } else {
                messageLabel.setText("all requirements must be met");
            }

        }

    }

}
