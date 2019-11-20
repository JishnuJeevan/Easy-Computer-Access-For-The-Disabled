package project;

// This file is used to create a window in which the user can type a text and it will be converted into speech.

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class TalkWindow extends JFrame 
{

	public static JPanel contentPane;
	public static JTextArea talkArea;
	public static JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TalkWindow frame = new TalkWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TalkWindow() {
		setTitle("Talk Window");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TalkWindow.class.getResource("/project/Talk.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MainWindow.width-430, 400, 430, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 424, 241);
		contentPane.add(scrollPane);
		
		talkArea = new JTextArea();
		talkArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(talkArea);
	}
}
