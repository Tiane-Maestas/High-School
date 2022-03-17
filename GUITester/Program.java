import java.awt.Graphics;
import java.awt.Color;

public class Program 
{
   
    public static void main(){
        
        Program p = new Program(400,600);
        p.start();
        
    }
    
    private int width, height;
    private Display display;
    private Graphics g;
    
    private boolean run;
    
    boolean button;
    int d;
    
    public Program(int width, int height)
    {
        
       this.width = width;
       this.height = height;
       initialize();
       
    }
    
    public void initialize(){
        
        display = new Display(width, height);
        display.createDisplay();
        
        g = display.getCanvas().getGraphics(); 
             
        run = true;
        
        button = false;        
        
    }  
    
    public void start(){
        
        while(run){
                      
            update();
            draw();
            
        }
        
    }
  
    public void update(){
        
        button = display.getButtonListener().state();
        
        if(display.getTextListener().state()){
            
            try{
        String input = display.getTextField().getText();
        int r = Integer.parseInt(input);
        d = r*2;
      }catch(NumberFormatException e){
         System.out.println("Try again");
         stop();
         display.getFrame().dispose();
       }
       
    }
        
   }
    
   
    public void draw(){
        
       //if(button){
        
        //g.clearRect(0, 0, width, height);
        g.setColor(Color.black);
        int pos = (width/2)-(d/2);
        g.fillOval(pos,10,d,d);        
        
      //}
      
    }
     
    public void stop(){
        run = false;
    }
  
}
