import java.awt.*;

public class Explode extends GameObject{
    static  Image[] picture = new Image[16];
    int explodeCount = 0;
    static{
        for(int i = 0;i<picture.length;i++){
            picture[i] = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\javaworkspace\\untitled\\AircraftBattle\\src\\Images\\e"+(i+1)+".gif");
        }
    }
    public Explode(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if (explodeCount < 16){
            img = picture[explodeCount];
            super.paintSelf(gImage);
            explodeCount++;
        }
        // 每一次绘制不会消失，下一次repaint方法再次调用重绘，知道爆炸图片绘制完
        if(explodeCount == 16) {
            GameUtils.explodeList.remove(this);
        }
    }
}
