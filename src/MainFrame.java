import javax.swing.*;
import java.awt.Color;

public class MainFrame implements Runnable {

    private GraphicsPanel panel;

    public MainFrame() {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1396, 908); // 540 height of image + 40 for window menu bar
        frame.setLocationRelativeTo(null); // auto-centers frame in screen
        frame.setResizable(false);

        // create and add panel
        panel = new GraphicsPanel();
        frame.add(panel);
        panel.setBackground(Color.BLACK);

        // display the frame
        frame.setVisible(true);

        // start thread, required for animation
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            panel.repaint();  // we don't ever call "paintComponent" directly, but call this to refresh the panel
        }
    }

}