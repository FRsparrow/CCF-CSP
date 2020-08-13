package ccf;

import java.util.Scanner;

public class SellVegetablesAgain18094 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int count = 0;
		int[] price = new int[n];
		int[] finalPrice = new int[n];
		int[] tomorrowPrice = new int[n];
		
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == n-1) {
				tomorrowPrice[i] = 2 * scanner.nextInt();
			}
			else {
				tomorrowPrice[i] = 3 * scanner.nextInt();
			}
		}
		scanner.close();
		
		if (n == 2) {
			int secondDayPrice = tomorrowPrice[0] - 1;
			System.out.println(1 + " " + secondDayPrice);
			return;
		}
		
		int possibilityNum = (int) Math.pow(3, n-2);
		for (int j = 0; j < 4; j++) {
			
			for (int state = 0; state < possibilityNum; state++) {
				int[] temp = tomorrowPrice.clone();
				temp[0] += j % 2;
				temp[n-1] += j / 2 % 2;
				int tempState = state;
				for (int i = 1; i < n-1; i++) {
					temp[i] += tempState % 3;
					tempState /= 3;
				}
				
//				if (state == 249) {
//					int a = 9;
//				}
				
				
				for (int firstDayPrice = 1; firstDayPrice < temp[0]; firstDayPrice++) {
					boolean hold = true;
					price[0] = firstDayPrice;
					price[1] = temp[0] - firstDayPrice;
					price[2] = temp[1] - temp[0];
					
//					if (state == 249) {
//						int a = 9;
//					}
					
					if (price[2] <= 0) {
						break;
					}
					
//					if (state == 249) {
//						int a = 9;
//					}
					
					if (count > 0) {
//						if (price[0] > finalPrice[0]
//								|| price[1] > finalPrice[1]
//										|| price[2] > finalPrice[2]) {
//							break;
//						}
						if (price[0] > finalPrice[0]) {
							break;
						}
						if (price[0] == finalPrice[0]) {
							if (price[1] > finalPrice[1]) {
								break;
							}
							if (price[1] == finalPrice[1]) {
								if (price[2] > finalPrice[2]) {
									break;
								}
							}
						}
					}
					
//					if (state == 249) {
//						int a = 9;
//					}
					
//					boolean hold = true;
					for (int i = 3; i < n; i++) {
						price[i] = temp[i-1] - price[i-1] - price[i-2];
						if (price[i] <= 0 || 
								((count > 0) && !isHold(price, finalPrice, i))) {
							hold = false;
							break;
						}
					}
					
//					if (state == 249) {
//						int a = 9;
//					}
					
					if (hold) {
						if (price[n-2] + price[n-1] != temp[n-1]) {
							hold = false;
						}
					}
					if (hold) {
						for (int i = 0; i < n; i++) {
							finalPrice[i] = price[i];
						}
						++count;
//						break;
					}
					
				}
				
				
//				for (int i = 0; i < n-1; i++) {
//					System.out.print(price[i] + " ");
//				}
//				System.out.println(price[n-1]);
				
			}
		}
		
		
		for (int i = 0; i < n-1; i++) {
			System.out.print(finalPrice[i] + " ");
		}
		System.out.println(finalPrice[n-1]);
		
		
//		for (int firstDayPrice = 1; firstDayPrice < tomorrowPrice[0]; firstDayPrice++) {
//			price[0] = firstDayPrice;
//			price[1] = tomorrowPrice[0] - firstDayPrice;
//			if (n == 2) {
//				System.out.println(1 + " " + price[1]);
//				break;
//			}
//			price[2] = tomorrowPrice[1] - tomorrowPrice[0];
//			boolean hold = true;
//			for (int i = 3; i < n; i++) {
//				price[i] = tomorrowPrice[i-1] - price[i-1] - price[i-2];
//				if (price[i] <= 0) {
//					hold = false;
//					break;
//				}
//			}
//			if (price[n-2] + price[n-1] != tomorrowPrice[n-1]) {
//				hold = false;
//			}
//			if (hold) {
//				break;
//			}
//		}
//		
//		
//		for (int i = 0; i < n-1; i++) {
//			System.out.print(price[i] + " ");
//		}
//		System.out.println(price[n-1]);
	}
	
	static boolean isHold(int[] price, int[] finalPrice, int end) {
		for (int i = 0; i <= end; i++) {
			if (price[i] < finalPrice[i]) {
				return true;
			}
			if (price[i] > finalPrice[i]) {
				return false;
			}
		}
		return true;
	}
}
