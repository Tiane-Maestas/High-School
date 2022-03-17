import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
public class Mouse2 implements MouseMotionListener
{
    
    public int x,y;
    
    public int X(){
       return x;
    }
    
     public int Y(){
       return y;
    }
    
   public void mouseMoved(MouseEvent e) {
       x = e.getX();
       y = e.getY();
    }

   public void mouseDragged(MouseEvent e) {
      
    }  
}

