package com.dhnsoft.tank;

import java.util.Random;

public enum Dir {
    LEFT,UP,RIGHT,DOWN,STOP;
    private static Random random=new Random();
    public static Dir randomDir() {
        return Dir.values()[random.nextInt(Dir.values().length)];
    }
}
