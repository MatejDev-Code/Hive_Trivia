import model.PulledQuestion;import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 PLayPage.java
 * The main game, that allows for answering questions
 @author Matteo Ristoski
 @version 0.1.0
 @since 5/2/2026
 */

public class PlayPage implements ActionListener {

    JFrame frame = new JFrame();
    JLabel questionLabel = new JLabel();

    List<PulledQuestion> questionBatch;

    public PlayPage() {

        questionLabel.setBounds(100, 100, 1500, 100);
        questionLabel.setFont(new Font(null, Font.BOLD,20));

        frame.add(questionLabel);

        loadQuestions();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void loadQuestions(){
        try{
            questionBatch = QuestionAPI.getQuestions();

            if(!questionBatch.isEmpty()){
                GrabQuestion g = new GrabQuestion(questionBatch.getFirst());

                questionLabel.setText(g.getQuestion());

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
