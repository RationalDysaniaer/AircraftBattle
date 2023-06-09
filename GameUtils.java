import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {
    public static String s = "src\\Images\\";
    public static Image background = Toolkit.getDefaultToolkit().getImage(
            s + "background.jpg");

    public static Image background2 = Toolkit.getDefaultToolkit().getImage(
            s +"background2.jpg");
    public static Image boos = Toolkit.getDefaultToolkit().getImage(
            s +"boos.png");
    public static Image ExplodeBig = Toolkit.getDefaultToolkit().getImage(
            s +"ExplodeBig.png");
    public static Image ExplodeSmall = Toolkit.getDefaultToolkit().getImage(
            s +"ExplodeSmall.png");
    public static Image red = Toolkit.getDefaultToolkit().getImage(
            s +"red.png");
    public static Image shell = Toolkit.getDefaultToolkit().getImage(
            s +"shell.png");
    public static Image enemyPlane = Toolkit.getDefaultToolkit().getImage(
            s +"enemyPlane.png");
    public static Image enemyShell = Toolkit.getDefaultToolkit().getImage(
            s +"enemyShell.png");
    public static Image heart = Toolkit.getDefaultToolkit().getImage(
            s +"heart.png");
    public static Image heart2 = Toolkit.getDefaultToolkit().getImage(
            s +"heart2.png");
    public static Image title = Toolkit.getDefaultToolkit().getImage(
            s +"title.png");
    public static Image level = Toolkit.getDefaultToolkit().getImage(
            s +"level.png");
    public static Image barrier1 = Toolkit.getDefaultToolkit().getImage(
            s +"barrier1.png");
    public static Image barrier2 = Toolkit.getDefaultToolkit().getImage(
            s +"barrier2.png");
    public static Image guard = Toolkit.getDefaultToolkit().getImage(
            s +"guard.png");

    public static List<GameObject> gameObjectList = new ArrayList<>();
    //我方子弹的集合
    public static List<Shell> shellObjList = new ArrayList<>();
    //敌机的集合
    public static List<Enemy> enemyObjList = new ArrayList<>();
    //要删除元素的集合
    public static List<GameObject> removeList = new ArrayList<>();
    //敌方子弹集合
    public static List<EnemyBullet> enemyShellList = new ArrayList<>();
    //爆炸类集合
    public static List<Explode> explodeList = new ArrayList<>();

    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("黑体",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }

    public  static void heartShow(Graphics gImage,int life,GameWin frame){
        int height = 35,width = 20,cha = 2;
        gImage.setColor(Color.white);
        for (int i = 0; i < 15; i++) {
            gImage.fillRect(width*i+5,750+cha*i,width,height-cha*i);
        }
        int[][] rgb = {{17,255,0},{33,238,0},{49,221,0},{65,204,0},
                       {80,187,0},{96,170,0},{112,153,0},{128,136,0},
                       {144,119,0},{160,102,0},{176,85,0},{192,68,0},
                       {207,51,0},{223,34,0},{255,0,0}};
        for (int i = 0; i < 15; i++) {
            gImage.setColor(new Color(rgb[i][0],rgb[i][1],rgb[i][2]));
            if(frame.planeObj.life>=15*2-2*i)
                gImage.fillRect(width*i+5,750+cha*i,width/2,height-cha*i);
            if(frame.planeObj.life>=15*2-2*i-1)
                gImage.fillRect(width*i+5+width/2,750+cha*i,width/2,height-cha*i);
        }
    }
}
