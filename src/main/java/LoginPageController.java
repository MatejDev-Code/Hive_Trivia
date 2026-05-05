import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.util.HashMap;

/**
 LoginPage.java
 * Where we sign in to the program
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/22/2026
 */

public class LoginPageController {

    private final Scene scene;
    private final HashMap<String, String> loginInfo;

    private final TextField userNameField = new TextField();
    private final PasswordField userPasswordField = new PasswordField();
    private final Label messageLabel = new Label();

    private final Label usernameText = new Label("username: ");
    private final Label passwordText = new Label("password: ");


    public LoginPageController(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;

        GridPane g = new GridPane();
        g.setAlignment(Pos.CENTER);
        g.setHgap(15);
        g.setVgap(20);
        g.setPadding(new Insets(25, 25, 25, 25));

        g.add(usernameText, 0, 0);
        g.add(userNameField, 1, 0);
        g.add(passwordText, 0, 1);
        g.add(userPasswordField, 1, 1);

        Button loginButton = new Button("Login");
        Button resetButton = new Button("Reset");
        userPasswordField.setStyle("-fx-font: 8 px;");
        userPasswordField.setPrefHeight(28);
        userNameField.setPrefHeight(28);
        g.add(loginButton, 0, 2);
        g.add(resetButton, 1, 2);

        messageLabel.setFont(Font.font(("Century"), FontPosture.ITALIC, 14));
        g.add(messageLabel, 0, 3, 3, 1);

        loginButton.setOnAction(e -> {

            String username = userNameField.getText();
            String password = userPasswordField.getText();

            if (loginInfo.containsKey(username) && loginInfo.get(username).equals(password)){
                SceneManager.getInstance().navigateTo(SceneType.MAIN);
            } else if (!loginInfo.containsKey(username)) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("username not found");
            } else if (!loginInfo.get(username).equals(password)){
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("incorrect password");
            }

        });

        resetButton.setOnAction(e ->
        {
            userNameField.clear();
            userPasswordField.clear();
            messageLabel.setText("");
        });

        this.scene = new Scene(g, 500, 500);

    }

    public Scene getScene(){
        return scene;
    }



}
