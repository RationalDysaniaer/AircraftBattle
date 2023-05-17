import java.awt.*;

public class Boos extends GameObject {
    int life = 300;
    public Boos() {
        super();
    }

    public Boos(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if(x > 450 || x < -50){
            speed = -speed;
        }
        x += speed;
        for(Shell shellObj:GameUtils.shellObjList){
            if(this.getRec().intersects(shellObj.getRec())){
                shellObj.setX(-100);
                shellObj.setY(100);
                GameUtils.removeList.add(shellObj);
                life--;
            }
            if(life <= 0){
                GameWin.state = 4;
            }
        }
        //血条
        if(life>200){
            gImage.setColor(Color.yellow);
            gImage.fillRect(x-50,y+130,300,5);
            gImage.setColor(Color.green);
            gImage.fillRect(x-50,y+130,(life-200)*3,5);
        } else if (life > 100) {
            gImage.setColor(Color.red);
            gImage.fillRect(x-50,y+130,300,5);
            gImage.setColor(Color.yellow);
            gImage.fillRect(x-50,y+130,(life-100)*3,5);
        }else{
            gImage.setColor(Color.white);
            gImage.fillRect(x-50,y+130,300,5);
            gImage.setColor(Color.red);
            gImage.fillRect(x-50,y+130,life*3,5);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
