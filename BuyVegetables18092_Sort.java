package ccf;

import java.util.Scanner;

public class BuyVegetables18092_Sort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Time[] h = new Time[2*n];
		Time[] w = new Time[2*n];
		Time[] times = new Time[4*n];
		int totalTime = 0;
		
		for (int i = 0; i < 2*n; i++) {
			h[i] = new Time();
			h[i].time = scanner.nextInt();
			h[i].type = true;
		}
		for (int i = 0; i < 2*n; i++) {
			w[i] = new Time();
			w[i].time = scanner.nextInt();
			w[i].type = false;
		}
		scanner.close();
		
		int p1 = 0, p2 = 0;
		while (p1 < 2*n && p2 < 2*n) {
			if (h[p1].time < w[p2].time) {
				times[p1+p2] = h[p1];
				++p1;
			}
			else {
				times[p1+p2] = w[p2];
				++p2;
			}
		}
		if (p1 >= 2*n) {
			for (; p2 < 2*n; p2++) {
				times[p1+p2] = w[p2];
			}
		}
		if (p2 >= 2*n) {
			for (; p1 < 2*n; p1++) {
				times[p1+p2] = h[p1];
			}
		}
		
		int a = 0, b = 1, c = 2;
		while (c < 4*n) {
			if (times[b].type == times[a].type) {
				a = c;
				b += 2;
				c += 2;
			}
			else {
				totalTime += times[c].time - times[b].time;
				if (times[c].type == times[b].type) {
					b += 2;
					c += 2;
				}
				else {
					a = b;
					b = c + 1;
					c += 2;
				}
			}
		}
		System.out.println(totalTime);
	}
}

class Time{
	int time;
	boolean type;
	
	public boolean biggerThan(Time t) {
		return time > t.time;
	}
}