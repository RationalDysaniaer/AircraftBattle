import java.awt.*;

public class barrier extends GameObject{
    int life;
    int lifeMax;
    int state = 0;
    int Y = 0;
    public barrier() {
    }

    public barrier(int x,int y,int speed,int life,int width,int height,GameWin frame) {
        this.frame = frame;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.life = life;
        this.lifeMax = life;
    }



    @Override
    public void paintSelf(Graphics gImage) {
        gImage.drawImage(GameUtils.barrier1,x,Y,50,50,frame);
        gImage.drawImage(GameUtils.barrier2,x+150,Y,50,50,frame);

        if(Y<y && life == lifeMax)
            Y += speed;
        if(Y == y && life == lifeMax){
            gImage.setColor(Color.red);
            gImage.fillRect(x+50-5,Y+20,110,10);
            state = 1;
        }
        if(state == 1){
            gImage.setColor(Color.red);
            gImage.fillRect(x+50-5,Y+20,110,10);

            for(Shell shellObj:GameUtils.shellObjList){
                if(shellObj.getRec().intersects(new Rectangle(x+20,Y+20,160,10))){
                    if(life > 0){
                        life--;
                        shellObj.setX(-100);
                        shellObj.setY(100);
                        GameUtils.removeList.add(shellObj);
                    }
                    else {
                        state = 2;
                        break;
                    }
                }
            }
            int n = 60/lifeMax;
            gImage.setColor(Color.white);
            gImage.fillRect(x+70,y+40,60,5);
            gImage.setColor(Color.green);
            gImage.fillRect(x+70,y+40,life*n,5);
        }
        if(state == 2){
            Y -= speed;
            if(Y<-50){
                GameUtils.removeList.add(this);
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
