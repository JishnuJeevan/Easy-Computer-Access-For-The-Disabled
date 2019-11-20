package project;

// This file is used to take a screen shot of the desktop, for mouse scanning.
// We use this screen shot to draw horizontal and vertical lines to give an illusion of mouse scanning the entire desktop.

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ScreenCapture extends JFrame {

	public static JPanel contentPane;
	
	// A rectangle that represents the computer screen 
	public static Rectangle screenRect1 = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	
	// An object representing the computer screen
	public static GraphicsDevice gd1 = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width1 = gd1.getDisplayMode().getWidth();	//width of screen
	public static int height1 = gd1.getDisplayMode().getHeight(); // height of screen
	public static BufferedImage capture1;	// to store captured image
	public static Graphics g1;	// graphic object
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenCapture frame = new ScreenCapture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void paint(Graphics g)
	{
		try 
		{
			capture1 = new Robot().createScreenCapture(screenRect1);	// take screen shot. This will be the initial setting of the screen
			g1=contentPane.getGraphics();	// take the graphics component of content pane
			g1.drawImage(capture1, 0, 0, null);	// draw the screen shot on content pane
		} catch (AWTException e) {	e.printStackTrace();	}
	}

	/**
	 * Create the frame.
	 */
	public ScreenCapture() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width1, height1);	// set the height and width of content pane as the height and width of computer screen
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
