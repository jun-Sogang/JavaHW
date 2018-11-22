package hw1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class s20121622hw1_2 {
	public static void main(String[] args) {
		Boolean isMatched = true;
		int iterationCount = 0;
		Map<Number, Boolean> answer = new HashMap<Number, Boolean>();
		Random generator = new Random();
		int[] nums = new int[5];

		answer.put(7, true);
		answer.put(18, true);
		answer.put(32, true);
		answer.put(37, true);
		answer.put(44, true);
		
		while (isMatched) {
			iterationCount += 1;
			int count = 0;
			Map<Number, Boolean> flag = new HashMap<Number, Boolean>();

			for (int i = 0; i < 5; ++i) {
				int randomValue = generator.nextInt(50);
				
				while (flag.containsKey(randomValue) == true) {
					randomValue = generator.nextInt(50);
				}
				flag.put(randomValue, true);
				nums[i]= randomValue;
				
				if (answer.containsKey(nums[i]) == true) {
					count += 1;
				}
				
				if (i - count >= 3) break;
			}
			if (count >= 3) {
				isMatched = false;
			}
		}
		System.out.println("Iteration count : " + iterationCount);
		System.out.println("Random number : " + nums[0] +" "+ nums[1] +" " + nums[2] + " " + nums[3] +" " + nums[4]);
	}
}
