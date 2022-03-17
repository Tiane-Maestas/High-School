import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyBoard implements KeyListener{
    
   private boolean[] keys;
   public boolean up1, down1, up2, down2;
   
   public KeyBoard(){
     keys = new boolean[256];  
    }
    
   public void update(){
     up1 = keys[KeyEvent.VK_W]; 
     down1 = keys[KeyEvent.VK_S]; 
     up2 = keys[KeyEvent.VK_UP]; 
     down2 = keys[KeyEvent.VK_DOWN];  
     
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

