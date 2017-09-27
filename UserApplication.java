import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import jdk.nashorn.internal.scripts.JO;

public class UserApplication {
	public static ArrayList<User> users = new ArrayList<User>();
	public static void main(String[] args) throws IOException {
		showOptions();

	}
	private static void showOptions() throws IOException {
		String filename = "Users.txt";
		String [] elements, options = {"Login", "Register", "Forgot Password", "Delete Account","Exit"};
		File aFile;
		boolean valid=false;
		Scanner in;
		aFile = new File(filename);
		String input = (String)JOptionPane.showInputDialog(null, "Select an option", "Account", 1, null, options, options[0]);
		if(aFile.exists())
		{
			in = new Scanner(aFile);
			while(in.hasNext())
			{
				elements= (in.nextLine()).split(",");
				users.add(new User(elements[0],elements[1], elements[2], elements[3]));
			}
			valid=true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Attempting to create new file...");
			JOptionPane.showMessageDialog(null, "Error, could not create new file.");
		}
		if(valid)
		{
			if(input==null)
			{
				JOptionPane.showMessageDialog(null, "Goodbye!");
			}
			else if(input.matches("Login"))
			{
				logon();
			}
			else if(input.matches("Register"))
			{
				register();
			}
			else if(input.matches("Forgot Password"))
			{
				forgotPassword();
			}
			else if(input.matches("Delete Account"))
			{
				deleteAccount();
			}
			else if(input.matches("Exit"))
			{
				
			}
			else
			{
				
			}
				
			
			
		}
		
	}
	public static void deleteAccount() throws IOException{
		String username = JOptionPane.showInputDialog("Please enter your username that you wish to delete");
		String password = JOptionPane.showInputDialog("Please enter your password that you wish to delete");
		String email = JOptionPane.showInputDialog("Please enter your email with the associated account");
		int found = 0;
		
		boolean logon = false; boolean pass = (password(password));
		for(int i=0; i<users.size()&&!logon; i++)
		{
			if(pass&&(username.matches(users.get(i).getUsername()))&&(email.matches(users.get(i).getEmail()))&&(password.matches(users.get(i).getPassword())))
			{
				System.out.println(users.get(i).getUsername());
				logon=true;
				found=i;
				
			}
		}
		String [] yesorno = {"yes", "no"};
		for(int j=0; j<users.size()&&logon;j++)
		{
		
			users.remove(found);
			String filename = "Users.txt";
			PrintWriter out;
			File aFile = new File(filename);
			out = new PrintWriter(aFile);
			out.println(users.get(j).getUsername()+","+users.get(j).getNickname()+","+users.get(j).getPassword()+","+users.get(j).getEmail());
			out.close();
			
			
		}
		if(logon)
		{
			String input = (String )JOptionPane.showInputDialog(null, "Account sucessfully deleted! Would you like to create a new account?","Deleting an account",1,null,yesorno, yesorno[0]);
			if(input.matches("yes"))
			{
				register();
			}
			else if(input.matches("no"))
			{
				showOptions();
			}
			else if(input.matches(null))
			{
				showOptions();	
			}
			else
			{
				showOptions();
			}
		
		}
		else
		{
			showOptions();
		}
		
		
	}
	public static void forgotPassword()throws IOException {
		String username = JOptionPane.showInputDialog("Please enter your username");
		String email = JOptionPane.showInputDialog("Please enter your email");
		String nickname = JOptionPane.showInputDialog("Please enter your nickname"), input = null;
		String [] yesorno = {"yes", "no"};
		int found=0;
		boolean logon = false;
		if(username.matches(nickname))
		{
			for(int i=0; i<users.size()&&!logon; i++)
			{
			
				if((username.matches(users.get(i).getUsername()))&&(email.matches(users.get(i).getEmail()))&&(nickname.matches(users.get(i).getNickname())))
				{
					System.out.println(users.get(i).getUsername());
					logon=true;
					found = i;
				}
			}
			if(logon)
			{
				JOptionPane.showMessageDialog(null, username + " your password is \n" + users.get(found).getPassword());
				input = (String)JOptionPane.showInputDialog(null, "Would you like to logon now?", "Logon", 1, null, yesorno, yesorno[0]);
				if(input.matches("yes"))
				{
					logon();
				}
				else if(input.matches("no"))
				{
					showOptions();
				}
				else if(input.matches(null))
				{
					showOptions();	
				}
				input=null;
			}
			else
			{
				input = (String)JOptionPane.showInputDialog(null, "Invalid creditials, would you like to try again?", "Logon", 1, null, yesorno, yesorno[0]);
		
				if(input.matches("yes"))
				{
					forgotPassword();
				}
				else if(input.matches("no"))
				{
					showOptions();
				}
				else if(input.matches(null))
				{
					showOptions();	
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Username and password you entered matched.");
			showOptions();
		}
}	
	public static void register() throws IOException{
		String username = JOptionPane.showInputDialog("Please enter a username");
		String password = JOptionPane.showInputDialog("Please enter a password");
		String email = JOptionPane.showInputDialog("Please enter a email");
		String nickname = JOptionPane.showInputDialog("Please enter a nickname");
		boolean unique=unique(username, nickname);
		if(unique)
		{
			users.add(new User(username, nickname,password,email));
		}
		if(username.matches(null)&&password.matches(null)&&nickname.matches(null)&&email.matches(null)){
		String filename = "Users.txt";
		PrintWriter out;
		File aFile = new File(filename);
		out = new PrintWriter(aFile); boolean pass = password(password);
		for(int i=0; i<users.size();i++)
		{
			out.println(users.get(i).getUsername()+","+users.get(i).getNickname()+","+users.get(i).getPassword()+","+users.get(i).getEmail());
		}
		if(pass&&unique)
		{
			JOptionPane.showMessageDialog(null, "Sucessfully created account!");
			showOptions();
		}
		else
		{
			showOptions();
		}
		out.close();
	}
}
	private static boolean unique(String username, String nickname) {
		boolean unique=true;
		if(!(username.matches(nickname)))
		{
			for(int i=0; i<users.size();i++)
			{
				if((username.matches(users.get(i).getUsername()))||(nickname.matches(users.get(i).getNickname())))
				{
					unique=false;
				}
			}
		}
		if(!unique)
		{
			JOptionPane.showMessageDialog(null, "username or nickname not unique");
		}	
		
		return unique;
	}
	public static void logon()throws IOException {
		String username = JOptionPane.showInputDialog("Please enter your username");
		String password = JOptionPane.showInputDialog("Please enter your password"), input = null;
		String [] yesorno = {"yes", "no"};
		boolean logon = false; boolean pass = password(password);
		for(int i=0; i<users.size()&&!logon; i++)
		{
			
			if((username.matches(users.get(i).getUsername()))&&(password.matches(users.get(i).getPassword())))
			{
				logon=true;
			}
		}
		if(logon&&pass)
		{
			JOptionPane.showMessageDialog(null, "You sucessfully logged on " + username);
		}
		else
		{
			input = (String)JOptionPane.showInputDialog(null, "Invalid creditials, would you like to try again?", "Logon", 1, null, yesorno, yesorno[0]);
			if(input.matches("yes"))
			{
				logon();
			}
			else if(input.matches("no"))
			{
				showOptions();
			}
			else if(input.matches(null))
			{
				showOptions();
			}
			else
			{
				showOptions();
			}
		}
	}
	public static boolean password(String pass)
	{
									boolean length = false;
		String pattern = "[A-Z]"; 	boolean oneUppercase=false;
		String pattern1 = "[a-z]";	boolean oneLowercase=false;
		String pattern2 = "[0-9]"; 	boolean oneDigit=false;
		String pattern3 = " ";		boolean hasSpace=true;
		if((pass.length()>5)&&(pass.length()<11))
		{
			length=true;
		}
		for(int i=0; i<pass.length();i++)
		{
			if((pass.substring(i, i+1)).matches(pattern)&&!oneUppercase)
			{
				oneUppercase=true;
			}
			if((pass.substring(i, i+1)).matches(pattern1)&&!oneLowercase)
			{
				oneLowercase=true;
			}
			if((pass.substring(i, i+1)).matches(pattern2)&&!oneDigit)
			{
				oneDigit=true;
			}
			if((pass.substring(i, i+1)).matches(pattern3)&&hasSpace)
			{
				hasSpace=false;
			}
		}
		String results="Erros in password:\n";
		if(!oneUppercase)
		{
			results+="You need at least one upper case letter.\n";
		}
		if(!oneLowercase)
		{
			results+="You need at least one lower case letter.\n";		
		}
		if(!oneDigit)
		{
			results+="You need at least one digit case letter.\n";
		}
		if(!length)
		{
			results+="Password needs to be between 6-10 characters long.\n";
		}
		if(hasSpace)
		{
			results+="Cannot contain Spaces.\n";
		}
		if(length && oneDigit && oneLowercase && oneUppercase && hasSpace)
		{
			return true;
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, results);
			return false;
		}
}
}








