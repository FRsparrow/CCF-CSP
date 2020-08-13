package ccf;

import java.util.Scanner;

public class SmallMiddleBig19031 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		float middle = 0;
		int max = 0, min = 0;
		boolean flag = false;
		int[] a = new int[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		scanner.close();
		
		if ((n & 1) == 0) {
			int sum = a[n/2] + a[n/2-1];
			middle = sum / 2;
			if ((sum & 1) == 1) {
				middle += 0.5f;
				flag = true;
			}
		}
		else {
			middle = a[n/2];
		}
		
		if (a[0] < a[n-1]) {
			max = a[n-1];
			min = a[0];
		}
		else {
			max = a[0];
			min = a[n-1];
		}
		
		if (flag) {
			System.out.println(max + " " + middle + " " + min);
		}
		else {
			System.out.println(max + " " + (int) middle + " " + min);
		}
	}
}
