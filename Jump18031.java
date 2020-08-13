package ccf;

import java.util.Scanner;

public class Jump18031 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		int k = 1, score = 0;
		while ((n = scanner.nextInt()) != 0) {
			if (n == 1) {
				score += 1;
				k = 1;
			}
			else {
				score += 2 * k;
				++k;
			}
		}
		scanner.close();
		
		System.out.println(score);
	}
}
