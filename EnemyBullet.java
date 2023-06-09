import java.awt.*;

public class EnemyBullet extends GameObject {
    public EnemyBullet() {
        super();
    }
    private int z;//我方飞机在boos的左右方位
    private int k;//斜率
    private int b;
    private int speed2;//子弹横向速度

    public EnemyBullet(Image img, int x, int y, int width, int height, double speed,int z,int speed2,int k,int b,GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
        this.z = z;
        this.speed2 = speed2;
        this.k = k;
        this.b = b;
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        //第一阶段
        if(this.frame.boos.life > 200) {
            if(z == 1)
                x += speed2;
            else if(z == 2)
                x -= speed2;
            y += speed;
        }//第二阶段
        else if(this.frame.boos.life > 100){
            y += 15;
            if(k != 0)
                x = (y-b)/k;
        }//第三阶段
        else{
            if(z == 1)
                x += speed2;
            else if(z == 2)
                x -= speed2;
            y += speed;
        }


        //碰撞检测(地方子弹和我方飞机)
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            if(this.frame.planeObj.life >1){
                if(this.frame.invincible == 0) {
                    this.frame.planeObj.life--;
                }
                this.x = -100;
                this.y = 100;
                GameUtils.removeList.add(this);
            }else
                GameWin.state = 3;
        }
        if(y > frame.HEIGHT){
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
