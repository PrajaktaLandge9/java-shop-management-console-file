package product_management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProductManagement {

	static ArrayList<Product> al = new ArrayList<> ();
	public static void productManagement() throws IOException
	{
		loadDataFromFileToArrayList();
		Scanner sc = new Scanner(System.in);
		boolean CanIKeepRunningTheProgram = true;
		while(CanIKeepRunningTheProgram == true) 
		{
			System.out.println("\n Welcome to Product management !");
			System.out.println("\n Select one of the options given below :");
			System.out.println("1. Add product : ");
			System.out.println("2. Edit product : " );
			System.out.println("3. Delete product : ");
			System.out.println("4. Search product : ");
			System.out.println("5. Quit product");
			int OptionSelected = sc.nextInt();
			if(OptionSelected == ProductOptions.QUIT_PRODUCT)
			{
				File file = new File("\\Users\\PRAJAKTA\\eclipse-workspace\\ShopManagement\\src\\product_management\\Products.csv");
				FileWriter fileWriter = new FileWriter(file, false); 
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for(Product product: al)
				{
					bufferedWriter.write(product.ProductId+","+product.ProductName+","+product.ProductPrice+","+product.ProductQuantity+","+product.ProductCategory+"\n");
				}
				bufferedWriter.close();                           // The data in ram is written into hardisk.
				fileWriter.close();
				file = null;
				System.out.println("\n Product management application closed !");
				CanIKeepRunningTheProgram = false;
			}
			else if(OptionSelected == ProductOptions.ADD_PRODUCT) //add product
			{
				addproduct();
			}
			else if(OptionSelected == ProductOptions.SEARCH_PRODUCT)//search product
			{
				System.out.println("\n Enter product to be searched : \n"); 
				sc.nextLine();                                             //Consume the newline character left from previous input.
				String SearchProductName = sc.nextLine();
				searchproduct(SearchProductName);
			}
			else if(OptionSelected == ProductOptions.DELETE_PRODUCT)//delete product
			{
				System.out.println("\n Enter product to be deleted : \n");
				sc.nextLine();                                            // Consume the newline character left from previous input.
				String DeleteProductName = sc.nextLine();
				deleteproduct(DeleteProductName);
			}
			else if(OptionSelected == ProductOptions.EDIT_PRODUCT) // edit product
			{
				System.out.println("\n Enter product to be edited : ");
				sc.nextLine();                                          // Consume the newline character left from previous input.
				String EditProductName = sc.nextLine();
				editproduct(EditProductName);
				System.out.println("\n Product edited successfully !");
			}
		}
	}
	public static void addproduct()
	{
		Product ProductObject = new Product();
		Scanner sc = new Scanner(System.in);

		System.out.println("\n Product name : ");
		ProductObject.ProductName = sc.nextLine();

		System.out.println(" Product id : ");
		ProductObject.ProductId = sc.nextLine();

		System.out.println(" Product price : ");
		ProductObject.ProductPrice = sc.nextLine();

		System.out.println(" Product quantity : ");
		ProductObject.ProductQuantity = sc.nextLine();

		System.out.println(" Product category : ");
		ProductObject.ProductCategory = sc.nextLine();

		System.out.println("\n Product details are : \n");
		System.out.println("Product name is : "+ProductObject.ProductName );
		System.out.println("Product id is : "+ProductObject.ProductId);
		System.out.println("Product price is : "+ProductObject.ProductPrice);
		System.out.println("Product quantity is : "+ProductObject.ProductQuantity);
		System.out.println("Product Category is : "+ProductObject.ProductCategory);
		al.add(ProductObject);
	}
	public static void searchproduct(String ProductName)
	{
		for(Product product :al)
		{
			if(product.ProductName.equalsIgnoreCase(ProductName))
			{
				System.out.println("\nProduct name : "+product.ProductName);
				System.out.println("Product id : "+product.ProductId);
				System.out.println("Product price : "+product.ProductPrice);
				System.out.println("Product quantity : "+product.ProductQuantity);
				System.out.println("Product categeory : "+product.ProductCategory);
				return;
			}
		}
		System.out.println("\n Product not found. ");
	}
	public static void deleteproduct(String ProductName)
	{
		Iterator<Product> ProductIterator = al.iterator();
		while(ProductIterator.hasNext())
		{
			Product product = ProductIterator.next();
			if(product.ProductName.equalsIgnoreCase(ProductName))
			{
				ProductIterator.remove();
				System.out.println("\n Product : "+product.ProductName+" has been deleted.");
				break;
			}
		}
	}	
	public static void editproduct(String ProductName) //edit product
	{
		for(Product product :al)
		{
			if(product.ProductName.equalsIgnoreCase(ProductName))
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("\n Editing product : "+product.ProductName);
				System.out.println("\n New product name : ");
				product.ProductName = sc.nextLine();
				System.out.println("\n New product id : ");
				product.ProductId = sc.nextLine();
				System.out.println("\n New product price : ");
				product.ProductPrice = sc.nextLine();
				System.out.println("\n New product quantity : ");
				product.ProductQuantity= sc.nextLine();
				System.out.println("\n New product category : ");
				product.ProductCategory = sc.nextLine();
				return;
			}
		}
		System.out.println("\n product not found.");
	}
	public static void loadDataFromFileToArrayList() throws IOException
	{
		File file = new File("\\Users\\PRAJAKTA\\eclipse-workspace\\ShopManagement\\src\\product_management\\Products.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr); 
		String line = " ";
		while((line = br.readLine())!= null) 
		{
			Product product = new Product();
			String[] productDataArray = line.split(",");
			if(productDataArray.length>4)
			{
				product.ProductId = productDataArray[0];
				product.ProductName= productDataArray[1];
				product.ProductPrice= productDataArray[2];
				product.ProductQuantity = productDataArray[3];
				product.ProductCategory = productDataArray[4];
				al.add(product);
			}
		}
		fr.close();
		br.close();
		file = null;
	}
}	


	
