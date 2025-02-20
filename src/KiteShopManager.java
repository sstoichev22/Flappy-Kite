import java.awt.*;

public class KiteShopManager {
    GamePanel gamePanel;
    Rectangle kiteBronze, kBSelect, kiteSilver, kSSelect, kiteGold, kGSelect, kitePlatinum, kPSelect, kiteDefault, kDSelect;
    Kite selected = Kite.DEFAULT;

    KiteShopManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        kiteBronze = new Rectangle(gamePanel.screenWidth/4-90, gamePanel.screenHeight/2-100, 100, 100);
        kBSelect = new Rectangle(gamePanel.screenWidth/4-90, gamePanel.screenHeight/2+20, 100, 40);
        kiteSilver = new Rectangle((gamePanel.screenWidth/4-90)*2, gamePanel.screenHeight/2-100, 100, 100);
        kSSelect = new Rectangle((gamePanel.screenWidth/4-90)*2, gamePanel.screenHeight/2+20, 100, 40);
        kiteGold = new Rectangle((gamePanel.screenWidth/4-90)*3, gamePanel.screenHeight/2-100, 100, 100);
        kGSelect = new Rectangle((gamePanel.screenWidth/4-90)*3, gamePanel.screenHeight/2+20, 100, 40);
        kitePlatinum = new Rectangle((gamePanel.screenWidth/4-90)*4, gamePanel.screenHeight/2-100, 100, 100);
        kPSelect = new Rectangle((gamePanel.screenWidth/4-90)*4, gamePanel.screenHeight/2+20, 100, 40);
        kiteDefault = new Rectangle(gamePanel.screenWidth/2-75, gamePanel.screenHeight-100, 100, 100);
        kDSelect = new Rectangle(gamePanel.screenWidth/2-75, gamePanel.screenHeight-100, 100, 40);
    }
    public void drawKiteShop(Graphics2D g2){
        int score = gamePanel.pipeManager.fileManager.readFile();
        g2.setFont(new Font("Georgia", Font.BOLD, 80));
        g2.drawString("Kite Store", gamePanel.screenWidth/2-225, 100);
        g2.setFont(new Font("Trebuchet MS", Font.BOLD, 30));

        g2.drawImage(ImageManager.defaultKite, kiteDefault.x+10, kiteDefault.y-kiteDefault.height, kiteDefault.width, kiteDefault.height, null);
        if(selected == Kite.DEFAULT){
            g2.setColor(Color.green);
            g2.drawString("Selected", kiteDefault.x, kiteDefault.y + 30);
            g2.drawRoundRect(kDSelect.x-10, kDSelect.y, kDSelect.width+40, kDSelect.height, 25, 25);
        } else{
            g2.setColor(Color.black);
            g2.drawString("Select", kiteDefault.x+10, kiteDefault.y+30);
            g2.drawRoundRect(kDSelect.x, kDSelect.y, kDSelect.width, kDSelect.height, 25, 25);
        }

        if(score < 50) {
            g2.drawImage(ImageManager.kiteSilhouette, kiteBronze.x, kiteBronze.y, kiteBronze.width, kiteBronze.height, null);
            g2.setColor(Color.red);
            g2.drawString("50", kiteBronze.x + kiteBronze.width / 2-10, kiteBronze.y + (int) (kiteBronze.height * 3.0 / 2));
        } else{
            g2.drawImage(ImageManager.kiteBronze, kiteBronze.x, kiteBronze.y, kiteBronze.width, kiteBronze.height, null);
            if(selected == Kite.BRONZE){
                g2.setColor(Color.green);
                g2.drawString("Selected", kiteBronze.x, kiteBronze.y + (int) (kiteBronze.height * 3.0 / 2));
                g2.drawRoundRect(kBSelect.x-10, kBSelect.y, kBSelect.width+40, kBSelect.height, 25, 25);
            } else{
                g2.setColor(Color.black);
                g2.drawString("Select", kiteBronze.x + 10, kiteBronze.y + (int) (kiteBronze.height * 3.0 / 2));
                g2.drawRoundRect(kBSelect.x, kBSelect.y, kBSelect.width, kBSelect.height, 25, 25);
            }

        }
        if(score < 100){
            g2.drawImage(ImageManager.kiteSilhouette, kiteSilver.x, kiteSilver.y, kiteSilver.width, kiteSilver.height, null);
            g2.setColor(Color.red);
            g2.drawString("100", kiteSilver.x + kiteSilver.width / 2-10, kiteSilver.y + (int) (kiteSilver.height * 3.0 / 2));
        } else{
            g2.drawImage(ImageManager.kiteSilver, kiteSilver.x, kiteSilver.y, kiteSilver.width, kiteSilver.height, null);
            if(selected == Kite.SILVER){
                g2.setColor(Color.green);
                g2.drawString("Selected", kiteSilver.x, kiteSilver.y + (int) (kiteSilver.height * 3.0 / 2));
                g2.drawRoundRect(kSSelect.x-10, kSSelect.y, kSSelect.width+40, kSSelect.height, 25, 25);
            } else{
                g2.setColor(Color.black);
                g2.drawString("Select", kiteSilver.x + 10, kiteSilver.y + (int) (kiteSilver.height * 3.0 / 2));
                g2.drawRoundRect(kSSelect.x, kSSelect.y, kSSelect.width, kSSelect.height, 25, 25);
            }
        }
        if(score < 250){
            g2.drawImage(ImageManager.kiteSilhouette, kiteGold.x, kiteGold.y, kiteGold.width, kiteGold.height, null);
            g2.setColor(Color.red);
            g2.drawString("250", kiteGold.x + kiteGold.width / 2-10, kiteGold.y + (int) (kiteGold.height * 3.0 / 2));
        } else{
            g2.drawImage(ImageManager.kiteGold, kiteGold.x, kiteGold.y, kiteGold.width, kiteGold.height, null);
            if(selected == Kite.GOLD){
                g2.setColor(Color.green);
                g2.drawString("Selected", kiteGold.x, kiteGold.y + (int) (kiteGold.height * 3.0 / 2));
                g2.drawRoundRect(kGSelect.x-10, kGSelect.y, kGSelect.width+40, kGSelect.height, 25, 25);
            } else{
                g2.setColor(Color.black);
                g2.drawString("Select", kiteGold.x + 10, kiteGold.y + (int) (kiteGold.height * 3.0 / 2));
                g2.drawRoundRect(kGSelect.x, kGSelect.y, kGSelect.width, kGSelect.height, 25, 25);
            }
        }
        if(score < 500){
            g2.drawImage(ImageManager.kiteSilhouette, kitePlatinum.x, kitePlatinum.y, kitePlatinum.width, kitePlatinum.height, null);
            g2.setColor(Color.red);
            g2.drawString("500", kitePlatinum.x + kitePlatinum.width / 2-10, kitePlatinum.y + (int) (kitePlatinum.height * 3.0 / 2));
        } else{
            g2.drawImage(ImageManager.kitePlatinum, kitePlatinum.x, kitePlatinum.y, kitePlatinum.width, kitePlatinum.height, null);
            if(selected == Kite.PLATINUM){
                g2.setColor(Color.green);
                g2.drawString("Selected", kitePlatinum.x, kitePlatinum.y + (int) (kitePlatinum.height * 3.0 / 2));
                g2.drawRoundRect(kPSelect.x-10, kPSelect.y, kPSelect.width+40, kPSelect.height, 25, 25);
            } else{
                g2.setColor(Color.black);
                g2.drawString("Select", kitePlatinum.x + 10, kitePlatinum.y + (int) (kitePlatinum.height * 3.0 / 2));
                g2.drawRoundRect(kPSelect.x, kPSelect.y, kPSelect.width, kPSelect.height, 25, 25);
            }
        }
    }
}
enum Kite{
    DEFAULT,
    BRONZE,
    SILVER,
    GOLD,
    PLATINUM
}
