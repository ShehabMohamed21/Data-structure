package Classes;

import Interface.IExpressionEvaluator;

import java.util.Arrays;

import Classes.stack;
/**
 * this class is implementing 2 functions infixToPostfix and evaluate 
 * 
 * 	@author shehab mohamed saad
 *	@version 1.0
 *
 */
public class Application implements IExpressionEvaluator{
	
	/**
	 * This function is responsible for determining the precedence of the character 
	 * @param a the symbol i want to know it's precedence
	 * @return 1 in case of sum or subtract & 2 in case of multiply and add (a higher number than sum and subtract) 
	 */
	static int precedence (char a) 
	{
		switch (a) 
		{
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		default:
			return -1;
		}
	}
	/**
	 * 
	 * @param expression the infix expression 
	 * @return return the expression am going to deal with in term of dummy zero 
	 */
	public String dummyZero(String expression) 
	{
		String out = "";
		for (int i = 0 ; i < expression.length() ; i++) 
		{
			char c = expression.charAt(i);
			if (i == 0 && c == '-') 
			{
				out += "(0 - " + expression.charAt(i+1) + ")";
				i++;
			}
			else if (c == '-' && Character.isDigit(expression.charAt(i+1)) && Character.isDigit(expression.charAt(i-1)) == false) 
			{
				out += "(0 - " + expression.charAt(i+1) + ")";
				i++;
			}
			else if (c == '-' && (expression.charAt(i+1)) == '(')
			{
				i++;
				i++;
				out += "(0 - " ;
				while (expression.charAt(i) != ')') 
				{
					out += expression.charAt(i) ;
					i++;
				}
				out += ")" ;
			}
			else 
			{
				out += c;
			}
		}
		
		return out;
		
	}
	/**
	 * 
	 * @param expression original infix took from the user 
	 * @return expression without spaces that help me in infix to postfix function 
	 */
	public String removeSpaces (String expression) 
	{
		String out = "" ;
		
		for (int i = 0 ; i < expression.length() ; i++) 
		{
			char c = expression.charAt(i);
			if (Character.isWhitespace(c)) 
			{
				continue;
			}
			else 
			{
				out += c;
			}
		}
		
		return out;
		
	}
	
	/**
	 * this function is changing the infix expression readable by human into postfix readable by computer
	 * @param expression infix expression i wanted to change to postfix
	 * @return postfix
	 */
	@Override
	public String infixToPostfix(String expression) {
		expression = removeSpaces(expression);
		expression = dummyZero(expression);
		String output = new String("");
		stack box = new stack();
		
		// looping through the expression character by character 
		for (int i = 0 ; i < expression.length() ; i++) 
		{
			
			char c = expression.charAt(i);
			
			if (i != 0 && Character.isLetter(c) && Character.isLetter(expression.charAt(i-1))) 
			{
				throw new RuntimeException();
			}
			
			//if the element is digit put it in postfix 
			else if (Character.isLetterOrDigit(c)) 
			{
			
				output += c;
			}
			
			// if there is a space, skip it
			else if (Character.isWhitespace(c)) 
			{
				continue;
			}
			
			// if there is negative sign at the first of infix put it in postfix 
			else if (i == 0 && c == '-') 
			{
				output += '-';
			}
			
			// if there is only one operator i want it to be read as an operator not a sign 
			else if (c == '-' && Character.isDigit(expression.charAt(i+1)) && Character.isDigit(expression.charAt(i-1)) == false) 
			{
				output += '-';
			}
			
			// checking for open brackets
			else if(c == '(') 
			{
				box.push(c);
			}
			
			//checking for closed brackets
			else if (c == ')') 
			{
				while (box.isEmpty() == false && (char)box.peek() != '(') 
				{
					if (output.charAt(output.length()-1)  !=  ' ') 
					{
					output += " ";
					}
					output += box.pop();
					output += " ";
				}
				
				if (box.isEmpty() == true) 
				{
					throw new RuntimeException();
				}
				else 
				{
					box.pop();
				}
				
			}
			
			// an operator 
			else 
			{
				// checking if there is a space before it in postfix or not 
				if( output.charAt(output.length()-1)  !=  ' ')	
				{
				output += " ";
				}
				while (box.isEmpty() == false && precedence(c) <= precedence((char)box.peek())) 
				{
					if((char)box.peek() == '(') 
					{ 
                        throw new RuntimeException();
                    } 
                    
					output += box.pop();
					output += " ";
				}
				
				box.push(c);
			}	
		}	
		
		// poping operators 
		while (box.isEmpty() == false)
		{ 
            if((char)box.peek() == '(') 
            { 
                return "Invalid Expression";
            } 
            if (Character.isLetterOrDigit(output.charAt(output.length()-1))) 
            {
            	output += " ";
            }
            output += box.pop();
            output += " ";
		}
		
		return output;
}
	/**
	 * this function is for evaluating the operations 
	 * @param expression is the postfix generated 
	 * @return value after evaluating proccess
	 */
	@Override
	public int evaluate(String expression)
	{
		stack box = new stack();
		
		// dealing with array of strings formed by spliting postfix by spaces
		String[] arr ;
		arr = expression.split(" ");
		
		for (int i = 0 ; i < arr.length ; i++) 
		{
			// length > 1 then it's a number
			if (arr[i].length() > 1) 
			{
				box.push(Float.parseFloat(arr[i]));            // changing string to float 
			}
			
			// if length is 1 then it can be number or operator 
			else if (arr[i].length() == 1) 
			{
				if (Character.isDigit(arr[i].charAt(0)))
				{
					box.push(Float.parseFloat(arr[i]));
				}
				else if(arr[i].charAt(0) == '+' || arr[i].charAt(0) == '-' || arr[i].charAt(0) == '*' ||arr[i].charAt(0) == '/' )
				{
					float tmp1 = (float) box.pop();
					float tmp2 = (float) box.pop();
					
					switch (arr[i].charAt(0)) 
					{
					case '+': 
	                    box.push(tmp2+tmp1); 
	                    break; 
	                      
	                    case '-': 
	                    box.push(tmp2- tmp1); 
	                    break; 
	                      
	                    case '/': 
	                    box.push(tmp2/tmp1); 
	                    break; 
	                      
	                    case '*': 
	                    box.push(tmp2*tmp1); 
	                    break; 
					}
					
				}
				else if (Character.isWhitespace(arr[i].charAt(0))) 
				{
					continue;
				}
				else if (!Character.isLetterOrDigit(arr[i].charAt(0)))
				{
					throw new RuntimeException();
				}
					
			}
			
		}
		
		
		
		return Math.round((float)box.pop());
	}

}
