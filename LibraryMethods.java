package Library_Tools;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;
public class LibraryMethods 
{
	public static Connection getConn() {
		try {
			//Registering Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String username="root";
			String url="jdbc:mysql://localhost/MY_LIB";
			String pass="**********";		//Enter Your Password
			
			//Connecting to the database 
			Connection conn = DriverManager.getConnection(url,username,pass);
			return conn;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean userIdValidater(String s)
	{
		//remove trailing spaces
		s=s.trim();
		int at=0,com=0;
		//checking for first character not numeric
		if(s.length()>0 && s.charAt(0)>=48 && s.charAt(0)<=57)
			return false;
		for(int i=0;i<s.length();++i) {
			//checking for valid and invalid character
			if(s.charAt(i)==' ')
				return false;
			else if(s.charAt(i)=='@')
				at=1;
			else if(s.charAt(i)=='.' && at==1) {
				com=1;
			}	
		}
		if(com==1 && at==1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean passValidater(String s)
	{
		if(s.length()>=8)
		{
			int digit=0,alpha=0,spl=0;
			for(int i=0;i<s.length();++i) {
				if(s.charAt(i)>=48 && s.charAt(i)<=57)
					digit=1;
				else if((s.charAt(i)>=65 && s.charAt(i)<=90) || (s.charAt(i)>=97 && s.charAt(i)<=122))
					alpha=1;
				else
					spl=1;
			}
			if(digit==1 && alpha==1 && spl==1)
				return true;
			else
				return false;
		}
		return false;
	}
	public static boolean removeBook(String bookname)
	{
		bookname = bookname.toUpperCase();
		try{
			Connection conn = getConn();
			Statement stmt =conn.createStatement();
		    stmt.executeUpdate("DELETE FROM bookdetail WHERE BookName='"+bookname+"'");
		    return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String[][] getUserBookDetail(String user)
	{
		String st[][]=new String[100][6];
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+user);
			int i=0;
			while(rs.next()) {
				for(int j=0;j<=5;++j) {
					st[i][j]=rs.getString(j+1);
				}
				++i;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	public static boolean adminCheck(String user,String Pass)
	{
		try {
			Connection conn =getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM loginadmin");
			while(rs.next()) {
				if(rs.getString(1).equals(user) && rs.getString(2).equals(Pass))
					return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean addBook(String book_id,String book_name,String book_auth,String book_stream,String book_status)
	{
		try
		{
			String arr[]= {book_id.toUpperCase(),book_name.toUpperCase(),book_auth.toUpperCase(),book_stream.toUpperCase(),book_status.toUpperCase()};
			Connection conn= getConn();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookdetail VALUES(?,?,?,?,?)");
			for(int i=0;i<arr.length;++i) {
				stmt.setString(i+1,arr[i]);
			}
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean createUser(String user,String pass)
	{
		try {
			Connection conn = getConn();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO logininf VALUES(?,?)");
			stmt.setString(1,user);
			stmt.setString(2,pass);
			stmt.close();
			conn.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean userCheck(String id,String pass) throws IOException
	{
		try {
		Connection conn = getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM logininf");
		while(rs.next())
		{
			if(rs.getString(1).equals(id) && rs.getString(2).equals(pass)) {
				return true;
			}
		}
		conn.close();
		}
		catch(Exception e) {}
		return false;
	}
	public static void setPass(String user,String newPass)
	{
		try {
			Connection conn = getConn();
			PreparedStatement stmt = conn.prepareStatement("UPDATE logininf SET pass=? WHERE username=?");
			stmt.setString(1,newPass);
			stmt.setString(2,user);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean addUser(String name,String pass)
	{
		try {
			Connection conn = getConn();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO logininf VALUES(?,?)");
			stmt.setString(1,name);
			stmt.setString(2,pass);
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String[][] getBookList()
	{
		String booklist[][]=new String[100][5];
		try
		{
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bookdetail");
			int i=0;
			while(rs.next()) {
				for(int j=0;j<5;++j) {
					booklist[i][j] = rs.getString(j+1);
				}
				++i;
			}
			rs.close();
			stmt.close();
			conn.close();
			return booklist;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args)
	{
		
	}
}
