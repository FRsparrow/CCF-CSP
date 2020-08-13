package ccf;

import java.util.Scanner;

public class Game17122 {
	static int k;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		k = scanner.nextInt();
		scanner.close();
		int eliminatedNumber = 0;
		int number = 1;
		boolean[] eliminated = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			eliminated[i] = false;
		}
		
		for (int i = 1; eliminatedNumber < n-1; i = i % n + 1) {
			if (eliminated[i]) {
				continue;
			}
			if (number % k == 0 || endIsK(number)) {
				eliminated[i] = true;
				++eliminatedNumber;
			}
			++number;
		}
		
		for (int i = 1; i <= n; i++) {
			if (!eliminated[i]) {
				System.out.println(i);
				break;
			}
		}
	}
	
	static boolean endIsK(int number) {
		return (number+"").charAt((number+"").length()-1) == (k+"").charAt(0);
	}
}
