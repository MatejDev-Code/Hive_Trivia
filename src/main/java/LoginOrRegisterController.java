import javafx.geometry.Pos;import javafx.scene.Scene;

import java.awt.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 LoginOrRegister.java
 * We choose to either register a new account, or log into an existing account
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/25/2026
 */

public class LoginOrRegisterController {

    private final Scene scene;

    LoginOrRegisterController(){

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label loginText = new Label("Log into existing account?");

        Label registerText = new Label("Create a new account?");

        loginButton.setOnAction(e -> {
            SceneManager.getInstance().navigateTo(SceneType.LOGIN);
        });

        registerButton.setOnAction(e -> {
            SceneManager.getInstance().navigateTo(SceneType.REGISTER);
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                loginText, loginButton, registerText, registerButton
        );

        this.scene = new Scene(layout, 300, 400);

    }
public Scene getScene() {
        return scene;
}}
