import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
public class Display
{
    
    private JFrame frame;
    private Canvas canvas;
    
    private MouseMotionEvent movement;
    
    private String title;
    private int width, height;
    
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
        
    }
    
    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        movement = new MouseMotionEvent();      
        canvas.addMouseMotionListener(movement); 
        
        frame.add(canvas);
        frame.pack();
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
    public MouseMotionEvent getMouse(){
        return movement;
    }
    
     public class MouseMotionEvent implements MouseMotionListener {
    
         public int y = 0;
         public int x = 0;
         
         public int Y(){
             return y;
            }
            
         public int X(){
             return x;
            }
         
    public void mouseMoved(MouseEvent e) {
       y = e.getY();
       x = e.getX();
    }

    public void mouseDragged(MouseEvent e) {
       
    }

    void saySomething(String eventDescription, MouseEvent e) {
        
    }
    
}
    
}
