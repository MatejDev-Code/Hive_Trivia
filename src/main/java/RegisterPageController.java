import database.DBManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.User;

/**
 RegisterPage.java
 * Where we first create an account, entering a username and password
 @author Matteo Ristoski
 @version 0.1.0
 @since 4/25/2026
 */

public class RegisterPageController {

    private Scene scene;

    private final TextField userNameField = new TextField();
    private final PasswordField userPasswordField = new PasswordField();
    private final PasswordField userConfirmField = new PasswordField();
    private final Label messageLabel = new Label("");

    private final Label passwordChar = new Label("❌ Contains at least 8 characters");
    private final Label passwordCase = new Label("❌ Contains both lower and uppercase letters");
    private final Label passwordNum = new Label("❌ Contains at least one number");
    private final Label passwordSpec = new Label("❌ Contains at least one special character (!@#$%^&) ");
    private final Label passwordMatch = new Label("❌ Both passwords must match ");

    public RegisterPageController(){}
    public Scene buildScene() {

        GridPane g = new GridPane();
        g.setHgap(10);
        g.setVgap(10);
        g.setAlignment(Pos.CENTER);

        userNameField.setPrefHeight(28);
        userPasswordField.setPrefHeight(28);
        userConfirmField.setPrefHeight(28);

        g.add(new Label("new username: "), 0, 0);
        g.add(userNameField, 1, 0);
        g.add(new Label("new password: "), 0, 1);
        g.add(userPasswordField, 1, 1);
        g.add(new Label("confirm password : "), 0, 2);
        g.add(userConfirmField, 1, 2);

        VBox checklist = new VBox(5);
        checklist.setAlignment(Pos.CENTER_LEFT);
        checklist.setPadding(new Insets(10, 0, 10, 20));

        passwordChar.setFont(Font.font(("Century"), FontPosture.ITALIC, 12));
        passwordChar.setTextFill(Color.RED);
        checklist.getChildren().add(passwordChar);

        passwordCase.setFont(Font.font(("Century"), FontPosture.ITALIC, 12));
        passwordCase.setTextFill(Color.RED);
        checklist.getChildren().add(passwordCase);

        passwordNum.setFont(Font.font(("Century"), FontPosture.ITALIC, 12));
        passwordNum.setTextFill(Color.RED);
        checklist.getChildren().add(passwordNum);

        passwordSpec.setFont(Font.font(("Century"), FontPosture.ITALIC, 12));
        passwordSpec.setTextFill(Color.RED);
        checklist.getChildren().add(passwordSpec);

        passwordMatch.setFont(Font.font(("Century"), FontPosture.ITALIC, 12));
        passwordMatch.setTextFill(Color.RED);
        checklist.getChildren().add(passwordMatch);

        Button createButton = new Button("Create");
        Button resetButton = new Button("Reset");
        GridPane buttonBox = new GridPane();
        buttonBox.setHgap(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.add(createButton, 0, 0);
        buttonBox.add(resetButton, 1, 0);

        userPasswordField.textProperty().addListener((obs, oldVal, newVal) -> check());
        userConfirmField.textProperty().addListener((obs, oldVal, newVal) -> check());

        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {
            SceneManager.getInstance().navigateTo(SceneType.LOGINORREGISTER);
        });
        buttonBox.add(backButton, 2,0);

        resetButton.setOnAction(e -> {
            userNameField.clear();
            userPasswordField.clear();
            userConfirmField.clear();
            messageLabel.setText("");
            check();
        });

        createButton.setOnAction(e -> {
            if (check()){
                DBManager.getInstance().insertUser( new User( userNameField.getText(), userPasswordField.getText()));
                SceneManager.getInstance().navigateTo(SceneType.LOGIN);
            } else {
                messageLabel.setText("all requirements must be met");
                messageLabel.setTextFill(Color.RED);
            }
        });

        VBox mainBox = new VBox(15, g, checklist, buttonBox, messageLabel);
        mainBox.setAlignment(Pos.CENTER);

        userPasswordField.setStyle("-fx-font: 8 px;");
        userConfirmField.setStyle("-fx-font: 8 px;");

        this.scene = new Scene(mainBox, 500, 600);
        return scene;
    }

    private boolean check(){
        String password = userPasswordField.getText();
        String confirmPassword = userConfirmField.getText();

        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        boolean isLong = false;
        boolean doesMatch = false;

        String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";

        char[] eachCharPassword = password.toCharArray();
        for (int i = 0; i < eachCharPassword.length; i++) {
            char c = eachCharPassword[i];

            if(Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            if(Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isDigit(c)){
                hasNumber = true;
            }
            if (specialChars.indexOf(c) >= 0) {
                hasSpecialChar = true;
            }

        }

        if (password.length() >= 8) {
            isLong = true;
        }
        if (password.equals(confirmPassword) && !password.isEmpty()) {
            doesMatch = true;
        }

        if (isLong) {
            passwordChar.setText("✅ Contains at least 8 characters");
            passwordChar.setTextFill(Color.DARKGREEN);
        } else {
            passwordChar.setText("❌ Contains at least 8 characters");
            passwordChar.setTextFill(Color.RED);
        }

        if (hasUpperCase && hasLowerCase) {
            passwordCase.setText("✅ Contains both lower and uppercase letters");
            passwordCase.setTextFill(Color.DARKGREEN);
        } else {
            passwordCase.setText("❌ Contains both lower and uppercase letters");
            passwordCase.setTextFill(Color.RED);
        }

        if (hasNumber) {
            passwordNum.setText("✅ Contains at least one number");
            passwordNum.setTextFill(Color.DARKGREEN);
        } else {
            passwordNum.setText("❌ Contains at least one number");
            passwordNum.setTextFill(Color.RED);
        }

        if (hasSpecialChar) {
            passwordSpec.setText("✅ Contains at least one special character (!@#$%^&)");
            passwordSpec.setTextFill(Color.DARKGREEN);
        } else {
            passwordSpec.setText("❌ Contains at least one special character (!@#$%^&)");
            passwordSpec.setTextFill(Color.RED);
        }

        if (doesMatch) {
            passwordMatch.setText("✅ Both passwords must match");
            passwordMatch.setTextFill(Color.DARKGREEN);
        } else {
            passwordMatch.setText("❌ Both passwords must match");
            passwordMatch.setTextFill(Color.RED);
        }

        return (hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar && isLong && doesMatch);

    }

}
