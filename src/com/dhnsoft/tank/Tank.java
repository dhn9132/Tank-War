package com.dhnsoft.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @Description
 * @ClassName Tank
 * @Author dhn
 * @date 2020.11.03 22:57
 */
//抽象坦克类
public class Tank {
    private boolean moving=true;
    private int x,y;
    private Dir dir;
    public static final int SPEED=5;
    private boolean bL,bR,bU,bD;
    private Group group;
    private Boolean live=true;
    private int oldX,oldY;


    public Tank(int x, int y,Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir=dir;
        this.group=group;

        oldX=x;
        oldY=y;
    }

    public Boolean isLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //画坦克
    public void paint(Graphics g) {
        //如果不活着就不画坦克
        if(!this.isLive()){
            return;
        }
            switch (dir){
                case LEFT:
                    g.drawImage(ResourceMgr.goodTankL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.goodTankR,x,y,null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.goodTankU,x,y,null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.goodTankD,x,y,null);
                    break;
            }
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
        //如果不按键就停止
        if(!bL&&!bR&&!bU&&!bD){
            moving=false;
        }
        else {
            //否则运行
            moving=true;
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

    }

    //坦克移动方法
    private void move(){
        if(!moving){
            return;
        }
        oldX=x;
        oldY=y;

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
        boundCheck();
        randomDir();
        if(r.nextInt(100)>90){
            fire();
        }
    }
    //碰到了边界就掉头
    private void boundCheck() {
        if(x<0||y<30||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT){
            this.back();
        }
    }

    private void back() {
        this.oldX=x;
        this.oldY=y;
    }

    private  Random r=new Random();
    private void randomDir() {
        if(r.nextInt(100)>90){
            this.dir=Dir.randomDir();
        }
    }


    private void fire() {
        TankFrame.INSTANCE.add(new Bullet(x+15,y+15,dir,group));


    }
    public void die(){
        this.setLive(false);
    }
}
