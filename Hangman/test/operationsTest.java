package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import Class.operations;

class operationsTest 
{

	@Test
	void testRead() throws Exception 
	{
		  operations test = new operations(); 
		  String[] wordsDictionary = test.read("C:\\Users\\shehab\\eclipse-workspace\\Hangman2\\wordsDictionary.txt", 5000);
		  assertNotNull(wordsDictionary);
	}

	@Test
	void testSetDictionary(){
		
	}

	@Test
	void testSelectRandomSecretWord() throws Exception 
	{
		operations test = new operations();
		String[] wordsDictionary = test.read("wordsDictionary.txt", 5000);
		test.setDictionary(wordsDictionary);
		String tst = test.selectRandomSecretWord();
		assertNotNull(tst);
	}

	@Test
	void testGuess() throws Exception 
	{
		operations test = new operations();
		String[] wordsDictionary = test.read("wordsDictionary.txt", 5000);
		test.setDictionary(wordsDictionary);
		test.setMaxWrongGuesses(3);
		String word = test.selectRandomSecretWord();
		assertNotNull(word);
		Random rand = new Random();
		int index = Math.abs(rand.nextInt()%word.length());
		Character c = word.charAt(index);
		assertNotNull(test.guess(c));
		
		for(int i = 0; i < 2; i++)
		{
			while(word.contains(c.toString()))
			{
				c = (char)(Math.abs(rand.nextInt())%26 + (int)'a');
			}
			test.guess(c);
		}
		while(word.contains(c.toString()))
		{
			c = (char)(Math.abs(rand.nextInt())%26 + (int)'a');
		}
		assertNull(test.guess(c));

		word = test.selectRandomSecretWord();
		String tmp = "";
		for(int i = 0; i < word.length(); i++)
		{
			tmp = test.guess(word.charAt(i));
		}
		assertEquals(tmp, word);
	}
	

	@Test
	void testSetMaxWrongGuesses() 
	{
		
	}
}


