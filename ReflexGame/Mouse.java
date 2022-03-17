import java.awt.event.*; 

public class Mouse implements MouseListener
  {
    
      int x, y, oldX, oldY;
    
    public int X(){
        
        if(oldX != x){
            
          oldX = x;  
          return x;
          
        }
          
          return 10000;
          
     }
        
    public int Y(){
        
        if(oldY != y){
                    
           oldY = y;
           return y;
           
        }
           
           return 10000;
           
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
