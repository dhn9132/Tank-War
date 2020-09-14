package com.dhnsoft.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) {
        Frame frame=new Frame();
        frame.setSize(800,600);
        //是否能改变大小
        frame.setResizable(false);
        frame.setTitle("Tank war");
        frame.setVisible(true);
        //监听窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
