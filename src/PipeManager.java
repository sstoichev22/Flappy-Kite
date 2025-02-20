import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PipeManager {
    ArrayList<Pipe> pipes;
    Player player;
    FileManager fileManager;
    int distanceBetweenPipes, ups, cooldown = 0, screenWidth, screenHeight, score = 0;
    Color[] tintArray;
    int tintIdx = -1;
    PipeManager(Player player, int distanceBetweenPipes, int ups, int screenWidth, int screenHeight){
        this.player = player;
        this.distanceBetweenPipes = distanceBetweenPipes;
        this.ups = ups;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        pipes = new ArrayList<>();
        fileManager = new FileManager();
        tintArray = new Color[]{new Color(70, 50, 255), new Color(255, 105, 180), new Color(90, 2, 30), new Color(69, 69, 70), new Color(200, 60, 169), new Color(100, 43, 224)};
    }
    public void updatePipes(){
        cooldown--;
        if(cooldown <= 0){
            int offset = (int)(Math.random() * (200+Math.min(score, 100)));
            int pipeWidth = 100;
            int gapSize = 300;
            BufferedImage bi = null;
            int rand = (int)(Math.random()*3)+1;
            switch(rand){
                case 1 -> bi = ImageManager.building1;
                case 2 -> bi = ImageManager.building2;
                case 3 -> bi = ImageManager.building3;

            }
            pipes.add(new Pipe(screenWidth, 0, pipeWidth, 300 - offset, ImageManager.kiteBundle));
            pipes.add(new Pipe(screenWidth, 300 - offset + gapSize, pipeWidth, screenHeight - (300 - offset + gapSize), bi));
            cooldown += (int) (ups*2.5)-Math.min(score, 200);
        }
        for(int i = 0 ; i < pipes.size(); i++){
            if(pipes.get(i).x <= 200 && !pipes.get(i).scoreUsed) {
                score++;
                pipes.get(i).scoreUsed = true;
                if(fileManager.readFile() < score/2) fileManager.writeToFile(score/2);
                if(score/2.0 % 50.0  == 0) tintIdx = (tintIdx + 1) % tintArray.length;
            }
            if(pipes.get(i).intersects(new Rectangle((int) (player.x+player.width/2), (int) (player.y+player.height/4), (int) (player.width/2), (int) (player.height/2)))) resetGame();
            else if(pipes.get(i).x < -pipes.get(i).width) pipes.remove(i);
            else pipes.get(i).x -= pipes.get(i).speed+(score/100.0);
        }
    }
    public void drawPipes(Graphics2D g2){
        for(int i = 0; i < pipes.size(); i++){
            Pipe p = pipes.get(i);
//            g2.drawRect((int) p.x, (int) p.y, (int) p.width, (int) p.height);
            g2.drawImage(p.bi, (int) p.x, (int) p.y, (int) p.width, (int) p.height, null);
        }
    }
    public void drawScore(Graphics g2){
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.drawString(""+(score/2), screenWidth/2-25, 100);
    }
    public void drawHighScore(Graphics g2){
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.drawString("High Score: "+(fileManager.readFile()), screenWidth/2+300, 100);
    }
    public void setTint(Graphics2D g2){
        if(tintIdx >= 0) {
            Color color = g2.getColor();
            Composite composite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
            g2.setColor(tintArray[tintIdx]);
            g2.fillRect(0, 0, screenWidth, screenHeight);
            g2.setColor(color);
            g2.setComposite(composite);
        }
    }
    private void resetGame(){
        pipes.clear();
        score = 0;
        player.y = screenWidth/2.0;
    }

}

