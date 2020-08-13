package ccf;
//100
import java.util.Arrays;
import java.util.Scanner;

public class MiddleNumber16121 {
	static int n;
	static int[] a;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		scanner.close();
		Arrays.sort(a);
		System.out.println(middleNumber());
	}
	
	static int middleNumber() {
		int left = 0, right = 0;
		int index = n / 2;
		while (index > 0 && a[index] == a[index-1]) { ++left; --index;}
		index = n / 2;
		while (index < n-1 && a[index] == a[index+1]) { ++right; ++index;}
		return left == right + 1 - n%2 ? a[n/2] : -1;
	}
}
