package ccf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Divide17095TreeArray {
	static int N;
	static int M;
	static int[] a;	    //原数组
	static long[] c;	//树状数组
	static ArrayList<Long> ans = new ArrayList<Long>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		a = new int[N+1];
		c = new long[N+1];
		Arrays.fill(a, 1, N+1, 0);
		Arrays.fill(c, 1, N+1, 0);
		
		for (int i = 1; i <= N; i++) {
			update(i, scanner.nextInt());
		}
		
		for (int i = 0; i < M; i++) {
			if (scanner.nextInt() == 1) {	//作除法
				int l = scanner.nextInt();
				int r = scanner.nextInt();
				int v = scanner.nextInt();
				if (v == 1) {
					continue;
				}
				for (int j = l; j <= r; j++) {
					if (a[j] >= v && a[j] % v == 0) {
						int d_value = a[j] / v - a[j];
						update(j, d_value);
					}
				}
			}
			else {		//查询和
				int l = scanner.nextInt();
				int r = scanner.nextInt();
//				System.out.println(getSum(r) - getSum(l-1));
				ans.add(getSum(r) - getSum(l-1));
			}
		}
		scanner.close();
		
		for (Long long1 : ans) {
			System.out.println(long1);
		}
	}
	
	static int lowbit(int i) { return i & -i; }
	static long getSum(int m) {
		long sum = 0;
		while (m > 0) {
			sum += c[m];
			m -= lowbit(m);
		}
		return sum;
	}
	
	static void update(int index, int value) {
		a[index] += value;
		while (index <= N) {
			c[index] += value;
			index += lowbit(index);
		}
	}
}
