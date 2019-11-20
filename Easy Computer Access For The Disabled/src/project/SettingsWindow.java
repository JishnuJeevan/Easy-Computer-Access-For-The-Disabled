package project;

// This file will open a settings window, that is used to by the user to change the system configuration according to their convenience.

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class SettingsWindow extends JFrame 
{
	private JPanel contentPane;
	private JTextField fieldMouseScannTime;
	private JTextField fieldAddWord;
	private JTextField fieldRemoveWord;
	private JTextField fieldAddApp;
	private JTextField fieldRemoveApp;
	private JTextField fieldScannTime;
	private JTextField fieldShortCutName;
	public static JTextField fieldAddShortCut;
	public static JTextField fieldRemoveShortCut;
	private JTextField fieldRemoveShortCutName;
	private JRadioButton rdbtnStepMouse;
	private JRadioButton rdbtnSwitchMouse;
	private JRadioButton rdbtnSwitchEyeBlink;
	private JRadioButton rdbtnMaleVoice;
	private JRadioButton rdbtnFemaleVoice;
	private JRadioButton rdbtnCloseApplication;
	java.sql.PreparedStatement pst=null;
	ResultSet result=null;
	public static String scannMethod;
	public static String voiceType;
	public static int scannTime;
	public static int mouseScannTime;
	private JTextField displayScannTime;
	private JTextField displayMouseScannTime;
	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow frame = new SettingsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**********************setting System configuration***************************************************************/
	public void setSystemConfig()
	{
		try
		{
			pst=MainWindow.conn.prepareStatement("select * from SystemConfiguration");
			result=pst.executeQuery();
			scannMethod=result.getString("ScannMethod");
			scannTime=result.getInt("ScannTime");
			mouseScannTime=result.getInt("MouseScannTime");
			voiceType=result.getString("VoiceType");
			displayScannTime.setText(Integer.toString(scannTime));
			displayMouseScannTime.setText((Integer.toString(mouseScannTime)));
						
			if(scannMethod.equals("Step Mouse"))
			{
				rdbtnStepMouse.setSelected(true);
				rdbtnSwitchMouse.setSelected(false);
				rdbtnSwitchEyeBlink.setSelected(false);
			}
			
			if(scannMethod.equals("Switch Mouse"))
			{
				rdbtnSwitchMouse.setSelected(true);
				rdbtnStepMouse.setSelected(false);
				rdbtnSwitchEyeBlink.setSelected(false);
			}
			if(scannMethod.equals("Switch Eye"))
			{
				rdbtnSwitchEyeBlink.setSelected(true);
				rdbtnSwitchMouse.setSelected(false);
				rdbtnStepMouse.setSelected(false);
			}
			if(voiceType.equals("cmu-rms-hsmm"))
			{
				rdbtnMaleVoice.setSelected(true);
				rdbtnFemaleVoice.setSelected(false);
			}
			
			if(voiceType.equals("dfki-poppy-hsmm"))
			{
				rdbtnFemaleVoice.setSelected(true);
				rdbtnMaleVoice.setSelected(false);
			}
			
		} 
		catch (SQLException e) {e.printStackTrace();}
		
	}
	/****************************************************************************************************************************/

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public SettingsWindow()  {
		setTitle("Settings");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SettingsWindow.class.getResource("/project/Settings.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(MainWindow.width-900, 20, 460, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		rdbtnStepMouse = new JRadioButton("Step (Mouse)");
		rdbtnStepMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String scannMethod="Step Mouse";
				rdbtnSwitchMouse.setSelected(false);
				rdbtnSwitchEyeBlink.setSelected(false);
				try
				{
					String query="update SystemConfiguration set ScannMethod = ? ";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,scannMethod);
					pst.execute();
					//pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();					
				}
				MainWindow.loadSystemConfig();
				DaemonThread.stopCamera();
				
			}
		});
		rdbtnStepMouse.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnStepMouse.setBounds(20, 20, 150, 20);
		contentPane.add(rdbtnStepMouse);
		
		rdbtnSwitchMouse = new JRadioButton("Switch (Mouse)");
		rdbtnSwitchMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String scannMethod="Switch Mouse";
				rdbtnStepMouse.setSelected(false);
				rdbtnSwitchEyeBlink.setSelected(false);
				try
				{
					String query="update SystemConfiguration set ScannMethod = ? ";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,scannMethod);
					pst.execute();
					//pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();					
				}
				MainWindow.loadSystemConfig();
				DaemonThread.stopCamera();
				
			}
		});
		rdbtnSwitchMouse.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnSwitchMouse.setBounds(20, 40, 150, 20);
		contentPane.add(rdbtnSwitchMouse);
		
		rdbtnSwitchEyeBlink = new JRadioButton("Switch (Eye Blink)");
		rdbtnSwitchEyeBlink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String scannMethod="Switch Eye";
				rdbtnSwitchMouse.setSelected(false);
				rdbtnStepMouse.setSelected(false);
				try
				{
					String query="update SystemConfiguration set ScannMethod = ? ";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,scannMethod);
					pst.execute();
					//pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();					
				}
				MainWindow.loadSystemConfig();
				DaemonThread.startCamera();
			}
		});
		rdbtnSwitchEyeBlink.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnSwitchEyeBlink.setBounds(20, 60, 150, 20);
		contentPane.add(rdbtnSwitchEyeBlink);
		
		rdbtnMaleVoice = new JRadioButton("Male Voice");
		rdbtnMaleVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String voiceType="cmu-rms-hsmm";
				rdbtnFemaleVoice.setSelected(false);
				try
				{
					String query="update SystemConfiguration set VoiceType = ? ";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,voiceType);
					pst.execute();
					//pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();					
				}
				MainWindow.loadSystemConfig();
			}
		});
		rdbtnMaleVoice.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnMaleVoice.setBounds(20, 80, 150, 20);
		contentPane.add(rdbtnMaleVoice);
		
		rdbtnFemaleVoice = new JRadioButton("Female Voice");
		rdbtnFemaleVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String voiceType="dfki-poppy-hsmm";
				rdbtnMaleVoice.setSelected(false);
				try
				{
					String query="update SystemConfiguration set VoiceType = ? ";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,voiceType);
					pst.execute();
					//pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();					
				}
				MainWindow.loadSystemConfig();
			}
		});
		rdbtnFemaleVoice.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnFemaleVoice.setBounds(20, 100, 150, 20);
		contentPane.add(rdbtnFemaleVoice);
		
		rdbtnCloseApplication = new JRadioButton("Close Application");
		rdbtnCloseApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.Talking("Hope I have been able to help you. Good bye. See you soon.");
				if(DaemonThread.camerastarted==true)
					DaemonThread.stopCamera();
				try {
					Robot r=new Robot();
					r.delay(5000);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		rdbtnCloseApplication.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnCloseApplication.setBounds(20, 120, 150, 20);
		contentPane.add(rdbtnCloseApplication);
		
		JLabel labelScanningTime = new JLabel("Scanning Time ");
		labelScanningTime.setFont(new Font("Arial", Font.BOLD, 14));
		labelScanningTime.setBounds(25, 160, 160, 20);
		contentPane.add(labelScanningTime);
		
		JLabel labelMouseScanningTime = new JLabel("Mouse Scanning Time ");
		labelMouseScanningTime.setFont(new Font("Arial", Font.BOLD, 14));
		labelMouseScanningTime.setBounds(25, 180, 160, 20);
		contentPane.add(labelMouseScanningTime);
		
		JLabel labelAddWord = new JLabel("Add Word ");
		labelAddWord.setFont(new Font("Arial", Font.BOLD, 14));
		labelAddWord.setBounds(25, 200, 160, 20);
		contentPane.add(labelAddWord);
		
		JLabel labelRemoveWord = new JLabel("Remove Word ");
		labelRemoveWord.setFont(new Font("Arial", Font.BOLD, 14));
		labelRemoveWord.setBounds(25, 220, 160, 20);
		contentPane.add(labelRemoveWord);
		
		JLabel labelAddApplication = new JLabel("Add Application ");
		labelAddApplication.setFont(new Font("Arial", Font.BOLD, 14));
		labelAddApplication.setBounds(25, 240, 160, 20);
		contentPane.add(labelAddApplication);
		
		JLabel labelRemoveApplication = new JLabel("Remove Application");
		labelRemoveApplication.setFont(new Font("Arial", Font.BOLD, 14));
		labelRemoveApplication.setBounds(25, 260, 160, 20);
		contentPane.add(labelRemoveApplication);
		
		JLabel labelAddShortcut = new JLabel("Add Shortcut ");
		labelAddShortcut.setFont(new Font("Arial", Font.BOLD, 14));
		labelAddShortcut.setBounds(25, 280, 160, 20);
		contentPane.add(labelAddShortcut);
		
		JLabel labelShortcutName = new JLabel("Shortcut Name ");
		labelShortcutName.setFont(new Font("Arial", Font.BOLD, 14));
		labelShortcutName.setBounds(25, 300, 160, 20);
		contentPane.add(labelShortcutName);
		
		JLabel labelRemoveShortcut = new JLabel("Remove Shortcut ");
		labelRemoveShortcut.setFont(new Font("Arial", Font.BOLD, 14));
		labelRemoveShortcut.setBounds(25, 320, 160, 20);
		contentPane.add(labelRemoveShortcut);
		
		JLabel lblShortcutName = new JLabel("Shortcut Name");
		lblShortcutName.setFont(new Font("Arial", Font.BOLD, 14));
		lblShortcutName.setBounds(25, 340, 160, 20);
		contentPane.add(lblShortcutName);
		
		fieldMouseScannTime = new JTextField();
		fieldMouseScannTime.setFont(new Font("Arial", Font.BOLD, 12));
		fieldMouseScannTime.setBounds(190, 180, 190, 20);
		contentPane.add(fieldMouseScannTime);
		fieldMouseScannTime.setColumns(10);
		
		fieldAddWord = new JTextField();
		fieldAddWord.setFont(new Font("Arial", Font.BOLD, 12));
		fieldAddWord.setColumns(10);
		fieldAddWord.setBounds(190, 200, 190, 20);
		contentPane.add(fieldAddWord);
		
		fieldRemoveWord = new JTextField();
		fieldRemoveWord.setFont(new Font("Arial", Font.BOLD, 12));
		fieldRemoveWord.setColumns(10);
		fieldRemoveWord.setBounds(190, 220, 190, 20);
		contentPane.add(fieldRemoveWord);
		
		fieldAddApp = new JTextField();
		fieldAddApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainWindow.Message.setText("Remember to add the extension of the file.");
				MainWindow.Talking("Remember to add the extension of the file.");
			}
		});
		fieldAddApp.setFont(new Font("Arial", Font.BOLD, 12));
		fieldAddApp.setColumns(10);
		fieldAddApp.setBounds(190, 240, 190, 20);
		contentPane.add(fieldAddApp);
		
		fieldRemoveApp = new JTextField();
		fieldRemoveApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainWindow.Message.setText("Remember to add the extension of the file.");
				MainWindow.Talking("Remember to add the extension of the file.");
			}
		});
		fieldRemoveApp.setFont(new Font("Arial", Font.BOLD, 12));
		fieldRemoveApp.setColumns(10);
		fieldRemoveApp.setBounds(190, 260, 190, 20);
		contentPane.add(fieldRemoveApp);
		
		fieldScannTime = new JTextField();
		fieldScannTime.setFont(new Font("Arial", Font.BOLD, 12));
		fieldScannTime.setColumns(10);
		fieldScannTime.setBounds(190, 160, 190, 20);
		contentPane.add(fieldScannTime);
		
		fieldShortCutName = new JTextField();
		fieldShortCutName.setFont(new Font("Arial", Font.BOLD, 12));
		fieldShortCutName.setColumns(10);
		fieldShortCutName.setBounds(190, 300, 190, 20);
		contentPane.add(fieldShortCutName);
		
		fieldAddShortCut = new JTextField();
		fieldAddShortCut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(fieldAddShortCut.getCaret().isVisible())
				{
					MainWindow.Message.setText("Enter space after every keys. E.g: Alt Tab ");
					MainWindow.Talking("Enter space after every keys. Example. Alt space Tab.");
				}
			}
		});
		fieldAddShortCut.setFont(new Font("Arial", Font.BOLD, 12));
		fieldAddShortCut.setColumns(10);
		fieldAddShortCut.setBounds(190, 280, 190, 20);
		contentPane.add(fieldAddShortCut);
		
		fieldRemoveShortCut = new JTextField();
		fieldRemoveShortCut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(fieldRemoveShortCut.getCaret().isVisible())
				{
					MainWindow.Message.setText("Enter space after every keys. E.g: Alt Tab ");
					MainWindow.Talking("Enter space after every keys. Example. Alt space Tab.");
				}
			}
		});
		fieldRemoveShortCut.setFont(new Font("Arial", Font.BOLD, 12));
		fieldRemoveShortCut.setColumns(10);
		fieldRemoveShortCut.setBounds(190, 320, 190, 20);
		contentPane.add(fieldRemoveShortCut);
		
		fieldRemoveShortCutName = new JTextField();
		fieldRemoveShortCutName.setFont(new Font("Arial", Font.BOLD, 12));
		fieldRemoveShortCutName.setColumns(10);
		fieldRemoveShortCutName.setBounds(190, 340, 190, 20);
		contentPane.add(fieldRemoveShortCutName);
		
		JButton btnOKScann = new JButton("OK");
		btnOKScann.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fieldScannTime.getText().equals(""))
				{
					MainWindow.Message.setText("Field cannot be empty");
					MainWindow.Talking("Field cannot be empty");
					
				}
				else
				{
					int scannTime=Integer.parseInt(fieldScannTime.getText());
					try
					{
						String query="update SystemConfiguration set ScannTime = ? ";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setInt(1,scannTime);
						pst.execute();
						//pst.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();					
					}
					fieldScannTime.setText("");
					displayScannTime.setText(Integer.toString(scannTime));
					MainWindow.loadSystemConfig();
				}
				
			}
		});
		btnOKScann.setFont(new Font("Arial", Font.BOLD, 11));
		btnOKScann.setBounds(390, 160, 50, 20);
		contentPane.add(btnOKScann);
		
		JButton btnOkMouseScann = new JButton("OK");
		btnOkMouseScann.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fieldMouseScannTime.getText().equals(""))
				{
					MainWindow.Message.setText("Field cannot be empty");
					MainWindow.Talking("Field cannot be empty");
				}
				else
				{
					int mouseScannTime=Integer.parseInt(fieldMouseScannTime.getText());
					try
					{
						String query="update SystemConfiguration set MouseScannTime = ? ";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setInt(1,mouseScannTime);
						pst.execute();
						//pst.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();					
					}
					fieldMouseScannTime.setText("");
					displayMouseScannTime.setText((Integer.toString(mouseScannTime)));
					MainWindow.loadSystemConfig();
				}
				
			}
		});
		btnOkMouseScann.setFont(new Font("Arial", Font.BOLD, 11));
		btnOkMouseScann.setBounds(390, 180, 50, 20);
		contentPane.add(btnOkMouseScann);
		
		JButton btnAddWord = new JButton("OK");
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String addWord=fieldAddWord.getText();
					fieldAddWord.setText(null);
					String query="select * from Words where Words = ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,addWord);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="update Words set count=count+1 where Words = ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,addWord);
						pst.execute();
						pst.close();
						rs.close();
					}
					else
					{
						query="insert into Words values(?,?)";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1, addWord);
						pst.setInt(2, 1);
						pst.execute();
						pst.close();
						rs.close();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnAddWord.setFont(new Font("Arial", Font.BOLD, 11));
		btnAddWord.setBounds(390, 200, 50, 20);
		contentPane.add(btnAddWord);
		
		JButton btnRemoveWord = new JButton("OK");
		btnRemoveWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String removeWord=fieldRemoveWord.getText();
					fieldRemoveWord.setText(null);
					String query="select * from Words where Words like ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,removeWord);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="delete from Words where Words like ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,removeWord);
						pst.execute();
						pst.close();
						rs.close();
						MainWindow.Message.setText("Word sucessfully removed.");
						MainWindow.Talking("Word sucessfully removed.");
					}
					else
					{
						MainWindow.Message.setText("Word does not exist");
						MainWindow.Talking("Word does not exist");
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnRemoveWord.setFont(new Font("Arial", Font.BOLD, 11));
		btnRemoveWord.setBounds(390, 220, 50, 20);
		contentPane.add(btnRemoveWord);
		
		JButton btnAddApp = new JButton("OK");
		btnAddApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path=fieldAddApp.getText();
				fieldAddApp.setText(null);
				path=path.replace("\\","\\\\");
				String arrayApp[]=path.split("\\\\");
				int last=arrayApp.length;
				String name=arrayApp[last-1];
				try
				{
					String query="select * from Application where Path = ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,path);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="update Application set count=count+1 where Words = ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,path);
						pst.execute();
						pst.close();
						rs.close();
					}
					else
					{
						query="insert into Application values(?,?,?)";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1, path);
						pst.setString(2, name);
						pst.setInt(3, 1);
						pst.execute();
						pst.close();
						rs.close();
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnAddApp.setFont(new Font("Arial", Font.BOLD, 11));
		btnAddApp.setBounds(390, 240, 50, 20);
		contentPane.add(btnAddApp);
		
		JButton btnRemoveApp = new JButton("OK");
		btnRemoveApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path=fieldRemoveApp.getText();
				fieldRemoveApp.setText(null);
				path=path.replace("\\","\\\\");
				String arrayApp[]=path.split("\\\\");
				int last=arrayApp.length;
				String name=arrayApp[last-1];
				try
				{
					String query="select * from Application where Path = ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,path);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="delete from Application where Path like ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,path);
						pst.execute();
						pst.close();
						rs.close();
						MainWindow.Message.setText("Application sucessfully removed.");
						MainWindow.Talking("Application sucessfully removed.");
					}
					else
					{
						MainWindow.Message.setText("Application does not exist");
						MainWindow.Talking("Application does not exist.");
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnRemoveApp.setFont(new Font("Arial", Font.BOLD, 11));
		btnRemoveApp.setBounds(390, 260, 50, 20);
		contentPane.add(btnRemoveApp);
		
		JButton btnShortCut = new JButton("OK");
		btnShortCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fieldAddShortCut.getText().equals("")||fieldShortCutName.getText().equals(""))
				{
					MainWindow.Message.setText("Both fields must have some value.");
					MainWindow.Talking(" Both field ust have some value.");
				}
				else
				{
					try
					{
						String shortcut=fieldAddShortCut.getText();
						String name=fieldShortCutName.getText();
						fieldAddShortCut.setText(null);
						fieldShortCutName.setText(null);
						String query="select * from Shortcut where Shortcut = ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,shortcut);
						ResultSet rs=pst.executeQuery();
						if(rs.next())
						{
							query="update Shortcut set count=count+1 where Shortcut = ?";
							pst=MainWindow.conn.prepareStatement(query);
							pst.setString(1,shortcut);
							pst.execute();
							pst.close();
							rs.close();
						}
						else
						{
							query="insert into Shortcut values(?,?,?)";
							pst=MainWindow.conn.prepareStatement(query);
							pst.setString(1, shortcut);
							pst.setString(2,name);
							pst.setInt(3, 1);
							pst.execute();
							pst.close();
							rs.close();
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		btnShortCut.setFont(new Font("Arial", Font.BOLD, 11));
		btnShortCut.setBounds(390, 280, 50, 40);
		contentPane.add(btnShortCut);
		
		JButton btnRemoveShort = new JButton("OK");
		btnRemoveShort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String shortcut=fieldRemoveShortCut.getText();
				fieldRemoveShortCut.setText(null);
				try
				{
					String query="select * from Shortcut where Shortcut = ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,shortcut);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="delete from Shortcut where Shortcut like ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,shortcut);
						pst.execute();
						pst.close();
						rs.close();
						MainWindow.Message.setText("Shortcut sucessfully removed.");
						MainWindow.Talking("Shortcut sucessfully removed.");
					}
					else
					{
						MainWindow.Message.setText("Short cut does not exist");
						MainWindow.Talking("Short cut does not exist.");
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnRemoveShort.setFont(new Font("Arial", Font.BOLD, 11));
		btnRemoveShort.setBounds(390, 320, 50, 20);
		contentPane.add(btnRemoveShort);
		
		JButton btnShortCutName = new JButton("OK");
		btnShortCutName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=fieldRemoveShortCutName.getText();
				fieldRemoveShortCutName.setText(null);
				try
				{
					String query="select * from Shortcut where Name = ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,name);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="delete from Shortcut where Name like ?";
						pst=MainWindow.conn.prepareStatement(query);
						pst.setString(1,name);
						pst.execute();
						pst.close();
						rs.close();
						MainWindow.Message.setText("Shortcut sucessfully removed.");
						MainWindow.Talking("Shortcut sucessfully removed.");
					}
					else
					{
						MainWindow.Message.setText("Short cut does not exist");
						MainWindow.Talking("Short cut does not exist.");
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		});
		btnShortCutName.setFont(new Font("Arial", Font.BOLD, 11));
		btnShortCutName.setBounds(390, 340, 50, 20);
		contentPane.add(btnShortCutName);
		
		displayScannTime = new JTextField();
		displayScannTime.setFont(new Font("Times New Roman", Font.BOLD, 12));
		displayScannTime.setBounds(350, 20, 90, 20);
		contentPane.add(displayScannTime);
		displayScannTime.setColumns(10);
		
		displayMouseScannTime = new JTextField();
		displayMouseScannTime.setFont(new Font("Times New Roman", Font.BOLD, 12));
		displayMouseScannTime.setColumns(10);
		displayMouseScannTime.setBounds(350, 60, 90, 20);
		contentPane.add(displayMouseScannTime);
		
		JLabel lblScanningTime = new JLabel("Scanning Time");
		lblScanningTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblScanningTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblScanningTime.setBounds(220, 20, 130, 20);
		contentPane.add(lblScanningTime);
		
		JLabel lblMouseScanningTime = new JLabel("Mouse Scanning Time");
		lblMouseScanningTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMouseScanningTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblMouseScanningTime.setBounds(220, 60, 130, 20);
		contentPane.add(lblMouseScanningTime);
		
	}
}
