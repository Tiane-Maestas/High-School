import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

public class TriangleProgram implements Runnable 
{
    
    Display display;
    
    private String title;
    private int width, height;  
    
    private Thread thread;
    
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g; 
        
    private ArrayList<Point> points;
    
    private int unit = 10; 
    
    public TriangleProgram(String title, int width, int height){
       
       this.title = title;
       this.width = width;
       this.height = height;
      
    }
  
    private void initialize(){
        
        display = new Display(title, width, height);   
        
        points = new ArrayList<>();
               
    }
    
    public void update(){
           
        updatePoints();
        
        if(points.size() > 10){
            points.clear();
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
  
        drawPoints();
        connectPoints();
                
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
    
    public void updatePoints(){
        
        if(display.getMouse().checkUpdate()){
            int x = display.getMouse().x;
            int y = display.getMouse().y;
            points.add(new Point(x,y));  
            display.getMouse().updateOldStuff();
        }
        
    }
    
    public void drawPoints(){
        g.setColor(Color.red);
        for(int i = 0; i < points.size(); i++){
            g.fillOval(points.get(i).x-(unit/2),points.get(i).y-(unit/2), unit, unit);            
        }        
    }
    
    public void connectPoints(){
        
        int lastPoint = points.size()-1;
        
        g.setColor(Color.red);
        for(int i = 0; i < lastPoint; i++){
            g.drawLine(points.get(i).x,points.get(i).y, points.get(i+1).x,points.get(i+1).y);            
        } 
        
        if(lastPoint > 0){
        g.drawLine(points.get(0).x,points.get(0).y, points.get(lastPoint).x,points.get(lastPoint).y); 
       }
    
    }
                  
     public synchronized void stop(){ 
         
         try{
         thread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
       
    }
         
}
