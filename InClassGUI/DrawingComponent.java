import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Color;
public class DrawingComponent extends JComponent
{
    
    public int width , height;
    
    private int unitX;
    private int unitY;
    
    private int times = 125;
    
 public DrawingComponent(int width, int height){
        this.width = width;
        this.height = height;
        unitX = width/(times*2);
        unitY = height/(times*2);  
   }
    
 public void paintComponent(Graphics g)
 {
     
  g.setColor(Color.black);
  g.fillRect(0, 0, width, height);
    
  for(int i = 1; i < times; i++){
      g.setColor(Color.red);
      g.drawRect(unitX*i, unitY*i, width-(unitX*(2*i)), height-(unitY*(2*i)));
 }
  
 }

}
