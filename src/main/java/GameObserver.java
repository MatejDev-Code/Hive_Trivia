/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 5/5/2026
 */
import model.Question;
public interface GameObserver{
    void onGameChanged(Question q, int lives, int score);
}
