package ccf;
//100
import java.util.Scanner;

public class AllocateCake17031 {
	static int n,k,ans;
	static int[] a;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		ans = 0;
		a = new int[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		scanner.close();
		int allocated = 0;
		while(allocated < n) {
			int sum = a[allocated++];
			while (sum < k && allocated < n) {
				sum += a[allocated++];
			}
			++ans;
		}
		System.out.println(ans);
	}
}
