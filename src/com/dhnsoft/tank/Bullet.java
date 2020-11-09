package com.dhnsoft.tank;

import java.awt.*;

/**
 * @Description
 * @ClassName Bullet
 * @Author dhn
 * @date 2020.11.05 10:28
 */
public class Bullet {
    private int x,y;
    private Dir dir;
    private Group group;
    public static final int SPEED=10;
    private boolean live=true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
    }
    public void paint(Graphics g) {
            switch (dir){
                case LEFT:
                    g.drawImage(ResourceMgr.bulletL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.bulletR,x,y,null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.bulletU,x,y,null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.bulletD,x,y,null);
                    break;
            }
            move();

        }

    private void move() {
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
        boundsCheck();
    }
    //子弹撞敌人
    public void collidesWithTank(Tank tank){
        //如果敌人死了 子弹不会撞
        if(!tank.isLive()){
            return;
        }
        if(this.group==tank.getGroup()){
            return;
        }
        Rectangle rect=new Rectangle(x,y,ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
        if(rect.intersects(rectTank)){
            this.die();
            tank.die();
        }
    }
    //子弹边界检查
    private void boundsCheck() {
        if(x<0||y<30||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT){
            live=false;
        }
    }
    public void die(){
        this.setLive(false);
    }
}

