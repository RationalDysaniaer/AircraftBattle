import java.awt.*;
public abstract class GameObject {
    Image img;
    int x;
    int y;
    int width;
    int height;
    double speed;
    GameWin frame;

    public GameObject() {
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(Image img, int x, int y, double speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public GameObject(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }

    public Image getImj() {
        return img;
    }

    public void setImj(Image imj) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }

    public  void paintSelf(Graphics gImage){
        gImage.drawImage(img,x,y,null);
    }
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}