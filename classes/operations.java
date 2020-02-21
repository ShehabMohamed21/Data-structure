package classes;

import Interface.ICalculator;

public class operations implements ICalculator{
	public int add(int a, int b) 
	{
		return (a+b);
	}
	
	public float divide(int a, int b) 
	{
		if (b!= 0) 
		{
			return (a/b);
		}
		else 
		{
			throw new RuntimeException();
		}	
		
	}
}
