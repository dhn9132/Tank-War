package com.dhnsoft.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    private Player myTank;
    private Tank enemyTank;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    public static final TankFrame INSTANCE=new TankFrame();
    private List<Bullet> bulletList;
    Dir dir;
    //tank速度
    final int SPEED=10;
    public TankFrame() throws HeadlessException {
        myTank=new Player(100,100,Dir.LEFT,Group.GOOD);
        enemyTank=new Tank(200,200,Dir.DOWN,Group.BAD);
        bulletList=new ArrayList<>();
        setSize(800,600);
        setTitle("Tank War");
        setResizable(false);
        setVisible(true);
        //控制自己创建的键盘监听类
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            //可以关闭java的类
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:"+bulletList.size(),10,50);
        g.setColor(color);
        myTank.paint(g);
        enemyTank.paint(g);
        for (int i=0;i<bulletList.size();i++) {
            bulletList.get(i).collidesWithTank(enemyTank);
            if(!bulletList.get(i).isLive()){
                bulletList.remove(i);
            }
            else {
                bulletList.get(i).paint(g);
            }
        }
    }
    public void add(Bullet bullet){
        bulletList.add(bullet);
    }

    //双缓冲解决闪烁
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    class MyKeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);

        }



        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }

    }

}

