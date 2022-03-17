import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;	

/**
An action listener that prints a message. 
*/
public class clickListener implements ActionListener
{
    
    public int i = 0;
    String name = "";
    ActionListener b;
    
 public clickListener(int i){
        this.i = i;
   }
    
 public clickListener(String name){
        this.name = name;
    }
 
   
 public clickListener(String name,ActionListener b){
        this.name = name;
        this.b = b;
    }
    
 public void actionPerformed(ActionEvent event)
 {
     
     if(name.equals("Up")){
         i++;
        } 
        
     if(name.equals("Reset")){
         //b.i = 0;
        } 
        
      System.out.println(name+" was clicked.");  
   
 }

}
