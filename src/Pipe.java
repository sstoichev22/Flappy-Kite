import java.awt.*;
import java.awt.image.BufferedImage;

public class Pipe extends Entity{
    final double speed = 1;
    boolean scoreUsed = false;
    PipeLocation pipeLocation;
    BufferedImage bi;
    Pipe(int x, int y, int width, int height, PipeLocation pipeLocation){
        super(x, y, width, height);
        this.pipeLocation = pipeLocation;
    }
    Pipe(int x, int y, int width, int height, BufferedImage bi){
        super(x, y, width, height);
        this.bi = bi;
    }
}
enum PipeLocation{
    TOP,
    BOTTOM
}
