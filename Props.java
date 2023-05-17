import java.awt.*;

public class Props extends GameObject{
    private String name;
    public Props() {
    }

    public Props(Image img, String name,int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
        this.name = name;
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y+=speed;
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            if(name.equals("level")) {
                GameWin.level += 1;
            }
            if(name.equals("life") && this.frame.planeObj.life < 30) {
                if(this.frame.planeObj.life >= 24)
                    this.frame.planeObj.life = 30;
                else
                    this.frame.planeObj.life += 6;
            }

            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
