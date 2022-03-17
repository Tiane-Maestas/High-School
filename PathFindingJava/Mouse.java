import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Mouse implements MouseListener {
        
    public int x, y;
    
    public Mouse(){
        
    }
    
    public int X(){
       return x;
    }
    
     public int Y(){
       return y;
    }

    public void mousePressed(MouseEvent e) {
        
     x = e.getX();
     y = e.getY();     
     
    }

    public void mouseReleased(MouseEvent e) {
     
    }

    public void mouseEntered(MouseEvent e) {
      
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
      
    }
    
}
