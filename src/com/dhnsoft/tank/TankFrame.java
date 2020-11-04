package com.dhnsoft.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private Tank myTank;
    private Tank enemyTank;
    Dir dir;
    //tank速度
    final int SPEED=10;
    public TankFrame() throws HeadlessException {
        myTank=new Tank(100,100,Dir.LEFT);
        enemyTank=new Tank(200,200,Dir.DOWN);
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
        myTank.paint(g);
        enemyTank.paint(g);
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

        private void setMainTankDir() {

        }
    }

}

