package Classes;
import java.util.Scanner;

import Classes.Application;
public class MainUI {

	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
		Application app = new Application();
		stack box = new stack();
		boolean out = false ;
		while (!out) 
		{
			System.out.println(
					"Hi !! Which operation would you like ?\r\n" + 
					"\r\n" + 
					"1: Convert infix to postfix\r\n" + 
					"2: Evaluate\r\n" +
					"3: Exit");  
			int choice=0;
			try {
				choice = sc.nextInt();
				
			}
			catch(Exception e){
				System.out.println("Invalid input");
				sc.next();
				continue;
			}
			String postfix = "";
			String value = "";
			if (choice == 1) 
			{
				System.out.println("Insert the expression");
				String recieve = sc.nextLine();
				recieve = sc.nextLine();
				postfix = app.infixToPostfix(recieve);
				System.out.println("Postfix = " + postfix);
			}
			else if (choice == 2) 
			{
				System.out.println("Insert the expression");
				String recieve = sc.nextLine();
				recieve = sc.nextLine();
				try 
				{	
					postfix = app.infixToPostfix(recieve);
					System.out.println("result = " + app.evaluate(postfix));
				}
				catch (Exception e)
				{
					postfix = app.infixToPostfix(recieve);
					for (int i = 0 ; i < postfix.length() ; i++) 
					{
						if (Character.isLetter(postfix.charAt(i)) ) 
						{
							System.out.println("Enter value of " + postfix.charAt(i) + ":" );
							value = sc.next();
							char str = postfix.charAt(i);
							postfix = postfix.replace(str, value.charAt(0));
						}
					}
					System.out.println("result = " + app.evaluate(postfix));
				}
			}
			else if (choice == 3) 
			{
				out = true;
			}
		}
	sc.close();
	}

}
