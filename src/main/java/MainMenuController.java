import database.DBManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;

/**
 * Name: Ash Straws
 * Date: 05/03/2026
 * Explanation: MainMenuController builds and manages the Main Menu scene for
 * Hive Trivia using JavaFX. It displays the title, subtitle, and navigation
 * buttons (START, STATS, LOGOUT), and wires each button to its handler method.
 * Version 2.0 — converted from Swing (MainMenuController.java)
 */
public class MainMenuController {
    private User user;
//    private final DatabaseManager db = DatabaseManager.getInstance();
    /**
     * Builds and returns the Main Menu scene.
     * Creates all widgets, loads any needed data, connects buttons to handlers,
     * and assembles the layout.
     *
     * @return A fully constructed JavaFX Scene for the Main Menu.
     */
    public Scene buildScene() {
        // Title label: "Hive Trivia"
        Label titleLabel = new Label("Hive Trivia");
        titleLabel.setFont(Font.font("Serif", FontWeight.BOLD, 28));

        // Subtitle label: "Main Menu"
        Label subtitleLabel = new Label("Main Menu");
        subtitleLabel.setFont(Font.font("SansSerif", FontWeight.NORMAL, 18));

        // Navigation buttons
        Button startButton  = createMenuButton("START");
        Button statsButton  = createMenuButton("STATS");
        Button logoutButton = createMenuButton("LOGOUT");

        //Connect buttons to handler methods
        startButton.setOnAction(e -> handleStart());
        statsButton.setOnAction(e -> handleStats());
        logoutButton.setOnAction(e -> handleLogout());

        // Create layout
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50, 10, 50, 10));

        // Add a larger gap between the subtitle and the first button
        VBox headerBox = new VBox(5, titleLabel, subtitleLabel);
        headerBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                headerBox,
                createSpacer(35),   // extra gap before buttons (matches original 50px spacer)
                startButton,
                statsButton,
                logoutButton
        );

        return new Scene(root, 400, 500);
    }

    // -------------------------------------------------------------------------
    // Handler methods
    // -------------------------------------------------------------------------

    /** Called when the START button is clicked. */
    private void handleStart() {
        // TODO: Replace dialog with actual navigation to the Game screen
        showInfo("Loading Game Screen...");
        SceneManager.getInstance().navigateTo(SceneType.GAME);
    }

    /** Called when the STATS button is clicked. */
    private void handleStats() {
        // TODO: Replace dialog with actual navigation to the Stats screen
        showInfo("Opening Player Statistics...");
    }

    /** Called when the LOGOUT button is clicked. Confirms before exiting. */
    private void handleLogout() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to log out?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm");
        confirm.setHeaderText(null);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                javafx.application.Platform.exit();
            }
        });
    }

    // Data refresh
    /** Reloads any dynamic data displayed on the menu (e.g. logged-in username). */
    private void refresh() {
        // TODO: pull player name or stats summary from db and update labels
    }


    // Helper/ actory methods

    /**
     * Creates a consistently styled menu button.
     *
     * @param text The label text for the button.
     * @return A styled Button ready to be added to the scene.
     */
    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
        button.setPrefSize(200, 45);   // fixed preferred size (matches Swing MaximumSize)
        return button;
    }

    /**
     * Creates an invisible spacer region to add vertical breathing room.
     *
     * @param height The height of the spacer in pixels.
     * @return A Region that occupies the requested vertical space.
     */
    private javafx.scene.layout.Region createSpacer(double height) {
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        spacer.setMinHeight(height);
        spacer.setMaxHeight(height);
        return spacer;
    }

    /** Convenience wrapper for informational alert dialogs. */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
