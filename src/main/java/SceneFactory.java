import javafx.stage.Stage;
import javafx.scene.Scene;
import package.SceneType;
/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/28/2026
 */
public class SceneFactory {

    public static void create(SceneType type, Stage stage){
        return switch (type) {
            case LOGIN -> buildLoginScene(stage);
            case MAIN -> buildMainScene(stage);
            case STATS -> buildStatsScene(stage);
            case GAME -> buildGameScene(stage);
        };
    }
    private static void buildMainScene(Stage stage){ return null;
    }
    private static void buildLoginScene(Stage stage){ return null;}
    private static void buildStatsScene(Stage stage){ return null;}
    private static void buildGameScene(Stage stage){ return null;}
}
