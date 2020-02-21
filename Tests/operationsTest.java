package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.operations;

class operationsTest {

	@Test
	void testAdd() {
		operations test = new operations();
		int output = test.add(5, 2);
		assertEquals(7, output);
		output = test.add(-5, 5);
		assertEquals(0, output);
		output = test.add(100, -5);
		assertEquals(95, output);
		output = test.add(5,0);
		assertEquals(5, output);
	}

	@Test
	void testDivide() {
		operations test = new operations();
		float output = test.divide(5, 1);
		assertEquals(5/1, output);
		output = test.divide(1, 4);
		assertEquals(1/4 , output);
		output = test.divide(10, -2);
		assertEquals(10/-2 , output);
		output = test.divide(0, 2);
		assertEquals(0 , output);
		output = test.divide(0, -1);
		assertEquals(-0 , output);
		assertThrows(RuntimeException.class, ()-> test.divide(5, 0));
	}

}
