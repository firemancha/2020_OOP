import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LineEventEx extends JFrame {
	private Clip clip;
	private String song="audio/애국가1절.wav";
	private JLabel label=new JLabel(song);
	
	public LineEventEx() {
		setTitle("애국가 1절 연주");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.setBackground(Color.YELLOW);
		c.add(label);
		setSize(300,150);
		setVisible(true);
		loadAudio(song);
	}

	private void loadAudio(String pathName) {
		try {
			File audioFile = new File(pathName); // 오디오 파일의 경로명
			final AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 파일로부터
			
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip.addLineListener(new LineListener() {
				public void update(LineEvent e) {
					if (e.getType() == LineEvent.Type.STOP) { // clip.stop()이 호출되거나 재생이 끝났을 때
						try {
							getContentPane().setBackground(Color.ORANGE);
							label.setText(song + " 연주 끝!");
							audioStream.close();
						} catch (IOException e1) {
							e1.printStackTrace();		
						}
	                }
				}
            });
			clip.open(audioStream); // 재생할 오디오 스트림 열기
			clip.start(); // 재생 시작
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}


	public static void main(String [] args) {
		new LineEventEx();
	}
}