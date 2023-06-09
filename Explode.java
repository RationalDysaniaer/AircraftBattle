import java.awt.*;

public class Explode extends GameObject{
    static  Image[] picture = new Image[16];
    int explodeCount = 0;//爆炸图片计数
    static{
        for(int i = 0;i<picture.length;i++){
            picture[i] = Toolkit.getDefaultToolkit().getImage("src\\Images\\e"+(i+1)+".gif");
        }
    }
    public Explode(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        // 每一次绘制不会消失，下一次repaint方法再次调用绘制下一张图片，直到爆炸图片绘制完
        if (explodeCount <= 15){
            img = picture[explodeCount];
            super.paintSelf(gImage);
            explodeCount++;
        }
        else {
            GameUtils.explodeList.remove(this);
        }
    }
}
