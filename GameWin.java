import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author RationalDysaniaer
 * @date 2023/4/20 23:10
 * @version 1.3
 * @BlogAddress <a href="https://blog.csdn.net/qq_43000128/article/details/129863206">【使用Java Swing创建飞机大战小游戏】</a>
 */

public class GameWin extends JFrame {
    //窗口大小
    public final int WIDTH = 600;
    public final int HEIGHT = 800;
    //游戏状态
    public static int state = 0;
    //在内存中创建一个offScreenImage的缓冲图像（双缓存）
    Image offScreenImage = null;
    //计数
    int count = 1;//时间计数
    int enemyCount = 0;//敌机数量
    static int score = 0;//得分
    static int level = 1;//飞机等级
    int levelProps = 0;//记录升级道具
    int lifeProps = 0;//记录生命回复道具
    int enhanceBoos = 0;//记录boos增强
    //记录各阶段障碍
    int isBarrier1 = 0;
    int isBarrier2 = 0;
    int isBarrier3 = 0;
    int invincible = 0;//无敌
    //技能次数
    int guard = 3;
    int cls = 3;

    Background background = new Background(GameUtils.background, 0, -400, 2);
    Background background2 = new Background(GameUtils.background2, 0, -1600, 2);
    Plane planeObj = new Plane(GameUtils.red, 0, 0, 50, 50, 0, this);

    //创建boos空对象
    public Boos boos = null;

    //重新开始游戏，数据初始化
    public void reGame(){
        //所有数据初始化
        count = 1;
        enemyCount = 0;
        score = 0;
        enhanceBoos = 0;
        level = 1;
        lifeProps = 0;
        levelProps = 0;
        boos = null;
        this.planeObj.life = 30;
        offScreenImage = null;
        isBarrier1 = 0;
        isBarrier2 = 0;
        isBarrier3 = 0;
        guard = 3;
        cls = 3;
        GameUtils.gameObjectList.clear();
        GameUtils.removeList.clear();
        GameUtils.enemyShellList.clear();
        GameUtils.enemyObjList.clear();
        GameUtils.shellObjList.clear();
        GameUtils.explodeList.clear();
        GameUtils.gameObjectList.add(background);
        GameUtils.gameObjectList.add(background2);
        GameUtils.gameObjectList.add(planeObj);
    }

    public static void main(String[] args) {
        GameWin game = new GameWin();
        game.launch();
    }

    @Override
    public void paint(Graphics g){
        if(offScreenImage == null){
            offScreenImage = createImage(WIDTH,HEIGHT);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,WIDTH,HEIGHT);
        //游戏状态
        //开始界面
        if(state == 0){
            gImage.drawImage(GameUtils.background,0,-400,this);
            gImage.drawImage(GameUtils.enemyPlane,20,240,this);
            gImage.drawImage(GameUtils.enemyPlane,500,240,this);
            gImage.drawImage(GameUtils.red,250,600,this);
            gImage.drawImage(GameUtils.title,(600-475)/2-12,80,this);
            GameUtils.drawWord(gImage,"<点击屏幕开始游戏>",Color.yellow,40,112,400);
        }

        //游戏运行
        if(state == 1){
            GameUtils.gameObjectList.addAll(GameUtils.explodeList);//？？？？
            for (int i = 0; i < GameUtils.gameObjectList.size(); i++) {
                GameUtils.gameObjectList.get(i).paintSelf(gImage);
            }
            GameUtils.heartShow(gImage, planeObj.life,this);
            GameUtils.gameObjectList.removeAll(GameUtils.removeList);
        }

        //游戏失败
        if(state == 3){
            for (int i = 0; i < GameUtils.gameObjectList.size(); i++) {
                GameUtils.gameObjectList.get(i).paintSelf(gImage);
            }
            gImage.drawImage(GameUtils.ExplodeSmall,planeObj.getX()-15, planeObj.getY()-10,this);
            GameUtils.heartShow(gImage, planeObj.life,this);
            GameUtils.drawWord(gImage,"GAME OVER",Color.yellow,60,160,330);
            GameUtils.drawWord(gImage,"*按空格键重新开始*",Color.yellow,60,20,400);
        }
        //游戏胜利
        if(state == 4){
            //遍历并绘制列表中的元素
            for (int i = 0; i < GameUtils.gameObjectList.size(); i++) {
                GameUtils.gameObjectList.get(i).paintSelf(gImage);
            }
            //绘制爆炸类图像
            gImage.drawImage(GameUtils.ExplodeBig, boos.getX()-10, boos.getY()-10,this);
            GameUtils.heartShow(gImage, planeObj.life,this);
            GameUtils.drawWord(gImage,"VICTORY!",Color.yellow,60,160,330);
            GameUtils.drawWord(gImage,"*按空格键重新开始*",Color.yellow,60,20,400);
        }


        //BOOS警告和变异字幕
        if(state == 1){
            if(enemyCount > 10 && enemyCount<20){
                GameUtils.drawWord(gImage,"============================================================",Color.red,100,0,300);
                if(enemyCount%2 == 0)
                    GameUtils.drawWord(gImage," 警告！！！",Color.red,100,0,400);
                GameUtils.drawWord(gImage,"============================================================",Color.red,100,0,500);
            }

            if(this.boos != null && this.boos.life <= 200 && enhanceBoos < 50){
                GameUtils.drawWord(gImage,"============================================================",Color.red,100,0,300);
                if(enemyCount%2 == 0){
                    GameUtils.drawWord(gImage," BOOS增强！！！",Color.red,100,0,400);
                    enhanceBoos ++;
                }
                GameUtils.drawWord(gImage,"============================================================",Color.red,100,0,500);
            }

            if(this.boos != null && this.boos.life <= 100 && enhanceBoos < 100){
                GameUtils.drawWord(gImage,"============================================================",Color.red,100,0,300);
                if(enemyCount%2 == 0){
                    GameUtils.drawWord(gImage," BOOS变异！！！",Color.red,100,0,400);
                    enhanceBoos ++;
                }
                GameUtils.drawWord(gImage,"============================================================",Color.red,100,0,500);
            }
        }

        //得分、等级、技能次数
        if(state != 0){
            GameUtils.drawWord(gImage,score+" 分",Color.green,40,460,100);
            GameUtils.drawWord(gImage,"Level:"+level,Color.green,30,455,775);
            GameUtils.drawWord(gImage,"护盾(C):"+guard,Color.blue,20,330,785);
            GameUtils.drawWord(gImage,"清屏(X):"+cls,Color.blue,20,330,760);
        }
        //将缓冲图像画入
        g.drawImage(offScreenImage,0,0,null);
        //时间计数
        count++;
    }

    public void launch(){
        //创建音乐类对象，播放背景音乐
        Music music = new Music();
        music.play();
        //窗口大小
        this.setSize(WIDTH,HEIGHT);
        //窗口居中
        this.setLocationRelativeTo(null);
        //设置标题
        this.setTitle("飞机大战");
        //窗口可见
        this.setVisible(true);
        //设置窗口不可改变大小
        this.setResizable(false);
        //关闭游戏时结束程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameUtils.gameObjectList.add(background);
        GameUtils.gameObjectList.add(background2);
        GameUtils.gameObjectList.add(planeObj);

        //鼠标监听
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 1 && state == 0){
                    state = 1;//开始游戏
                    repaint();
                }
            }
        });

        //游戏暂停、重新开始
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 32){
                    switch (state){
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                        case 3:
                        case 4:
                            reGame();
                            state = 0;
                            repaint();
                            break;
                    }
                }
                if(e.getKeyCode() == 67){
                    //护盾数量大于零,并且不处于护盾技能释放阶段
                    if(guard > 0 && invincible == 0){
                        guard--;
                        invincible = 1;
                        Thread th = new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(5000);
                                    System.out.println(66);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                invincible = 0;
                            }
                        };
                        th.start();
                    }
                }
                if(e.getKeyCode() == 88){
                    if(cls > 0){
                        GameUtils.gameObjectList.removeAll(GameUtils.enemyObjList);
                        GameUtils.gameObjectList.removeAll(GameUtils.enemyShellList);
                        cls--;
                    }
                }
            }
        });

        while (true){
            if(state == 1){
                createObject();
                repaint();
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Thread.sleep()是Thread类的一个静态方法，使当前线程休眠，进入阻塞状态（暂停执行），如果线程在睡眠状态被中断，将会抛出InterruptedException中断异常
        }
    }

    void createObject() {
        //生成我方飞机子弹
        int shellSpeedRed = 11 - level;
        int speed = 5 + (level-1)*2;
        if (count % shellSpeedRed == 0) {
            GameUtils.shellObjList.add(new Shell(GameUtils.shell, planeObj.getX(), planeObj.getY() - 50, 30, 50, speed, this));
            GameUtils.gameObjectList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }

        //生成敌方飞机
        if (count % 15 == 0) {
            GameUtils.enemyObjList.add(new Enemy(GameUtils.enemyPlane, (int) (Math.random() * 12) * 46, 10, 60, 36, (int) (Math.random() * (10 - 3 + 1) + 3), this));
            GameUtils.gameObjectList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
            enemyCount++;
        }
        //生成敌方boos子弹
        int shellSpeed;
        if (boos != null && this.boos.life >= 100)
            shellSpeed = 5;
        else
            shellSpeed = 3;
        if (count % shellSpeed == 0 && boos != null) {
            //散弹
            int z, speed2;
            z = (int) (Math.random() * (3 - 1 + 1) + 1);
            speed2 = (int) (Math.random() * (5 - 3 + 1) + 3);
            //追踪弹
            int x1 = boos.getX() + 76;
            int y1 = boos.getY() + 85;
            int x2 = planeObj.getX();
            int y2 = planeObj.getY() - 50;
            int k;
            if (x2 != x1)
                k = (y2 - y1) / (x2 - x1);
            else k = 0;
            int b = (int) (y1 - 1.0 * k * x1);
            GameUtils.enemyShellList.add(new EnemyBullet(GameUtils.enemyShell, boos.getX() + 82, boos.getY() + 85, 5, 20, (int) (Math.random() * (15 - 5 + 1) + 5), z, speed2, k, b, this));
            GameUtils.gameObjectList.add(GameUtils.enemyShellList.get(GameUtils.enemyShellList.size() - 1));
        }
        //出现二十架敌机后出现boos
        if (enemyCount > 20 && boos == null) {
            boos = new Boos(GameUtils.boos, 250, 0, 170, 100, 5, this);
            GameUtils.gameObjectList.add(boos);
        }

        //生成升级道具
        if (boos != null
            && boos.life <= 300 - levelProps*50
            && levelProps < 5){
            levelProps++;
            GameUtils.gameObjectList.add(new Props(GameUtils.level, "level", (int) (Math.random() * 12) * 46, 10, 35, 50, 2, this));
        }

        //生成生命道具
        if (score > 100 + lifeProps * 100) {
            lifeProps++;
            GameUtils.gameObjectList.add(new Props(GameUtils.heart, "life", (int) (Math.random() * 12) * 46, 10, 50, 50, 2, this));
        }

        //生成障碍
        if(score > 0 && isBarrier1 == 0){
            isBarrier1 = 1;
            GameUtils.gameObjectList.add(new barrier(50,200,5,15,50,50,this));
            GameUtils.gameObjectList.add(new barrier(350,200,5,15,50,50,this));
        }
        if(boos!= null && boos.life < 100 && isBarrier1 == 1){
            isBarrier1 = 2;
            GameUtils.gameObjectList.add(new barrier(50,200,5,30,50,50,this));
            GameUtils.gameObjectList.add(new barrier(350,200,5,30,50,50,this));
        }
        if(score > (isBarrier2+1)*100){
            isBarrier2++;
            GameUtils.gameObjectList.add(new barrier(200,250,5,10,50,50,this));
        }
        if(score > (isBarrier3+1)*140){
            isBarrier3++;
            GameUtils.gameObjectList.add(new barrier(50,150,5,5,50,50,this));
            GameUtils.gameObjectList.add(new barrier(350,150,5,5,50,50,this));
            GameUtils.gameObjectList.add(new barrier(50,250,5,5,50,50,this));
            GameUtils.gameObjectList.add(new barrier(350,250,5,5,50,50,this));
        }

    }
}
