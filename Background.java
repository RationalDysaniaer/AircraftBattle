import java.awt.*;

public class Background extends GameObject {
    public Background() {
        super();
    }

    public Background(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    public Background(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        if(y >= 800){
            y = -1600;
        }
    }
}
