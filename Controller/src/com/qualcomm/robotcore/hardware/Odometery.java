package com.qualcomm.robotcore.hardware;

import virtual_robot.controller.VirtualBot;

public class Odometery {
    private VirtualBot virtualBot;
    private static Double scaleFactor = null; //ratio between inches and pixels

    public Odometery(VirtualBot virtualBot) {
        this.virtualBot = virtualBot;
    }

    public static void setScaleFactor(double fieldSize){
        if(scaleFactor != null){
            return;
        }
        scaleFactor = 144 / fieldSize;
    }

    public double getX(){
        return virtualBot.getX() * scaleFactor;
    }

    public double getY(){
        return virtualBot.getY() * scaleFactor;
    }

    public double getHeading(){
        return virtualBot.getHeadingRadians();
    }
}
