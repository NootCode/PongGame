/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre
 */
public class Pong extends Applet implements Runnable, KeyListener {
    final int WIDTH = 700;
    final int HEIGHT = 500;
    int p1Score, p2Score;
    Thread thread;
    HumanPaddle p1;
    AIPaddle p2;
    Ball b1;
    public boolean gameStarted, gamePaused, gameOver, flipCont;
    Graphics gfx;
    Image img;
    int hitCount;
    
    public void init(){
        this.resize(WIDTH, HEIGHT);
        b1 = new Ball();
        this.addKeyListener(this);
        p1 = new HumanPaddle(1);
        p2 = new AIPaddle(2,b1);
        p1Score = 0;
        p1Score = 0;
        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics();
        thread = new Thread(this);
        gameStarted = false;
        flipCont = false;
        gameOver = false;
        hitCount = 0;
        thread.start();
    }
    
    public void paint(Graphics g){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
        
        if(gameStarted){
            gfx.setColor(Color.WHITE);
            gfx.drawString("Score: " + p1Score, 30, 30);
            gfx.drawString("Score: "+ p2Score, 610 , 30);
        }    
        
        if(b1.getX() < -20){
            p2Score++;
            b1.reset();
            p1.reset(1);
            p2.reset(2, b1);
            gamePaused = true;
        }
                
         else if(b1.getX() > 720){
              p1Score++;
              b1.reset();
              gamePaused = true;
        }
          if(p1Score == 10 || p2Score == 20){
             gameOver = true;
             gfx.setColor(Color.RED);
             gfx.drawString("Game Over", 310 , 210);
         }  
          
        else{
            p1.draw(gfx);
            b1.draw(gfx);
            p2.draw(gfx);
        }
        if(!gameStarted){
            gfx.setColor(Color.WHITE);
            gfx.drawString("Noots P0ng Board :P" , 310, 100);
            gfx.drawString("Press Enter to begin..", 310, 130);
        }
        g.drawImage(img, 0, 0, this);
    }
    
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        for(;;){
            if(gameStarted && !gamePaused && !gameOver){
                p1.move();
                p2.move();
                b1.move();
                b1.checkPaddleCollision(p1, p2);
             
                if(b1.didHit(p1))
                    hitCount++;
                
//                if(hitCount % 2 == 0)
//                    flipCont = false;
//                else
//                    flipCont = true;
                
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            if(!flipCont)
                p1.setUpAccel(true);
            else
                p1.setDownAccel(true);
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(!flipCont)
                p1.setDownAccel(true);
            else
                p1.setUpAccel(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStarted = true;
            gamePaused = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_UP)
        {
                if(!flipCont)
                p1.setUpAccel(false);
            else
                p1.setDownAccel(false);
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(!flipCont)
                p1.setDownAccel(false);
            else
                p1.setUpAccel(false);
        }
    }
    
}
