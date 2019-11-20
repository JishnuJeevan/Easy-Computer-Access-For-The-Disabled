package project;

// This file performs the Switch access scanning operation.

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.ListModel;

public class SwitchAcessScanning 
{
	public static int row=0,coloumn=-1,index=-1;
	public static void row1()
	{
		MainWindow.BtnList.setBackground(Color.GREEN);
		MainWindow.BtnA.setBackground(Color.GREEN);
		MainWindow.BtnB.setBackground(Color.GREEN);
		MainWindow.BtnC.setBackground(Color.GREEN);
		MainWindow.BtnD.setBackground(Color.GREEN);
		MainWindow.BtnE.setBackground(Color.GREEN);
		MainWindow.BtnF.setBackground(Color.GREEN);
		
		MainWindow.BtnOtherButton.setBackground(Color.WHITE);
		MainWindow.BtnSS1.setBackground(Color.WHITE);
		MainWindow.BtnSS2.setBackground(Color.WHITE);
		MainWindow.BtnSS3.setBackground(Color.WHITE);
		MainWindow.BtnOpenApp.setBackground(Color.WHITE);
		MainWindow.BtnSwitchApp.setBackground(Color.WHITE);
		MainWindow.BtnSetting.setBackground(Color.WHITE);		
	}
	public static void row2()
	{
		MainWindow.BtnG.setBackground(Color.GREEN);
		MainWindow.BtnH.setBackground(Color.GREEN);
		MainWindow.BtnI.setBackground(Color.GREEN);
		MainWindow.BtnJ.setBackground(Color.GREEN);
		MainWindow.BtnK.setBackground(Color.GREEN);
		MainWindow.BtnL.setBackground(Color.GREEN);
		MainWindow.BtnM.setBackground(Color.GREEN);
		
		MainWindow.BtnList.setBackground(Color.WHITE);
		MainWindow.BtnA.setBackground(Color.WHITE);
		MainWindow.BtnB.setBackground(Color.WHITE);
		MainWindow.BtnC.setBackground(Color.WHITE);
		MainWindow.BtnD.setBackground(Color.WHITE);
		MainWindow.BtnE.setBackground(Color.WHITE);
		MainWindow.BtnF.setBackground(Color.WHITE);
	}
	public static void row3()
	{
		MainWindow.BtnN.setBackground(Color.GREEN);
		MainWindow.BtnO.setBackground(Color.GREEN);
		MainWindow.BtnP.setBackground(Color.GREEN);
		MainWindow.BtnQ.setBackground(Color.GREEN);
		MainWindow.BtnR.setBackground(Color.GREEN);
		MainWindow.BtnS.setBackground(Color.GREEN);
		MainWindow.BtnT.setBackground(Color.GREEN);
		
		MainWindow.BtnG.setBackground(Color.WHITE);
		MainWindow.BtnH.setBackground(Color.WHITE);
		MainWindow.BtnI.setBackground(Color.WHITE);
		MainWindow.BtnJ.setBackground(Color.WHITE);
		MainWindow.BtnK.setBackground(Color.WHITE);
		MainWindow.BtnL.setBackground(Color.WHITE);
		MainWindow.BtnM.setBackground(Color.WHITE);
	}
	public static void row4()
	{
		MainWindow.BtnU.setBackground(Color.GREEN);
		MainWindow.BtnV.setBackground(Color.GREEN);
		MainWindow.BtnW.setBackground(Color.GREEN);
		MainWindow.BtnX.setBackground(Color.GREEN);
		MainWindow.BtnY.setBackground(Color.GREEN);
		MainWindow.BtnZ.setBackground(Color.GREEN);
		MainWindow.BtnNumber.setBackground(Color.GREEN);
		
		MainWindow.BtnN.setBackground(Color.WHITE);
		MainWindow.BtnO.setBackground(Color.WHITE);
		MainWindow.BtnP.setBackground(Color.WHITE);
		MainWindow.BtnQ.setBackground(Color.WHITE);
		MainWindow.BtnR.setBackground(Color.WHITE);
		MainWindow.BtnS.setBackground(Color.WHITE);
		MainWindow.BtnT.setBackground(Color.WHITE);
	}
	public static void row5()
	{
		MainWindow.BtnSpace.setBackground(Color.GREEN);
		MainWindow.BtnBackspace.setBackground(Color.GREEN);
		MainWindow.BtnTalk.setBackground(Color.GREEN);
		MainWindow.BtnOCTalk.setBackground(Color.GREEN);
		MainWindow.BtnMouse.setBackground(Color.GREEN);
		MainWindow.BtnShortCut.setBackground(Color.GREEN);
		MainWindow.BtnNavigation.setBackground(Color.GREEN);
		
		MainWindow.BtnU.setBackground(Color.WHITE);
		MainWindow.BtnV.setBackground(Color.WHITE);
		MainWindow.BtnW.setBackground(Color.WHITE);
		MainWindow.BtnX.setBackground(Color.WHITE);
		MainWindow.BtnY.setBackground(Color.WHITE);
		MainWindow.BtnZ.setBackground(Color.WHITE);
		MainWindow.BtnNumber.setBackground(Color.WHITE);
	}
	public static void row6()
	{
		MainWindow.BtnOtherButton.setBackground(Color.GREEN);
		MainWindow.BtnSS1.setBackground(Color.GREEN);
		MainWindow.BtnSS2.setBackground(Color.GREEN);
		MainWindow.BtnSS3.setBackground(Color.GREEN);
		MainWindow.BtnOpenApp.setBackground(Color.GREEN);
		MainWindow.BtnSwitchApp.setBackground(Color.GREEN);
		MainWindow.BtnSetting.setBackground(Color.GREEN);	
		
		MainWindow.BtnSpace.setBackground(Color.WHITE);
		MainWindow.BtnBackspace.setBackground(Color.WHITE);
		MainWindow.BtnTalk.setBackground(Color.WHITE);
		MainWindow.BtnOCTalk.setBackground(Color.WHITE);
		MainWindow.BtnMouse.setBackground(Color.WHITE);
		MainWindow.BtnShortCut.setBackground(Color.WHITE);
		MainWindow.BtnNavigation.setBackground(Color.WHITE);
	}
		
	public static void bList()
	{
		MainWindow.BtnList.setBackground(Color.GREEN);
		MainWindow.BtnA.setBackground(Color.WHITE);
		MainWindow.BtnB.setBackground(Color.WHITE);
		MainWindow.BtnC.setBackground(Color.WHITE);
		MainWindow.BtnD.setBackground(Color.WHITE);
		MainWindow.BtnE.setBackground(Color.WHITE);
		MainWindow.BtnF.setBackground(Color.WHITE);
		
	}
	public static void bG()
	{
		MainWindow.BtnG.setBackground(Color.GREEN);
		MainWindow.BtnH.setBackground(Color.WHITE);
		MainWindow.BtnI.setBackground(Color.WHITE);
		MainWindow.BtnJ.setBackground(Color.WHITE);
		MainWindow.BtnK.setBackground(Color.WHITE);
		MainWindow.BtnL.setBackground(Color.WHITE);
		MainWindow.BtnM.setBackground(Color.WHITE);
	}
	public static void bN()
	{
		MainWindow.BtnN.setBackground(Color.GREEN);
		MainWindow.BtnO.setBackground(Color.WHITE);
		MainWindow.BtnP.setBackground(Color.WHITE);
		MainWindow.BtnQ.setBackground(Color.WHITE);
		MainWindow.BtnR.setBackground(Color.WHITE);
		MainWindow.BtnS.setBackground(Color.WHITE);
		MainWindow.BtnT.setBackground(Color.WHITE);
		
	}
	public static void bU()
	{
		MainWindow.BtnU.setBackground(Color.GREEN);
		MainWindow.BtnV.setBackground(Color.WHITE);
		MainWindow.BtnW.setBackground(Color.WHITE);
		MainWindow.BtnX.setBackground(Color.WHITE);
		MainWindow.BtnY.setBackground(Color.WHITE);
		MainWindow.BtnZ.setBackground(Color.WHITE);
		MainWindow.BtnNumber.setBackground(Color.WHITE);
	}
	public static void bSpace()
	{
		MainWindow.BtnSpace.setBackground(Color.GREEN);
		MainWindow.BtnBackspace.setBackground(Color.WHITE);
		MainWindow.BtnTalk.setBackground(Color.WHITE);
		MainWindow.BtnOCTalk.setBackground(Color.WHITE);
		MainWindow.BtnMouse.setBackground(Color.WHITE);
		MainWindow.BtnShortCut.setBackground(Color.WHITE);
		MainWindow.BtnNavigation.setBackground(Color.WHITE);
	}
	public static void bOtherButton()
	{
		MainWindow.BtnOtherButton.setBackground(Color.GREEN);
		MainWindow.BtnSS1.setBackground(Color.WHITE);
		MainWindow.BtnSS2.setBackground(Color.WHITE);
		MainWindow.BtnSS3.setBackground(Color.WHITE);
		MainWindow.BtnOpenApp.setBackground(Color.WHITE);
		MainWindow.BtnSwitchApp.setBackground(Color.WHITE);
		MainWindow.BtnSetting.setBackground(Color.WHITE);	
	}
	
	public static void reset(JButton btn)
	{
		row=0;coloumn=-1;btn.setBackground(Color.white);	
	}
	
	public static void highlighting()
	{
		while(true)
		{
			//****************ROW HIGHLIGHTITNG***************************
			if(row==6&&coloumn==-1)row=0;
			if(row==0&&coloumn==-1)
			{	row1();	row++;	break;	}			
			else if(row==1&&coloumn==-1)
			{	row2(); row++; break;	}			
			else if(row==2&&coloumn==-1)
			{	row3(); row++; break;	}			
			else if(row==3&&coloumn==-1)
			{	row4(); row++; break;	}			
			else if(row==4&&coloumn==-1)
			{	row5(); row++; break;	}
			else if(row==5&&coloumn==-1)
			{	row6(); row++; break;	}			
			//************************************************************
			
			
			//******************COLOUMN OF ROW 1 *************************
			if(row==1&&coloumn==7&&MainWindow.listvalue==false)coloumn=0;
			if(row==1&&coloumn==0&&MainWindow.listvalue==false)	
			{	bList();	coloumn++;	break;	}
			else if(row==1&&coloumn==1&&MainWindow.listvalue==false)
			{	MainWindow.BtnA.setBackground(Color.GREEN);	MainWindow.BtnList.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==1&&coloumn==2&&MainWindow.listvalue==false)
			{	MainWindow.BtnB.setBackground(Color.GREEN);	MainWindow.BtnA.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==1&&coloumn==3&&MainWindow.listvalue==false)
			{	MainWindow.BtnC.setBackground(Color.GREEN);	MainWindow.BtnB.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==1&&coloumn==4&&MainWindow.listvalue==false)
			{	MainWindow.BtnD.setBackground(Color.GREEN);	MainWindow.BtnC.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==1&&coloumn==5&&MainWindow.listvalue==false)
			{	MainWindow.BtnE.setBackground(Color.GREEN);	MainWindow.BtnD.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==1&&coloumn==6&&MainWindow.listvalue==false)
			{	MainWindow.BtnF.setBackground(Color.GREEN);	MainWindow.BtnE.setBackground(Color.WHITE);	coloumn++;	break;	}
			//*****************************************************************
			

			//******************COLOUMN OF ROW 2 *************************
			if(row==2&&coloumn==7)coloumn=0;
			if(row==2&&coloumn==0)	
			{	bG();	coloumn++;	break;	}
			else if(row==2&&coloumn==1)
			{	MainWindow.BtnH.setBackground(Color.GREEN);	MainWindow.BtnG.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==2&&coloumn==2)
			{	MainWindow.BtnI.setBackground(Color.GREEN);	MainWindow.BtnH.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==2&&coloumn==3)
			{	MainWindow.BtnJ.setBackground(Color.GREEN);	MainWindow.BtnI.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==2&&coloumn==4)
			{	MainWindow.BtnK.setBackground(Color.GREEN);	MainWindow.BtnJ.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==2&&coloumn==5)
			{	MainWindow.BtnL.setBackground(Color.GREEN);	MainWindow.BtnK.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==2&&coloumn==6)
			{	MainWindow.BtnM.setBackground(Color.GREEN);	MainWindow.BtnL.setBackground(Color.WHITE);	coloumn++;	break;	}
			//*****************************************************************
			

			//******************COLOUMN OF ROW 3 *************************
			if(row==3&&coloumn==7)coloumn=0;
			if(row==3&&coloumn==0)	
			{	bN();	coloumn++;	break;	}
			else if(row==3&&coloumn==1)
			{	MainWindow.BtnO.setBackground(Color.GREEN);	MainWindow.BtnN.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==3&&coloumn==2)
			{	MainWindow.BtnP.setBackground(Color.GREEN);	MainWindow.BtnO.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==3&&coloumn==3)
			{	MainWindow.BtnQ.setBackground(Color.GREEN);	MainWindow.BtnP.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==3&&coloumn==4)
			{	MainWindow.BtnR.setBackground(Color.GREEN);	MainWindow.BtnQ.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==3&&coloumn==5)
			{	MainWindow.BtnS.setBackground(Color.GREEN);	MainWindow.BtnR.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==3&&coloumn==6)
			{	MainWindow.BtnT.setBackground(Color.GREEN);	MainWindow.BtnS.setBackground(Color.WHITE);	coloumn++;	break;	}
			//*****************************************************************
			

			//******************COLOUMN OF ROW 4 *************************
			if(row==4&&coloumn==7)coloumn=0;
			if(row==4&&coloumn==0)	
			{	bU();	coloumn++;	break;	}
			else if(row==4&&coloumn==1)
			{	MainWindow.BtnV.setBackground(Color.GREEN);	MainWindow.BtnU.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==4&&coloumn==2)
			{	MainWindow.BtnW.setBackground(Color.GREEN);	MainWindow.BtnV.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==4&&coloumn==3)
			{	MainWindow.BtnX.setBackground(Color.GREEN);	MainWindow.BtnW.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==4&&coloumn==4)
			{	MainWindow.BtnY.setBackground(Color.GREEN);	MainWindow.BtnX.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==4&&coloumn==5)
			{	MainWindow.BtnZ.setBackground(Color.GREEN);	MainWindow.BtnY.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==4&&coloumn==6)
			{	MainWindow.BtnNumber.setBackground(Color.GREEN);	MainWindow.BtnZ.setBackground(Color.WHITE);	coloumn++;	break;	}
			//*****************************************************************
			

			//******************COLOUMN OF ROW 5 *************************
			if(row==5&&coloumn==7)coloumn=0;
			if(row==5&&coloumn==0)	
			{	bSpace();	coloumn++;	break;	}
			else if(row==5&&coloumn==1)
			{	MainWindow.BtnBackspace.setBackground(Color.GREEN);	MainWindow.BtnSpace.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==5&&coloumn==2)
			{	MainWindow.BtnTalk.setBackground(Color.GREEN);	MainWindow.BtnBackspace.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==5&&coloumn==3)
			{	MainWindow.BtnOCTalk.setBackground(Color.GREEN);	MainWindow.BtnTalk.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==5&&coloumn==4)
			{	MainWindow.BtnMouse.setBackground(Color.GREEN);	MainWindow.BtnOCTalk.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==5&&coloumn==5)
			{	MainWindow.BtnShortCut.setBackground(Color.GREEN);	MainWindow.BtnMouse.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==5&&coloumn==6)
			{	MainWindow.BtnNavigation.setBackground(Color.GREEN);	MainWindow.BtnShortCut.setBackground(Color.WHITE);	coloumn++;	break;	}
			//*****************************************************************
			
			//******************COLOUMN OF ROW 6 *************************
			if(row==6&&coloumn==7)coloumn=0;
			if(row==6&&coloumn==0)	
			{	bOtherButton();	coloumn++;	break;	}
			else if(row==6&&coloumn==1)
			{	MainWindow.BtnSS1.setBackground(Color.GREEN);	MainWindow.BtnOtherButton.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==6&&coloumn==2)
			{	MainWindow.BtnSS2.setBackground(Color.GREEN);	MainWindow.BtnSS1.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==6&&coloumn==3)
			{	MainWindow.BtnSS3.setBackground(Color.GREEN);	MainWindow.BtnSS2.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==6&&coloumn==4)
			{	MainWindow.BtnOpenApp.setBackground(Color.GREEN);	MainWindow.BtnSS3.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==6&&coloumn==5)
			{	MainWindow.BtnSwitchApp.setBackground(Color.GREEN);	MainWindow.BtnOpenApp.setBackground(Color.WHITE);	coloumn++;	break;	}
			else if(row==6&&coloumn==6)
			{	MainWindow.BtnSetting.setBackground(Color.GREEN);	MainWindow.BtnSwitchApp.setBackground(Color.WHITE);	coloumn++;	break;	}
			//*****************************************************************
			
			//********************************LIST VALUE SELCTION**************************************
			if(row==1&&coloumn==1&&MainWindow.listvalue==true)
			{
				ListModel LM=MainWindow.list.getModel();
				int n=LM.getSize();
				if(index==n-1)index=-1;
				index++;
				MainWindow.ListField.setText((String)LM.getElementAt(index));
				break;
			}
			break;
			//**************************************************************************************
		}
	}
	public static void selection()
	{
		while(true)
		{
			//****************ROW SELECTION*****************************************
			if(row==1&&coloumn==-1)//ROW1
			{	coloumn++;	break;	}
			else if(row==2&&coloumn==-1)//ROW2
			{	coloumn++;	break;	}
			else if(row==3&&coloumn==-1)//ROW3
			{	coloumn++;	break;	}
			else if(row==4&&coloumn==-1)//ROW4
			{	coloumn++;	break;	}
			else if(row==5&&coloumn==-1)//ROW5
			{	coloumn++;	break;	}
			else if(row==6&&coloumn==-1)//ROW6
			{	coloumn++;	break;	}
			//*********************************************************************
			
			//******************COLOUMN SELECTION OF ROW1**********************************
			if(row==1&&coloumn==1&&MainWindow.listvalue==false)
			{	MainWindow.listvalue=true;	break;	}
			if(row==1&&coloumn==1&&MainWindow.listvalue==true)//list value selection
			{	
				String selectedValue=MainWindow.ListField.getText();
				if(MainWindow.whichList.equals("Letters"))
				{
					try 
					{
						MainWindow.handleLetters(selectedValue);
					} catch (AWTException e1) {	e1.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("OpenApp"))
					MainWindow.handleOpenApp(selectedValue);
				if(MainWindow.whichList.equals("MouseClick"))
					MainWindow.handleMouseClick(selectedValue);
				if(MainWindow.whichList.equals("Shortcuts"))
				{
					try 
					{
						MainWindow.handleShortcuts(selectedValue);
					} catch (AWTException e1) {	e1.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("Numbers"))
				{
					try 
					{
						MainWindow.handleNumbers(selectedValue);
					} 
					catch (AWTException e2) {	e2.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("Navigation"))
				{
					 try 
					 {
						MainWindow.handleNavigation(selectedValue);
					 } 
					 catch (AWTException e3) {	e3.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("OtherButton"))
				{
					try 
					{
						MainWindow.handleOtherButton(selectedValue);
					} catch (AWTException e4) {	e4.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("SS1"))
				{
					try 
					{
						MainWindow.handleSS1(selectedValue);
					} catch (AWTException e5) {	e5.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("SS2"))
				{
					try 
					{
						MainWindow.handleSS2(selectedValue);
					} catch (AWTException e6) {	e6.printStackTrace();	}
				}				
				if(MainWindow.whichList.equals("SS3"))
				{
					try 
					{
						MainWindow.handleSS3(selectedValue);
					} catch (AWTException e7) {	e7.printStackTrace();	}
				}
				if(MainWindow.whichList.equals("SwitchApp"))
				{
					 try 
					 {
						MainWindow.handleSwitchApp(selectedValue);
					 } catch (AWTException e8) {	e8.printStackTrace();	}
				}
				MainWindow.words="";	
				reset(MainWindow.BtnList);	MainWindow.listvalue=false;	
				MainWindow.ListField.setText(null);	
				index=-1;	break;	
			}
			if(row==1&&coloumn==2)
			{	ButtonAction.pressA();	reset(MainWindow.BtnA);	break;	}
			if(row==1&&coloumn==3)
			{	ButtonAction.pressB();	reset(MainWindow.BtnB);	break;	}	
			if(row==1&&coloumn==4)
			{	ButtonAction.pressC();	reset(MainWindow.BtnC);	break;	}
			if(row==1&&coloumn==5)
			{	ButtonAction.pressD();	reset(MainWindow.BtnD);	break;	}
			if(row==1&&coloumn==6)
			{	ButtonAction.pressE();	reset(MainWindow.BtnE);	break;	}	
			if(row==1&&coloumn==7)
			{	ButtonAction.pressF();	reset(MainWindow.BtnF);	break;	}	
			//****************************************************************
			
			//******************COLOUMN SELECTION OF ROW2**********************************
			if(row==2&&coloumn==1)
			{	ButtonAction.pressG();	reset(MainWindow.BtnG);	break;	}
			if(row==2&&coloumn==2)
			{	ButtonAction.pressH();	reset(MainWindow.BtnH);	break;	}
			if(row==2&&coloumn==3)
			{	ButtonAction.pressI();	reset(MainWindow.BtnI);	break;	}	
			if(row==2&&coloumn==4)
			{	ButtonAction.pressJ();	reset(MainWindow.BtnJ);	break;	}
			if(row==2&&coloumn==5)
			{	ButtonAction.pressK();	reset(MainWindow.BtnK);	break;	}
			if(row==2&&coloumn==6)
			{	ButtonAction.pressL();	reset(MainWindow.BtnL);	break;	}	
			if(row==2&&coloumn==7)
			{	ButtonAction.pressM();	reset(MainWindow.BtnM);	break;	}	
			//****************************************************************
			
			//******************COLOUMN SELECTION OF ROW3**********************************
			if(row==3&&coloumn==1)
			{	ButtonAction.pressN();	reset(MainWindow.BtnN);	break;	}
			if(row==3&&coloumn==2)
			{	ButtonAction.pressO();	reset(MainWindow.BtnO);	break;	}
			if(row==3&&coloumn==3)
			{	ButtonAction.pressP();	reset(MainWindow.BtnP);	break;	}	
			if(row==3&&coloumn==4)
			{	ButtonAction.pressQ();	reset(MainWindow.BtnQ);	break;	}
			if(row==3&&coloumn==5)
			{	ButtonAction.pressR();	reset(MainWindow.BtnR);	break;	}
			if(row==3&&coloumn==6)
			{	ButtonAction.pressS();	reset(MainWindow.BtnS);	break;	}	
			if(row==3&&coloumn==7)
			{	ButtonAction.pressT();	reset(MainWindow.BtnT);	break;	}	
			//****************************************************************
			
			//******************COLOUMN SELECTION OF ROW4**********************************
			if(row==4&&coloumn==1)
			{	ButtonAction.pressU();	reset(MainWindow.BtnU);	break;	}
			if(row==4&&coloumn==2)
			{	ButtonAction.pressV();	reset(MainWindow.BtnV);	break;	}
			if(row==4&&coloumn==3)
			{	ButtonAction.pressW();	reset(MainWindow.BtnW);	break;	}	
			if(row==4&&coloumn==4)
			{	ButtonAction.pressX();	reset(MainWindow.BtnX);	break;	}
			if(row==4&&coloumn==5)
			{	ButtonAction.pressY();	reset(MainWindow.BtnY);	break;	}
			if(row==4&&coloumn==6)
			{	ButtonAction.pressZ();	reset(MainWindow.BtnZ);	break;	}	
			if(row==4&&coloumn==7)
			{	ButtonAction.pressNumbers();	reset(MainWindow.BtnNumber);	break;	}	
			//****************************************************************
			
			//******************COLOUMN SELECTION OF ROW5**********************************
			if(row==5&&coloumn==1)
			{	ButtonAction.pressSpace();	reset(MainWindow.BtnSpace);	break;	}
			if(row==5&&coloumn==2)
			{	ButtonAction.pressBackspace();	reset(MainWindow.BtnBackspace);	break;	}
			if(row==5&&coloumn==3)
			{	ButtonAction.pressTalk();	reset(MainWindow.BtnTalk);	break;	}	
			if(row==5&&coloumn==4)
			{	ButtonAction.pressOpenCloseTalk();	reset(MainWindow.BtnOCTalk);	break;	}
			if(row==5&&coloumn==5)
			{	ButtonAction.pressMouseClick();	reset(MainWindow.BtnMouse);	break;	}
			if(row==5&&coloumn==6)
			{	ButtonAction.pressShortcut();	reset(MainWindow.BtnShortCut);	break;	}	
			if(row==5&&coloumn==7)
			{	ButtonAction.pressNavigation();	reset(MainWindow.BtnNavigation);	break;	}	
			//****************************************************************
			
			//******************COLOUMN SELECTION OF ROW6**********************************
			if(row==6&&coloumn==1)
			{	ButtonAction.pressOtherButton();	reset(MainWindow.BtnOtherButton);	break;	}
			if(row==6&&coloumn==2)
			{	ButtonAction.pressSS1();	reset(MainWindow.BtnSS1);	break;	}
			if(row==6&&coloumn==3)
			{	ButtonAction.pressSS2();	reset(MainWindow.BtnSS2);	break;	}	
			if(row==6&&coloumn==4)
			{	ButtonAction.pressSS3();	reset(MainWindow.BtnSS3);	break;	}
			if(row==6&&coloumn==5)
			{	ButtonAction.pressOpenApp();	reset(MainWindow.BtnOpenApp);	break;	}
			if(row==6&&coloumn==6)
			{	ButtonAction.pressSwitchApp();	reset(MainWindow.BtnSwitchApp);	break;	}	
			if(row==6&&coloumn==7)
			{	ButtonAction.pressOpenCloseSetting();	reset(MainWindow.BtnSetting);	break;	}	
			//****************************************************************
			
			break;
		}
		MainWindow.ans=false;
	}
}
