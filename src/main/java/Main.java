/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/6/2026
 */
import package.database.DBManager;
import package.SceneFactory;
public class Main {
    private static DBManager db;
    private static Stage stage;

    public static void main(String[] args){
        db = new DBManager();
        stage.setScene(SceneFactory.create(SceneType.Main, stage, db))
    }

}
