import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

 public class MouseMotionEvent implements MouseMotionListener {
    
         public int y = 0;
         public int x = 0;
         
         public int Y(){
             return y;
            }
            
         public int X(){
             return x;
            }
         
    public void mouseMoved(MouseEvent e) {
       y = e.getY();
       x = e.getX();
    }

    public void mouseDragged(MouseEvent e) {
       
    }

    void saySomething(String eventDescription, MouseEvent e) {
        
    }
    
}
