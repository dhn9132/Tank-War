package com.dhnsoft.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Description
 * @ClassName Tank
 * @Author dhn
 * @date 2020.11.03 22:57
 */
//抽象坦克类
public class Tank {
    private int x,y;
    private Dir dir;
    public static final int SPEED=5;
    private boolean bL,bR,bU,bD;

    public Tank(int x, int y,Dir dir) {
        this.x = x;
        this.y = y;
        this.dir=dir;
    }
    //画坦克
    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        move();
    }
    //键盘按下事件
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
        setMainDir();
    }
    //判断松开键盘停止
    private void setMainDir() {
        if(!bL&&!bR&&!bU&&!bD){
            dir=Dir.STOP;
        }
        if(bL&&!bR&&!bU&&!bD){
            dir=Dir.LEFT;
        }
        if(!bL&&bR&&!bU&&!bD){
            dir=Dir.RIGHT;
        }
        if(!bL&&!bR&&bU&&!bD){
            dir=Dir.UP;
        }
        if(!bL&&!bR&&!bU&&bD){
            dir=Dir.DOWN;
        }
    }

    //坦克移动方法
    private void move(){
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                bL=false;
                break;
            case KeyEvent.VK_RIGHT:
                bR=false;
                break;
            case KeyEvent.VK_UP:
                bU=false;
                break;
            case KeyEvent.VK_DOWN:
                bD=false;
                break;
            default:
                break;
        }
        setMainDir();
    }
}
