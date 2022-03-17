import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

public class Program implements Runnable 
{
    
    Display display;
    
    private String title;
    private int width, height;  
    
    private Thread thread;
    
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g; 
    
    private KeyBoard keyBoard;
    
    private Pathfind pathfind;
    
    private Nodes nodes;
    private Nodes[][] nodesArray;
    
    private Random random = new Random(); 

    int unit = 20;
    
    int posX = 0;
    int posY = 0;
    
    int mX,mY;
    private ArrayList<Integer> Mx = new ArrayList<>();
    private ArrayList<Integer> My = new ArrayList<>();
    
    boolean startBlock;
    int startX, startY;
    
    boolean endBlock;
    int endX, endY;
    
    boolean startPath = false;
    boolean showPath = false;
    
    boolean Draw = false;
    boolean undo = false;
    boolean oldStateSpace = false;
    boolean oldStateQ = false;
    
    boolean[][] background;
    
    public Program(String title, int width, int height){
       
       this.title = title;
       this.width = width;
       this.height = height;
       
       keyBoard = new KeyBoard(); 
      
    }
    
    public Program(){
        
    }
    
    private void initialize(){
        
        display = new Display(title, width, height);  
        display.getFrame().addKeyListener(keyBoard);
       
        createBackground();
        
        nodesArray = new Nodes[background.length][background[0].length];
        
        for(int i = 0; i < background.length; i++){
            for(int j = 0; j < background[i].length; j++){
             
            nodes = new Nodes(background[i][j], i, j);    
                
            nodesArray[i][j] = nodes;
            
         }
        }
        
        pathfind = new Pathfind(unit);
        
        startBlock = false;
        endBlock = false;
                
    }
    
    public void update(){
        
        keyBoard.update();
        
        updateMouse();
        
        movement();
        
        backgroundCheck();
        
        updateNodes();
                
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
         
        //newBackground(); 
        
        drawNodes();
        
        cursor();   
        
        if(startBlock){
            g.setColor(Color.green);
            g.fillRect(startX, startY, unit, unit); 
        }
        
        if(showPath){
            drawPath();
        }
        
        if(endBlock){
            g.setColor(Color.red);
            g.fillRect(endX, endY, unit, unit); 
        }
        
        //g.setColor(Color.white);
        //g.drawString(""+mouse.mouseX, 100, 100);
        
        bs.show();
        g.dispose();
        
    }
  
    public void run(){
        
        initialize();
        
        int FPS = 20;
        double timePerUpdate = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while(running){
            
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            lastTime = now;
            
             if(delta >= 1){
                 
                 if(startPath){
                     
                     pathfind.path.clear();
                     
                     startX = startX/unit;
                     startY = startY/unit;
                     
                     endX = endX/unit;
                     endY = endY/unit;
                     
                 pathfind.start(nodesArray, startX, startY, endX, endY, startBlock, endBlock);
                 
                     startX = startX*unit;
                     startY = startY*unit;
                     
                     endX = endX*unit;
                     endY = endY*unit;
                 
                 startPath = false;
                 
                 showPath = true;
                 
                }
                                                  
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
    
    
     public void createBackground(){
        
        background = new boolean[height/unit][width/unit];
        
        for(int i = 0; i < background.length; i++){
            for(int j = 0; j < background[i].length; j++){
            background[i][j] = true;
         }
        }
        
        
     }
     
     
     public void newBackground(){
         
        g.setColor(Color.cyan);
         
         for(int i = 0; i < background.length; i++){
            for(int j = 0; j < background[i].length; j++){
                
            if(background[i][j]){
               g.fillRect(i*unit, j*unit, unit, unit);
            }
            
         }
        }
         
        }
        
        public void backgroundCheck(){
            
             if(Draw){
                 
            background[posX/unit][posY/unit] = false;
            
         }
         
         if(undo){
             
             background[posX/unit][posY/unit] = true;
             
            }
        
        }
        
        public void updateNodes(){
            
            for(int i = 0; i < background.length; i++){
            for(int j = 0; j < background[i].length; j++){
             
            nodesArray[i][j].update(background[i][j]);            
            
          }
         }
         
         for(int i = 0; i < Mx.size(); i++){
             
             nodesArray[Mx.get(i)][My.get(i)].update(false);
      
            }

        }
        
        public void updateMouse(){
           // mX = display.getMouse().X();
           // mY = display.getMouse().Y();
            mX = display.getMouse2().X();
            mY = display.getMouse2().Y();
            mX = mX/unit;
            mY = mY/unit;
           
            if(undo){
                
                for(int i = 0; i < Mx.size(); i++){
             
             if(Mx.get(i) == mX && My.get(i) == mY){
                 Mx.remove(i);
                 My.remove(i);
                }
            
            }
            
         }else{
             if(mX != 0 && mY != 0){
              Mx.add(mX);           
              My.add(mY);
             }             
            }
          
        }
        
        public void drawNodes(){
            
            g.setColor(Color.cyan);
            
              for(int i = 0; i < background.length; i++){
             for(int j = 0; j < background[i].length; j++){
               
              if(nodesArray[i][j].getState()){
               g.fillRect(i*unit, j*unit, unit, unit);
            }
            
           }
         }
            
        }
        
        public void cursor(){
            
            if(!Draw && !undo){
            g.setColor(Color.yellow);
         }else if(Draw && !undo){
            g.setColor(Color.blue);
         }else{
            g.setColor(Color.pink);
         }
                        
            g.fillRect(posX, posY, unit, unit);
            
        }
        
        public void drawPath(){
            
            g.setColor(Color.gray);
            for(int i = 0; i < pathfind.length(); i++){
                g.fillRect(pathfind.pathX(i)*unit, pathfind.pathY(i)*unit, unit, unit);
            }
            
        }

    
    public void movement(){
        
            if(keyBoard.up ){
          
          if(posY > 0)
          posY -= unit;
           
        } 
        
            if(keyBoard.down){
              
           if(posY+unit < height)
           posY += unit;
           
        }
        
            if(keyBoard.left){
             
           if(posX > 0)
           posX -= unit;
           
        } 
        
            if(keyBoard.right){
            
            if(posX+unit < width)
            posX += unit;
           
        }    
                
        if(keyBoard.draw && keyBoard.draw != oldStateSpace){    
                 
            if(!Draw){
                Draw = true;
            } else {
                Draw = false;
            }
                
            undo = false;
            
        }        
        
        oldStateSpace = keyBoard.draw;
        
        if(keyBoard.Undo && keyBoard.Undo != oldStateQ){    
                 
            if(!undo){
                undo = true;
            } else {
                undo = false;
            }
            
            Draw = false;
                
        }
        
        oldStateQ = keyBoard.Undo;
        
         if(keyBoard.startKey){
             
             startBlock = true;
             startX = posX;
             startY = posY;
             
            }
            
         if(keyBoard.endKey){
             
              endBlock = true;
              endX = posX;
              endY = posY;
                
            }
            
         if(keyBoard.enter){
             
             startPath = true;
                
            }
        
    }
    
   
    
    public void endGame(){
        
        g.setColor(Color.red);
        g.drawString("GAME OVER", width/2, height/2);
        
    }
    
}
