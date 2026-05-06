package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/30/2026
 */
public class Question {
    private String question;
    private String correctAns;
    private String[] wrongAns = new String[3];
    private String category;

    public Question(){}

    public Question(PulledQuestion pulled){
        if (pulled != null){
            this.question = pulled.question().text();
            this.correctAns = pulled.correctAnswer();
            this.category = pulled.category();

            List<String> incorrectList = pulled.incorrectAnswers();
            for (int i = 0; i < 3 && i < incorrectList.size(); i++){
                this.wrongAns[i] = incorrectList.get(i);
            }
        }
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect() {
        return correctAns;
    }

    public String[] getWrongs() {
        return wrongAns;
    }

    public String getCategory() {
        return category;
    }
}