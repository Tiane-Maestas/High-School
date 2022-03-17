import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GUI
{
 public static void main(String[] args)
 {
     
     int width = 900;
     int height = 1200;
     
  JFrame frame = new JFrame();

  frame.setSize(width, height);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  DrawingComponent component = new DrawingComponent(width, height);
  frame.add(component);
  
  JLabel statusbar;
  statusbar = new JLabel("Squares");        
  frame.add(statusbar, BorderLayout.SOUTH);
 
  frame.setVisible(true);
 }
}
