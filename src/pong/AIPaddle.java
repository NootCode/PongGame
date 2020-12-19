/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Andre
 */
public class AIPaddle implements Paddle {

    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    final double GRAVITY = 0.94;
    Ball b1;
    
    public AIPaddle(int player, Ball b){
        upAccel = false; downAccel = false;
        y = 210; yVel = 0;
        b1 = b;
        if(player == 1)
            x = 20;
        else
            x = 660;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, (int) y, 20 , 80);
    }
    
    
    public void move() {
        int rand = (int)(Math.random() * 3) + 1 ;
        if( rand == 1 && b1.yVel > 0)
            y= y-10;
        else
            y = b1.getY() - 40;
    }
    
 public void reset(int player, Ball b){
       upAccel = false; downAccel = false;
        y = 210; yVel = 0;
        b1 = b;
        if(player == 1)
            x = 20;
        else
            x = 660;
    }
 
    public int getY() {
        return (int) y;
    }
    
}
