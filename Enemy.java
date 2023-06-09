import java.awt.*;

public class Enemy extends GameObject {
    public Enemy() {
        super();
    }

    public Enemy(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //敌我飞机碰撞检测
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            if(this.frame.planeObj.life > 1){
                if(this.frame.invincible == 0) {
                    this.frame.planeObj.life--;
                }
                Explode explode = new Explode(x+5,y);
                GameUtils.explodeList.add(explode);
                GameUtils.removeList.add(explode);
                this.x = -200;
                this.y = 200;
                GameUtils.removeList.add(this);
            }else
                GameWin.state = 3;
        }
        //超出屏幕后移除
        if(y> frame.HEIGHT){
            this.x = -100;
            this.y = 100;
            GameUtils.removeList.add(this);
        }

        //敌方飞机与我方子弹的碰撞检测
        for(Shell shellObj:GameUtils.shellObjList){
            if(this.getRec().intersects(shellObj.getRec())){
                Explode explode = new Explode(x+5,y);
                GameUtils.explodeList.add(explode);
                GameUtils.removeList.add(explode);
                shellObj.setX(-100);
                shellObj.setY(100);
                this.x = -200;
                this.y = 200;
                GameUtils.removeList.add(shellObj);
                GameUtils.enemyObjList.add(this);
                GameWin.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
