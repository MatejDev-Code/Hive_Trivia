/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/6/2026
 */
import database.DBManager;
import javafx.stage.Stage;

public class Main {
    private static DBManager db;
    private static Stage stage;

    @Override
    public void start(Stage stage){
        stage.setTitle("Hive Trivia");
        stage.setScene(SceneFactory.create(SceneType.MAIN, stage));
        stage.show();
    }

    @Override
    public void stop(){
        DBManager.getInstance().close();
    }
}
