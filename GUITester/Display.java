import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;   
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Display
{
        
    private JFrame frame;
    private JLabel statusbar;
    private Canvas canvas;  
    private JPanel Panel1, Panel2;
    private Listener b, t;
    private JLabel label;
    private JTextField textField;
    private JButton Button;
    
    private static int width, height;
   
    public Display(int width, int height)
    {
       this.width = width;
       this.height = height;        
    }

    public void createDisplay(){
            
    frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    statusbar = new JLabel("Menu");        
    frame.add(statusbar, BorderLayout.SOUTH);
          
    Panel1 = new JPanel();
    Panel1.setLayout(new GridLayout(2, 1));
    Panel1.setBorder(new TitledBorder(new EtchedBorder(), "Select Size"));
    Panel1.setSize(width, 100);
    frame.add(Panel1, BorderLayout.NORTH);  
   
    Button = new JButton("Draw Circle");
    b = new Listener("Button");
    Button.addActionListener(b);      
    Panel1.add(Button);
  
    
    Panel2 = new JPanel();
    Panel2.setLayout(new GridLayout(1, 2));
    textField = new JTextField();
    t = new Listener("TextField");
    textField.addActionListener(t);
    
    label = new JLabel("Radius (Hit Enter First):");
    
    Panel2.add(label);
    Panel2.add(textField);
    
    Panel1.add(Panel2);
       
    canvas = new Canvas();       
    frame.add(canvas);

    frame.setVisible(true);
  
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JTextField getTextField(){
        return textField;
    }
    
    public Listener getButtonListener(){
        return b;
    }
    
    public Listener getTextListener(){
        return b;
    }
           
}
