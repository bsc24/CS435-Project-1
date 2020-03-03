package testers;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;

import question_2.Sort_It;

public class Sort_It_Tester {

	@Test
	public void RecurseiveTests() throws Exception {
		int[] numbers = new int[]{14, 12, 8, 18, 2, 13, 9, 11, 10};
		int[] mySorted = Sort_It.sort(numbers);
		Arrays.sort(numbers);
		
		for (int i = 0; i < numbers.length; i++)
			assertEquals(mySorted[i], numbers[i]);
	}
}
