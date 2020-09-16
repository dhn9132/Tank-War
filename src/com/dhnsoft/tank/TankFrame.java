package com.dhnsoft.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x=200,y=200;
    Dir dir=Dir.DOWN;
    //tank速度
    final int SPEED=10;
    public TankFrame() throws HeadlessException {
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
        //tank的位置、大小
        g.fillRect(x,y,50,50);
        //x+=10;
//        y+=10;
        switch (dir){
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case LEFT:
                x-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;
        }
    }

    class MyKeyListener extends KeyAdapter{
        Boolean bL=false;
        Boolean bU=false;
        Boolean bR=false;
        Boolean bD=false;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }
            setMainTankDir();

//            x+=200;
//            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(bL) dir=Dir.LEFT;
            if(bR) dir=Dir.RIGHT;
            if(bU) dir=Dir.UP;
            if(bD) dir=Dir.DOWN;

        }
    }

}

