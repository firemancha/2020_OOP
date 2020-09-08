import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class AudioStartAndStopFrame extends JFrame {
	private Clip clip = null;
	private AudioInputStream audioStream = null;
	private JLabel msgLabel = new JLabel("");
	
	public AudioStartAndStopFrame() {
		super("오디오 시작 중단 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();

		c.setLayout(new FlowLayout());
		msgLabel.setFont(new Font("Gothic", Font.PLAIN, 15));
		c.add(msgLabel);
		setSize(300,150);
		setVisible(true);
		
		playAudio("audio\\hiphop.wav");
		msgLabel.setText("audio\\hiphop.wav 연주 중");
		
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				msgLabel.setText("audio\\hiphop.wav 연주 계속");
				clip.start(); // 중단된 위치에서부터 시작				
			}
			
		 	@Override
			public void mouseExited(MouseEvent e) {
				msgLabel.setText("audio\\hiphop.wav 연주 일시 중단");
				clip.stop(); // 중단된 위치에서부터 시작				
			}

		});
	}
	
	private void playAudio(String pathName) {
		try {
			File audioFile = new File(pathName); // 오디오 파일의 경로명
			audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 파일로부터
			
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip.open(audioStream); // 재생할 오디오 스트림 열기
			clip.start(); // 재생 시작
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}

	static public void main(String[] arg) {
		new AudioStartAndStopFrame();
	}
}

