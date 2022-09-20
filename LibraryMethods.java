package Library_Tools;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
public class LibraryMethods 
{
	public static boolean userIdValidater(String s)
	{
		String arr[]=s.trim().split("\\s+"); //Since we are spliting via space character
		if(s.length()<100)
		{
			try 
			{
				if(arr[1]==arr[1])
					{
						System.out.println("Please Remove Space Or instead use Underscore");
						return false;
					}
			}
			catch(IndexOutOfBoundsException e) {};
			arr=s.trim().split("@");
			try
			{
				if(arr[1]==arr[1])
				{
					System.out.println("UserId set");
					return true;
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("@ Missing !");
				return false;
			};
		}
		else
		{
			System.out.println("Characters Should be less than 18");
			return false;
		}
		return false;
	}
	public static boolean passValidater(String s)
	{
		if(s.length()>=8)
		{
		int i;
		int spl[]={33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,58,59,60,61,62,63,64,91,92,93,94,95,96,123,124,125,126};
		for(i=48;i<=57;i++)
			if(s.contains(String.valueOf((char)i)))
				break;
		if(i==58)
			{
				System.out.println("Digits are missing");
				return false;
			}
		for(i=65;i<=90;i++)
			if(s.contains(String.valueOf((char)i)))
				break;
		if(i==91)
			{
				System.out.println("Capital Letter is Missing");
				return false;
			}
		for(i=97;i<=122;i++)
			if(s.contains(String.valueOf((char)i)))
				break;
		if(i==123)
		{
			System.out.println("LowerCase Alphabel is Missing!");
			return false;
		}
		try
		{
			for(i=0;i<100;i++)
			{
				if(s.contains(String.valueOf((char)spl[i])))
				{
					return true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Special Character Missing!");
			return false;
		}
		}
		else
		{
			System.out.println("Length should be greater than or equal to 8");
		}
		return false;
	}
	public static boolean removeBook(String bookn)
	{
		int c=0,m=-1,len=0;
		String st[][]=getBookList();
		while(st[c][1]!=null)
		{
			if(st[c][1].toUpperCase().equals(bookn.toUpperCase()))
			{
				m=c;
			}
			c++;
		}
		len=c;
		if(m!=-1)
		{
			try
			{
			FileWriter fw=new FileWriter("D:\\OOP_Project\\Book_Details.txt");
			c=0;
			while(c<len)
			{
				if(c==m)
				{
					c++;
					continue;
				}
				if(c==len-1)
					{
					for(int i=0;i<5;i++)
						{
							if(i!=4)
								fw.write(st[c][i]+"\n");
							if(i==4)
								{
									fw.write(st[c][i]);
									break;
								}
						}
					}
				else
					{
					if(c!=0)
						fw.write("\n");
					for(int i=0;i<5;i++)
					
					{
						if(i!=4)
							fw.write(st[c][i]+"\n");
						else
							fw.write(st[c][i]);
					}
					}
				c++;
			}
			fw.close();
			return true;
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public static String[][] getUserBookDetail(String user) throws IOException
	{
		String st[][]=new String[100][6];
		String userf[]=user.split("@");
		File fr = new File("D:\\OOP_Project\\"+userf[0]+".txt");
		Scanner s=new Scanner(fr);
		int i=0;
		while(s.hasNextLine())
		{
			for(int j=0;j<=5;j++)
			{
				st[i][j]=s.nextLine();
			}
			i++;
		}
		s.close();
		return st;
	}
	public static boolean adminCheck(String user,String Pass) throws IOException
	{
		File fr = new File("D:\\OOP_Project\\login_admin.txt");
		Scanner s =new Scanner(fr);
		while(s.hasNextLine())
		{
			if(s.nextLine().equals(user))
			{
				if(s.nextLine().equals(Pass))
					{
						s.close();
						return true;
					}
				else
					{
						s.close();
						return false;
					}
			}
		}
		s.close();
		return false;
	}
	public static boolean addBook(String bookn,String booki,String booka,String books,String bookav)
	{
		try
		{
			FileWriter fw =new FileWriter("D:\\OOP_Project\\Book_Details.txt",true);
			fw.write("\n"+booki);
			fw.write("\n"+bookn);
			fw.write("\n"+booka);
			fw.write("\n"+books);
			fw.write("\n"+bookav);
			fw.close();
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	public static boolean createUser(String user,String pass) throws IOException
	{
		FileWriter fw =new FileWriter("D:\\OOP_Project\\login_admin.txt",true);
		fw.write("\n"+user+"\n");
		fw.write(pass);
		fw.close();
		return true;
	}
	public static boolean userCheck(String id,String pass) throws IOException
	{
		int alt=0;
		String login_user="D:\\OOP_Project\\login_inf.txt";
		File fr =new File(login_user);
		ArrayList<String> userid = new ArrayList<String>();
		ArrayList<String> userpass =new ArrayList<String>();
		Scanner s= new Scanner(fr);
		while(s.hasNextLine())
		{
			if(alt==0)
			{
				userid.add(s.nextLine());
				alt=1;
			}
			else if(alt==1)
			{
				userpass.add(s.nextLine());
				alt=0;
			}
		}
		s.close();
		if(userid.contains(id))
		{
			if(userpass.get(userid.indexOf(id)).equals(pass))
				{
					return true;
				}
			else
				return false;
		}
		else
			return false;
	}
	public static void setPass(String user,String newPass) throws IOException
	{
		int count=0,st=0;
		ArrayList<String> lst =new ArrayList<String>();
		File fr =new File("D:\\OOP_Project\\login_inf.txt");
		Scanner s =new Scanner(fr);
		while(s.hasNextLine())
		{
		    lst.add(s.nextLine());
		    count++;
		}
		s.close();
		FileWriter fw =new FileWriter("D:\\OOP_Project\\login_inf.txt");
		while(st<count)
		{
			if(lst.get(st).equals(user))
			{
				fw.write(lst.get(st++)+"\n");
				fw.write(newPass+"\n");
			}
			else
				fw.write(lst.get(st)+"\n");
			st++;
		}
		fw.close();
	}
	public static boolean addUser(String n,String p)
	{
		try 
		{
			FileWriter fw =new FileWriter("D:\\OOP_Project\\login_inf.txt",true);
			fw.write(n);
			fw.write("\n"+p+"\n");
			fw.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static String[][] getBookList()
	{
		String booklist[][]=new String[100][5];
		try
		{
		String Book_List="D:\\OOP_Project\\Book_Details.txt";
		File fr = new File(Book_List);
		Scanner s =new Scanner(fr);
		int i=0;
		while(s.hasNextLine())
		{
			for(int j=0;j<5;j++)
			{
				booklist[i][j]=s.nextLine();
			}
			i++;
		}
		s.close();
		return booklist;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args)
	{
	//	try
		//{
	//	System.out.println(userCheck("aniketkr08903@gmail.com","Passis@1"));
	//	}
	//	catch(Exception e) {};
		//try
	//	{
		//	String st[][]=getUserBookDetail("aniketkr08903");
		//	System.out.println(st[0][0]+" "+st[0][1]);
	//	}
	//	catch(Exception e) 
	//	{
		//	e.printStackTrace();
	//	};
	//	Scanner s1 =new Scanner(System.in);
	//	System.out.println(passValidater("abcdefghiJj1@"));
	//	ArrayList<String> userarr = new ArrayList<String>();
	//	ArrayList<String> passarr = new ArrayList<String>();
		/*try
		{
			System.out.println(userCheck("aniketkr08903@gmail.com","Passwordisthis"));
		}
		catch(Exception e)
		{
			
		};*/
	/*	String login_user="D:\\OOP_Project\\login_inf.txt";
		String Book_Details="D:\\OOP_Project\\Book_Details.txt";
		try
		{
		File  fr = new File(login_user);
		File fr1 =new File(Book_Details);
		Scanner s =new Scanner(fr);
		Scanner s2 =new Scanner(fr1);
		while(s.hasNextLine())
		{
			if(alt==0)
				{
					userarr.add(s.nextLine());
					alt=1;
				}
			else if(alt==1)
			{
				passarr.add(s.nextLine());
				alt=0;
			}
		}
		int j=0;
		while(s2.hasNextLine())
		{
			for(int i=0;i<7;i++)
				book_list[j][i]=s2.nextLine();
		}
		for(int i=0;i<7;i++)
			System.out.println(book_list[0][i]);
		System.out.println(userarr+"\n"+passarr);
		String userid =s1.next();
		String passw=s1.next();
		if(userarr.contains(userid))
		{
			if(passarr.get(userarr.indexOf(userid)).equals(passw))
				System.out.println("Correct UserId and Password");
			else
				System.out.println("UserId or Password is incorrect !");
		}
		else
			System.out.println("UserId or Password is incorect !");
		
		s.close();
		s1.close();
		s2.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
	}
}
