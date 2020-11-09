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
public class Player {
    private boolean moving=false;
    private int x,y;
    private Dir dir;
    public static final int SPEED=10;
    private boolean bL,bR,bU,bD;
    private Group group;
    private Boolean live=true;


    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir=dir;
        this.group=group;
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

    //画坦克
    public void paint(Graphics g) {
        //如果不活着就不画坦克
        if(!this.isLive()){
            return;
        }
        if(this.group==Group.GOOD){
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
        }
        if(this.group==Group.BAD){
            switch (dir){
                case LEFT:
                    g.drawImage(ResourceMgr.badTankL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.badTankR,x,y,null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.badTankU,x,y,null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.badTankD,x,y,null);
                    break;
            }
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
            case KeyEvent.VK_SPACE:
                fire();
                break;
        }
        setMainDir();
    }

    private void fire() {
        TankFrame.INSTANCE.add(new Bullet(x+15,y+15,dir,group));
    }
    public void die(){
        this.setLive(false);
    }
}
