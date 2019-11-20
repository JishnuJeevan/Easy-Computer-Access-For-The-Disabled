package project;

// This file has 42 functions which corresponds to each individual action of the buttons in the GUI.
// When a button is to perform the action, we call the corresponding function in this file.

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class ButtonAction 
{
	public static TalkWindow talk=new TalkWindow();
	public static SettingsWindow setting=new SettingsWindow();
	
	/**************** Function for pressing buttons on UI ******************************************/	
	public static void pressA()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_A);	r.keyRelease(KeyEvent.VK_A);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"A";
		else	MainWindow.words=MainWindow.words+"a";
		MainWindow.suggest();
	}
	
	public static void pressB()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_B);	r.keyRelease(KeyEvent.VK_B);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"B";
		else	MainWindow.words=MainWindow.words+"b";
		MainWindow.suggest();
		
	}
	
	public static void pressC()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_C);	r.keyRelease(KeyEvent.VK_C);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"C";
		else	MainWindow.words=MainWindow.words+"c";
		MainWindow.suggest();
		
	}
	public static void pressD()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_D);	r.keyRelease(KeyEvent.VK_D);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"D";
		else	MainWindow.words=MainWindow.words+"d";
		MainWindow.suggest();
		
	}
	public static void pressE()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_E);	r.keyRelease(KeyEvent.VK_E);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"E";
		else	MainWindow.words=MainWindow.words+"e";
		MainWindow.suggest();
		
	}
	public static void pressF()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_F);	r.keyRelease(KeyEvent.VK_F);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"F";
		else	MainWindow.words=MainWindow.words+"f";
		MainWindow.suggest();
		
	}
	public static void pressG()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_G);	r.keyRelease(KeyEvent.VK_G);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"G";
		else	MainWindow.words=MainWindow.words+"g";
		MainWindow.suggest();
		
	}
	public static void pressH()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_H);	r.keyRelease(KeyEvent.VK_H);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"H";
		else	MainWindow.words=MainWindow.words+"h";
		MainWindow.suggest();
		
	}
	public static void pressI()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_I);	r.keyRelease(KeyEvent.VK_I);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"I";
		else	MainWindow.words=MainWindow.words+"i";
		MainWindow.suggest();
		
	}
	public static void pressJ()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_J);	r.keyRelease(KeyEvent.VK_J);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"J";
		else	MainWindow.words=MainWindow.words+"j";
		MainWindow.suggest();
		
	}
	public static void pressK()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_K);	r.keyRelease(KeyEvent.VK_K);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"K";
		else	MainWindow.words=MainWindow.words+"k";
		MainWindow.suggest();
		
	}
	public static void pressL()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_L);	r.keyRelease(KeyEvent.VK_L);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"L";
		else	MainWindow.words=MainWindow.words+"l";
		MainWindow.suggest();
		
	}
	public static void pressM()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_M);	r.keyRelease(KeyEvent.VK_M);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"M";
		else	MainWindow.words=MainWindow.words+"m";
		MainWindow.suggest();
		
	}
	public static void pressN()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_N);	r.keyRelease(KeyEvent.VK_N);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"N";
		else	MainWindow.words=MainWindow.words+"n";
		MainWindow.suggest();
		
	}
	public static void pressO()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_O);	r.keyRelease(KeyEvent.VK_O);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"O";
		else	MainWindow.words=MainWindow.words+"o";
		MainWindow.suggest();
		
	}
	public static void pressP()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_P);	r.keyRelease(KeyEvent.VK_P);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"P";
		else	MainWindow.words=MainWindow.words+"p";
		MainWindow.suggest();
		
	}
	public static void pressQ()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_Q);	r.keyRelease(KeyEvent.VK_Q);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"Q";
		else	MainWindow.words=MainWindow.words+"q";
		MainWindow.suggest();
		
	}
	public static void pressR()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_R);	r.keyRelease(KeyEvent.VK_R);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"R";
		else	MainWindow.words=MainWindow.words+"r";
		MainWindow.suggest();
		
	}
	public static void pressS()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_S);	r.keyRelease(KeyEvent.VK_S);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"S";
		else	MainWindow.words=MainWindow.words+"s";
		MainWindow.suggest();
		
	}
	public static void pressT()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_T);	r.keyRelease(KeyEvent.VK_T);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"T";
		else	MainWindow.words=MainWindow.words+"t";
		MainWindow.suggest();
		
	}
	public static void pressU()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_U);	r.keyRelease(KeyEvent.VK_U);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"U";
		else	MainWindow.words=MainWindow.words+"u";
		MainWindow.suggest();
		
	}
	public static void pressV()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_V);	r.keyRelease(KeyEvent.VK_V);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"V";
		else	MainWindow.words=MainWindow.words+"v";
		MainWindow.suggest();
		
	}
	public static void pressW()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_W);	r.keyRelease(KeyEvent.VK_W);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"W";
		else	MainWindow.words=MainWindow.words+"w";
		MainWindow.suggest();
		
	}
	public static void pressX()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_X);	r.keyRelease(KeyEvent.VK_X);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"X";
		else	MainWindow.words=MainWindow.words+"x";
		MainWindow.suggest();
		
	}
	public static void pressY()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_Y);	r.keyRelease(KeyEvent.VK_Y);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"Y";
		else	MainWindow.words=MainWindow.words+"y";
		MainWindow.suggest();
		
	}
	public static void pressZ()
	{
		MainWindow.whichList="Letters";
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_Z);	r.keyRelease(KeyEvent.VK_Z);	}
		catch(Exception e){	e.printStackTrace();	}
		if(MainWindow.capsOn==true)	MainWindow.words=MainWindow.words+"Z";
		else	MainWindow.words=MainWindow.words+"z";
		MainWindow.suggest();
		
	}	
		
	public static void pressSpace()
	{
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_SPACE);	r.keyRelease(KeyEvent.VK_SPACE);	}
		catch(Exception espace){	espace.printStackTrace();	}		
		if(MainWindow.words.length()>=3)
		{
			try
			{
				String query="select * from Words where Words = ?";
				java.sql.PreparedStatement pst= MainWindow.conn.prepareStatement(query);
				pst.setString(1,MainWindow.words);
				ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
					query="update Words set count=count+1 where words = ?";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1,MainWindow.words);
					pst.execute();
					rs.close();
				}
				else
				{
					query="insert into Words values(?,?)";
					pst=MainWindow.conn.prepareStatement(query);
					pst.setString(1, MainWindow.words);
					pst.setInt(2, 1);
					pst.execute();
					rs.close();
				}
			}
			catch(Exception espace1){	espace1.printStackTrace();}
		}
		MainWindow.words="";
		
	}
	
	public static void pressBackspace()
	{
		Robot r;
		try{	r=new Robot();	r.keyPress(KeyEvent.VK_BACK_SPACE);	r.keyRelease(KeyEvent.VK_BACK_SPACE);	}
		catch(Exception ebackspace){	ebackspace.printStackTrace();	}
		if(MainWindow.words.length()>0)
		{
			StringBuilder strb=new StringBuilder(MainWindow.words);
			strb.deleteCharAt(MainWindow.words.length()-1);
			MainWindow.words=strb.toString();
			MainWindow.suggest();
		}
		
		if(MainWindow.words.equals(""))	MainWindow.whichList="Letters";
	}
	
	public static void pressNumbers()
	{
		MainWindow.whichList="Numbers";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 "};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressTalk()
	{
		MainWindow.words="";
		if(MainWindow.isTalkOpen==false){	MainWindow.Message.setText("Open Talk Window to Speak");	MainWindow.Talking("Open Talk Window to Speak");	}
		else{	MainWindow.Talking(TalkWindow.talkArea.getText());	TalkWindow.talkArea.setText("");	MainWindow.words="";	}
	}
	
	public static void pressOpenCloseTalk()
	{
		if(MainWindow.isTalkOpen==false){	talk.setVisible(true);	MainWindow.isTalkOpen=true;	}
		else {	talk.setVisible(false);		talk.dispose();	MainWindow.isTalkOpen=false;	}				
	}
	
	public static void pressMouseClick()
	{
		MainWindow.whichList="MouseClick";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {
					" Right ",
					" Left ",
					" Left Double Click ",
					" Drag ",
					" Hover ",
					" Right + Left ",
					" Left + Left ",
					};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressShortcut()
	{
		MainWindow.whichList="Shortcuts";
		MainWindow.words="";
		try
		{
				
			DefaultListModel DLM=new DefaultListModel();
			String query="select Name from Shortcut order by Count desc";
			java.sql.PreparedStatement pst=MainWindow.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){	DLM.addElement(rs.getString("Name"));	}
			MainWindow.list.setModel(DLM);
			rs.close();
		}
		catch (Exception e)	{	e.printStackTrace();	}
	}
	
	public static void pressNavigation()
	{
		MainWindow.whichList="Navigation";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {
					" Up ",
					" Down ",
					" Left ",
					" Right ",
					" Page Up ",
					" Page Down ",
					" Home ",
					" End ",
					" Windows "
					};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressOtherButton()
	{
		MainWindow.whichList="OtherButton";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {
					" CapsLk ",
					" Enter ",
					" Tab ",
					" Ctrl ",
					" Alt ",
					" Delete ",
					" Insert ",
					" Esc ",
					" Shift ",
					" F1 ",
					" F2 ",
					" F3 ",
					" F4 ",
					" F5 ",
					" F6 ",
					" F7 ",
					" F8 ",
					" F9 ",
					" F10 ",
					" F11 ",
					" F12 "
					};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressSS1()
	{
		MainWindow.whichList="SS1";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {" ( "," ) "," < "," > "," [ "," ] "," { "," } "," \" "," \' "};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressSS2()
	{
		MainWindow.whichList="SS2";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {" + "," - "," * ", " / "," % "," ^ "," = "," \\ "," ` "," ~ "};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressSS3()
	{
		MainWindow.whichList="SS3";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {" : "," ; "," ! "," @ "," $ "," # "," & "," _ "," . "," , "," ? "," | "};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressOpenApp()
	{
		MainWindow.whichList="OpenApp";
		MainWindow.words="";
		try
		{
				
			DefaultListModel DLM=new DefaultListModel();
			String query="select Name from Application order by Count desc";
			java.sql.PreparedStatement pst=MainWindow.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				DLM.addElement(rs.getString("Name"));
			}
			MainWindow.list.setModel(DLM);
			//pst.close();
			rs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void pressSwitchApp()
	{
		MainWindow.whichList="SwitchApp";
		MainWindow.words="";
		MainWindow.list.setModel(new AbstractListModel() {
			String[] values = new String[] {
					" Alt + Tab ",
					" Alt + 2 Tab ",
					" Alt + 3 Tab ",
					" Alt + 4 Tab ",
					" Alt + 5 Tab ",
					" Alt + 6 Tab ",
					" Alt + 7 Tab ",
					" Alt + 8 Tab ",
					" Alt + 9 Tab ",
					" Alt + 10 Tab ",
					" Alt + 11 Tab ",
					" Alt + 12 Tab ",
					};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		
		});
	}
	
	public static void pressOpenCloseSetting()
	{
		MainWindow.words="";
		if(MainWindow.isSettingsOpen==false)
		{
			setting.setVisible(true);
			MainWindow.isSettingsOpen=true;
		}
		else 
		{
			setting.setVisible(false);
			setting.dispose();
			MainWindow.isSettingsOpen=false;
		}
		setting.setSystemConfig();
	}
}
