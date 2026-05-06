/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 5/5/2026
 */
import database.DBManager;
import model.PulledQuestion;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class gamerepo{

    private final List<GameObserver> observers = new ArrayList<>();

    private Question question;
    private int lives = 3;
    private int score;

    public gamerepo() {
        score = DBManager.getInstance().getScore(DBManager.getUserInstance());
        loadNewQuestion();
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameChanged(question, lives, score);
        }
    }

    public void loadNewQuestion() {
        try {
            PulledQuestion pulledQuestion = QuestionAPI.getSingleQuestion();

            if (pulledQuestion != null) {
                question = new Question(pulledQuestion);
            }

            notifyObservers();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkAnswer(String selectedAnswer) {
        DBManager db = DBManager.getInstance();

        boolean isCorrect = selectedAnswer.equals(question.getCorrect());

        db.insertQuestion(question);

        if (isCorrect) {
            int userId = DBManager.getUserInstance().getId();
            int categoryId = db.getCategory(question.getCategory());

            db.addUserScore(userId, categoryId);
            score = db.getScore(DBManager.getUserInstance());

            System.out.println("Correct!");
        } else {
            lives--;
            System.out.println("Wrong!");
        }

        if (lives > 0) {
            loadNewQuestion();
        } else {
            notifyObservers();
        }

        return isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }
}
