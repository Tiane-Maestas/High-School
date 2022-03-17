import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;

public class Game implements Runnable 
{
    
    Display display;
    
    private String title;
    private int width, height;  
    
    private Thread thread;
    
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g; 
  
    private KeyBoard keyBoard;
    
    int posX, posY;
    int speedX, speedY;
    
    int padW = 10;
    int padH = 140;
    int padOneY, padTwoY;
    
    int goalsP1 = 0;
    int goalsP2 = 0;
    int goals = 0;
    
    int speed = 3;
    
    
    public Game(String title, int width, int height){
       
       this.title = title;
       this.width = width;
       this.height = height;
       
       keyBoard = new KeyBoard();       
       
    }
    
    private void initialize(){
        
        display = new Display(title, width, height);  
        display.getFrame().addKeyListener(keyBoard);
        
        posX = width/2;
        posY = height/2;
        speedX = speed;
        speedY = 0;
        padOneY = height/2;
        padTwoY = height/2;
        
    }
    
    private void update(){
        
        keyBoard.update();
        
        padMovement();
        
        updateBallPosition();
        
        if(goalsP1 >= 10 || goalsP2 >= 10){
            stop();
        }
        
    }
    
    private void render(){
        
        bs = display.getCanvas().getBufferStrategy();
        
        if(bs == null){
           display.getCanvas().createBufferStrategy(3); 
           return;
        }
        
        g = bs.getDrawGraphics();
        
        g.clearRect(0, 0, width, height);
        
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height); 
        
        g.setColor(Color.white);
        g.drawString("Score    "+goalsP1, width-55, 15);
        g.drawString("Score    "+goalsP2, 5, 15);
        
        ball();
        
        paddles();
        
        bs.show();
        g.dispose();
        
    }
    
    public void run(){
        
        initialize();
        
        int FPS = 150;
        double timePerUpdate = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while(running){
            
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            lastTime = now;
            
             if(delta >= 1){
                 
             update();
             render();
             
             delta--;
             
            }

        }
        
        
        
    }
    
    public synchronized void start(){
        
        if(running)
            return;
        
        running = true;        
        thread = new Thread(this);
        thread.start();
        
        //run() is called when you start a thread.
        
    }
    
    
     public synchronized void stop(){ 
       
         //frame.close();
       
    }
    
    public void ball(){
        
        g.setColor(Color.red);
        g.fillRoundRect(posX, posY, 20, 20, 20, 20);
        
    }
    
    public void paddles(){
        
        padOneY = display.getMouse().Y() - (padH/2);
        
        g.setColor(Color.green);
        g.fillRect(20, padOneY, padW, padH);
        g.fillRect(width-20, padTwoY, padW, padH);
                                             
            if(padTwoY < posY){
                padTwoY += 4;                
            }
            if((padTwoY+20) > posY){
                padTwoY -= 4;                
            }
                                        
        //padOneY = posY-20;
        //padTwoY = posY-80;
        
    }
    
    public void updateBallPosition(){
        
        
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY && posY <= padTwoY + padH/7){
            speedX = (1) * -speed;
            speedY = (2) * -speed;
        }
       
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY +padH/7 && posY <= padTwoY + 2*padH/7){   
           speedX = (1) * -speed;
           speedY = (1) * -speed; 
        }
        
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY + 2*padH/7 && posY <= padTwoY + 3*padH/7){
           speedX = (2) * -speed;
           speedY = (1) * -speed; 
        }
        
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY + 3*padH/7 && posY <= padTwoY + 4*padH/7){
           speedX = (1) * -speed;
           speedY = (0) * -speed; 
        }
        
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY + 4*padH/7 && posY <= padTwoY + 5*padH/7){
           speedX = (2) * -speed;
           speedY = (1) * speed; 
        }
        
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY + 5*padH/7 && posY <= padTwoY + 6*padH/7){
           speedX = (1) * -speed;
           speedY = (1) * speed; 
        }
        
        if(posX+20 >= width-20 && posX+20 <= width-15 && posY+20 >= padTwoY + 6*padH/7 && posY <= padTwoY + padH){
           speedX = (1) * -speed;
           speedY = (2) * speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY && posY <= padOneY + padH/7){
           speedX = (1) * speed;
           speedY = (2) * -speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY + padH/7 && posY <= padOneY + 2*padH/7){
           speedX = (1) * speed;
           speedY = (1) * -speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY + 2*padH/7 && posY <= padOneY + 3*padH/7){
           speedX = (2) * speed;
           speedY = (1) * -speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY + 3*padH/7 && posY <= padOneY + 4*padH/7){
           speedX = (1) * speed;
           speedY = (0) * -speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY + 4*padH/7 && posY <= padOneY + 5*padH/7){
           speedX = (2) * speed;
           speedY = (1) * speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY + 5*padH/7 && posY <= padOneY + 6*padH/7){
           speedX = (1) * speed;
           speedY = (1) * speed; 
        }
        
        if(posX <= 20 && posX >= 15 && posY+20 >= padOneY + 6*padH/7 && posY <= padOneY + padH){
           speedX = (1) * speed;
           speedY = (2) * speed; 
        }
        
  
       
        if(posY >= 0)
        speedY = -speedY;
        
        if(posY <= height)
        speedY = -speedY;
        
        if(posX <= 0){
         goalsP1++;
         posX = width/2;
         posY = height/2;
         speedX = -speed;
         speedY = 0;
         goals++;
        }
        
        if(posX >= width){
         goalsP2++;
         posX = width/2;
         posY = height/2;
         speedX = speed;
         speedY = 0;
         goals++;
         /*if(speed <= 4 && goals >= 5){
         speed++;
         goals = 0;
        }*/
        }
        
        posX += speedX;
        posY += speedY;
        
    }

    public void padMovement(){
        
            if(keyBoard.up1){
                
           if(padOneY >= 0)   
           padOneY -= 2*speed;
           
        } 
        
            if(keyBoard.down1){
            
            if(padOneY+padH <= height) 
            padOneY += 2*speed;
            
        }
        
        if(keyBoard.up2){
            
           if(padTwoY >= 0)   
           padTwoY -= 2*speed;
           
        } 
        
            if(keyBoard.down2){
 
            if(padTwoY+padH <= height)
            padTwoY += 2*speed;
            
        }
  
    }
       
}
