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
      
  JButton buttonB = new JButton("Count Up");
  buttonB.setPreferredSize(new Dimension(100, 60));
  
  ActionListener cB = new clickListener("Up");
  buttonB.addActionListener(cB);
      
  panel.add(buttonB);
 
  JButton buttonR = new JButton("Reset");
  buttonR.setPreferredSize(new Dimension(100, 60));
    
  ActionListener cR = new clickListener("Reset", cB);
  buttonR.addActionListener(cR);
      
  panel.add(buttonR); 
    
  //also check boxes and radio buttons!

  frame.setVisible(true);
 }
 
}
