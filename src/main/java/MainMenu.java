import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Name: Ash Straws
 * Date: 4/25/2026
 * Explanation: MainMenu is the page that holds the start game button, stats button, logout button,
 * and *Hive Trivia: Main Menu* label
 * version 1.0
 */
public class MainMenu extends JFrame {
    /**
     * Constructor for the MainMenu class.
     * Initializes the frame settings, creates the UI components (labels and buttons),
     * and sets up the layout and action listeners for navigation.
     */
    public MainMenu() {
        //window setup
        setTitle("The Hive Trivia");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel with BoxLayout to stack items vertically
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //Padding around the edges
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));

        //Title Label: Hive Trivia
        JLabel titleLabel = new JLabel("Hive Trivia");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Subtitle Label: Main Menu
        JLabel subtitleLabel = new JLabel("Main Menu");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Buttons stacked vertically *START*, *STATs*, *LOGOUT*
        JButton startButton = createMenuButton("START");
        JButton statsButton = createMenuButton("STATS");
        JButton logoutButton = createMenuButton("LOGOUT");

        //Listener for START
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Loading Game Screen...");
            }
        });
        //Listener for STATS
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Opening Player Statistics...");
            }
        });
        //Listener for the LOGOUT button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirm before closing the app
                int response = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        //Assembly
        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Spacer
        mainPanel.add(startButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer
        mainPanel.add(statsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer
        mainPanel.add(logoutButton);

        // Add the finished panel to the frame
        add(mainPanel);
    }
    /**
     * Helper method to keep button styles consistent across the menu.
     * Sets the alignment, maximum size, and font for the button.
     * @param text The String text to be displayed on the button.
     * @return A JButton configured with the standard menu styling.
     */
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 45));
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        return button;
    }
    /**
     * The main entry point of the application.
     * Uses SwingUtilities to launch the GUI on the Event Dispatch Thread.
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Ensure UI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
