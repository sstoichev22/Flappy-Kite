import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    Thread gameThread;
    Input input;
    Player player;
    PipeManager pipeManager;
    ImageManager imageManager;
    KiteShopManager kiteShop;
    GameState gameState = GameState.MAIN_MENU;
    int screenWidth, screenHeight;
    Rectangle playButton, backButton, credits, resetScore, kiteSkins;
    BufferedImage currentKite;
    double UPS;
    GamePanel(Input input, int width, int height){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(input);
        this.input = input;
        screenWidth = width;
        screenHeight = height;
        player = new Player(200, 200, 100, 100);
        pipeManager = new PipeManager(player, 150, 120, width, height);
        imageManager = new ImageManager(this, screenWidth, screenHeight);
        kiteShop = new KiteShopManager(this);
        setGameSize(width, height);
        playButton = new Rectangle(screenWidth/2-50, screenHeight/2+150, 100, 100);
        backButton = new Rectangle(5, 5, 50, 50);
        credits = new Rectangle(30, screenHeight-75, 200, 50);
        resetScore = new Rectangle(screenWidth-300, screenHeight-75, 275, 50);
        kiteSkins = new Rectangle(screenWidth/2-125, screenHeight-75, 250, 50);
        initImages();
        currentKite = ImageManager.defaultKite;
    }

    @Override
    public void run() {
        UPS = 1_000_000_000.0/200;
        double FPS = 1_000_000_000.0/120;
        long previousTime = System.nanoTime();
        double deltaU = 0, deltaF = 0;
        int frames = 0, updates = 0;
        long lastCheck = System.currentTimeMillis();
        while(gameThread != null){
            long currentTime = System.nanoTime();
            deltaF += (currentTime-previousTime)/FPS;
            deltaU += (currentTime-previousTime)/UPS;
            previousTime = currentTime;
            if(deltaF >= 1){
                repaint();
                frames++;
                deltaF--;
            }
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(System.currentTimeMillis()-lastCheck >= 1_000){
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }
    public void update(){
        switch(gameState){
            case GameState.MAIN_MENU:
                break;
            case GameState.GAME_STATE:
                player.velocity += player.gravity;
                if(player.velocity > player.maxFallVelocity) player.velocity = player.maxFallVelocity;
                player.y += player.velocity;
                if(player.y > this.getHeight()-player.height){
                    player.y = this.getHeight()-player.height;
                    player.velocity = 0;
                }
                if(player.y < 0) player.y = 0;
                if(input.tapped > 0){
                    player.velocity = player.jumpVelocity;
                    input.tapped--;
                }
                pipeManager.updatePipes();
                imageManager.updateGround();
                break;
        }


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.white);
        switch(gameState){
            case GameState.MAIN_MENU:
                g2.drawImage(ImageManager.background, 0, 0, screenWidth, screenHeight, null);
                g2.drawImage(ImageManager.groundBackground, 0, screenHeight-550, screenWidth, screenHeight, null);
                g2.setFont(new Font("Georgia", Font.BOLD, 100));
                g2.drawString("Flappy Kite", screenWidth/2-275, 300);
                g2.setFont(new Font("Georgia", Font.BOLD, 30));
                g2.drawString("High Score: " + pipeManager.fileManager.readFile(), screenWidth/2-100, 400);
                g2.fillPolygon(new Polygon(new int[]{screenWidth/2-25, screenWidth/2-25, screenWidth/2+25}, new int[]{screenHeight/2+175, screenHeight/2+225, screenHeight/2+200}, 3));
                g2.drawRoundRect(playButton.x, playButton.y, playButton.width, playButton.height, 25, 25);
                g2.setFont(new Font("Cooper Black", Font.PLAIN, 40));
                g2.drawString("Credits", 50, credits.y+credits.height-10);
                g2.drawRoundRect(credits.x, credits.y, credits.width, credits.height, 25, 25);
                g2.drawString("Reset Score", resetScore.x+25, resetScore.y+resetScore.height-10);
                g2.drawRoundRect(resetScore.x, resetScore.y, resetScore.width, resetScore.height, 25, 25);
                g2.drawString("Kite Skins", kiteSkins.x+25, kiteSkins.y+kiteSkins.height-10);
                g2.drawRoundRect(kiteSkins.x, kiteSkins.y, kiteSkins.width, kiteSkins.height, 25, 25);

                break;
            case GameState.GAME_STATE:
                g2.drawImage(ImageManager.background, 0, 0, screenWidth, screenHeight, null);
                pipeManager.setTint(g2);
                imageManager.drawGround(g2);
//                g2.fillRect((int) player.x, (int) player.y, (int) player.width, (int) player.height);
                g2.drawImage(currentKite, (int) player.x, (int)player.y, (int)player.width, (int)player.height, null);
//                g2.drawRect((int) (player.x+player.width/2), (int) (player.y+player.height/4), (int) (player.width/2), (int) (player.height/2));
                pipeManager.drawPipes(g2);
                pipeManager.drawScore(g2);
                pipeManager.drawHighScore(g2);
                drawBackButton(g2);
                break;
            case GameState.CREDITS:
                g2.setColor(Color.ORANGE);
                g2.setFont(new Font("Trebuchet MS", Font.BOLD, 75));
                g2.drawString("Code: Stefan", screenWidth/2-250, 200);
                g2.drawString("Visuals: Luca", screenWidth/2-250, 400);
                g2.drawString("Play Tester: Josh", screenWidth/2-300, 600);
                drawBackButton(g2);
                break;
            case GameState.KITE_SKINS:
                g2.setColor(new Color(90, 60, 30));
                g2.fillRect(0, 0, screenWidth, screenHeight);
                g2.setColor(Color.white);
                g2.drawImage(ImageManager.kiteSkinsShelf, -200, -100, screenWidth+400, screenHeight*2, null);
                kiteShop.drawKiteShop(g2);
                drawBackButton(g2);
                break;
        }
    }
    private void initImages(){
        try {
            ImageManager.backArrow = ImageIO.read(getClass().getResourceAsStream("/res/backButton.png"));
            ImageManager.defaultKite = ImageIO.read(getClass().getResourceAsStream("/res/flappyKite.png"));
            ImageManager.background = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteBackground.png"));
            ImageManager.building = ImageIO.read(getClass().getResourceAsStream("/res/buildingFlappyKite.png"));
            ImageManager.groundBackground = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteGround.png"));
            ImageManager.kiteSkinsShelf = ImageIO.read(getClass().getResourceAsStream("/res/shelfFlappyKite.png"));
            ImageManager.kiteSilhouette = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteSilhouette.png"));
            ImageManager.kiteBronze = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteBronze.png"));
            ImageManager.kiteSilver = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteSilver.png"));
            ImageManager.kiteGold = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteGold.png"));
            ImageManager.kitePlatinum = ImageIO.read(getClass().getResourceAsStream("/res/flappyKitePlatinum.png"));
            ImageManager.kiteBundle = ImageIO.read(getClass().getResourceAsStream("/res/kiteBundle.png"));
            ImageManager.building1 = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteBuilding1.png"));
            ImageManager.building2 = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteBuilding2.png"));
            ImageManager.building3 = ImageIO.read(getClass().getResourceAsStream("/res/flappyKiteBuilding3.png"));
        } catch(IOException ignored){}
    }
    private void setGameSize(int width, int height){
        Dimension d = new Dimension(width, height);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void drawBackButton(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(backButton.x, backButton.y, backButton.width, backButton.height, 25, 25);
        g2.drawImage(ImageManager.backArrow, backButton.x+10, backButton.y+10, backButton.width-20, backButton.height-20, null);
    }

}
enum GameState{
    MAIN_MENU,
    CREDITS,
    GAME_STATE,
    KITE_SKINS
}
