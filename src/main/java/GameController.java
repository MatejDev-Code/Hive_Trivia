/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 5/3/2026
 */
import database.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController {
    private Question question;
    private int lives = 3;

    public GameController() {
        this.question = new Question();
    }

    public Scene buildScene() {
        Label questionLabel = new Label(question.getQuestion());

        List<String> answers = new ArrayList<>();
        answers.add(question.getCorrect());
        answers.addAll(Arrays.asList(question.getWrongs()));

        Collections.shuffle(answers);

        Button answerBtn1 = new Button(answers.get(0));
        Button answerBtn2 = new Button(answers.get(1));
        Button answerBtn3 = new Button(answers.get(2));
        Button answerBtn4 = new Button(answers.get(3));
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {
            SceneManager.getInstance().navigateTo(SceneType.MAIN);
        });
        answerBtn1.setOnAction(e -> checkAnswer(answerBtn1.getText()));
        answerBtn2.setOnAction(e -> checkAnswer(answerBtn2.getText()));
        answerBtn3.setOnAction(e -> checkAnswer(answerBtn3.getText()));
        answerBtn4.setOnAction(e -> checkAnswer(answerBtn4.getText()));

        HBox answerRow = new HBox(10, answerBtn1, answerBtn2, answerBtn3, answerBtn4);

        Label lifeCount = new Label(lives+"");
        Label score = new Label(""+DBManager.getInstance().getScore(DBManager.getUserInstance()));
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        HBox ui = new HBox( backButton, score, lifeCount);
        VBox root = new VBox(200, questionLabel, spacer, answerRow);
        root.setPadding(new Insets(20));

        return new Scene(root, 600, 450);
    }

    private void checkAnswer(String selectedAnswer) {
        int userId = DBManager.getUserInstance().getId();
        DBManager db = DBManager.getInstance();
        boolean isCorrect = selectedAnswer.equals(question.getCorrect());
        db.insertQuestion(question);
        if (isCorrect) {
            int categoryId = db.getCategory(question.getCategory());

            db.addUserScore(userId, categoryId);

            System.out.println("Correct!");
        } else {
            System.out.println("Wrong!");
            lives--;
            if(lives == 0){
                SceneManager.getInstance().navigateTo(SceneType.MAIN);
            }
        }

        // optional: move to next question or back
        // SceneManager.getInstance().navigateTo(SceneType.MAIN);
    }
}