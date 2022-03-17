import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

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
    private Random random = new Random(); 
    
    int unit = 10;
    int X, Y;
    int posX, posY;
    int Xspeed = 2*unit; 
    int Yspeed = 0;
    
    boolean foodCount = true;
     
    int snakeLength = 0;
    int[] snakeX, snakeY;
    int[] oldSnakeX, oldSnakeY;
    
    public Game(String title, int width, int height){
       
       this.title = title;
       this.width = width;
       this.height = height;
       
       keyBoard = new KeyBoard(); 
       
       posX = random.nextInt(width - (3*unit));
       posY = random.nextInt(height - (3*unit));
       
       oldSnakeX = new int[256];
       oldSnakeY = new int[256];
       
    }
    
    private void initialize(){
        
        display = new Display(title, width, height);  
        display.getFrame().addKeyListener(keyBoard);
        X = width/2;
        Y = height/2;
        
    }
    
    private void update(){
        
        keyBoard.update();
        
        if(snakeLength > 0)
        movement();
        
        if((X+unit >= posX && X-unit <= (posX+unit)) && (Y+unit >= posY && Y-unit <= (posY+unit)))
        foodCount = true;
        
        if(X+unit > width || X-unit < 0 || Y+unit > height || Y-unit < 0)
        running = false;
        
        checkSnake();
        
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
        g.drawString("Score    "+snakeLength, width-75, 15);
        
        createFood();
        
        createNewBody();
        
        createBody(); 
        
        bs.show();
        g.dispose();
        
    }
    
    public void run(){
        
        initialize();
        
        int FPS = 15;
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
        
        stop();
        
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
           
        endGame();   
            
         try{
         thread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
       
    }
    
    public void createBody(){
        
        g.setColor(Color.cyan);
            
         for(int i = 0; i <= snakeX.length-1; i++){
             
                 
            g.fillRect(snakeX[i],      snakeY[i],      unit, unit);
            g.fillRect(snakeX[i]-unit, snakeY[i]-unit, unit, unit);
            g.fillRect(snakeX[i]-unit, snakeY[i],      unit, unit);
            g.fillRect(snakeX[i],      snakeY[i]-unit, unit, unit);
  
            oldSnakeX[i] = snakeX[i];
            oldSnakeY[i] = snakeY[i];
            
       }
          
    }
    
    public void createNewBody(){
 
        snakeX = new int[snakeLength];
        snakeY = new int[snakeLength];
                
          if(snakeLength > 1){
              
           for(int i = snakeX.length-1; i >= 1; i--){
            
            snakeX[i] = oldSnakeX[i-1];
            snakeY[i] = oldSnakeY[i-1];
            
          }
        
        }
        
        snakeX[0] = X;
        snakeY[0] = Y;
        
        //snakeX[0] = posX;
        //snakeY[0] = posY;
   
    }
    
    public void createFood(){
        
          if(foodCount){
              
          posX = random.nextInt(width - (3*unit));
          posY = random.nextInt(height - (3*unit));
          
          snakeLength++;
  
        }
        
        g.setColor(Color.red);
        g.fillOval(posX, posY, unit, unit);
        
        foodCount = false;
        
    }
    
    public void movement(){
        
        
            if(keyBoard.up && Yspeed > -1 && Xspeed != 0){
              
           Yspeed -= 2*unit;
           Xspeed = 0;
           
        } 
        
            if(keyBoard.down && Yspeed < 1 && Xspeed != 0){
              
           Yspeed += 2*unit;
           Xspeed = 0;
           
        }
        
            if(keyBoard.left && Xspeed > -1 && Yspeed != 0){
             
           Xspeed -= 2*unit;
           Yspeed = 0;
           
        } 
        
            if(keyBoard.right && Xspeed < 1 && Yspeed != 0){
              
           Xspeed += 2*unit;
           Yspeed = 0;
           
        }       
            
           
           /*
           if(X != display.getMouse().X()){
               if(X < display.getMouse().X()){
               Xspeed = 2*unit;
            }else{
               Xspeed = -2*unit; 
            }               
            }
            
            if(Y != display.getMouse().Y()){
               if(Y < display.getMouse().Y()){
               Yspeed = 2*unit;
            }else{
               Yspeed = -2*unit; 
            }               
            }
           */
           
            snakeX[0] = snakeX[0] + Xspeed;
            snakeY[0] = snakeY[0] + Yspeed;
             
          X = snakeX[0];
          Y = snakeY[0];
        
    }
    
    public void checkSnake(){
        
        if(snakeLength > 2 ){
            
            for(int i = snakeX.length-1; i >= 1; i--){
                
            if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i] )
            running = false;
            
         }
        
        }
        
    }
    
    public void endGame(){
        
        g.setColor(Color.red);
        g.drawString("GAME OVER", width/2, height/2);
        
    }
    
}
