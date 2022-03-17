import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Program implements Runnable 
{
    
    Display display;
    
    private String title;
    private int width, height;  
    
    private Thread thread;
    
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g; 
  
    private Random random = new Random(); 
    
    private Color c;
    
    int R,G,B;

    int unit = 400;
    int limit;
    
    int posX, posY, mX, mY, oldMx, oldMy;
   
    double score = 0;
    int update = 0;
    double clicks = 0;
    int time = 10;
    double acc = 1;
    
    public Program(String title, int width, int height){
       
       this.title = title;
       this.width = width;
       this.height = height;
      
    }
  
    private void initialize(){
        
        display = new Display(title, width, height);                 
       
        updateTarget();
        
        limit = unit/4;
        
         R = random.nextInt(255);
         G = random.nextInt(255);
         B = random.nextInt(255);
        
         c = new Color(R,G,B);
         
    }
    
    public void update(){
        
         updateMouse();
        
         if(mX > posX && mX < posX+unit){
             if(mY > posY && mY < posY+unit){
                if(time > 0) {                    
                 updateTarget();                             
                }
              if(time > 0){
               update++;
               score++;
              }
             }
            }
            
         if(mX != oldMx || mY != oldMy){
             if(mX > 0 && mX < width && mY > 0 && mY < height)
             clicks++;
            }
            
          oldMx = mX;
          oldMy = mY;
          
         if(score > 0 && time > 0)
          acc = score/clicks;
          
          if(update >= 15){
            if(unit > limit)
            unit -= unit/2;
            time += 5;
            update = 0;
        }
      
    }
    
    public void render(){
        
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
           display.getCanvas().createBufferStrategy(3); 
           return;
        }
        
        g = bs.getDrawGraphics();
        
        g.clearRect(0, 0, width, height);
        
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height); 
  
        drawTarget();
      
        display.getStatusbar().setText("Score: " + score + "    Time: " + time + "    Accuracy: " + acc);
        
        if(time <= 0){
         g.setColor(Color.red);
         g.drawString("Game Over",width/2,height/2);    
        }
        
        bs.show();
        g.dispose();
        
    }
  
    public void run(){
        
        initialize();
        
        int FPS = 60;
        int dt = 0;
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
             
             if(dt >= FPS && time > 0){
                 time--;
                 dt = 0;
                }else{
                  dt++;  
                }
      
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
    
    public void drawTarget(){
  
        g.setColor(c);
        g.fillOval(posX, posY, unit, unit); 
        
    }
    
    public void updateTarget(){
        
        R = random.nextInt(255);
        G = random.nextInt(255);
        B = random.nextInt(255);
        
        c = new Color(R,G,B);
        
        posX = random.nextInt(width-unit);
        posY = random.nextInt(height-unit);
        
    }
    
    public void updateMouse(){
        
        mX = display.getMouse().X();
        mY = display.getMouse().Y();
        
    }
    
     public synchronized void stop(){ 
         
         try{
         thread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
       
    }
         
}
