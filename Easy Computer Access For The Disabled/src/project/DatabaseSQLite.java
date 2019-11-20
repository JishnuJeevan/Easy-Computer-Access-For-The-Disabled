package project;

// This file is used to connect the application to the database.

/* For this class to work do the following.
 * 1. In the "resource" folder there will be "libraries" folder.
 * 2. In "libraries" folder there will be a "sqlite-jdbc-3.16.1.jar" file.
 * 3. Configure JRE System Library and add the "sqlite-jdbc-3.16.1.jar" file to the JRE System library.
*/

import java.sql.*;

public class DatabaseSQLite 
{
	private static Connection conn;
	private static boolean hasData=false;
	private static Statement state;
	private static ResultSet result;
	private static PreparedStatement pst;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		Class.forName("org.sqlite.JDBC");
		conn=DriverManager.getConnection("jdbc:sqlite:JAWF.sqlite");
		initialize();
		System.out.println("Connection sucessful");
		return conn;
	}

	public static void initialize() throws SQLException
	{
		if(hasData==false)
		{
			hasData=true;
			// For table Application
			
			state=conn.createStatement();
			// Check if a table named "Applications" exist.
			result=state.executeQuery(" select name from sqlite_master where type=\"table\" and name=\"Application\"; ");
			
			// If it does not exist, create a new one.
			if(result.next()==false)
			{
				// create the table Application
				state=conn.createStatement();
				state.execute(" create table Application (Path text,Name text,Count integer); ");
				
			}
			
			// For table Shortcut
			state=conn.createStatement();
			
			// Check if a table named "Shortcut" exist. 
			result=state.executeQuery(" select name from sqlite_master where type=\"table\" and name=\"Shortcut\"; ");
			
			// If it does not exist, create a new one, and add some short cut functions in it.
			if(result.next()==false)
			{
				// create the table Shortcut
				state=conn.createStatement();
				state.execute(" create table Shortcut (Shortcut text,Name text,Count integer); ");
				
				// Insert into Shortcut
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Alt + F4");
				pst.setString(2, "Close");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + a");
				pst.setString(2, "Select All");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + x");
				pst.setString(2, "Cut");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + v");
				pst.setString(2, "Paste");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + c");
				pst.setString(2, "Copy");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + z");
				pst.setString(2, "Undo");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + y");
				pst.setString(2, "Redo");
				pst.setInt(3,0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Shortcut values(?,?,?);");
				pst.setString(1, "Ctrl + s");
				pst.setString(2, "Save");
				pst.setInt(3,0);
				pst.execute();
				
			}
			
			// For Words
			state=conn.createStatement();
			// Check if the table "Words" exist.
			result=state.executeQuery(" select name from sqlite_master where type=\"table\" and name=\"Words\"; ");
			
			// If it doesn't, create a new one and add a few words to the database.
			if(result.next()==false)
			{
				// create the table Words
				state=conn.createStatement();
				state.execute(" create table Words (Words text,count integer); ");
				
				// insert some data into Words
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "HUNGRY");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "WATER");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "MEDICINE");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "NAME");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "HELLO");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "HELP");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "FOOD");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "LIGHT");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "FAN");
				pst.setInt(2, 0);
				pst.execute();
				
				pst=conn.prepareStatement("insert into Words values(?,?);");
				pst.setString(1, "TELEVISION");
				pst.setInt(2, 0);
				pst.execute();
				
			}
			
			// For SystemConfiguration
			state=conn.createStatement();
			
			// Check if the table "SystemConfiguration" exist. 
			result=state.executeQuery(" select name from sqlite_master where type=\"table\" and name=\"SystemConfiguration\"; ");
			
			// If it doesn't create 
			if(result.next()==false)
			{
					// create the table SystemConfiguration and initialize it with the default configurations.
					state=conn.createStatement();
					state.execute(" create table SystemConfiguration (ScannMethod text,ScannTime integer,MouseScannTime integer,VoiceType text); ");
					
					pst=conn.prepareStatement("insert into SystemConfiguration values(?,?,?,?);");
					pst.setString(1, "Step Mouse");
					pst.setInt(2, 1000);
					pst.setInt(3,20);
					pst.setString(4, "cmu-rms-hsmm");
					pst.execute();
			}
		}
	}
}
