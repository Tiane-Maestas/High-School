import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

public class Game implements Runnable 
{
    
    Display display;
    
    private String title;
    private int width, height;  
    
    private Thread thread;
    
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g; 
       
    private Random random = new Random(); 
    
    private ArrayList<BodyPart> snake;
   
    int unit = 20;
    int xSpeed = 1; 
    int ySpeed = 1;
    int appleX, appleY;
    
    public Game(String title, int width, int height){
       
       this.title = title;
       this.width = width;
       this.height = height;
            
    }
    
    private void initialize(){
        
        display = new Display(title, width, height);  
        
        snake = new ArrayList<BodyPart>();
        createNewBodyPart(width/2, height/2);
        
        updateApple();
       
    }
    
    private void update(){
        
       movement(); 
       updateSnake();
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
        g.drawString("Score    "+ snake.size(), width-75, 15);
        
        drawSnake();
        
        drawApple();
        
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
    
    public void createNewBodyPart(int x, int y){
        snake.add(new BodyPart(x, y));
    }
    
    public void updateSnake(){
       
         snake.get(0).setX(snake.get(0).X() + xSpeed);
         snake.get(0).setY(snake.get(0).Y() + ySpeed);
        
      //if(xSpeed != 0 || ySpeed != 0){
        for(int i = snake.size()-1; i > 0; i--){
        
            snake.get(i).setX(snake.get(i-1).X());
            snake.get(i).setY(snake.get(i-1).Y());
            
       }
      //}
      
      if(snake.get(0).X() >= appleX && snake.get(0).Y() >= appleY && snake.get(0).X() <= appleX+unit && snake.get(0).Y() <= appleY+unit){
          createNewBodyPart(snake.get(snake.size()-1).X()+unit, snake.get(snake.size()-1).Y());
          updateApple();
        }
        
      if(snake.get(0).X()+unit <= appleX && snake.get(0).Y()+unit <= appleY && snake.get(0).X()+unit >= appleX+unit && snake.get(0).Y()+unit >= appleY+unit){
          createNewBodyPart(snake.get(snake.size()-1).X()+unit, snake.get(snake.size()-1).Y());
          updateApple();
        }
        
      if(snake.get(0).X()+unit/2 >= appleX && snake.get(0).Y()+unit/2 >= appleY && snake.get(0).X()+unit/2 <= appleX+unit && snake.get(0).Y()+unit/2 <= appleY+unit){
          createNewBodyPart(snake.get(snake.size()-1).X()+unit, snake.get(snake.size()-1).Y());
          updateApple();
        }
        
    }
    
    public void checkSnake(){
    
            
            for(int i = snake.size()-1; i > 2; i--){
                
            if(snake.get(0).X() == snake.get(i).X() && snake.get(0).Y() == snake.get(i).Y())
            running = false;
            
         }

    }
    
    public void updateApple(){
        
       appleX = random.nextInt(width-unit);
       appleY = random.nextInt(height-unit); 
        
    }
    
    public void drawApple(){
        
      g.setColor(Color.red);
      g.fillRect(appleX, appleY, unit, unit);
      
    }
    
    public void drawSnake(){
        
        g.setColor(Color.green);
        
        for(int i = 0; i < snake.size(); i++){
        g.fillRect(snake.get(i).X(), snake.get(i).Y(), unit, unit);
       }
       
    }
    
     public synchronized void stop(){ 
           
        //endGame();   
            
         try{
         thread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
       
    }
   
    public void movement(){   
        
          /* 
          if(display.getMouse().X() < snake.get(0).X()+unit && display.getMouse().X() > snake.get(0).X()){
              xSpeed = 0;
            }
            
          if(display.getMouse().Y() < snake.get(0).Y()+unit && display.getMouse().Y() > snake.get(0).Y()){
              ySpeed = 0;
            }  
           */
          
          if(display.getMouse().X() > snake.get(0).X()+unit){
              xSpeed = unit;
            }else if(display.getMouse().X() < snake.get(0).X()){
              xSpeed = -unit;  
            }else{
              xSpeed = 0;
            }
            
          if(display.getMouse().Y() > snake.get(0).Y()+unit){
              ySpeed = unit;
            }else if(display.getMouse().Y() < snake.get(0).Y()){
              ySpeed = -unit;  
            }else{
              ySpeed = 0; 
            }
                             
    }
 
    public void endGame(){
        
        g.setColor(Color.red);
        g.drawString("GAME OVER", width/2, height/2);
        
    }
    
}
