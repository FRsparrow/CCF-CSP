package ccf;

import java.util.Arrays;
import java.util.Scanner;

public class CollideBall18032_Force {
	static int n;
	static int L;
	static int t;
	static int[] a;
	static boolean[] direction;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		L = scanner.nextInt();
		t = scanner.nextInt();
		int[] ans = new int[n];
		a = new int[n+2];
		BallForce[] balls = new BallForce[n];
		direction = new boolean[n+2];
		a[0] = 0;
		a[n+1] = L;
		direction[0] = true;
		direction[n+1] = false;
		
		for (int i = 1; i < n+1; i++) {
			balls[i-1] = new BallForce(i-1, scanner.nextInt());
//			a[i] = scanner.nextInt();
			direction[i] = true;
		}
		scanner.close();
		
		Arrays.sort(balls);
		for (int i = 0; i < n; i++) {
			a[i+1] = balls[i].initialPosition;
		}
		
		collide();
		
		for (int i = 0; i < n; i++) {
			ans[balls[i].index] = a[i+1];
		}
		
		for (int i = 0; i < n-1; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println(ans[n-1]);
	}
	
	static void collide() {
		int nextTime = 100 + 1;
		if (!direction[1]) {
			nextTime = a[1];
		}
		if (direction[n] && L - a[n] < nextTime) {
			nextTime = L - a[n];
		}
		for (int i = 1; i < n; i++) {
			if (direction[i] && !direction[i+1] && nextTime > (a[i+1] - a[i]) / 2) {
				nextTime = (a[i+1] - a[i]) / 2;
			}
		}
		if (t > nextTime) {
			for (int i = 1; i <= n; i++) {
				a[i] += direction[i] ? nextTime : -nextTime;
			}
			for (int i = 0; i <= n; i++) {
				if (a[i] == a[i+1]) {
					direction[i] = false;
					direction[i+1] = true;
				}
			}
			t -= nextTime;
			collide();
		}
		else {
			for (int i = 1; i <= n; i++) {
				a[i] += direction[i] ? t : -t;
			}
		}
	}
}

class BallForce implements Comparable<BallForce>{
	int index;
	int initialPosition;
	
	public BallForce() {}
	
	public BallForce(int index, int initialPosition) {
		this.index = index;
		this.initialPosition = initialPosition;
	}
	
	@Override
	public int compareTo(BallForce o) {
		return ((Integer) initialPosition).compareTo(o.initialPosition);
	}
}