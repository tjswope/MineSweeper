
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MineSweeper extends JFrame{
	
    public static void main(String[] args) {
    	MineSweeper window = new MineSweeper();
	    JPanel p = new JPanel();
	    p.add(new GraphicsPanel()); 
	    window.setTitle("MineSweeper");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setContentPane(p);
	    window.pack();
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
    }
}
