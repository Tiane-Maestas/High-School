import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;	

/**
An action listener that prints a message. 
*/
public class clickListener implements ActionListener
{
    
    int i = 0;
    String name = "";
    
 public clickListener(int i){
        this.i = i;
   }
    
 public clickListener(String name){
        this.name = name;
    }
    
 public void actionPerformed(ActionEvent event)
 {
     
     if(name.equals("Date")){
         System.out.println(new java.util.Date());
        }else{     
      System.out.println(i+" was clicked.");  
   }
  
 }

}
