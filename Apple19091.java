package ccf;

/*ตรทึ100*/
import java.util.Scanner;

public class Apple19091 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int sum = 0;
		int maxLoss = 0;
		int index = 1;
		
		for (int i = 1; i <= n; i++) {
			sum += scanner.nextInt();
			int currentLoss = 0;
			for (int j = 0; j < m; j++) {
				currentLoss += scanner.nextInt();
			}
			sum += currentLoss;
			currentLoss = -currentLoss;
			if (currentLoss > maxLoss) {
				maxLoss = currentLoss;
				index = i;
			}
		}
		
		scanner.close();
		System.out.println(sum + " " + index + " " + maxLoss);
	}
}
