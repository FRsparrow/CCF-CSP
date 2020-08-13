package ccf;
//70，暴力，运行超时
import java.util.ArrayList;
import java.util.Scanner;

public class Divide17095 {
	static int N;
	static int M;
	static int[] a;
	static ArrayList<Long> ans = new ArrayList<Long>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		a = new int[N];
		
		for (int i = 0; i < N; i++) {
			a[i] = scanner.nextInt();
		}
		
		for (int i = 0; i < M; i++) {
			if (scanner.nextInt() == 1) {
				int l = scanner.nextInt() - 1;
				int r = scanner.nextInt() - 1;
				int v = scanner.nextInt();
				for (int j = l; j <= r; j++) {
					if (a[j] % v == 0) {
						a[j] /= v;
					}
				}
			}
			else {
				int l = scanner.nextInt() - 1;
				int r = scanner.nextInt() - 1;
				long sum = 0;
				for (int j = l; j <= r; j++) {
					sum += a[j];
				}
				ans.add(sum);
			}
		}
		scanner.close();
		for (Long long1 : ans) {
			System.out.println(long1);
		}
	}
}
