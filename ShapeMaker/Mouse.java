import java.awt.event.*; 

public class Mouse implements MouseListener
  {
    
      int x, y, oldX, oldY;
      boolean updated = false;
    
    public int X(){
        
           return x;
        
     }
        
    public int Y(){
                   
           return y;
       
     }
     
    public boolean checkUpdate(){
         
         if(oldX == x && oldY == y){
             return false;
            }
        
         return true;
         
        }
        
        public void updateOldStuff(){
            oldX = x; 
            oldY = y;
        }
      
    public void mousePressed(MouseEvent e) {
           
    }

    public void mouseReleased(MouseEvent e) {
      x = e.getX();
      y = e.getY();             
    }

    public void mouseEntered(MouseEvent e) {
      
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
      
    }
  
  }

