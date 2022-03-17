import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
public class Display
{
    
    private JFrame frame;
    private Canvas canvas;
    
    private JLabel statusbar;
    
    //private Mouse mouse;
    private Mouse2 mouse2;
    
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
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
         statusbar = new JLabel("default");        
        frame.add(statusbar, BorderLayout.SOUTH);
        
        //mouse = new Mouse();        
        //canvas.addMouseListener(mouse);
        
        mouse2 = new Mouse2();
        canvas.addMouseMotionListener(mouse2);
        
        frame.add(canvas);
        frame.pack();
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
    /*public Mouse getMouse(){
      return mouse;  
    }*/
    
    public Mouse2 getMouse2(){
      return mouse2;  
    }
    
}
