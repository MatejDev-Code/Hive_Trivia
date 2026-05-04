import database.DBManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.scene.manager.*;
import javafx.scene.layout.VBox;
import model.User;
/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/28/2026
 */
public class SceneFactory {

    public static Scene create(SceneType type, Stage stage){
        return switch (type) {
            case LOGIN -> new LoginController().buildScene();
            case MAIN -> new MainMenuController().buildScene();
            case STATS -> buildStatsScene(stage);
            case GAME -> new GameController().buildScene();
        };
    }
 
    private static Scene buildStatsScene(Stage stage) {
        //TableView columns
        TableColumn<User, String> nameCol = new TableColumn<>("Player");
        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUsername()));

        TableColumn<User, Integer> scoreCol = new TableColumn<>("Total Score");
        scoreCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getScore()).asObject());

        TableView<User> table = new TableView<>();
        table.getColumns().addAll(nameCol, scoreCol);

        // --- Live data from DB ---
        ObservableList<User> players = FXCollections.observableArrayList(
                DBManager.getInstance().getTopPlayers()
        );
        table.setItems(players);

        // --- Back button ---
        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.MAIN));

        Label title = new Label("Stats");

        // --- Fixed VBox construction ---
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(title, table, backBtn);
        layout.setStyle("-fx-padding: 20;");

        return new Scene(layout, 600, 400);
    }
}
