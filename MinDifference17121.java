package ccf;

import java.util.Arrays;
import java.util.Scanner;

public class MinDifference17121 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int ans = Integer.MAX_VALUE;
		int[] a = new int[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		scanner.close();
		Arrays.sort(a);
		
		for (int i = 0; i < n-1; i++) {
			if (a[i+1] - a[i] < ans) {
				ans = a[i+1] - a[i];
			}
		}
		System.out.println(ans);
	}
}
