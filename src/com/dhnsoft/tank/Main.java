package com.dhnsoft.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame=new TankFrame();
        tankFrame.setVisible(true);
        while(true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
