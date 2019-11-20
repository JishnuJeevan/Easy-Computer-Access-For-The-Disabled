package project;

// This is where the execution of the program starts.

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class MainWindow 
{
	public static boolean isMouseClicked=false;
	private static JFrame frmEcad;
	public static JTextField ListField;
	public static JTextField Message;
	public static JList list;
	public static boolean isTalkOpen=false;
	public static boolean isSettingsOpen=false;
	public static Connection conn=null;
	public static boolean capsOn=false;
	public static String words="";
	SettingsWindow setting=new SettingsWindow();
	public static String scannMethod;
	public static String voiceType;
	public static int scannTime;
	public static int mouseScannTime;
	public static String whichList="Letters";
	public static JButton BtnList,BtnA,BtnB,BtnC,BtnD,BtnE,BtnF,BtnG,BtnH,BtnI,BtnJ,BtnK,BtnL,BtnM,BtnN,BtnO,BtnP,BtnQ,BtnR,BtnS,BtnT,BtnU,BtnV,BtnW,BtnX,BtnY,BtnZ;
	public static JButton BtnNumber,BtnSpace,BtnBackspace,BtnTalk,BtnOCTalk,BtnMouse,BtnShortCut,BtnNavigation,BtnOtherButton,BtnSS1,BtnSS2,BtnSS3,BtnOpenApp,BtnSwitchApp,BtnSetting;
	public static boolean listvalue;
	// Same as MouseGrab class i.e. for taking screen shot
	public static Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	public static BufferedImage capture;
	static ScreenCapture frame1 = new ScreenCapture();	// object for MouseGrab 
	public static Graphics g;
	public static boolean ans=false;
	public static boolean a=false;
	public static boolean b=false;
	public static boolean c=false;
	public static boolean sel=false;
	public static int x=0;
	public static int y=0;
	public static int x1=0,x2=0;
	public static int y1=0,y2=0;	
	public static int rowMouse=0;	// row is the other name to represent y coordinate pixel
	public static int coloumnMouse=0;	// column is the other name to represent x coordinate pixel
	public static int count=0;		
	public static JLabel canvas;	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmEcad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean select() // An action listener that listens for mouse click and returns the value as true
	{
		
		ScreenCapture.contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1)
				ans=true;			
		}});
		return ans;
	}
	
	public static boolean selectButton() // An action listener that listens for mouse click and returns the value as true
	{
		
		frmEcad.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1)
				ans=true;			
		}});
		return ans;
	}
	
	// This is used to place the mouse cursor over the application.
	// This is because the application can only listen to mouse click, if it is on the application window.
	// If it is away from the application window it won't be able to listen to mouse actions.
	public static void initializeMouse()
	{
		try 
		{
			Robot r=new Robot();
			int x,y;
			x=width-430+50;
			y=70;
			r.mouseMove(x, y);
		} catch (AWTException e) {	e.printStackTrace();	}		
	}
	
	/********************Function to talk***************************************************************************/
	public static void Talking(String sentence)
	{
		TextToSpeech tts=new TextToSpeech();
		//Enable Voice 
		tts.getAvailableVoices().stream().forEach(voice -> System.out.println("Voice: " + voice));
		tts.setVoice(voiceType);
		tts.speak(sentence, 1.5f, false, false);
	}
	/******************************************************************************************************************/
	
	/**********************Loading System configuration***************************************************************/
	public static void loadSystemConfig()
	{
		try
		{
			java.sql.PreparedStatement pst=conn.prepareStatement("select * from SystemConfiguration");
			ResultSet result=pst.executeQuery();
			scannMethod=result.getString("ScannMethod");
			scannTime=result.getInt("ScannTime");
			mouseScannTime=result.getInt("MouseScannTime");
			voiceType=result.getString("VoiceType");
			System.out.println(scannMethod);
			System.out.println(scannTime);
			System.out.println(mouseScannTime);
			System.out.println(voiceType);
		}
		catch (SQLException e) {e.printStackTrace();}		
	}
	/*****************************************************************************************************************/
	
	/***************************Function to perform each list action ie every button that uses the list
	 * @throws AWTException ****************************************************/
	
		//--------------------------For Letters------------------------------------------------------------------------
		public static void handleLetters(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			String pressWord=selectedValue;
			boolean flagCaps=false;
			
			if(capsOn==true)
			{
				r.keyPress(KeyEvent.VK_CAPS_LOCK);	
				r.keyRelease(KeyEvent.VK_CAPS_LOCK);
				flagCaps=true;
				/* We need the word selected from the list to be printed the exact way. 
				 * So of caps lock is on the word selected from list will be printed in opposite case.
				 * So we temporarily turn off caps lock
				 */
			}
			
			///////////Deleting the part of the word that is typed///////////////////////
			while(words.length()>0)
			{
				StringBuilder strb=new StringBuilder(words);
				strb.deleteCharAt(words.length()-1);
				words=strb.toString();
				r.keyPress(KeyEvent.VK_BACK_SPACE);
				r.keyRelease(KeyEvent.VK_BACK_SPACE);
			}
			words="";
			////////////////////////////////////////////////////////////////////////////
			
			for(int i=0;i<pressWord.length();++i)
			{
				r.delay(20);
				//++++++++++++++++ CAPITAL LETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
				if(pressWord.charAt(i)=='A')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_A);	
					r.keyRelease(KeyEvent.VK_A);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"A";
				}
				else if(pressWord.charAt(i)=='B')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_B);	
					r.keyRelease(KeyEvent.VK_B);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"B";
				}
				else if(pressWord.charAt(i)=='C')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_C);	
					r.keyRelease(KeyEvent.VK_C);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"C";
				}
				else if(pressWord.charAt(i)=='D')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_D);	
					r.keyRelease(KeyEvent.VK_D);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"D";
				}
				else if(pressWord.charAt(i)=='E')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_E);	
					r.keyRelease(KeyEvent.VK_E);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"E";
				}
				else if(pressWord.charAt(i)=='F')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_F);	
					r.keyRelease(KeyEvent.VK_F);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"F";
				}
				else if(pressWord.charAt(i)=='G')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_G);	
					r.keyRelease(KeyEvent.VK_G);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"G";
				}
				else if(pressWord.charAt(i)=='H')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_H);	
					r.keyRelease(KeyEvent.VK_H);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"H";
				}
				else if(pressWord.charAt(i)=='I')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_I);	
					r.keyRelease(KeyEvent.VK_I);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"I";
				}
				else if(pressWord.charAt(i)=='J')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_J);	
					r.keyRelease(KeyEvent.VK_J);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"J";
				}
				else if(pressWord.charAt(i)=='K')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_K);	
					r.keyRelease(KeyEvent.VK_K);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"K";
				}
				else if(pressWord.charAt(i)=='L')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_L);	
					r.keyRelease(KeyEvent.VK_L);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"L";
				}
				else if(pressWord.charAt(i)=='M')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_M);	
					r.keyRelease(KeyEvent.VK_M);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"M";
				}
				else if(pressWord.charAt(i)=='N')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_N);	
					r.keyRelease(KeyEvent.VK_N);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"N";
				}
				else if(pressWord.charAt(i)=='O')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_O);	
					r.keyRelease(KeyEvent.VK_O);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"O";
				}
				else if(pressWord.charAt(i)=='P')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_P);	
					r.keyRelease(KeyEvent.VK_P);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"P";
				}
				else if(pressWord.charAt(i)=='Q')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_Q);	
					r.keyRelease(KeyEvent.VK_Q);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"Q";
				}
				else if(pressWord.charAt(i)=='R')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_R);	
					r.keyRelease(KeyEvent.VK_R);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"R";
				}
				else if(pressWord.charAt(i)=='S')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_S);	
					r.keyRelease(KeyEvent.VK_S);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"S";
				}
				else if(pressWord.charAt(i)=='T')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_T);	
					r.keyRelease(KeyEvent.VK_T);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"T";
				}
				else if(pressWord.charAt(i)=='U')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_U);	
					r.keyRelease(KeyEvent.VK_U);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"U";
				}
				else if(pressWord.charAt(i)=='V')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_V);	
					r.keyRelease(KeyEvent.VK_V);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"V";
				}
				else if(pressWord.charAt(i)=='W')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_W);	
					r.keyRelease(KeyEvent.VK_W);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"W";
				}
				else if(pressWord.charAt(i)=='X')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_X);	
					r.keyRelease(KeyEvent.VK_X);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"X";
				}
				else if(pressWord.charAt(i)=='Y')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_Y);	
					r.keyRelease(KeyEvent.VK_Y);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"Y";
				}
				else if(pressWord.charAt(i)=='Z')
				{	
					r.keyPress(KeyEvent.VK_SHIFT);	
					r.keyPress(KeyEvent.VK_Z);	
					r.keyRelease(KeyEvent.VK_Z);	
					r.keyRelease(KeyEvent.VK_SHIFT);
					words=words+"Z";
				}
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
				
				//----------- small letters ------------------------------------------------------------------------//
				else if(pressWord.charAt(i)=='a')
				{
					r.keyPress(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_A);
					words=words+"a";
				}
				else if(pressWord.charAt(i)=='b')
				{
					r.keyPress(KeyEvent.VK_B);
					r.keyRelease(KeyEvent.VK_B);
					words=words+"b";
				}
				else if(pressWord.charAt(i)=='c')
				{
					r.keyPress(KeyEvent.VK_C);
					r.keyRelease(KeyEvent.VK_C);
					words=words+"c";
				}
				else if(pressWord.charAt(i)=='d')
				{
					r.keyPress(KeyEvent.VK_D);
					r.keyRelease(KeyEvent.VK_D);
					words=words+"d";
				}
				else if(pressWord.charAt(i)=='e')
				{
					r.keyPress(KeyEvent.VK_E);
					r.keyRelease(KeyEvent.VK_E);
					words=words+"e";
				}
				else if(pressWord.charAt(i)=='f')
				{
					r.keyPress(KeyEvent.VK_F);
					r.keyRelease(KeyEvent.VK_F);
					words=words+"f";
				}
				else if(pressWord.charAt(i)=='g')
				{
					r.keyPress(KeyEvent.VK_G);
					r.keyRelease(KeyEvent.VK_G);
					words=words+"g";
				}
				else if(pressWord.charAt(i)=='h')
				{
					r.keyPress(KeyEvent.VK_H);
					r.keyRelease(KeyEvent.VK_H);
					words=words+"h";
				}
				else if(pressWord.charAt(i)=='i')
				{
					r.keyPress(KeyEvent.VK_I);
					r.keyRelease(KeyEvent.VK_I);
					words=words+"i";
				}
				else if(pressWord.charAt(i)=='j')
				{
					r.keyPress(KeyEvent.VK_J);
					r.keyRelease(KeyEvent.VK_J);
					words=words+"j";
				}
				else if(pressWord.charAt(i)=='k')
				{
					r.keyPress(KeyEvent.VK_K);
					r.keyRelease(KeyEvent.VK_K);
					words=words+"k";
				}
				else if(pressWord.charAt(i)=='l')
				{
					r.keyPress(KeyEvent.VK_L);
					r.keyRelease(KeyEvent.VK_L);
					words=words+"l";
				}
				else if(pressWord.charAt(i)=='m')
				{
					r.keyPress(KeyEvent.VK_M);
					r.keyRelease(KeyEvent.VK_M);
					words=words+"m";
				}
				else if(pressWord.charAt(i)=='n')
				{
					r.keyPress(KeyEvent.VK_N);
					r.keyRelease(KeyEvent.VK_N);
					words=words+"n";
				}
				else if(pressWord.charAt(i)=='o')
				{
					r.keyPress(KeyEvent.VK_O);
					r.keyRelease(KeyEvent.VK_O);
					words=words+"o";
				}
				else if(pressWord.charAt(i)=='p')
				{
					r.keyPress(KeyEvent.VK_P);
					r.keyRelease(KeyEvent.VK_P);
					words=words+"p";
				}
				else if(pressWord.charAt(i)=='q')
				{
					r.keyPress(KeyEvent.VK_Q);
					r.keyRelease(KeyEvent.VK_Q);
					words=words+"q";
				}
				else if(pressWord.charAt(i)=='r')
				{
					r.keyPress(KeyEvent.VK_R);
					r.keyRelease(KeyEvent.VK_R);
					words=words+"r";
				}
				else if(pressWord.charAt(i)=='s')
				{
					r.keyPress(KeyEvent.VK_S);
					r.keyRelease(KeyEvent.VK_S);
					words=words+"s";
				}
				else if(pressWord.charAt(i)=='t')
				{
					r.keyPress(KeyEvent.VK_T);
					r.keyRelease(KeyEvent.VK_T);
					words=words+"t";
				}
				else if(pressWord.charAt(i)=='u')
				{
					r.keyPress(KeyEvent.VK_U);
					r.keyRelease(KeyEvent.VK_U);
					words=words+"u";
				}
				else if(pressWord.charAt(i)=='v')
				{
					r.keyPress(KeyEvent.VK_V);
					r.keyRelease(KeyEvent.VK_V);
					words=words+"v";
				}
				else if(pressWord.charAt(i)=='w')
				{
					r.keyPress(KeyEvent.VK_W);
					r.keyRelease(KeyEvent.VK_W);
					words=words+"w";
				}
				else if(pressWord.charAt(i)=='x')
				{
					r.keyPress(KeyEvent.VK_X);
					r.keyRelease(KeyEvent.VK_X);
					words=words+"x";
				}
				else if(pressWord.charAt(i)=='y')
				{
					r.keyPress(KeyEvent.VK_Y);
					r.keyRelease(KeyEvent.VK_Y);
					words=words+"y";
				}
				else if(pressWord.charAt(i)=='z')
				{
					r.keyPress(KeyEvent.VK_Z);
					r.keyRelease(KeyEvent.VK_Z);
					words=words+"z";
				}
			}// For
			//-------------------------------------------------------------------------------------------------//
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);
				
			if(words.length()>=3)
			{
				try
				{
					String query="select * from Words where Words = ?";
					java.sql.PreparedStatement pst= conn.prepareStatement(query);
					pst.setString(1,words);
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						query="update Words set count=count+1 where words = ?";
						pst=conn.prepareStatement(query);
						pst.setString(1,words);
						pst.execute();
						//pst.close();
						rs.close();
					}
					else
					{
						query="insert into Words values(?,?)";
						pst=conn.prepareStatement(query);
						pst.setString(1, words);
						pst.setInt(2, 1);
						pst.execute();
						//pst.close();
						rs.close();
					}
				}
				catch(Exception espace1)
				{
					espace1.printStackTrace();					
				}
			}
			words="";
			if(flagCaps==true)
			{
				flagCaps=false;
				r.keyPress(KeyEvent.VK_CAPS_LOCK);
				r.keyRelease(KeyEvent.VK_CAPS_LOCK);
			}
		}
		//-------------------------------------------------------------------------------------------------------------
		
		//--------------------------For Numbers------------------------------------------------------------------------
		public static void handleNumbers(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" 0 "))
			{
				r.keyPress(KeyEvent.VK_0);
				r.keyRelease(KeyEvent.VK_0);
			}
			else if(selectedValue.equals(" 1 "))
			{
				r.keyPress(KeyEvent.VK_1);
				r.keyRelease(KeyEvent.VK_1);
			}
			else if(selectedValue.equals(" 2 "))
			{
				r.keyPress(KeyEvent.VK_2);
				r.keyRelease(KeyEvent.VK_2);
			}
			
			else if(selectedValue.equals(" 3 "))
			{
				r.keyPress(KeyEvent.VK_3);
				r.keyRelease(KeyEvent.VK_3);
			}
			else if(selectedValue.equals(" 4 "))
			{
				r.keyPress(KeyEvent.VK_4);
				r.keyRelease(KeyEvent.VK_4);
			}
			else if(selectedValue.equals(" 5 "))
			{
				r.keyPress(KeyEvent.VK_5);
				r.keyRelease(KeyEvent.VK_5);
			}
			else if(selectedValue.equals(" 6 "))
			{
				r.keyPress(KeyEvent.VK_6);
				r.keyRelease(KeyEvent.VK_6);
			}
			else if(selectedValue.equals(" 7 "))
			{
				r.keyPress(KeyEvent.VK_7);
				r.keyRelease(KeyEvent.VK_7);
			}
			else if(selectedValue.equals(" 8 "))
			{
				r.keyPress(KeyEvent.VK_8);
				r.keyRelease(KeyEvent.VK_8);
			}
			else if(selectedValue.equals(" 9 "))
			{
				r.keyPress(KeyEvent.VK_9);
				r.keyRelease(KeyEvent.VK_9);
			}
		}
		//-------------------------------------------------------------------------------------------------------------
	
		//-------------------------For MouseClick----------------------------------------------------------------------
		public static void handleMouseClick(String selectedValue)
		{
			isMouseClicked=true;
			frmEcad.setVisible(false);
			if(selectedValue.equals(" Left ")||selectedValue.equals(" Left Double Click ")||selectedValue.equals(" Hover ")||selectedValue.equals(" Right "))
			{
				try 
				{
					frame1.setVisible(true);
					capture = new Robot().createScreenCapture(screenRect);
					g=ScreenCapture.contentPane.getGraphics();
					g.setColor(Color.RED);
					Robot r=new Robot();
					r.mouseMove(width/2, height/2);
					
				} catch (AWTException e) {e.printStackTrace();	}
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
				@Override public void run() 
				{
					a=select(); 
					if(a==true)
					{
						selection(selectedValue);
						if(sel==true)
						{
							sel=false;
							timer.cancel();
							timer.purge();
						}
					}
					else highlighting();
					}} ,1000,mouseScannTime);
			}
			else if(selectedValue.equals(" Drag "))
			{
				try 
				{
					frame1.setVisible(true);
					capture = new Robot().createScreenCapture(screenRect);
					g=ScreenCapture.contentPane.getGraphics();
					g.setColor(Color.RED);
					Robot r=new Robot();
					r.mouseMove(width/2, height/2);					
				} catch (AWTException e) {e.printStackTrace();	}
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
				@Override public void run() 
				{
					a=select(); 
					if(a==true)
					{
						sel();
						if(sel==true)
						{
							sel=false;
							timer.cancel();
							timer.purge();
						}
					}
					else highlighting();
					}} ,1000,mouseScannTime);
			}
			else if(selectedValue.equals(" Right + Left ")||selectedValue.equals(" Left + Left "))
			{
				try 
				{
					frame1.setVisible(true);
					capture = new Robot().createScreenCapture(screenRect);
					g=ScreenCapture.contentPane.getGraphics();
					g.setColor(Color.RED);
					Robot r=new Robot();
					r.mouseMove(width/2, height/2);					
				} catch (AWTException e) {e.printStackTrace();	}
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
				@Override public void run() 
				{
					a=select(); 
					if(a==true)
					{
						selectit(selectedValue);
						if(sel==true)
						{
							sel=false;
							timer.cancel();
							timer.purge();
						}
					}
					else highlighting();
					}} ,1000,mouseScannTime);
			}
		}
		//-------------------------------------------------------------------------------------------------------------
	
		//--------------------------For Shortcuts----------------------------------------------------------------------
		// Take the shortcut of copy - Ctrl + c
		// If we look closer we see that we will first press Ctrl then press c
		// After that we will release c and release Ctrl i.e. go in reverse direction.
		// So for a command like "Ctrl+c" we 
		// We use a for loop to go forward and press Ctrl and then C
		// We then use another for loop to go in reverse and release c then Ctrl.
		public static void handleShortcuts(String selectedValue) throws AWTException
		{
			String query="select * from Shortcut where Name = ?";
			String query2="update Shortcut set count = count +1 where Name = ?";
			String keyName="";
			try 
			{
				java.sql.PreparedStatement pst= conn.prepareStatement(query);
				pst.setString(1,selectedValue);
				ResultSet rs=pst.executeQuery();
				keyName=rs.getString("Shortcut");
			} catch (SQLException e) {e.printStackTrace();}
			try 
			{
				java.sql.PreparedStatement pst= conn.prepareStatement(query2);
				pst.setString(1,selectedValue);
				pst.execute();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			String Keys[]=keyName.split(" ");
			for(int i=0;i<Keys.length;++i)
				System.out.println(Keys[i]);
			Robot r=new Robot();
			for(int i=0;i<Keys.length;++i)
			{
				r.delay(20);
				//------------- Key Pressing -------------------------------------------------------------------------------//
				
				//............... Letter A-Z.................................................................................//
				if(Keys[i].equals("A")||Keys[i].equals("a")){	r.keyPress(KeyEvent.VK_A);	}
				else if(Keys[i].equals("B")||Keys[i].equals("b")){	r.keyPress(KeyEvent.VK_B);	}
				else if(Keys[i].equals("C")||Keys[i].equals("c")){	r.keyPress(KeyEvent.VK_C);	}
				else if(Keys[i].equals("D")||Keys[i].equals("d")){	r.keyPress(KeyEvent.VK_D);	}
				else if(Keys[i].equals("E")||Keys[i].equals("e")){	r.keyPress(KeyEvent.VK_E);	}
				else if(Keys[i].equals("F")||Keys[i].equals("f")){	r.keyPress(KeyEvent.VK_F);	}
				else if(Keys[i].equals("G")||Keys[i].equals("g")){	r.keyPress(KeyEvent.VK_G);	}
				else if(Keys[i].equals("H")||Keys[i].equals("h")){	r.keyPress(KeyEvent.VK_H);	}
				else if(Keys[i].equals("I")||Keys[i].equals("i")){	r.keyPress(KeyEvent.VK_I);	}
				else if(Keys[i].equals("J")||Keys[i].equals("j")){	r.keyPress(KeyEvent.VK_J);	}
				else if(Keys[i].equals("K")||Keys[i].equals("k")){	r.keyPress(KeyEvent.VK_K);	}
				else if(Keys[i].equals("L")||Keys[i].equals("l")){	r.keyPress(KeyEvent.VK_L);	}
				else if(Keys[i].equals("M")||Keys[i].equals("m")){	r.keyPress(KeyEvent.VK_M);	}
				else if(Keys[i].equals("N")||Keys[i].equals("n")){	r.keyPress(KeyEvent.VK_N);	}
				else if(Keys[i].equals("O")||Keys[i].equals("o")){	r.keyPress(KeyEvent.VK_O);	}
				else if(Keys[i].equals("P")||Keys[i].equals("p")){	r.keyPress(KeyEvent.VK_P);	}
				else if(Keys[i].equals("Q")||Keys[i].equals("q")){	r.keyPress(KeyEvent.VK_Q);	}
				else if(Keys[i].equals("R")||Keys[i].equals("r")){	r.keyPress(KeyEvent.VK_R);	}
				else if(Keys[i].equals("S")||Keys[i].equals("s")){	r.keyPress(KeyEvent.VK_S);	}
				else if(Keys[i].equals("T")||Keys[i].equals("t")){	r.keyPress(KeyEvent.VK_T);	}
				else if(Keys[i].equals("U")||Keys[i].equals("u")){	r.keyPress(KeyEvent.VK_U);	}
				else if(Keys[i].equals("V")||Keys[i].equals("v")){	r.keyPress(KeyEvent.VK_V);	}
				else if(Keys[i].equals("W")||Keys[i].equals("w")){	r.keyPress(KeyEvent.VK_W);	}
				else if(Keys[i].equals("X")||Keys[i].equals("x")){	r.keyPress(KeyEvent.VK_X);	}
				else if(Keys[i].equals("Y")||Keys[i].equals("y")){	r.keyPress(KeyEvent.VK_Y);	}
				else if(Keys[i].equals("Z")||Keys[i].equals("z")){	r.keyPress(KeyEvent.VK_Z);	}				
				//............................................................................................................//
				
				//...................... Numbers...............................................................................//
				else if(Keys[i].equals("0")){	r.keyPress(KeyEvent.VK_0);	}
				else if(Keys[i].equals("1")){	r.keyPress(KeyEvent.VK_1);	}
				else if(Keys[i].equals("2")){	r.keyPress(KeyEvent.VK_2);	}
				else if(Keys[i].equals("3")){	r.keyPress(KeyEvent.VK_3);	}
				else if(Keys[i].equals("4")){	r.keyPress(KeyEvent.VK_4);	}
				else if(Keys[i].equals("5")){	r.keyPress(KeyEvent.VK_5);	}
				else if(Keys[i].equals("6")){	r.keyPress(KeyEvent.VK_6);	}
				else if(Keys[i].equals("7")){	r.keyPress(KeyEvent.VK_7);	}
				else if(Keys[i].equals("8")){	r.keyPress(KeyEvent.VK_8);	}
				else if(Keys[i].equals("9")){	r.keyPress(KeyEvent.VK_9);	}				
				//.............................................................................................................//
				
				//.....................Function keys...........................................................................//
				else if(Keys[i].equals("F1")){	r.keyPress(KeyEvent.VK_F1);	}
				else if(Keys[i].equals("F2")){	r.keyPress(KeyEvent.VK_F2);	}
				else if(Keys[i].equals("F3")){	r.keyPress(KeyEvent.VK_F3);	}
				else if(Keys[i].equals("F4")){	r.keyPress(KeyEvent.VK_F4);	}
				else if(Keys[i].equals("F5")){	r.keyPress(KeyEvent.VK_F5);	}
				else if(Keys[i].equals("F6")){	r.keyPress(KeyEvent.VK_F6);	}
				else if(Keys[i].equals("F7")){	r.keyPress(KeyEvent.VK_F7);	}
				else if(Keys[i].equals("F8")){	r.keyPress(KeyEvent.VK_F8);	}
				else if(Keys[i].equals("F9")){	r.keyPress(KeyEvent.VK_F9);	}
				else if(Keys[i].equals("F10")){	r.keyPress(KeyEvent.VK_F10);	}		
				else if(Keys[i].equals("F11")){	r.keyPress(KeyEvent.VK_F11);	}
				else if(Keys[i].equals("F12")){	r.keyPress(KeyEvent.VK_F12);	}		
				//.............................................................................................................//
				
				//.....................Symbols keys...........................................................................//
				else if(Keys[i].equals("(")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_9);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(")")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_0);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("<")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_COMMA);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(">")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_PERIOD);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("[")){	r.keyPress(KeyEvent.VK_OPEN_BRACKET);	}
				else if(Keys[i].equals("]")){	r.keyPress(KeyEvent.VK_CLOSE_BRACKET);	}
				else if(Keys[i].equals("{")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_OPEN_BRACKET);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("}")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_CLOSE_BRACKET);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("\"")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_QUOTE);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("\'")){	r.keyPress(KeyEvent.VK_QUOTE);	}
				
				else if(Keys[i].equals("+")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_EQUALS);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("-")){	r.keyPress(KeyEvent.VK_MINUS);	}		
				else if(Keys[i].equals("*")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_8);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("/")){	r.keyPress(KeyEvent.VK_SLASH);	}
				else if(Keys[i].equals("%")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_5);	r.keyRelease(KeyEvent.VK_SHIFT);	}		
				else if(Keys[i].equals("^")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_6);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("=")){	r.keyPress(KeyEvent.VK_EQUALS);	}
				else if(Keys[i].equals("\\")){	r.keyPress(KeyEvent.VK_BACK_SLASH);	}
				else if(Keys[i].equals("`")){	r.keyPress(192);	}
				else if(Keys[i].equals("~")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(192);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				
				else if(Keys[i].equals(":")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_SEMICOLON);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(";")){	r.keyPress(KeyEvent.VK_SEMICOLON);	}
				else if(Keys[i].equals("!")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_1);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("@")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_2);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("#")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_3);	r.keyRelease(KeyEvent.VK_SHIFT);	}		
				else if(Keys[i].equals("$")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_4);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("&")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_7);	r.keyRelease(KeyEvent.VK_SHIFT);	}		
				else if(Keys[i].equals("_")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_MINUS);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(".")){	r.keyPress(KeyEvent.VK_PERIOD);		}
				else if(Keys[i].equals(",")){	r.keyPress(KeyEvent.VK_COMMA);		}
				else if(Keys[i].equals("?")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_SLASH);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("!")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyPress(KeyEvent.VK_BACK_SLASH);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				//.............................................................................................................//
				
				//............ Navigations................................................................................
				else if(Keys[i].equals("Up")){	r.keyPress(KeyEvent.VK_UP);	}
				else if(Keys[i].equals("Down")){	r.keyPress(KeyEvent.VK_DOWN);	}
				else if(Keys[i].equals("Left")){	r.keyPress(KeyEvent.VK_LEFT);	}
				else if(Keys[i].equals("Right")){	r.keyPress(KeyEvent.VK_RIGHT);	}
				else if(Keys[i].equals("PageUp")){	r.keyPress(KeyEvent.VK_PAGE_UP);	}
				else if(Keys[i].equals("PageDown")){	r.keyPress(KeyEvent.VK_PAGE_DOWN);	}
				else if(Keys[i].equals("Home")){	r.keyPress(KeyEvent.VK_HOME);	}
				else if(Keys[i].equals("End")){	r.keyPress(KeyEvent.VK_END);	}
				else if(Keys[i].equals("Windows")){	r.keyPress(KeyEvent.VK_WINDOWS);	}
				//.......................................................................................................
				
				//............ Other Buttons................................................................................
				else if(Keys[i].equals("CapsLk")){	r.keyPress(KeyEvent.VK_CAPS_LOCK);	}
				else if(Keys[i].equals("Enter")){	r.keyPress(KeyEvent.VK_ENTER);	}
				else if(Keys[i].equals("Tab")){	r.keyPress(KeyEvent.VK_TAB);	}
				else if(Keys[i].equals("Ctrl")){	r.keyPress(KeyEvent.VK_CONTROL);	}
				else if(Keys[i].equals("Alt")){	r.keyPress(KeyEvent.VK_ALT);	}
				else if(Keys[i].equals("Delete")){	r.keyPress(KeyEvent.VK_DELETE);	}
				else if(Keys[i].equals("Insert")){	r.keyPress(KeyEvent.VK_INSERT);	}
				else if(Keys[i].equals("Esc")){	r.keyPress(KeyEvent.VK_ESCAPE);	}
				else if(Keys[i].equals("Shift")){	r.keyPress(KeyEvent.VK_SHIFT);	}
				//.......................................................................................................
			}
			
			for(int i=Keys.length-1;i>=0;--i)
			{
				r.delay(20);
				//------------- Key Releasing -------------------------------------------------------------------------------//
				
				//............... Letter A-Z.................................................................................//
				if(Keys[i].equals("A")||Keys[i].equals("a")){	r.keyRelease(KeyEvent.VK_A);	}
				else if(Keys[i].equals("B")||Keys[i].equals("b")){	r.keyRelease(KeyEvent.VK_B);	}
				else if(Keys[i].equals("C")||Keys[i].equals("c")){	r.keyRelease(KeyEvent.VK_C);	}
				else if(Keys[i].equals("D")||Keys[i].equals("d")){	r.keyRelease(KeyEvent.VK_D);	}
				else if(Keys[i].equals("E")||Keys[i].equals("e")){	r.keyRelease(KeyEvent.VK_E);	}
				else if(Keys[i].equals("F")||Keys[i].equals("f")){	r.keyRelease(KeyEvent.VK_F);	}
				else if(Keys[i].equals("G")||Keys[i].equals("g")){	r.keyRelease(KeyEvent.VK_G);	}
				else if(Keys[i].equals("H")||Keys[i].equals("h")){	r.keyRelease(KeyEvent.VK_H);	}
				else if(Keys[i].equals("I")||Keys[i].equals("i")){	r.keyRelease(KeyEvent.VK_I);	}
				else if(Keys[i].equals("J")||Keys[i].equals("j")){	r.keyRelease(KeyEvent.VK_J);	}
				else if(Keys[i].equals("K")||Keys[i].equals("k")){	r.keyRelease(KeyEvent.VK_K);	}
				else if(Keys[i].equals("L")||Keys[i].equals("l")){	r.keyRelease(KeyEvent.VK_L);	}
				else if(Keys[i].equals("M")||Keys[i].equals("m")){	r.keyRelease(KeyEvent.VK_M);	}
				else if(Keys[i].equals("N")||Keys[i].equals("n")){	r.keyRelease(KeyEvent.VK_N);	}
				else if(Keys[i].equals("O")||Keys[i].equals("o")){	r.keyRelease(KeyEvent.VK_O);	}
				else if(Keys[i].equals("P")||Keys[i].equals("p")){	r.keyRelease(KeyEvent.VK_P);	}
				else if(Keys[i].equals("Q")||Keys[i].equals("q")){	r.keyRelease(KeyEvent.VK_Q);	}
				else if(Keys[i].equals("R")||Keys[i].equals("r")){	r.keyRelease(KeyEvent.VK_R);	}
				else if(Keys[i].equals("S")||Keys[i].equals("s")){	r.keyRelease(KeyEvent.VK_S);	}
				else if(Keys[i].equals("T")||Keys[i].equals("t")){	r.keyRelease(KeyEvent.VK_T);	}
				else if(Keys[i].equals("U")||Keys[i].equals("u")){	r.keyRelease(KeyEvent.VK_U);	}
				else if(Keys[i].equals("V")||Keys[i].equals("v")){	r.keyRelease(KeyEvent.VK_V);	}
				else if(Keys[i].equals("W")||Keys[i].equals("w")){	r.keyRelease(KeyEvent.VK_W);	}
				else if(Keys[i].equals("X")||Keys[i].equals("x")){	r.keyRelease(KeyEvent.VK_X);	}
				else if(Keys[i].equals("Y")||Keys[i].equals("y")){	r.keyRelease(KeyEvent.VK_Y);	}
				else if(Keys[i].equals("Z")||Keys[i].equals("z")){	r.keyRelease(KeyEvent.VK_Z);	}				
				//............................................................................................................//
				
				//...................... Numbers...............................................................................//
				else if(Keys[i].equals("0")){	r.keyRelease(KeyEvent.VK_0);	}
				else if(Keys[i].equals("1")){	r.keyRelease(KeyEvent.VK_1);	}
				else if(Keys[i].equals("2")){	r.keyRelease(KeyEvent.VK_2);	}
				else if(Keys[i].equals("3")){	r.keyRelease(KeyEvent.VK_3);	}
				else if(Keys[i].equals("4")){	r.keyRelease(KeyEvent.VK_4);	}
				else if(Keys[i].equals("5")){	r.keyRelease(KeyEvent.VK_5);	}
				else if(Keys[i].equals("6")){	r.keyRelease(KeyEvent.VK_6);	}
				else if(Keys[i].equals("7")){	r.keyRelease(KeyEvent.VK_7);	}
				else if(Keys[i].equals("8")){	r.keyRelease(KeyEvent.VK_8);	}
				else if(Keys[i].equals("9")){	r.keyRelease(KeyEvent.VK_9);	}				
				//.............................................................................................................//
				
				//.....................Function keys...........................................................................//
				else if(Keys[i].equals("F1")){	r.keyRelease(KeyEvent.VK_F1);	}
				else if(Keys[i].equals("F2")){	r.keyRelease(KeyEvent.VK_F2);	}
				else if(Keys[i].equals("F3")){	r.keyRelease(KeyEvent.VK_F3);	}
				else if(Keys[i].equals("F4")){	r.keyRelease(KeyEvent.VK_F4);	}
				else if(Keys[i].equals("F5")){	r.keyRelease(KeyEvent.VK_F5);	}
				else if(Keys[i].equals("F6")){	r.keyRelease(KeyEvent.VK_F6);	}
				else if(Keys[i].equals("F7")){	r.keyRelease(KeyEvent.VK_F7);	}
				else if(Keys[i].equals("F8")){	r.keyRelease(KeyEvent.VK_F8);	}
				else if(Keys[i].equals("F9")){	r.keyRelease(KeyEvent.VK_F9);	}
				else if(Keys[i].equals("F10")){	r.keyRelease(KeyEvent.VK_F10);	}		
				else if(Keys[i].equals("F11")){	r.keyRelease(KeyEvent.VK_F11);	}
				else if(Keys[i].equals("F12")){	r.keyRelease(KeyEvent.VK_F12);	}		
				//.............................................................................................................//
				
				//.....................Symbols keys...........................................................................//
				else if(Keys[i].equals("(")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_9);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(")")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_0);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("<")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_COMMA);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(">")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_PERIOD);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("[")){	r.keyRelease(KeyEvent.VK_OPEN_BRACKET);	}
				else if(Keys[i].equals("]")){	r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);	}
				else if(Keys[i].equals("{")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_OPEN_BRACKET);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("}")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("\"")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_QUOTE);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("\'")){	r.keyRelease(KeyEvent.VK_QUOTE);	}
				
				else if(Keys[i].equals("+")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_EQUALS);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("-")){	r.keyRelease(KeyEvent.VK_MINUS);	}		
				else if(Keys[i].equals("*")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_8);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("/")){	r.keyRelease(KeyEvent.VK_SLASH);	}
				else if(Keys[i].equals("%")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_5);	r.keyRelease(KeyEvent.VK_SHIFT);	}		
				else if(Keys[i].equals("^")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_6);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("=")){	r.keyRelease(KeyEvent.VK_EQUALS);	}
				else if(Keys[i].equals("\\")){	r.keyRelease(KeyEvent.VK_BACK_SLASH);	}
				else if(Keys[i].equals("`")){	r.keyRelease(192);	}
				else if(Keys[i].equals("~")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(192);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				
				else if(Keys[i].equals(":")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_SEMICOLON);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(";")){	r.keyRelease(KeyEvent.VK_SEMICOLON);	}
				else if(Keys[i].equals("!")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_1);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("@")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_2);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("#")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_3);	r.keyRelease(KeyEvent.VK_SHIFT);	}		
				else if(Keys[i].equals("$")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_4);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("&")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_7);	r.keyRelease(KeyEvent.VK_SHIFT);	}		
				else if(Keys[i].equals("_")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_MINUS);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals(".")){	r.keyRelease(KeyEvent.VK_PERIOD);		}
				else if(Keys[i].equals(",")){	r.keyRelease(KeyEvent.VK_COMMA);		}
				else if(Keys[i].equals("?")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_SLASH);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				else if(Keys[i].equals("!")){	r.keyPress(KeyEvent.VK_SHIFT);	r.keyRelease(KeyEvent.VK_BACK_SLASH);	r.keyRelease(KeyEvent.VK_SHIFT);	}
				//.............................................................................................................//
				
				//............ Navigations................................................................................
				else if(Keys[i].equals("Up")){	r.keyRelease(KeyEvent.VK_UP);	}
				else if(Keys[i].equals("Down")){	r.keyRelease(KeyEvent.VK_DOWN);	}
				else if(Keys[i].equals("Left")){	r.keyRelease(KeyEvent.VK_LEFT);	}
				else if(Keys[i].equals("Right")){	r.keyRelease(KeyEvent.VK_RIGHT);	}
				else if(Keys[i].equals("PageUp")){	r.keyRelease(KeyEvent.VK_PAGE_UP);	}
				else if(Keys[i].equals("PageDown")){	r.keyRelease(KeyEvent.VK_PAGE_DOWN);	}
				else if(Keys[i].equals("Home")){	r.keyRelease(KeyEvent.VK_HOME);	}
				else if(Keys[i].equals("End")){	r.keyRelease(KeyEvent.VK_END);	}
				else if(Keys[i].equals("Windows")){	r.keyRelease(KeyEvent.VK_WINDOWS);	}
				//.......................................................................................................
				
				//............ Other Buttons................................................................................
				else if(Keys[i].equals("CapsLk")){	r.keyRelease(KeyEvent.VK_CAPS_LOCK);	}
				else if(Keys[i].equals("Enter")){	r.keyRelease(KeyEvent.VK_ENTER);	}
				else if(Keys[i].equals("Tab")){	r.keyRelease(KeyEvent.VK_TAB);	}
				else if(Keys[i].equals("Ctrl")){	r.keyRelease(KeyEvent.VK_CONTROL);	}
				else if(Keys[i].equals("Alt")){	r.keyRelease(KeyEvent.VK_ALT);	}
				else if(Keys[i].equals("Delete")){	r.keyRelease(KeyEvent.VK_DELETE);	}
				else if(Keys[i].equals("Insert")){	r.keyRelease(KeyEvent.VK_INSERT);	}
				else if(Keys[i].equals("Esc")){	r.keyRelease(KeyEvent.VK_ESCAPE);	}
				else if(Keys[i].equals("Shift")){	r.keyRelease(KeyEvent.VK_SHIFT);	}
				//.......................................................................................................
			}
		}
		//------------------------------------------------------------------------------------------------------------------
		
		//--------------------------For Navigation---------------------------------------------------------------------
		// If we press Up, Down etc in the settings windows add short cut field, 
		// Instead of performing that function, it will write 'up', 'down' (whatever button we have pressed) as text in the field.
		// As in the add shortcut field, we don't want to perform any action. 
		// We just want to write the action in English like text like "ctrl+c"
		// So we disable the function of some buttons if there is a cursor in the add shortcut filed.
		public static void handleNavigation(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" Up "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Up");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Up");
				}
				else
				{
					r.keyPress(KeyEvent.VK_UP);
					r.keyRelease(KeyEvent.VK_UP);
				}
			}
			else if(selectedValue.equals(" Down "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Down");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Down");
				}
				else
				{
					r.keyPress(KeyEvent.VK_DOWN);
					r.keyRelease(KeyEvent.VK_DOWN);
				}				
			}
			else if(selectedValue.equals(" Left "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Left");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Left");
				}
				else
				{
					r.keyPress(KeyEvent.VK_LEFT);
					r.keyRelease(KeyEvent.VK_LEFT);
				}				
			}
			else if(selectedValue.equals(" Right "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Right");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Right");
				}
				else
				{
					r.keyPress(KeyEvent.VK_RIGHT);
					r.keyRelease(KeyEvent.VK_RIGHT);
				}
			}
			else if(selectedValue.equals(" Page Up "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"PageUp");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"PageUp");
				}
				else
				{
					r.keyPress(KeyEvent.VK_PAGE_UP);
					r.keyRelease(KeyEvent.VK_PAGE_UP);
				}			
			}
			else if(selectedValue.equals(" Page Down "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"PageDown");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"PageDown");
				}
				else
				{
					r.keyPress(KeyEvent.VK_PAGE_DOWN);
					r.keyRelease(KeyEvent.VK_PAGE_DOWN);
				}				
			}
			else if(selectedValue.equals(" Home "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Home");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Home");
				}
				else
				{
					r.keyPress(KeyEvent.VK_HOME);
					r.keyRelease(KeyEvent.VK_HOME);
				}				
			}
			else if(selectedValue.equals(" End "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"End");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"End");
				}
				else
				{
					r.keyPress(KeyEvent.VK_END);
					r.keyRelease(KeyEvent.VK_END);
				}				
			}
			else if(selectedValue.equals(" Windows "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Windows");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Windows");
				}
				else
				{
					r.keyPress(KeyEvent.VK_WINDOWS);
					r.keyRelease(KeyEvent.VK_WINDOWS);
				}
			}
		}
		//------------------------------------------------------------------------------------------------------------
		
		//--------------------------For OpenApp------------------------------------------------------------------------
		public static void handleOpenApp(String selectedValue) 		
		{
			try
			{
				String name=selectedValue;
				java.sql.PreparedStatement pst=MainWindow.conn.prepareStatement("select * from Application where Name = ?");
				java.sql.PreparedStatement pst2=MainWindow.conn.prepareStatement("update Application set count=count+1 where Name=?");
				pst.setString(1, name);
				pst2.setString(1, name);
				ResultSet result=pst.executeQuery();
				pst2.execute();
				String path=result.getString("Path");
				Desktop.getDesktop().open(new File(path));
			}
			catch(Exception e)
			{
				Message.setText("Application cannot be opened. Path may be incorrect.");
				Talking("Application cannot be opened. Path may be incorrect.");
			}			
		}
		//-----------------------------------------------------------------------------------------------------------------
		
		//--------------------------For SwichApp-----------------------------------------------------------------------
		public static void handleSwitchApp(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" Alt + Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<1;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 2 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<2;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 3 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<3;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 4 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<4;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 5 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<5;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 6 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<6;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 7 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<7;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 8 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<8;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 9 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<9;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 10 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<10;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 11 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<11;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
			else if(selectedValue.equals(" Alt + 12 Tab "))
			{
				r.keyPress(KeyEvent.VK_ALT);
				for(int i=0;i<12;++i)
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.delay(2000);
					r.keyRelease(KeyEvent.VK_TAB);
				}
				r.keyRelease(KeyEvent.VK_ALT);
			}
		}
		//-------------------------------------------------------------------------------------------------------------
	
		//--------------------------For SS1----------------------------------------------------------------------------
		public static void handleSS1(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" ( "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_9);
				r.keyRelease(KeyEvent.VK_9);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" ) "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_0);
				r.keyRelease(KeyEvent.VK_0);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" < "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_COMMA);
				r.keyRelease(KeyEvent.VK_COMMA);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			
			else if(selectedValue.equals(" > "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_PERIOD);
				r.keyRelease(KeyEvent.VK_PERIOD);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" [ "))
			{
				r.keyPress(KeyEvent.VK_OPEN_BRACKET);
				r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			}
			else if(selectedValue.equals(" ] "))
			{
				r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
				r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			}
			else if(selectedValue.equals(" { "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_OPEN_BRACKET);
				r.keyRelease(KeyEvent.VK_OPEN_BRACKET);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" } "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_CLOSE_BRACKET);
				r.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" \" "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_QUOTE);
				r.keyRelease(KeyEvent.VK_QUOTE);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" \' "))
			{
				r.keyPress(KeyEvent.VK_QUOTE);
				r.keyRelease(KeyEvent.VK_QUOTE);
			}
		}
		//-------------------------------------------------------------------------------------------------------------------
		
		//--------------------------For SS2----------------------------------------------------------------------------
		public static void handleSS2(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" + "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_EQUALS);
				r.keyRelease(KeyEvent.VK_EQUALS);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" - "))
			{
				r.keyPress(KeyEvent.VK_MINUS);
				r.keyRelease(KeyEvent.VK_MINUS);
			}
			else if(selectedValue.equals(" * "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_8);
				r.keyRelease(KeyEvent.VK_8);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" / "))
			{
				r.keyPress(KeyEvent.VK_SLASH);
				r.keyRelease(KeyEvent.VK_SLASH);
			}
			else if(selectedValue.equals(" % "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_5);
				r.keyRelease(KeyEvent.VK_5);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" ^ "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_6);
				r.keyRelease(KeyEvent.VK_6);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" = "))
			{
				r.keyPress(KeyEvent.VK_EQUALS);
				r.keyRelease(KeyEvent.VK_EQUALS);
			}
			else if(selectedValue.equals(" \\ "))
			{
				r.keyPress(KeyEvent.VK_BACK_SLASH);
				r.keyRelease(KeyEvent.VK_BACK_SLASH);
			}
			else if(selectedValue.equals(" ` "))
			{
				r.keyPress(192);	//key code for `
				r.keyRelease(192);
			}
			else if(selectedValue.equals(" ~ "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(192);
				r.keyRelease(192);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
		//-------------------------------------------------------------------------------------------------------------------//
		
		//--------------------------For SS3----------------------------------------------------------------------------
		public static void handleSS3(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" : "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_SEMICOLON);
				r.keyRelease(KeyEvent.VK_SEMICOLON);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" ; "))
			{
				r.keyPress(KeyEvent.VK_SEMICOLON);
				r.keyRelease(KeyEvent.VK_SEMICOLON);
			}
			else if(selectedValue.equals(" ! "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_1);
				r.keyRelease(KeyEvent.VK_1);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" @ "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_2);
				r.keyRelease(KeyEvent.VK_2);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" # "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_3);
				r.keyRelease(KeyEvent.VK_3);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" $ "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_4);
				r.keyRelease(KeyEvent.VK_4);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" & "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_7);
				r.keyRelease(KeyEvent.VK_7);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" _ "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_MINUS);
				r.keyRelease(KeyEvent.VK_MINUS);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" . "))
			{
				r.keyPress(KeyEvent.VK_PERIOD);	
				r.keyRelease(KeyEvent.VK_PERIOD);
			}
			else if(selectedValue.equals(" , "))
			{
				r.keyPress(KeyEvent.VK_COMMA);	
				r.keyRelease(KeyEvent.VK_COMMA);
			}
			else if(selectedValue.equals(" ? "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_SLASH);
				r.keyRelease(KeyEvent.VK_SLASH);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(selectedValue.equals(" | "))
			{
				r.keyPress(KeyEvent.VK_SHIFT);
				r.keyPress(KeyEvent.VK_BACK_SLASH);
				r.keyRelease(KeyEvent.VK_BACK_SLASH);
				r.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
		//-------------------------------------------------------------------------------------------------------------------//
		
		//--------------------------For Other buttons------------------------------------------------------------------
		// If we press crtl, capslk etc in the settings windows add short cut field, 
		// Instead of performing that function, it will write 'ctrl', 'CapsLk' (whatever button we have pressed) as text in the field.
		// As in the add shortcut field, we don't want to perform any action. 
		// We just want to write the action in English like text like "ctrl+c"
		// So we disable the function of some buttons if there is a cursor in the add shortcut filed.
		public static void handleOtherButton(String selectedValue) throws AWTException
		{
			Robot r=new Robot();
			if(selectedValue.equals(" CapsLk "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"CapsLk");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"CapsLk");
				}
				else
				{
					r.keyPress(KeyEvent.VK_CAPS_LOCK);
					r.keyRelease(KeyEvent.VK_CAPS_LOCK);
					if(capsOn==false)
					{
						capsOn=true;
						BtnA.setText("A");	BtnB.setText("B");	BtnC.setText("C");	BtnD.setText("D");	BtnE.setText("E");
						BtnF.setText("F");	BtnG.setText("G");	BtnH.setText("H");	BtnI.setText("I");	BtnJ.setText("J");
						BtnK.setText("K");	BtnL.setText("L");	BtnM.setText("M");	BtnN.setText("N");	BtnO.setText("O");
						BtnP.setText("P");	BtnQ.setText("Q");	BtnR.setText("R");	BtnS.setText("S");	BtnT.setText("T");
						BtnU.setText("U");	BtnV.setText("V");	BtnW.setText("W");	BtnX.setText("X");	BtnY.setText("Y");
						BtnZ.setText("Z");	
					}
					else
					{
						capsOn=false;
						BtnA.setText("a");	BtnB.setText("b");	BtnC.setText("c");	BtnD.setText("d");	BtnE.setText("e");
						BtnF.setText("f");	BtnG.setText("g");	BtnH.setText("h");	BtnI.setText("i");	BtnJ.setText("j");
						BtnK.setText("k");	BtnL.setText("l");	BtnM.setText("m");	BtnN.setText("n");	BtnO.setText("o");
						BtnP.setText("p");	BtnQ.setText("q");	BtnR.setText("r");	BtnS.setText("s");	BtnT.setText("t");
						BtnU.setText("u");	BtnV.setText("v");	BtnW.setText("w");	BtnX.setText("x");	BtnY.setText("y");
						BtnZ.setText("z");
					}
				}
			}
			else if(selectedValue.equals(" Enter "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Enter");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Enter");
				}
				else
				{
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					words="";
				}
			}
			else if(selectedValue.equals(" Tab "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Tab");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Tab");
				}
				else
				{
					r.keyPress(KeyEvent.VK_TAB);
					r.keyRelease(KeyEvent.VK_TAB);
				}
			}
			else if(selectedValue.equals(" Ctrl "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Ctrl");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Ctrl");
				}
				else
				{
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_CONTROL);
				}
			}
			else if(selectedValue.equals(" Alt "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Alt");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Alt");
				}
				else
				{
					r.keyPress(KeyEvent.VK_ALT);
					r.keyRelease(KeyEvent.VK_ALT);
				}				
			}
			else if(selectedValue.equals(" Delete "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Delete");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Delete");
				}
				else
				{
					r.keyPress(KeyEvent.VK_DELETE);
					r.keyRelease(KeyEvent.VK_DELETE);
				}				
			}
			else if(selectedValue.equals(" Insert "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Inset");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Insert");
				}
				else
				{
					r.keyPress(KeyEvent.VK_INSERT);
					r.keyRelease(KeyEvent.VK_INSERT);
				}
				
			}
			else if(selectedValue.equals(" Esc "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Esc");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Esc");
				}
				else
				{
					r.keyPress(KeyEvent.VK_ESCAPE);
					r.keyRelease(KeyEvent.VK_ESCAPE);
				}				
			}
			else if(selectedValue.equals(" Shift "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"Shift");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"Shift");
				}
				else
				{
					r.keyPress(KeyEvent.VK_SHIFT);
					r.keyRelease(KeyEvent.VK_SHIFT);
				}				
			}
			
			////////////////////For Function Keys///////////////////////////////////////////////////////////
			
			else if(selectedValue.equals(" F1 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F1");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F1");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F1);
					r.keyRelease(KeyEvent.VK_F1);
				}
			}
			else if(selectedValue.equals(" F2 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F2");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F2");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F2);
					r.keyRelease(KeyEvent.VK_F2);
				}
			}
			else if(selectedValue.equals(" F3 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F3");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F3");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F3);
					r.keyRelease(KeyEvent.VK_F3);
				}
			}
			else if(selectedValue.equals(" F4 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F4");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F4");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F4);
					r.keyRelease(KeyEvent.VK_F4);
				}
			}
			else if(selectedValue.equals(" F5 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F5");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F5");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F5);
					r.keyRelease(KeyEvent.VK_F5);
				}
			}
			else if(selectedValue.equals(" F6 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F6");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F6");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F6);
					r.keyRelease(KeyEvent.VK_F6);
				}
			}
			else if(selectedValue.equals(" F7 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F7");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F7");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F7);
					r.keyRelease(KeyEvent.VK_F7);
				}
			}
			else if(selectedValue.equals(" F8 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F8");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F8");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F8);
					r.keyRelease(KeyEvent.VK_F8);
				}
			}
			else if(selectedValue.equals(" F9 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F9");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F9");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F9);
					r.keyRelease(KeyEvent.VK_F9);
				}
			}
			else if(selectedValue.equals(" F10 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F10");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F10");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F10);
					r.keyRelease(KeyEvent.VK_F10);
				}
			}
			else if(selectedValue.equals(" F11 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F11");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F11");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F11);
					r.keyRelease(KeyEvent.VK_F11);
				}
			}
			else if(selectedValue.equals(" F12 "))
			{
				if(SettingsWindow.fieldAddShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldAddShortCut.setText(SettingsWindow.fieldAddShortCut.getText()+"F12");
				}
				else if(SettingsWindow.fieldRemoveShortCut.getCaret().isSelectionVisible())
				{
					SettingsWindow.fieldRemoveShortCut.setText(SettingsWindow.fieldRemoveShortCut.getText()+"F12");
				}
				else
				{
					r.keyPress(KeyEvent.VK_F12);
					r.keyRelease(KeyEvent.VK_F12);
				}
			}
			
			///////////////////////////////////////////////////////////////////////////////////////////////
		}
		//----------------------------------------------------------------------------------------------------------------------//
		
	/**************************************************************************************************************************/
		
	/********************************FUNCTION TO PRINT ALL WORDS IN DATABASE*******************************************************/
		public void extractAll()
		{
			try
			{
					
				DefaultListModel DLM=new DefaultListModel();
				String query="select * from Words order by count desc";
				java.sql.PreparedStatement pst=conn.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					DLM.addElement(rs.getString("Words"));
				}
				list.setModel(DLM);
				//pst.close();
				rs.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	/*****************************************************************************************************************************/
		
	/**************************FUNCTION FOR SUGGESTION*****************************************************************************/
		public static void suggest()
		{
				try
				{
						
					DefaultListModel DLM=new DefaultListModel();
					String suggestWord=words+"%";
					String query="select * from Words where Words like ? order by count desc ";
					java.sql.PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,suggestWord);
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
						DLM.addElement(rs.getString("Words"));
					}
					list.setModel(DLM);
					//pst.close();
					rs.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		}
	/******************************************************************************************************************************/
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MainWindow() throws ClassNotFoundException, SQLException {
		conn=DatabaseSQLite.getConnection();
		initialize();
		extractAll();
		loadSystemConfig();
		initializeMouse();
		Talking("Hi. I am ECAD. I will make it easier for you to use you computer.");
		if(scannMethod.equals("Switch Eye"))
		{
			scannTime=scannTime+1000;
			DaemonThread.startCamera();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEcad = new JFrame();
		frmEcad.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(scannMethod.equals("Step Mouse"))
					StepScanning.executeHighlighting(e);
				else if(scannMethod.equals("Switch Mouse")||scannMethod.equals("Switch Eye"))
				{
					if(e.getClickCount()==2)
					{
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
								@Override public void run() 
								{
									a=selectButton(); 
								if(a==true)
								{
									SwitchAcessScanning.selection();
									if(isMouseClicked==true)
									{
										isMouseClicked=false;
										timer.cancel();
										timer.purge();										
									}
									else SwitchAcessScanning.highlighting();
								}else SwitchAcessScanning.highlighting();} } ,1000,scannTime);
					}
				}
			}
		});
		frmEcad.setTitle("ECAD");
		frmEcad.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/project/ECADIcon.png")));
		frmEcad.getContentPane().setFont(new Font("Arial", Font.BOLD, 12));
		frmEcad.setResizable(false);
		frmEcad.setFocusableWindowState(false);
		frmEcad.setAlwaysOnTop(true);
		frmEcad.setBounds(width-430,20, 430, 350);
		frmEcad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEcad.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 150, 210);
		frmEcad.getContentPane().add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selectedValue=(String) list.getSelectedValue();
				if(whichList.equals("Letters"))
				{
					try 
					{
						handleLetters(selectedValue);
					} catch (AWTException e1) {	e1.printStackTrace();	}
				}
				if(whichList.equals("OpenApp"))
					handleOpenApp(selectedValue);
				if(whichList.equals("MouseClick"))
					handleMouseClick(selectedValue);
				if(whichList.equals("Shortcuts"))
				{
					try 
					{
						handleShortcuts(selectedValue);
					} catch (AWTException e1) {	e1.printStackTrace();	}
				}
				if(whichList.equals("Numbers"))
				{
					try 
					{
						handleNumbers(selectedValue);
					} 
					catch (AWTException e) {	e.printStackTrace();	}
				}
				if(whichList.equals("Navigation"))
				{
					 try 
					 {
						handleNavigation(selectedValue);
					 } 
					 catch (AWTException e) {	e.printStackTrace();	}
				}
				if(whichList.equals("OtherButton"))
				{
					try 
					{
						handleOtherButton(selectedValue);
					} catch (AWTException e) {	e.printStackTrace();	}
				}
				if(whichList.equals("SS1"))
				{
					try 
					{
						handleSS1(selectedValue);
					} catch (AWTException e) {	e.printStackTrace();	}
				}
				if(whichList.equals("SS2"))
				{
					try 
					{
						handleSS2(selectedValue);
					} catch (AWTException e) {	e.printStackTrace();	}
				}				
				if(whichList.equals("SS3"))
				{
					try 
					{
						handleSS3(selectedValue);
					} catch (AWTException e) {	e.printStackTrace();	}
				}
				if(whichList.equals("SwitchApp"))
				{
					 try 
					 {
						handleSwitchApp(selectedValue);
					 } catch (AWTException e) {	e.printStackTrace();	}
				}
			}
		});
		list.setFont(new Font("Arial", Font.BOLD, 12));
		scrollPane.setViewportView(list);
		
		ListField = new JTextField();
		ListField.setFont(new Font("Arial", Font.BOLD, 12));
		ListField.setBounds(10, 70, 150, 20);
		frmEcad.getContentPane().add(ListField);
		ListField.setColumns(10);
		
		Message = new JTextField();
		Message.setFont(new Font("Arial", Font.BOLD, 12));
		Message.setBounds(170, 70, 245, 20);
		frmEcad.getContentPane().add(Message);
		Message.setColumns(10);
		
		BtnList = new JButton("LIST");
		BtnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BtnList.setBackground(Color.WHITE);
		BtnList.setFont(new Font("Arial", Font.BOLD, 12));
		BtnList.setBorder(null);
		BtnList.setBounds(170, 100, 35, 35);
		frmEcad.getContentPane().add(BtnList);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(70, 107, 35, 35);
		frmEcad.getContentPane().add(btnNewButton);
		
		BtnA = new JButton("a");
		BtnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonAction.pressA();
			}
		});
		BtnA.setBackground(Color.WHITE);
		BtnA.setBorder(null);
		BtnA.setFont(new Font("Arial", Font.BOLD, 12));
		BtnA.setBounds(205, 100, 35, 35);
		frmEcad.getContentPane().add(BtnA);
		
		BtnB = new JButton("b");
		BtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressB();	
			}
		});
		BtnB.setBorder(null);
		BtnB.setFont(new Font("Arial", Font.BOLD, 12));
		BtnB.setBackground(Color.WHITE);
		BtnB.setBounds(240, 100, 35, 35);
		frmEcad.getContentPane().add(BtnB);
		
		BtnC = new JButton("c");
		BtnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressC();		
			}
		});
		BtnC.setFont(new Font("Arial", Font.BOLD, 12));
		BtnC.setBackground(Color.WHITE);
		BtnC.setBorder(null);
		BtnC.setBounds(275, 100, 35, 35);
		frmEcad.getContentPane().add(BtnC);
		
		BtnD = new JButton("d");
		BtnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressD();
			}
		});
		BtnD.setFont(new Font("Arial", Font.BOLD, 12));
		BtnD.setBorder(null);
		BtnD.setBackground(Color.WHITE);
		BtnD.setBounds(310, 100, 35, 35);
		frmEcad.getContentPane().add(BtnD);
		
		BtnE = new JButton("e");
		BtnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressE();
			}
		});
		BtnE.setBorder(null);
		BtnE.setFont(new Font("Arial", Font.BOLD, 12));
		BtnE.setBackground(Color.WHITE);
		BtnE.setBounds(345, 100, 35, 35);
		frmEcad.getContentPane().add(BtnE);
		
		BtnF = new JButton("f");
		BtnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressF();
			}
		});
		BtnF.setFont(new Font("Arial", Font.BOLD, 12));
		BtnF.setBorder(null);
		BtnF.setBackground(Color.WHITE);
		BtnF.setBounds(380, 100, 35, 35);
		frmEcad.getContentPane().add(BtnF);
		
		BtnG = new JButton("g");
		BtnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressG();
			}
		});
		BtnG.setBorder(null);
		BtnG.setFont(new Font("Arial", Font.BOLD, 12));
		BtnG.setBackground(Color.WHITE);
		BtnG.setBounds(170, 135, 35, 35);
		frmEcad.getContentPane().add(BtnG);
		
		BtnH = new JButton("h");
		BtnH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressH();
			}
		});
		BtnH.setFont(new Font("Arial", Font.BOLD, 12));
		BtnH.setBorder(null);
		BtnH.setBackground(Color.WHITE);
		BtnH.setBounds(205, 135, 35, 35);
		frmEcad.getContentPane().add(BtnH);
		
		BtnI = new JButton("i");
		BtnI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressI();
			}
		});
		BtnI.setFont(new Font("Arial", Font.BOLD, 12));
		BtnI.setBorder(null);
		BtnI.setBackground(Color.WHITE);
		BtnI.setBounds(240, 135, 35, 35);
		frmEcad.getContentPane().add(BtnI);
		
		BtnJ = new JButton("j");
		BtnJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressJ();
			}
		});
		BtnJ.setFont(new Font("Arial", Font.BOLD, 12));
		BtnJ.setBorder(null);
		BtnJ.setBackground(Color.WHITE);
		BtnJ.setBounds(275, 135, 35, 35);
		frmEcad.getContentPane().add(BtnJ);
		
		BtnK = new JButton("k");
		BtnK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressK();
			}
		});
		BtnK.setFont(new Font("Arial", Font.BOLD, 12));
		BtnK.setBorder(null);
		BtnK.setBackground(Color.WHITE);
		BtnK.setBounds(310, 135, 35, 35);
		frmEcad.getContentPane().add(BtnK);
		
		BtnL = new JButton("l");
		BtnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressL();
			}
		});
		BtnL.setFont(new Font("Arial", Font.BOLD, 12));
		BtnL.setBorder(null);
		BtnL.setBackground(Color.WHITE);
		BtnL.setBounds(345, 135, 35, 35);
		frmEcad.getContentPane().add(BtnL);
		
		BtnM = new JButton("m");
		BtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressM();
			}
		});
		BtnM.setBackground(Color.WHITE);
		BtnM.setBorder(null);
		BtnM.setFont(new Font("Arial", Font.BOLD, 12));
		BtnM.setBounds(380, 135, 35, 35);
		frmEcad.getContentPane().add(BtnM);
		
		BtnN = new JButton("n");
		BtnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressN();
			}
		});
		BtnN.setFont(new Font("Arial", Font.BOLD, 12));
		BtnN.setBorder(null);
		BtnN.setBackground(Color.WHITE);
		BtnN.setBounds(170, 170, 35, 35);
		frmEcad.getContentPane().add(BtnN);
		
		BtnO = new JButton("o");
		BtnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressO();
			}
		});
		BtnO.setBorder(null);
		BtnO.setBackground(Color.WHITE);
		BtnO.setFont(new Font("Arial", Font.BOLD, 12));
		BtnO.setBounds(205, 170, 35, 35);
		frmEcad.getContentPane().add(BtnO);
		
		BtnP = new JButton("p");
		BtnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressP();
			}
		});
		BtnP.setFont(new Font("Arial", Font.BOLD, 12));
		BtnP.setBorder(null);
		BtnP.setBackground(Color.WHITE);
		BtnP.setBounds(240, 170, 35, 35);
		frmEcad.getContentPane().add(BtnP);
		
		BtnT = new JButton("t");
		BtnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressT();
			}
		});
		BtnT.setFont(new Font("Arial", Font.BOLD, 12));
		BtnT.setBorder(null);
		BtnT.setBackground(Color.WHITE);
		BtnT.setBounds(380, 170, 35, 35);
		frmEcad.getContentPane().add(BtnT);
		
		BtnR = new JButton("r");
		BtnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressR();
			}
		});
		BtnR.setFont(new Font("Arial", Font.BOLD, 12));
		BtnR.setBorder(null);
		BtnR.setBackground(Color.WHITE);
		BtnR.setBounds(310, 170, 35, 35);
		frmEcad.getContentPane().add(BtnR);
		
		BtnQ = new JButton("q");
		BtnQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressQ();
			}
		});
		BtnQ.setBackground(Color.WHITE);
		BtnQ.setBorder(null);
		BtnQ.setFont(new Font("Arial", Font.BOLD, 12));
		BtnQ.setBounds(275, 170, 35, 35);
		frmEcad.getContentPane().add(BtnQ);
		
		BtnS = new JButton("s");
		BtnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressS();
			}
		});
		BtnS.setBorder(null);
		BtnS.setBackground(Color.WHITE);
		BtnS.setFont(new Font("Arial", Font.BOLD, 12));
		BtnS.setBounds(345, 170, 35, 35);
		frmEcad.getContentPane().add(BtnS);
		
		BtnMouse = new JButton("");
		BtnMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressMouseClick();
			}
		});
		BtnMouse.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Mouse.png")));
		BtnMouse.setBorder(null);
		BtnMouse.setBackground(Color.WHITE);
		BtnMouse.setFont(new Font("Arial", Font.BOLD, 12));
		BtnMouse.setBounds(310, 240, 35, 35);
		frmEcad.getContentPane().add(BtnMouse);
		
		BtnU = new JButton("u");
		BtnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressU();
			}
		});
		BtnU.setFont(new Font("Arial", Font.BOLD, 12));
		BtnU.setBorder(null);
		BtnU.setBackground(Color.WHITE);
		BtnU.setBounds(170, 205, 35, 35);
		frmEcad.getContentPane().add(BtnU);
		
		BtnV = new JButton("v");
		BtnV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressV();
			}
		});
		BtnV.setFont(new Font("Arial", Font.BOLD, 12));
		BtnV.setBorder(null);
		BtnV.setBackground(Color.WHITE);
		BtnV.setBounds(205, 205, 35, 35);
		frmEcad.getContentPane().add(BtnV);
		
		BtnW = new JButton("w");
		BtnW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressW();
			}
		});
		BtnW.setBackground(Color.WHITE);
		BtnW.setBorder(null);
		BtnW.setFont(new Font("Arial", Font.BOLD, 12));
		BtnW.setBounds(240, 205, 35, 35);
		frmEcad.getContentPane().add(BtnW);
		
		BtnX = new JButton("x");
		BtnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressX();
			}
		});
		BtnX.setFont(new Font("Arial", Font.BOLD, 12));
		BtnX.setBorder(null);
		BtnX.setBackground(Color.WHITE);
		BtnX.setBounds(275, 205, 35, 35);
		frmEcad.getContentPane().add(BtnX);
		
		BtnY = new JButton("y");
		BtnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressY();
			}
		});
		BtnY.setBackground(Color.WHITE);
		BtnY.setBorder(null);
		BtnY.setFont(new Font("Arial", Font.BOLD, 12));
		BtnY.setBounds(310, 205, 35, 35);
		frmEcad.getContentPane().add(BtnY);
		
		BtnZ = new JButton("z");
		BtnZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressZ();
			}
		});
		BtnZ.setFont(new Font("Arial", Font.BOLD, 12));
		BtnZ.setBorder(null);
		BtnZ.setBackground(Color.WHITE);
		BtnZ.setBounds(345, 205, 35, 35);
		frmEcad.getContentPane().add(BtnZ);
		
		BtnNumber = new JButton("0-9");
		BtnNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressNumbers();
			}
		});
		BtnNumber.setFont(new Font("Arial", Font.BOLD, 12));
		BtnNumber.setBorder(null);
		BtnNumber.setBackground(Color.WHITE);
		BtnNumber.setBounds(380, 205, 35, 35);
		frmEcad.getContentPane().add(BtnNumber);
		
		BtnSpace = new JButton("");
		BtnSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressSpace();
			}			
		});
		BtnSpace.setFont(new Font("Arial", Font.BOLD, 12));
		BtnSpace.setBorder(null);
		BtnSpace.setBackground(Color.WHITE);
		BtnSpace.setBounds(170, 240, 35, 35);
		frmEcad.getContentPane().add(BtnSpace);
		
		BtnBackspace = new JButton("");
		BtnBackspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressBackspace();
			}
		});
		BtnBackspace.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Backspace.png")));
		BtnBackspace.setBackground(Color.WHITE);
		BtnBackspace.setBorder(null);
		BtnBackspace.setFont(new Font("Arial", Font.BOLD, 12));
		BtnBackspace.setBounds(205, 240, 35, 35);
		frmEcad.getContentPane().add(BtnBackspace);
		
		BtnTalk = new JButton();
		BtnTalk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonAction.pressTalk();
			}
		});
		BtnTalk.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Speak.png")));
		BtnTalk.setFont(new Font("Arial", Font.BOLD, 12));
		BtnTalk.setBorder(null);
		BtnTalk.setBackground(Color.WHITE);
		BtnTalk.setBounds(240, 240, 35, 35);
		frmEcad.getContentPane().add(BtnTalk);
		
		BtnOCTalk = new JButton("");
		BtnOCTalk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ButtonAction.pressOpenCloseTalk();
			}
		});
		BtnOCTalk.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Talk.png")));
		BtnOCTalk.setBackground(Color.WHITE);
		BtnOCTalk.setHorizontalTextPosition(SwingConstants.LEADING);
		BtnOCTalk.setFont(new Font("Arial", Font.BOLD, 12));
		BtnOCTalk.setBorder(null);
		BtnOCTalk.setBounds(275, 240, 35, 35);
		frmEcad.getContentPane().add(BtnOCTalk);
		
		BtnShortCut = new JButton("");
		BtnShortCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonAction.pressShortcut();
			}
		});
		BtnShortCut.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Shortcut.png")));
		BtnShortCut.setBorder(null);
		BtnShortCut.setFont(new Font("Arial", Font.BOLD, 12));
		BtnShortCut.setBackground(Color.WHITE);
		BtnShortCut.setBounds(345, 240, 35, 35);
		frmEcad.getContentPane().add(BtnShortCut);
		
		BtnNavigation = new JButton("");
		BtnNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressNavigation();
			}
		});
		BtnNavigation.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Arrow.png")));
		BtnNavigation.setBorder(null);
		BtnNavigation.setFont(new Font("Arial", Font.BOLD, 12));
		BtnNavigation.setBackground(Color.WHITE);
		BtnNavigation.setBounds(380, 240, 35, 35);
		frmEcad.getContentPane().add(BtnNavigation);
		
		BtnOtherButton = new JButton("");
		BtnOtherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressOtherButton();
			}
		});
		BtnOtherButton.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Control.png")));
		BtnOtherButton.setBorder(null);
		BtnOtherButton.setFont(new Font("Arial", Font.BOLD, 12));
		BtnOtherButton.setBackground(Color.WHITE);
		BtnOtherButton.setBounds(170, 275, 35, 35);
		frmEcad.getContentPane().add(BtnOtherButton);
		
		BtnSS1 = new JButton("SS1");
		BtnSS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressSS1();
			}
		});
		BtnSS1.setBorder(null);
		BtnSS1.setFont(new Font("Arial", Font.BOLD, 12));
		BtnSS1.setBackground(Color.WHITE);
		BtnSS1.setBounds(205, 275, 35, 35);
		frmEcad.getContentPane().add(BtnSS1);
		
		BtnSS2 = new JButton("SS2");
		BtnSS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressSS2();
			}
		});
		BtnSS2.setBorder(null);
		BtnSS2.setFont(new Font("Arial", Font.BOLD, 12));
		BtnSS2.setBackground(Color.WHITE);
		BtnSS2.setBounds(240, 275, 35, 35);
		frmEcad.getContentPane().add(BtnSS2);
		
		BtnSS3 = new JButton("SS3");
		BtnSS3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressSS3();
			}
		});
		BtnSS3.setBorder(null);
		BtnSS3.setFont(new Font("Arial", Font.BOLD, 12));
		BtnSS3.setBackground(Color.WHITE);
		BtnSS3.setBounds(275, 275, 35, 35);
		frmEcad.getContentPane().add(BtnSS3);
		
		BtnOpenApp = new JButton("");
		BtnOpenApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonAction.pressOpenApp();
			}
		});
		BtnOpenApp.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Open file.png")));
		BtnOpenApp.setBorder(null);
		BtnOpenApp.setFont(new Font("Arial", Font.BOLD, 12));
		BtnOpenApp.setBackground(Color.WHITE);
		BtnOpenApp.setBounds(310, 275, 35, 35);
		frmEcad.getContentPane().add(BtnOpenApp);
		
		BtnSwitchApp = new JButton("");
		BtnSwitchApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonAction.pressSwitchApp();
			}
		});
		BtnSwitchApp.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Swtich.png")));
		BtnSwitchApp.setFont(new Font("Arial", Font.BOLD, 12));
		BtnSwitchApp.setBorder(null);
		BtnSwitchApp.setBackground(Color.WHITE);
		BtnSwitchApp.setBounds(345, 275, 35, 35);
		frmEcad.getContentPane().add(BtnSwitchApp);
		
		BtnSetting = new JButton("");
		BtnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonAction.pressOpenCloseSetting();
			}
		});
		BtnSetting.setIcon(new ImageIcon(MainWindow.class.getResource("/project/Settings.png")));
		BtnSetting.setBorder(null);
		BtnSetting.setFont(new Font("Arial", Font.BOLD, 12));
		BtnSetting.setBackground(Color.WHITE);
		BtnSetting.setBounds(380, 275, 35, 35);
		frmEcad.getContentPane().add(BtnSetting);
		
		canvas = new JLabel();
		canvas.setBounds(170, 10, 245, 50);
		frmEcad.getContentPane().add(canvas);
	}
	
	/*****************Highlighting and selection functions for mouse actions******************************/
	
	public static void highlighting()
	{
		g.drawImage(capture, 0, 0, width, height, null);	
		while(true)		
		{
			// Highlighting row
			if(rowMouse==height) rowMouse=0;	// if we have reached the last row pixel, reset row pixel to 0
			if(coloumnMouse==0)	// draw a horizontal line only when column is o i.e x coordinate is 0
			{	
				g.drawLine(0, rowMouse, width, rowMouse);	// draw a horizontal line. Only change the x coordinate
				rowMouse++;	// to move the horizontal line down change the y coordinate
				break;	
			}	
			
			// highlighting column
			if(coloumnMouse==width) coloumnMouse=1;	// if we have reached the last column the reset column value
			if(coloumnMouse>0)	// only start drawing vertical line if the horizontal line is fixed
			{	
				g.drawLine(0, rowMouse, width, rowMouse);	// to show the original horizontal line drawn.
				// Since the first line of code always places a fresh new screen shot, the horizontal line will be gone. To show that we execute this code
				g.drawLine(coloumnMouse, 0, coloumnMouse, height);	// draw vertical line
				coloumnMouse++;	break;	// to move the vertical line to the next x coordinate pixel change the x coordinate (column value)
			}
			break;
		}
	}
	
	//++++++++++++++++++++++ For single click,double click and hover++++++++++++++++++++++++++++++++++++++
	public static void selection(String selectedValue)
	{
		while(true)
		{
			// the first selection occurs for the horizontal line.
			// This means that while the horizontal line is being drawn we clicked the mouse. 
			// To indicate it and also for which row the selection occurred, we set column value (previously 0) to 1 
			if(coloumnMouse==0) 
			{
				coloumnMouse++;
				//g.clearRect(0, 0, width, height);	// not really necessary
				break;
			}
			if(rowMouse>=0&&coloumnMouse>=0)
			{
				try 
				{
				
					Robot r=new Robot();
					frame1.setVisible(false);	// remove the MouseGrab frame
					frame1.dispose();			// completely destroy it
					frmEcad.setVisible(false);	// only remove Main frame
					r.delay(1000);
					r.mouseMove(coloumnMouse, rowMouse);
					r.delay(1000);
					if(selectedValue.equals(" Left Double Click "))
					{
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					}
					else if(selectedValue.equals(" Left "))
					{
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					}
					else if(selectedValue.equals(" Right "))
					{
						r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
						r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
					}
					else if(selectedValue.equals(" Hover "))
					{
						r.delay(5000);
					}
					r.delay(1000);
					initializeMouse();
					frmEcad.setVisible(true);
					rowMouse=0;coloumnMouse=0;	// reset all previously used variables
					a=false;
					sel=true;	// This variable is used to indicate that the mouse was placed in the corresponding coordinate and the action was performed
					
				} catch (AWTException e1) {	e1.printStackTrace();}
				break;
			}
			break;
		}
		ans=false;	
		/* Here we need to click the mouse twice. First one for the row and the next one for column. 
		 * So once we have selected the row, we break out of the selection function and got to the highlighting function
		 * But before we go to the highlighting function we need to enable the mouse action listener, so that it can again listen for mouse click
		 * but this time for column selection. 
		 * To do that we set ans (the variable which indicates if mouse button was clicked) to false as it returns true when mouse is clicked
		 * 
		*/
	}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//++++++++++++++++++++++++++For Dragging+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static void sel()
	{
		while(true)
		{
			if(coloumnMouse==0)
			{
				coloumnMouse++;
				g.clearRect(0, 0, width, height);
				break;
			}
			if(rowMouse>=0&&coloumnMouse>=0)
			{
				count++;
				if(count==1)
				{
					x1=coloumnMouse;y1=rowMouse;
					coloumnMouse=0;
					rowMouse=0;
					a=false;
				}
				if(count==2)
				{
					x2=coloumnMouse;y2=rowMouse;
					try 
					{
						Robot r=new Robot();
						frame1.setVisible(false);
						frame1.dispose();
						frmEcad.setVisible(false);
						r.delay(1000);
						r.mouseMove(x1, y1);
						r.delay(1000);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						if(y2>y1)
						{
							for(int i=y1;i<=y2;++i)
							{
								r.mouseMove(x1, i);
								r.delay(18);
							}
						}
						if(y2<y1)
						{
							for(int i=y1;i>=y2;--i)
							{
								r.mouseMove(x1, i);
								r.delay(18);
							}
						}
						
						if(x2<x1)
						{
							for(int i=x1;i>=x2;--i)
							{
								r.mouseMove(i, y2);
								r.delay(18);
							}
						}
						if(x2>x1)
						{
							for(int i=x1;i<=x2;++i)
							{
								r.mouseMove(i, y2);
								r.delay(18);
							}
						}						
						r.delay(1000);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						r.delay(1000);
						initializeMouse();
						frmEcad.setVisible(true);
						rowMouse=0;coloumnMouse=0;
						a=false;sel=true;
						count=0;
					} catch (AWTException e1) {	e1.printStackTrace();}
				}
			}
			break;
		}
		ans=false;		
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//++++++++++++++++++ For Left + Left  and Right + Left ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static void selectit(String selectedValue)
	{
		while(true)
		{
			if(coloumnMouse==0)
			{
				coloumnMouse++;
				g.drawImage(capture, 0, 0, width, height, null);
				break;
			}
			if(rowMouse>=0&&coloumnMouse>=0)
			{
				count++;
				if(count==1)
				{
					x1=coloumnMouse;y1=rowMouse;
					try 
					{
						Robot r=new Robot();
						frame1.setVisible(false);
						frmEcad.setVisible(false);
						r.delay(1000);
						r.mouseMove(x1,y1);		
						r.delay(1000);
						if(selectedValue.equals(" Left + Left "))
						{
							r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
							r.delay(50);
							r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						}
						else if(selectedValue.equals(" Right + Left "))
						{
							r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
							r.delay(50);
							r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
						}
						r.mouseMove(width/2, height/2);	
						coloumnMouse=0;rowMouse=0;a=false;sel=false;
						r.delay(2000);
						capture = new Robot().createScreenCapture(screenRect);
						g=ScreenCapture.contentPane.getGraphics();
						g.drawImage(capture, 0, 0, width, height, null);
						g.setColor(Color.RED);
						frame1.setVisible(true);
						frmEcad.setVisible(true);						
					} catch (AWTException e) {e.printStackTrace();	}					
				}
				if(count==2)
				{
					x2=coloumnMouse;y2=rowMouse;
					try 
					{
					
						Robot r=new Robot();
						frame1.setVisible(false);
						frmEcad.setVisible(false);
						r.delay(1000);
						r.mouseMove(x1, y1);	
						r.delay(1000);
						if(selectedValue.equals(" Left + Left "))
						{
							r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
							r.delay(50);
							r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						}
						else if(selectedValue.equals(" Right + Left "))
						{
							r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
							r.delay(50);
							r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
						}
						for(int i=y1;i<=y2;++i)
						{
							r.mouseMove(x1, i);
							r.delay(18);
						}
						if(x2<x1)
						{
							for(int i=x1;i>=x2;--i)
							{
								r.mouseMove(i, y2);
								r.delay(18);
							}
						}
						if(x2>x1)
						{
							for(int i=x1;i<=x2;++i)
							{
								r.mouseMove(i, y2);
								r.delay(18);
							}
						}
						r.delay(1000);
						r.mouseMove(x2, y2);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						r.delay(1000);
						initializeMouse();
						frmEcad.setVisible(true);
						rowMouse=0;coloumnMouse=0;
						a=false;sel=true;
						count=0;
						frame1.dispose();
					} catch (AWTException e1) {	e1.printStackTrace();}
				}
			}
			break;
		}
		ans=false;
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
