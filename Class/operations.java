package Class;

import Interface.ihangman;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;

public class operations implements ihangman 
{
	private String [] dictionary;
	private String mainSecretRandomWord;
	private String play;
	private int maximum = 10 ;
	private int guessingNum = 0;
	char [] test ; 
	
	public String[] read (String path, int num) throws Exception
	{
		FileReader fr=new FileReader(path);
		BufferedReader br=new BufferedReader(fr);
		String temp;
		int i = 0; 
		String [] word = new String [num];
        while(((temp=br.readLine())!= null) && i < num)
        {
        	word[i++] = temp;
        }
        fr.close();
        br.close();
        return word;
	}

	@Override
	public void setDictionary(String[] words) 
	{
		dictionary = words ;
		
	}

	@Override
	public String selectRandomSecretWord() 
	{
		Random rand = new Random();
		int index = Math.abs(rand.nextInt()% dictionary.length);
		String randomSecretWord = dictionary[index];
		mainSecretRandomWord = randomSecretWord ;
		play = "" ;
		
		test = new char [mainSecretRandomWord.length()];
		for(int i = 0; i < mainSecretRandomWord.length(); i++)
		{
			play += "-";
		}
		
		for (int i = 0 ; i < mainSecretRandomWord.length() ; i++) {
			test [i] = '-';
		}
		return randomSecretWord;
		
	}

	@Override
	public String guess(Character c) throws Exception 
	{
		if ((mainSecretRandomWord.matches("^[a-zA-Z]*$"))== false) 
		{
			throw new Exception("Error: invalid");
		}
		
		if (c == null) 
		{
			return null;
		}
		
		if (mainSecretRandomWord.toLowerCase().contains(c.toString().toLowerCase())) 
		{
			int l = mainSecretRandomWord.length();
			
			
			
			for (int i = 0 ; i< l ; i++) 
			{
				if (mainSecretRandomWord.toLowerCase().charAt(i) == Character.toLowerCase(c))
				{
					test[i] = c ;
				
				}
				else 
				{
					continue ;
				}
				
			}
		play = String.valueOf(test);
		System.out.printf("You are genius");
		if(play.contains("-") == false)
		{
			System.out.printf("GG (Good Game)");
		}
		else
		{
			guessingNum++;
			System.out.printf("Nope !!");
			if(guessingNum >= maximum)
			{
				System.out.printf("Looooser XD ");
				return null;
			}
		}
		return play;
		}
		
		if(play.toLowerCase().contains(c.toString().toLowerCase()))
		{
			System.out.print("Don't cheat !! guessed before");
			return play;
		}
		
		
		return null;
	}

	@Override
	public void setMaxWrongGuesses(Integer max)
	{
		if(max != null && max > 0)
		{
			maximum = max;
		}
		else
		{
			maximum = 1;
		}
		
	}

//setters and getters
	public void setDictionaryWords(String [] dictionaryWords) {
		this.dictionary = dictionaryWords;
	}

	public void setMainSecretRandomVariable(String mainSecretRandomVariable) {
		this.mainSecretRandomWord = mainSecretRandomVariable;
	}



	
	
}
