import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.HashMap;
/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/28/2026
 */
public class SceneFactory {

    public static Scene create(SceneType type, Stage stage){
        return switch (type) {
            case LOGINORREGISTER -> buildLoginOrRegiserScene(stage);
            case LOGIN -> buildLoginScene(stage);
            case REGISTER -> buildRegiserScene(stage);
            case MAIN -> buildMainScene(stage);
            case STATS -> buildStatsScene(stage);
            case GAME -> buildGameScene(stage);
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
    private static Scene buildMainScene(Stage stage){ return null;}
    private static Scene buildLoginOrRegiserScene(Stage stage){
        return new LoginOrRegisterController().getScene();
    }
    private static Scene buildLoginScene(Stage stage){
        IDandPasswords iDandPasswords = new IDandPasswords();
        HashMap<String, String> info = iDandPasswords.getLoginInfo();

        return new LoginPageController(info).getScene();
    }
    private static Scene buildRegiserScene(Stage stage){
        return new RegisterPageController(stage).getScene();
    }
    private static Scene buildStatsScene(Stage stage){ return null;}
    private static Scene buildGameScene(Stage stage){ return null;}
}
