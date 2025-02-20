import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        // Set the system property for OpenGL
        System.setProperty("sun.java2d.opengl", "true");
        JFrame frame = new JFrame("Flappy Kite");
        Input input = new Input();
        GamePanel gamePanel = new GamePanel(input, 1280, 720);
        input.getGamePanel(gamePanel);
        frame.add(gamePanel);
        frame.addMouseListener(input);
        frame.addKeyListener(input);
        frame.pack();

        frame.setIconImage(ImageManager.defaultKite);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gamePanel.startGameThread();
//        gamePanel.pipeManager.fileManager.writeToFile(1000);
        //kite skins
        //50, 100, 250, 500
    }
}
