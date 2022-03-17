import javax.swing.JLabel;
import javax.swing.JFrame;
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
public class GUITester
{
  
 public static void main(String[] args)
 {
     
     //isSelected
     
     int width = 600;
     int height = 600;
     
  JFrame frame = new JFrame();

  frame.setSize(width, height);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  
  JLabel statusbar;
  statusbar = new JLabel("Menu");        
  frame.add(statusbar, BorderLayout.SOUTH);
  
  frame.setVisible(true);
  
  
  JPanel radioButtonPanel = new JPanel();
  radioButtonPanel.setLayout(new GridLayout(3, 1));
  radioButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
  //frame.add(radioButtonPanel);
  
  
  /*
  JButton smallButton = new JButton("small");

  ActionListener c = new clickListener("small");
  smallButton.addActionListener(c);
      
  radioButtonPanel.add(smallButton);
  
  
  JButton mediumButton = new JButton("medium");
  
  c = new clickListener("medium");
  mediumButton.addActionListener(c);
      
  radioButtonPanel.add(mediumButton);
  
  
  JButton largeButton = new JButton("large");
  
  c = new clickListener("large");
  largeButton.addActionListener(c);
      
  radioButtonPanel.add(largeButton);
  */
  
  
  JRadioButton smallButton = new JRadioButton("small");

  ActionListener c = new clickListener("small");
  smallButton.addActionListener(c);
      
  radioButtonPanel.add(smallButton);
  
  JRadioButton mediumButton = new JRadioButton("medium");

  c = new clickListener("medium");
  mediumButton.addActionListener(c);
      
  radioButtonPanel.add(mediumButton);
  
  JRadioButton largeButton = new JRadioButton("large");

  c = new clickListener("large");
  largeButton.addActionListener(c);
      
  radioButtonPanel.add(largeButton);
 
  
  
  JPanel checkBoxPanel = new JPanel();
  //frame.add(checkBoxPanel);
  
  JCheckBox pepperoniButton = new JCheckBox("pepperoni");

  c = new clickListener("pepperoni");
  pepperoniButton.addActionListener(c);
      
  checkBoxPanel.add(pepperoniButton);
  
  
  JCheckBox anchoviesButton = new JCheckBox("anchovies");

  c = new clickListener("anchovies");
  anchoviesButton.addActionListener(c);
      
  checkBoxPanel.add(anchoviesButton);
  
  
  
  
  JPanel pricePanel = new JPanel(); // Uses FlowLayout by default
  pricePanel.add(new JLabel("Your Price: "));
  //frame.add(pricePanel);
  
  JTextField priceTextField = new JTextField(10);
  ActionListener listener = new clickListener("TextField");
  priceTextField.addActionListener(listener);
  pricePanel.add(priceTextField);
  
   
  
  JMenuBar menuBar = new JMenuBar();
  frame.setJMenuBar(menuBar);
  
  JMenu fileMenu = new JMenu("File");
  JMenu fontMenu = new JMenu("Font");
  menuBar.add(fileMenu);
  menuBar.add(fontMenu);
  
   JMenuItem saveItem = new JMenuItem("Save");
   fileMenu.add(saveItem);
   listener = new clickListener("Save");
   saveItem.addActionListener(listener);

   JMenu styleMenu = new JMenu("Style");
   fontMenu.add(styleMenu); // A submenu
   JMenuItem blueItem = new JMenuItem("Blue");
   styleMenu.add(blueItem);
   listener = new clickListener("Blue");
   blueItem.addActionListener(listener);

   
  JPanel centerPanel = new JPanel(); // Uses FlowLayout
  //centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
  centerPanel.setLayout(new GridLayout(3, 1));
  frame.add(centerPanel);
  
  centerPanel.add(radioButtonPanel);
  centerPanel.add(checkBoxPanel);
  centerPanel.add(pricePanel);  
      
 }

}
