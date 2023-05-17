import java.awt.*;

public class Shell extends GameObject {
    public Shell() {
        super();
    }

    public Shell(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public Image getImj() {
        return super.getImj();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y -= speed;

        if(y<-200){
            this.x = -100;
            this.y = 100;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
