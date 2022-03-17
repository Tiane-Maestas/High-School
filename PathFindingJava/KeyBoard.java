import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyBoard implements KeyListener{
    
   private boolean[] keys;
   public boolean up, down, left, right, draw, Undo, startKey, endKey, enter;
   
   public KeyBoard(){
       
     keys = new boolean[256];  
     
    }
    
   public void update(){
     up = keys[KeyEvent.VK_W]; 
     down = keys[KeyEvent.VK_S]; 
     left = keys[KeyEvent.VK_A]; 
     right = keys[KeyEvent.VK_D]; 
     draw = keys[KeyEvent.VK_SPACE];
     Undo = keys[KeyEvent.VK_Q];
     startKey = keys[KeyEvent.VK_B];
     endKey = keys[KeyEvent.VK_E];
     enter = keys[KeyEvent.VK_ENTER];
    }
    
   public void keyPressed(KeyEvent e){
       keys[e.getKeyCode()] = true;
    }
    
   public void keyReleased(KeyEvent e){
       keys[e.getKeyCode()] = false;
    }
    
   public void keyTyped(KeyEvent e){
       
    }
   
}
