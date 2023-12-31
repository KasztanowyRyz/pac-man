package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @brief The main application class.
 *
 * This class contains the main method and initializes different windows/screens of the application.
 */
class App {

    /**
     * @brief Initializes the main window of the application.
     *
     * This method creates a window frame, sets the title, and adds a JPanel to draw on.
     * It also sets up keyboard input handling and configures the window properties.
     */
    private static void initWindow() {
        // create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Ale to juz nie jest smieszne");
        // when we close the window, stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create the jpanel to draw on.
        // this also initializes the game loop
        WelcomeScreen welcome = new WelcomeScreen();
        // board = new Board();
        // add the jpanel to the window
        window.add(welcome);
        Board board = null;
        // pass keyboard inputs to the jpanel
        window.addKeyListener(board);
        // don't allow the user to resize the window
        window.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);

    }

    /**
     * @brief Initializes the welcome window of the application.
     *
     * This method creates a window frame for the welcome screen and configures its properties.
     */
    private static void initWelcomeWindow() {
        JFrame welcomeFrame = new JFrame("Pacman");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setResizable(false);

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeFrame.add(welcomeScreen);

        welcomeFrame.pack();
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
    }

    /**
     * @brief Initializes the board window of the application.
     *
     * This method creates a window frame for the game board and configures its properties.
     */
    public static void initBoardWindow() {
        JFrame boardFrame = new JFrame("Pacman");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setResizable(false);

        Board board = new Board();
        boardFrame.add(board);
        boardFrame.addKeyListener(board);

        boardFrame.pack();
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setVisible(true);
    }

    /**
     * @brief Initializes the leaderboard window of the application.
     *
     * This method creates a window frame for the leaderboard screen and configures its properties.
     */
    public static void initLeaderboardWindow(){
        JFrame leaderboardFrame = new JFrame("Leaderboard");
        leaderboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leaderboardFrame.setResizable(false);

        Leaderboard leaderboard = new Leaderboard();
        leaderboardFrame.add(leaderboard);

        leaderboardFrame.pack();
        leaderboardFrame.setLocationRelativeTo(null);
        leaderboardFrame.setVisible(true);
    }

    /**
     * @breif program's main method - starts by initialising the WelcomeWindow
     * @param args
     */
    public static void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWelcomeWindow();
            }
        });
    }

}