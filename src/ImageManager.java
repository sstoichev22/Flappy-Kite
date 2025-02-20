import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageManager {
    public static BufferedImage backArrow;
    public static BufferedImage defaultKite;
    public static BufferedImage background;
    public static BufferedImage building;
    public static BufferedImage groundBackground;
    public static BufferedImage kiteSkinsShelf;
    public static BufferedImage kiteBronze;
    public static BufferedImage kiteSilhouette;
    public static BufferedImage kiteSilver;
    public static BufferedImage kiteGold;
    public static BufferedImage kitePlatinum;
    public static BufferedImage kiteBundle;
    public static BufferedImage building1;
    public static BufferedImage building2;
    public static BufferedImage building3;

    double speed = 1;
    private final ArrayList<Integer> grounds;
    int screenWidth, screenHeight;
    GamePanel gamePanel;
    ImageManager(GamePanel gamePanel, int screenWidth, int screenHeight){
        this.gamePanel = gamePanel;
        grounds = new ArrayList<>();
        grounds.add(0);
        grounds.add(screenWidth);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    public void updateGround() {
        // Loop through all ground images
        for (int i = 0; i < grounds.size(); i++) {
            // Move ground to the left
//            grounds.set(i, (int) (grounds.get(i) - (speed + (gamePanel.pipeManager.score / 1000.0))));
            grounds.set(i, (int) (grounds.get(i) - speed));


            // If the ground has moved off the screen
            if (grounds.get(i) <= -screenWidth) {
                // Move it to the right, after the last ground image in the list
                int lastGroundPosition = grounds.get((i == 0 ? grounds.size() - 1 : i - 1)); // Get the position of the previous ground
                grounds.set(i, lastGroundPosition + screenWidth); // Place it to the right of the last ground
            }
        }
    }

    public void drawGround(Graphics2D g2) {
        for (int i = 0; i < grounds.size(); i++) {
            g2.drawImage(groundBackground, grounds.get(i), screenHeight - 550, screenWidth, screenHeight, null);
        }
    }
}
