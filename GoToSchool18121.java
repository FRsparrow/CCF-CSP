package ccf;

import java.util.Scanner;

public class GoToSchool18121 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int red = scanner.nextInt();
		int yellow = scanner.nextInt();
		int green = scanner.nextInt();
		int n = scanner.nextInt();
		int totalTime = 0;
		
		for (int i = 0; i < n; i++) {
			int type = scanner.nextInt();
			int time = scanner.nextInt();
			switch (type) {
			case 0:		//��·
				totalTime += time;
				break;
			case 1:		//���
				totalTime += time;
				break;
			case 2:		//�Ƶ�
				totalTime += time + red;
				break;
			default:
				break;
			}
		}
		
		scanner.close();
		System.out.println(totalTime);
	}
}
