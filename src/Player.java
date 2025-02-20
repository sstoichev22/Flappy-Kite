public class Player extends Entity{
    double velocity;
    final double gravity = .1, maxFallVelocity = 2, jumpVelocity = -5;
    Player(int x, int y, int width, int height){
        super(x, y, width, height);
    }
}
