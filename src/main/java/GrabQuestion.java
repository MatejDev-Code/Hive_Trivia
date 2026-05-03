import java.util.List;

/**
 GrabQuestions.java
 * Grabs the questions, category, wrong and correct answers
 @author Matteo Ristoski
 @version 0.1.0
 @since 5/2/2026
 */

public class GrabQuestion {

    private final Question q;

    public GrabQuestion(Question q){
        this.q = q;
    }

    public String getQuestion(){
        return q.question().text();
    }

    public String getCategory(){
        return q.category();
    }

    public String getCorrectAnswer(){
        return q.correctAnswer();
    }

    public List<String> getIncorrectAnswers(){
        return q.incorrectAnswers();
    }

    public String getID(){
        return q.id();
    }

}
