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
public class Ball {
    double xVel, yVel, x, y;
    
    public Ball(){
       x = 350;
        y = 250;
        xVel = getRandomSpeed() * getRandomDirection();
        yVel = getRandomSpeed() * getRandomDirection();
    }  
    
    public double getRandomSpeed(){
        return (Math.random() * 3 + 5 );
    }
    
    public int getRandomDirection(){
        int rand = (int) (Math.random() * 2);
        
        if (rand == 1)
            return 1;
        else
            return -1;
    }
    
    public void move(){
        x += xVel;
        y += yVel;
        
        if(y < 10)
            yVel = -yVel;
        if(y > 490)
            yVel = -yVel;
    }
    
    public void draw (Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int) x - 10, (int) y -10, 20, 20);
    }
    
    public void checkPaddleCollision(Paddle p1, Paddle p2){
        boolean hit = false;
        if(x <= 50){
            if( y >= p1.getY() && y <= p1.getY() + 80){
                xVel = -xVel;
                //hit = true;
            }
         }
        
        else if( x >= 650){
              if( y >= p2.getY() && y <= p2.getY() + 80){
                xVel = -xVel;
                //hit = true;
              }
         }
        //return hit;
    }
    
    public boolean didHit(Paddle p1){
        boolean hit = false;
        if(x <= 50){
            if( y >= p1.getY() && y <= p1.getY() + 80){
                hit = true;
            }
        }
            return hit;
    }
    
    public void reset(){
        x = 350;
        y = 250;
        xVel = getRandomSpeed()* getRandomDirection();
        yVel = getRandomSpeed() * getRandomDirection();
    }
    
    public int getX(){
        return (int) x;
    }
    
    public int getY(){
        return (int) y;
    }
    
}
