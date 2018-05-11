import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ivan Mota, Isaias Leos Ayala
 */
public final class Infinity extends JPanel
{

	private static final long serialVersionUID = 1L;

	public Infinity()
	{
		initComponents();
	}

	public void initComponents()
	{
		JFrame frame = new JFrame("Infinity");
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

//		JPanel panel = new JPanel();
//		panel.setBackground(Color.black);
//		panel.setLayout(null);
//
//		JButton button = new JButton("Button");
//		button.setBounds(0, 0, 0, 0);
//
//		panel.add(button);
//		frame.add(panel);
		frame.add(this);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle2D rect = new Rectangle2D.Double(0,0,100,100);
		Ellipse2D circ = new Ellipse2D.Double(200, 200, 100, 100);
		g2.setColor(Color.red);
		g2.fill(circ);
		g2.fill(rect);
	}

//	public static void main(String[] args)
//	{
//		Infinity S = new Infinity();
//
//	}

}
