package ccf;
//100
import java.util.Scanner;

public class SellVegetables18091 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] price = new int[n];
		int[] tomorrowPrice = new int[n];
		
		for (int i = 0; i < n; i++) {
			price[i] = scanner.nextInt();
			if (i > 1) {
				tomorrowPrice[i-1] = (price[i-2] + price[i-1] + price[i]) / 3;
			}
		}
		scanner.close();
		
		tomorrowPrice[0] = (price[0] + price[1]) / 2;
		tomorrowPrice[n-1] = (price[n-2] + price[n-1]) / 2;
		System.out.print(tomorrowPrice[0]);
		for (int i = 1; i < tomorrowPrice.length; i++) {
			System.out.print(" " + tomorrowPrice[i]);
		}
		System.out.println();
	}
}
