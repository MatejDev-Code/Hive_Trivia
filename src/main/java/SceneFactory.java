import database.DBManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.LeaderboardEntry;
import model.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/28/2026
 */
public class SceneFactory {

    public static Scene create(SceneType type, Stage stage){
        return switch (type) {
            case LOGINORREGISTER -> new LoginOrRegisterController().buildScene();
            case LOGIN -> new LoginPageController().buildScene();
            case REGISTER -> new RegisterPageController().buildScene();
            case MAIN -> new MainMenuController().buildScene();
            case STATS -> buildStatsScene(stage);
            case GAME -> new GameController().buildScene();
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
    private static Scene buildStatsScene(Stage stage) {

        TableColumn<LeaderboardEntry, Integer> rankCol = new TableColumn<>("Rank");
        rankCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getRank()).asObject()
        );

        TableColumn<LeaderboardEntry, String> nameCol = new TableColumn<>("Player");
        nameCol.setCellValueFactory(data -> {
            User user = data.getValue().getUser();
            return new SimpleStringProperty(user != null ? user.getUsername() : "");
        });

        TableColumn<LeaderboardEntry, Integer> scoreCol = new TableColumn<>("Total Score");
        scoreCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getScore()).asObject()
        );

        TableView<LeaderboardEntry> table = new TableView<>();
        table.getColumns().addAll(rankCol, nameCol, scoreCol);

        // --- Get leaderboard data ---
        List<LeaderboardEntry> leaderboardEntryList = DBManager.getInstance().getTopUsers();

        // --- Pad to always show 5 rows ---
        int desiredSize = 5;
        for (int i = leaderboardEntryList.size(); i < desiredSize; i++) {
            leaderboardEntryList.add(new LeaderboardEntry(i + 1, null, 0));
        }

        ObservableList<LeaderboardEntry> leaderboard =
                FXCollections.observableArrayList(leaderboardEntryList);

        table.setItems(leaderboard);

        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.MAIN)
        );

        Label title = new Label("Leaderboard");

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(title, table, backBtn);
        layout.setStyle("-fx-padding: 20;");

        return new Scene(layout, 600, 400);
    }
//    private static Scene buildLoginOrRegiserScene(Stage stage){
//        return new LoginOrRegisterController().getScene();
//    }
//    private static Scene buildLoginScene(Stage stage){
//        IDandPasswords iDandPasswords = new IDandPasswords();
//        HashMap<String, String> info = iDandPasswords.getLoginInfo();
//
//        return new LoginPageController(info).getScene();
//    }
//    private static Scene buildRegiserScene(Stage stage){
//        return new RegisterPageController(stage).getScene();
//    }
//    private static Scene buildStatsScene(Stage stage){ return null;}
//    private static Scene buildGameScene(Stage stage){ return null;}
}
