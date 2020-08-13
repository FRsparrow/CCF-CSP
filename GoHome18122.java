package ccf;
//60·Ö
import java.util.Scanner;

public class GoHome18122 {
//	static int waitTime(int red, int yellow, int green, int pastTime)
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int red = scanner.nextInt();
		int yellow = scanner.nextInt();
		int green = scanner.nextInt();
		int sum = red + yellow + green;
		int n = scanner.nextInt();
		int totalTime = 0;
		
		for (int i = 0; i < n; i++) {
			int type = scanner.nextInt();
			int time = scanner.nextInt();
			int nowTime = time - (totalTime % sum);
			switch (type) {
			case 0:		//×ßÂ·
				totalTime += time;
				break;
			case 1:		//ºìµÆ
				if (nowTime > 0) {
					totalTime += nowTime;
				}
				else if (green + nowTime <= 0) {
					totalTime += sum + nowTime;
				}
				break;
			case 2:		//»ÆµÆ
				if (red + nowTime > 0) {
					totalTime += red + nowTime;
				}
				else if (green + red + nowTime <= 0) {
					totalTime += red + sum + nowTime;
				}
				break;
			case 3:		//ÂÌµÆ
				if (nowTime <= 0 && red + yellow + nowTime > 0) {
					totalTime += red + yellow + nowTime;
				}
				break;
			default:
				break;
			}
		}
		
		scanner.close();
		System.out.println(totalTime);
	}
}
