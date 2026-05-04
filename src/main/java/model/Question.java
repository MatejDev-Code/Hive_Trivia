package model;

import java.util.ArrayList;

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

    //THERE IS NO CONSTRUCTOR YET.
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