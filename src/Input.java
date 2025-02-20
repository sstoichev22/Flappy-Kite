import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;

public class Input implements MouseListener, KeyListener {
    GamePanel gamePanel;
    int tapped = 0, mouseXOffset = -10, mouseYOffset = -30;
    public void getGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        switch(gamePanel.gameState){
            case GameState.MAIN_MENU:
                if(gamePanel.playButton.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) gamePanel.gameState = GameState.GAME_STATE;
                if(gamePanel.credits.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) gamePanel.gameState = GameState.CREDITS;
                if(gamePanel.resetScore.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset) && gamePanel.pipeManager.fileManager.readFile() != 0){
                    int response =JOptionPane.showConfirmDialog(gamePanel, "Reset High Score?");
                    if(response == JOptionPane.YES_OPTION) {
                        gamePanel.pipeManager.fileManager.writeToFile(0);
                        gamePanel.kiteShop.selected = Kite.DEFAULT;
                        gamePanel.currentKite = ImageManager.defaultKite;
                    }
                }
                if(gamePanel.kiteSkins.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) gamePanel.gameState = GameState.KITE_SKINS;
                break;
            case GameState.GAME_STATE:
                if(gamePanel.backButton.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) gamePanel.gameState = GameState.MAIN_MENU;
                tapped++;
                break;
            case GameState.CREDITS:
                if(gamePanel.backButton.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) gamePanel.gameState = GameState.MAIN_MENU;
                break;
            case GameState.KITE_SKINS:
                if(gamePanel.backButton.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) gamePanel.gameState = GameState.MAIN_MENU;
                int highscore = gamePanel.pipeManager.fileManager.readFile();
                if(gamePanel.kiteShop.kDSelect.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset)) {
                    gamePanel.currentKite = ImageManager.defaultKite;
                    gamePanel.kiteShop.selected = Kite.DEFAULT;
                }
                if(gamePanel.kiteShop.kBSelect.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset) && highscore >= 50) {
                    gamePanel.currentKite = ImageManager.kiteBronze;
                    gamePanel.kiteShop.selected = Kite.BRONZE;
                }
                if(gamePanel.kiteShop.kSSelect.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset) && highscore >= 100) {
                    gamePanel.currentKite = ImageManager.kiteSilver;
                    gamePanel.kiteShop.selected = Kite.SILVER;
                }
                if(gamePanel.kiteShop.kGSelect.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset) && highscore >= 250) {
                    gamePanel.currentKite = ImageManager.kiteGold;
                    gamePanel.kiteShop.selected = Kite.GOLD;
                }
                if(gamePanel.kiteShop.kPSelect.contains(e.getX()+mouseXOffset, e.getY()+mouseYOffset) && highscore >= 500) {
                    gamePanel.currentKite = ImageManager.kitePlatinum;
                    gamePanel.kiteShop.selected = Kite.PLATINUM;
                }
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(gamePanel.gameState){
            case GameState.MAIN_MENU:
                if(e.getKeyCode() == KeyEvent.VK_SPACE) gamePanel.gameState = GameState.GAME_STATE;
                break;
            case GameState.GAME_STATE:
                if(e.getKeyCode() == KeyEvent.VK_SPACE) tapped++;
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) gamePanel.gameState = GameState.MAIN_MENU;
            case GameState.CREDITS:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) gamePanel.gameState = GameState.MAIN_MENU;
                break;
            case GameState.KITE_SKINS:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) gamePanel.gameState = GameState.MAIN_MENU;
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



}
