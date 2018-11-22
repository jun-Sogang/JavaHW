package hw1;

class s20121622hw1_1 {
	public static void main(String[] args) {
		int sum = 0;
		
		for (int i = 1; i < 1000; i++) {
			if (i % 2 == 1) {
				sum += i;
			}
		}
		System.out.println("The sum of all odd number to 1000 is :" + sum);
	}
}
