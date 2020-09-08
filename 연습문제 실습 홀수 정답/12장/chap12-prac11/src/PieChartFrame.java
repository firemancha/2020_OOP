import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PieChartFrame extends JFrame {
	private int [] data = {0,0,0,0};
	private int [] arcAngle = new int [4];
	private Color [] color = {Color.RED, Color.BLUE, Color.MAGENTA, Color.ORANGE};
	private String [] itemName = {"apple", "cherry", "strawberry", "prune"};
	private JTextField [] tf = new JTextField [4];
	private ChartPanel chartPanel = new ChartPanel();
	
	public PieChartFrame() {
		setTitle("파이 차트 그리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.add(new InputPanel(), BorderLayout.NORTH);
		c.add(chartPanel, BorderLayout.CENTER);
		
		setSize(500,350);
		setVisible(true);
		drawChart();
	}
	
	private void drawChart() {
		int sum=0;
		for(int i=0; i<data.length; i++) {
			data[i] = Integer.parseInt(tf[i].getText());
			sum+=data[i];			
		}

		if(sum == 0) return;

		for(int i=0; i<data.length; i++) 
			arcAngle[i]=(int)Math.round((double)data[i]/(double)sum*360);
		
		chartPanel.repaint();
	}

	private class InputPanel extends JPanel {
		public InputPanel() {
			this.setBackground(Color.LIGHT_GRAY);
			for(int i=0; i<tf.length; i++) {
				tf[i] = new JTextField("0", 5);
				tf[i].addActionListener(new MyActionListener());
				add(new JLabel(itemName[i]));
				add(tf[i]);
			}
		}
		
		private class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) { // <Enter> 키 처리
				JTextField t = (JTextField)e.getSource();
				int n;
				try {
					n = Integer.parseInt(t.getText());
				}catch(NumberFormatException ex) {
					t.setText("0");
					return;
				}
				drawChart();
			}
		}
	}
	
	private class ChartPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int startAngle = 0;
			for(int i=0; i<data.length; i++) {
				g.setColor(color[i]);
				g.drawString(itemName[i]+" "+Math.round(arcAngle[i]*100./360.)+"%", 50+i*100, 20);
			}
			for(int i=0; i<data.length; i++) {
				g.setColor(color[i]);
				g.fillArc(150,50,200,200,startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
			}
		}	
	}
	
	public static void main(String [] args) {
		new PieChartFrame();
	}
} 