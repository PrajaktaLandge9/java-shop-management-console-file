package user_management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class UserManagement {
	static ArrayList<User> al = new ArrayList<>();
	static {
		try {
			loadDataFromFileToArrayList();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void userManagement() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		boolean CanIKeepRunningTheProgram = true;
		while(CanIKeepRunningTheProgram == true) 
		{
			System.out.println("\n Welcome to user management !");
			System.out.println("\n Select one of the options given below :");
			System.out.println("1. Add user : ");
			System.out.println("2. Edit user : " );
			System.out.println("3. Delete user : ");
			System.out.println("4. Search user : ");
			System.out.println("5. Quit");
			int OptionSelectedByUser = sc.nextInt();
			if(OptionSelectedByUser == UserOptions.QUIT)
			{
				File file = new File("\\Users\\PRAJAKTA\\eclipse-workspace\\ShopManagement\\src\\user_management\\UserData.csv");
				FileWriter fileWriter = new FileWriter(file, false); 
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for(User user: al)
				{
					bufferedWriter.write(user.UserName+","+user.LoginName+","+user.UserRole+","+user.Password+"\n");
				}
				bufferedWriter.close(); //The data in ram is written into harddisk.
				fileWriter.close();
				file = null;
				System.out.println("\n User management application closed !");
				CanIKeepRunningTheProgram = false;
			}
			else if(OptionSelectedByUser == UserOptions.ADD_USER) //add user
			{
				adduser();
			}
			else if(OptionSelectedByUser == UserOptions.SEARCH_USER) // search user
			{
				System.out.println("\n Enter user to be searched : \n"); 
				sc.nextLine();                                          // Consume the newline character left from previous input.
				String SearchUserName = sc.nextLine();
				searchuser(SearchUserName);
			}
			else if(OptionSelectedByUser == UserOptions.DELETE_USER) // delete user
			{
				System.out.println("\n Enter user to be deleted : \n");
				sc.nextLine();                                       // Consume the newline character left from previous input.
				String DeleteUserName = sc.nextLine();
				deleteuser(DeleteUserName);
			}
			else if(OptionSelectedByUser == UserOptions.EDIT_USER) // edit user
			{
				System.out.println("\n Enter user to be edited : ");
				sc.nextLine();                                     // Consume the newline character left from previous input.
				String EditUserName = sc.nextLine();
				edituser(EditUserName);
				System.out.println("\n User edited successfully !");
			}
		}
	}
	public static void adduser()
	{
		User UserObject = new User();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n Username : ");
		UserObject.UserName = sc.nextLine();

		System.out.println(" LoginName : ");
		UserObject.LoginName = sc.nextLine();

		System.out.println(" UserRole : ");
		UserObject.UserRole = sc.nextLine();

		System.out.println(" Password : ");
		UserObject.Password = sc.nextLine();

		System.out.println(" ConfirmPassword : ");
		UserObject.ConfirmPassword = sc.nextLine();

		if(UserObject.Password.equals(UserObject.ConfirmPassword))
		{
			System.out.println("\n User details are : \n");
			System.out.println("User name is : "+UserObject.UserName);
			System.out.println("Login name is : "+UserObject.LoginName);
			System.out.println("User role is : "+UserObject.UserRole);
			System.out.println("Password is : "+UserObject.Password);

			al.add(UserObject);
		}
		else
		{
			System.out.println("\nPassword and ConfirmPassword did not match.Please try again.");
		}
	}
	public static void searchuser(String UserName)
	{
		for(User user :al)
		{
			if(user.UserName.equalsIgnoreCase(UserName))
			{
				System.out.println("\nUser name : "+user.UserName);
				System.out.println("Login name : "+user.LoginName);
				System.out.println("User role : "+user.UserRole);
				System.out.println("Password : "+user.Password);
				return;
			}
		}
		System.out.println("\n User not found. ");
	}
	public static void deleteuser(String UserName)
	{
		Iterator<User> UserIterator = al.iterator();
		while(UserIterator.hasNext())
		{
			User user = UserIterator.next();
			if(user.UserName.equalsIgnoreCase(UserName))
			{
				UserIterator.remove();
				System.out.println("\n User : "+user.UserName+" has been deleted.");
				break;
			}
		}
	}
	public static void edituser(String UserName) // edit user
	{
		for(User user :al)
		{
			if(user.UserName.equalsIgnoreCase(UserName))
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("\n Editing user : "+user.UserName);
				System.out.println("\n New user name : ");
				user.UserName = sc.nextLine();
				System.out.println("\n New login name : ");
				user.LoginName = sc.nextLine();
				System.out.println("\n New user role : ");
				user.UserRole = sc.nextLine();
				System.out.println("\n New password : ");
				user.Password= sc.nextLine();
				System.out.println("\n New confirm password : ");
				user.ConfirmPassword = sc.nextLine();
				return;
			}
		}
		System.out.println("\n user not found.");
	}
	public static void loadDataFromFileToArrayList() throws IOException
	{
		File file = new File("\\Users\\PRAJAKTA\\eclipse-workspace\\ShopManagement\\src\\user_management\\UserData.csv");
		FileReader fr = new FileReader(file);         // fr object to read file
		BufferedReader br = new BufferedReader(fr); // br object to read buffered data.
		String line = " ";
		while((line = br.readLine())!= null)       // execute while loop until line in the file is not equal to null.
		{
			User user = new User();
			String[] userDataArray = line.split(",");
			if(userDataArray.length>3)
			{
				user.UserName = userDataArray[0];
				user.LoginName= userDataArray[1];
				user.UserRole= userDataArray[2];
				user.Password = userDataArray[3];
				al.add(user);
			}
		}
		fr.close();
		br.close();
		file = null;
	}
	public static boolean validateUserAndPassword( String LoginName, String Password ) // To verify the user.
	{
		Iterator<User> userIterator = al.iterator();
		while(userIterator.hasNext())
		{
			User user = userIterator.next();
			if(user.LoginName.equalsIgnoreCase(LoginName) && user.Password.equalsIgnoreCase(Password))
			{
				return true;
			}
		}
		return false;
	}
}
   
			


