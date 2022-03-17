import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;   
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class GUI
{
    
    int width, height;
    
    public GUI(int width, int height){
        this.width = width;
        this.height = height;
    }
    
 public void createFrame()
 {
  
     
  JFrame frame = new JFrame();

  frame.setSize(width, height);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  JLabel statusbar;
  statusbar = new JLabel("Buttons");        
  frame.add(statusbar, BorderLayout.SOUTH);
  
  JPanel panel = new JPanel();     
  frame.add(panel);
  
  for(int i = 1; i <= 100; i++){   
      
    JButton button = new JButton(""+ i);
    button.setPreferredSize(new Dimension(60, 60));
  
    ActionListener c = new clickListener(i);
    button.addActionListener(c);
      
    panel.add(button);
  
   }
   
   JButton button = new JButton("Date");
    button.setPreferredSize(new Dimension(100, 60));
    
    ActionListener c = new clickListener("Date");
    button.addActionListener(c);
      
    panel.add(button);
    
    //also check boxes and radio buttons!

  frame.setVisible(true);
 }
 
}
