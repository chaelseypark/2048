import javax.swing.*;
import java.awt.*;

public class RunTwentyFortyEight implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("");
        frame.setLocation(450, 75);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Score: 0");
        status_panel.add(status);

        // Game board
        final GameCourt backend = new GameCourt(status, frame);
        frame.add(backend, BorderLayout.CENTER);

        //Control panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> backend.reset());
        control_panel.add(reset);

        //Undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> backend.undo());
        control_panel.add(undo);

        //Save Progress button
        final JButton save = new JButton("Save Progress");
        save.addActionListener(e -> backend.saveGame());
        control_panel.add(save);

        //instructions window
        String instructions = "2048\n\n" + "HOW TO PLAY: Use your arrow keys to move the tiles.\n" +
                "Tiles with the same number merge into one when they touch.\n" +
                "Add them up to reach " +
                "2048!\n\n" +
                "FEATURES:\n" + "Undo - Click to undo a move\n" + "Save - Click to save your " +
                "progress and resume later\n" +
                "Reset - Click to reset round";
        JOptionPane.showConfirmDialog(null, instructions, "2048",
                JOptionPane.DEFAULT_OPTION);

        int userDecision = JOptionPane.showConfirmDialog(null,
                "Would you like to load previous progress?", "2048",
                JOptionPane.YES_NO_OPTION);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if (userDecision == JOptionPane.YES_OPTION) {
            backend.loadGame();
        }

        if (userDecision != JOptionPane.YES_OPTION) {
            backend.reset();
        }
    }
}
