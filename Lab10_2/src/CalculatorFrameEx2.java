import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorFrameEx2 extends JFrame {
	public JTextField won;
	public JTextField dallar;
	private boolean won_mode = true;
	private int rate = 1100;
	
	public CalculatorFrameEx2() {
		super("환율 계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		
		won= new JTextField(10);
		dallar = new JTextField(6);
		
		c.add(new SouthPanel(this), BorderLayout.SOUTH);
		c.add(new CenterPanel(this), BorderLayout.CENTER);
		c.add(new NorthPanel(this), BorderLayout.NORTH);
		setSize(300,300);
		setVisible(true);
		
		
	}
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton bt = (JButton)e.getSource();
			try{
				if(Integer.parseInt(bt.getText()) >= 0 && Integer.parseInt(bt.getText()) <= 9)
					addNumber(bt.getText());
			}
			catch (Exception e1){
				switch(bt.getText())
				{
				case "reset":
					won.setText("");
					dallar.setText("");
					break;
				case "변환":
					if(won_mode)
					{
						int money = Integer.parseInt(won.getText());
						dallar.setText(Integer.toString(money / rate));
					}
					else
					{
						int money = Integer.parseInt(dallar.getText());
						won.setText(Integer.toString(money * rate));
					}
					break;
				case "(원) -> (달러)":
					if(won_mode)
					{
						won.setText("");
						dallar.setText("");
						won_mode = false;
					}
					break;
				case "(달러) -> (원)":
					if(!won_mode)
					{
						won.setText("");
						dallar.setText("");
						won_mode = true;
					}
					break;					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new CalculatorFrameEx2();
	}

	private void addNumber(String s) {
		if(won_mode)
			setWonNumber(s);
		else
			setDallarNumber(s);
	}
	private void setWonNumber(String s) {
		String temp = won.getText();
		won.setText(temp.concat(s));
	}
	private void setDallarNumber(String s) {
		String temp = dallar.getText();
		dallar.setText(temp.concat(s));
	}
}

class SouthPanel extends JPanel {
	public SouthPanel(CalculatorFrameEx2 cf) {
		setBackground(Color.YELLOW);
		setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton wtd = new JButton("(원) -> (달러)");
		JButton dtw = new JButton("(달러) -> (원)");
		add(wtd);
		wtd.addActionListener(cf.new MyActionListener());
		add(new JLabel("  "));
		add(dtw);
		dtw.addActionListener(cf.new MyActionListener());
	}
}

class CenterPanel extends JPanel {
	public CenterPanel(CalculatorFrameEx2 cf) {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(4,3,4,4));
		for(int i=1; i<10; i++) {
			JButton b = new JButton(Integer.toString(i));
			add(b);
			b.addActionListener(cf.new MyActionListener());
		}
		JButton reset = new JButton("reset");
		JButton zero = new JButton("0");
		JButton conversion = new JButton("변환");
		add (reset); add(zero); add(conversion);
		reset.addActionListener(cf.new MyActionListener());
		zero.addActionListener(cf.new MyActionListener());
		conversion.addActionListener(cf.new MyActionListener());
	}
}

class NorthPanel extends JPanel {
	public NorthPanel(CalculatorFrameEx2 cf) {
		setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		setLayout(new FlowLayout());
		add(new JLabel("(원)"));
		add(cf.won);
		add(new JLabel("  "));
		add(new JLabel("(달러)"));
		add(cf.dallar);
	}
}

