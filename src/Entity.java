
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    double x, y, width, height;
    BufferedImage bi;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public Entity(int x, int y, int width, int height, BufferedImage bi){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bi = bi;
    }
    public boolean intersects(Rectangle rect){
        return rect.intersects(new Rectangle((int) x, (int) y, (int) width, (int) height));
    }
}
