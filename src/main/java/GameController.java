import database.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController implements GameObserver {

    private final gamerepo repo;

    private Question question;

    private final Label questionLabel = new Label();
    private final Label lifeCount = new Label();
    private final Label scoreLabel = new Label();

    private final Button answerBtn1 = new Button();
    private final Button answerBtn2 = new Button();
    private final Button answerBtn3 = new Button();
    private final Button answerBtn4 = new Button();

    public GameController() {
        repo = new gamerepo();
        repo.addObserver(this);
    }

    public Scene buildScene() {
        Button backButton = new Button("Back");

        backButton.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.MAIN)
        );

        answerBtn1.setOnAction(e -> checkAnswer(answerBtn1.getText()));
        answerBtn2.setOnAction(e -> checkAnswer(answerBtn2.getText()));
        answerBtn3.setOnAction(e -> checkAnswer(answerBtn3.getText()));
        answerBtn4.setOnAction(e -> checkAnswer(answerBtn4.getText()));

        HBox answerRow = new HBox(10, answerBtn1, answerBtn2, answerBtn3, answerBtn4);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        HBox ui = new HBox(100, backButton, scoreLabel, lifeCount);
        VBox root = new VBox(20, ui, questionLabel, spacer, answerRow);
        root.setPadding(new Insets(20));

        refresh();

        return new Scene(root, 600, 450);
    }

    private void checkAnswer(String selectedAnswer) {
        repo.checkAnswer(selectedAnswer);

        if (repo.getLives() == 0) {
            showInfo("GAME OVER \n Score: " + repo.getScore());
            SceneManager.getInstance().navigateTo(SceneType.MAIN);
        }
    }

    private void refresh() {
        onGameChanged(repo.getQuestion(), repo.getLives(), repo.getScore());
    }

    @Override
    public void onGameChanged(Question question, int lives, int score) {
        this.question = question;

        if (question == null) {
            return;
        }

        questionLabel.setText(question.getQuestion());
        lifeCount.setText("Lives: " + lives);
        scoreLabel.setText("Score: " + score);

        List<String> answers = new ArrayList<>();
        answers.add(question.getCorrect());
        answers.addAll(Arrays.asList(question.getWrongs()));

        Collections.shuffle(answers);

        answerBtn1.setText(answers.get(0));
        answerBtn2.setText(answers.get(1));
        answerBtn3.setText(answers.get(2));
        answerBtn4.setText(answers.get(3));
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}