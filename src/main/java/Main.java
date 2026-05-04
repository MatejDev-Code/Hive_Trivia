/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/6/2026
 */
import database.DBManager;
import javafx.stage.Stage;

public class Main {
    private static Stage stage;

    public static void start(Stage stage){
        stage.setTitle("Hive Trivia");
        stage.setScene(SceneFactory.create(SceneType.MAIN, stage));
        stage.show();
    }

    public void stop(){
        DBManager.getInstance().close();
    }
    public static void main(String[] args){
        stage = new Stage();
        Main.start(stage);
    }
}
