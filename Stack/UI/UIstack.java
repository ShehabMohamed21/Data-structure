package Classes;
import java.util.EmptyStackException;
import java.util.Scanner;

import Classes.stack;

public class UIstack {

	public static void main(String[] args) 
	{
		
		Scanner sc= new Scanner(System.in);
		stack box = new stack();
		boolean out = false ;
		while (!out) 
		{
			System.out.println(
					"Hi !! Which operation would you like ?\r\n" + 
					"\r\n" + 
					"1: Push\r\n" + 
					"2: Pop\r\n" + 
					"3: peek\r\n" + 
					"4: Get size\r\n" + 
					"5: Check if empty\r\n"+
					"6: Exit");  
			int choice=0;
			try {
				choice = sc.nextInt();
				
			}
			catch(Exception e){
				System.out.println("Invalid input");
				sc.next();
				continue;
			}
			
			// push
			if (choice == 1) 
			{
				System.out.println("Insert the value you want to push");
				String recieve = sc.next();
				box.push(recieve);
				System.out.println("top = " + box.peek());
				
			}
			else if (choice == 2) 
			{
				try 
				{
					System.out.println("Popped element = " + box.pop());
				}
				catch (Exception e) 
				{
					System.out.println("Invalid process");
				}
			}
			
			else if (choice == 3) 
			{
				try 
				{
					System.out.println("Peek = " + box.peek());
				}
				catch (Exception e) 
				{
					System.out.println("Invalid process");
				}	
			}
			else if (choice == 4) 
			{
				try 
				{
					System.out.println("size = " + box.size());
				}
				catch (Exception e) 
				{
					System.out.println("Invalid process");
				}
			}
			else if (choice == 5) 
			{
				if (box.isEmpty()) 
				{
					System.out.println("Empty");
				}
				else 
				{
					System.out.println("Not empty");
				}
			}
			else if (choice == 6) 
			{
				out = true;
			}
	}
	
		sc.close();	
	}

}
