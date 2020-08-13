package ccf;

import java.util.Arrays;
import java.util.Scanner;

public class CollideBall18032 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int L = scanner.nextInt();
		int t = scanner.nextInt();
		int[] a = new int[n];
		int[] ans = new int[n];
		Ball[] balls = new Ball[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
			balls[i] = new Ball(i, a[i]);
			if (a[i] <= L - t) {
				a[i] += t;
			}
			else {
				int remainTime = t - L + a[i];
				int remain = remainTime % (2 * L);
				a[i] = Math.abs(remain - L);
			}
		}
		scanner.close();
		Arrays.sort(a);
		Arrays.sort(balls);
		for (int i = 0; i < n; i++) {
			ans[balls[i].index] = a[i];
		}
		
		for (int i = 0; i < n-1; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println(ans[n-1]);
	}
}

class Ball implements Comparable<Ball>{
	int index;
	int initialPosition;
	
	public Ball() {}
	
	public Ball(int index, int initialPosition) {
		this.index = index;
		this.initialPosition = initialPosition;
	}
	
	@Override
	public int compareTo(Ball o) {
		return ((Integer) initialPosition).compareTo(o.initialPosition);
	}
}