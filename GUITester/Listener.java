import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;	

public class Listener implements ActionListener
{
       
    String name = "";   
    boolean state;
  
 public Listener(String name){
        this.name = name; 
        state = false;
    }
    
 public void actionPerformed(ActionEvent event)
 {
     
     //System.out.println(event.paramString());
    
           state = true;
        
 }
 
 public String name(){
     return name;
    }
 
 public boolean state(){
     return state;
    }

}
