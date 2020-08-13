package ccf;

import java.util.Scanner;

public class BuySoySauce17091 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		scanner.close();
		int n = N / 10;
		int a = n / 5;
		int b = (n - 5 * a) / 3;
		int total = n + 2 * a + b;
		System.out.println(total);
	}
}
