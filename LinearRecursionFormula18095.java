package ccf;
//20
import java.util.Scanner;

public class LinearRecursionFormula18095 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int l = scanner.nextInt();
		int r = scanner.nextInt();
		int Q = 998244353;
		int[] k = new int[m];
		long[] a = new long[r+1];
		
		for (int i = 0; i < m; i++) {
			k[i] = scanner.nextInt();
		}
		scanner.close();
		a[0] = 1;
		
		for (int n = 1; n <= r; n++) {
			a[n] = 0;
			int up = n < m ? n : m;
			for (int i = 1; i <= up; i++) {
				a[n] = (a[n] + k[i-1] * a[n-i]) % Q;
			}
		}
		
		for (int i = l; i <= r; i++) {
			System.out.println(a[i]);
		}
	}
}
