import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class DrawPolygonWithMouse extends JFrame {
	public DrawPolygonWithMouse(){
		super("마우스로 폐다각형 그리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new MyPanel());
		setSize(300,300);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		private Vector<Integer> x = new Vector<Integer>();
		private Vector<Integer> y = new Vector<Integer>();

		public MyPanel() {
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					x.add(e.getX());
					y.add(e.getY());
					repaint();
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.MAGENTA);
			
			// drawPolygon()을 호출하기 위한 다각형 점들의 배열 만들기
			// 배열 공간 확보
			int xs [] = new int [x.size()];
			int ys [] = new int [y.size()];
			
			// 배열에 값 채우기
			for(int i=0; i<x.size(); i++) {
				xs[i] = x.get(i);
				ys[i] = y.get(i);
			}
			
			// 다각형 그리기
			g.drawPolygon(xs,  ys,  xs.length);
		}
	}
	
	static public void main(String[] args) {
		new DrawPolygonWithMouse();
	}
}
