import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;	

/**
An action listener that prints a message. 
*/
public class clickListener implements ActionListener
{
       
    String name = "";
    
 public clickListener(String name){
        this.name = name;
    }
    
 public void actionPerformed(ActionEvent event)
 {
         
         if(!name.equals("TextField")){
         System.out.println(name);      
        }else{
           System.out.println(event.paramString());   
        }
  
 }

}
