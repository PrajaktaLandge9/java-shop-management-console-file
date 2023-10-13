package shop_management;

import java.io.IOException;
import java.util.Scanner;
import product_management.ProductManagement;
import user_management.UserManagement;

public class ApplicationMain {
	public static void main (String[] args) throws IOException //Signature function in java.
	{
		Scanner sc = new Scanner(System.in);
		boolean CanIKeepRunningTheProgram = true;

		System.out.println("\n ! Welcome !");
		System.out.println("\n Login ");
		System.out.println("\n Enter login name : ");
		String LoginName = sc.nextLine();
		System.out.println("\n Enter password : ");
		String Password = sc.nextLine();

		if(! UserManagement.validateUserAndPassword(LoginName, Password)) // False -> True
		{
			System.out.println("\n Login failed. Closing the application !);");
			return;
		}
		else
		{
			System.out.println("\n Login Successful !");
		}

		while(CanIKeepRunningTheProgram == true)
		{
			System.out.println("\n ! Welcome to shop management !");
			System.out.println("\n What would you like to do ?");
			System.out.println("\n 1. User Management");
			System.out.println("\n 2. Product Management");
			System.out.println("\n 3. Quit");

			int OptionSelectedByUser = sc.nextInt();

			if(OptionSelectedByUser == 1)
			{
				UserManagement.userManagement();
			}

			else if(OptionSelectedByUser == 2)
			{
				ProductManagement.productManagement();
			}

			else if(OptionSelectedByUser == 3)
			{
				System.out.println("\n Shop management application closed. Thankyou !");
				break;
			}
		}
	}
}


