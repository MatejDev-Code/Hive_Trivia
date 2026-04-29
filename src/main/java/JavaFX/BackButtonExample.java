import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;

public class BackButtonExample {

    public static void main(String[] args) {
        JFrame firstWindow = createFirstWindow();
        firstWindow.setVisible(true);
    }

    // Creates the first window
    public static JFrame createFirstWindow() {
        JFrame firstFrame = new JFrame("First Window");
        firstFrame.setSize(400, 300);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setLayout(null);

        JLabel label = new JLabel("This is the first window");
        label.setBounds(120, 60, 200, 30);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(140, 120, 100, 40);

        nextButton.addActionListener(e -> {
            firstFrame.dispose();
            JFrame secondWindow = createSecondWindow();
            secondWindow.setVisible(true);
        });

        firstFrame.add(label);
        firstFrame.add(nextButton);

        return firstFrame;
    }

    // Creates the second window
    public static JFrame createSecondWindow() {
        JFrame secondFrame = new JFrame("Second Window");
        secondFrame.setSize(400, 300);
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        secondFrame.setLayout(null);

        JLabel label = new JLabel("This is the second window");
        label.setBounds(110, 60, 200, 30);

        JButton backButton = new JButton("Back");
        backButton.setBounds(140, 120, 100, 40);

        backButton.addActionListener(e -> {
            secondFrame.dispose();
            JFrame firstWindow = createFirstWindow();
            firstWindow.setVisible(true);
        });

        secondFrame.add(label);
        secondFrame.add(backButton);

        return secondFrame;
    }

    // Helper method to find a button by its text
    public static JButton findButton(JFrame frame, String buttonText) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            }
        }
        return null;
    }
}