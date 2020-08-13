package ccf;
//100
import java.util.Scanner;

public class StudentQueue17032 {
	static int n,m;
	static int[] a;
	static int[] position;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		a = new int[n+1];
		position = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = position[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			move(scanner.nextInt(), scanner.nextInt());
		}
		scanner.close();
		for (int i = 1; i < n; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n]);
	}
	
	static void move(int id, int number) {
		int thisPosition = position[id];
		if (number > 0) {
			while (number-- > 0) {
				a[thisPosition] = a[++thisPosition];
				--position[a[thisPosition-1]];
			}
		}
		else {
			while (number++ < 0) {
				a[thisPosition] = a[--thisPosition];
				++position[a[thisPosition+1]];
			}	
		}
		a[thisPosition] = id;
		position[id] = thisPosition;
	}
}
