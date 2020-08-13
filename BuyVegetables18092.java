package ccf;

import java.util.Arrays;
import java.util.Scanner;

public class BuyVegetables18092 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		int[] d = new int[n];
		int totalTime = 0;
		
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
			b[i] = scanner.nextInt();
		}
		for (int i = 0; i < n; i++) {
			c[i] = scanner.nextInt();
			d[i] = scanner.nextInt();
		}
		scanner.close();
		
		int minPossibleIndex = 0;
		for (int i = 0; i < n; i++) {
			for (int j = minPossibleIndex; j < n; j++) {
				if (a[i] < d[j]) {
					if (b[i] > c[j]) {	//有交集
						//求共同时间
						int[] temp = {a[i], b[i], c[j], d[j]};
						Arrays.sort(temp);
						totalTime += temp[2] - temp[1];
						if (b[i] >= d[j]) {
							++minPossibleIndex;
						}
					}
					else {
						break;
					}
				}
				else {
					++minPossibleIndex;
				}
			}
		}
		
		System.out.println(totalTime);
	}
}
