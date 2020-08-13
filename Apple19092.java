package ccf;

/*�÷�90*/
import java.util.Scanner;

public class Apple19092 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int sum = 0;		//ƻ������
		int lossTree = 0;	//����ƻ������������
		int E = 0;			//����3�����ڵ�����ƻ��������
		int treeSum = 0;	//������treeSum��������ƻ��
		boolean firstLoss = false;		//��һ�����Ƿ����ƻ��
		boolean secondLoss = false;		//�ڶ������Ƿ����ƻ��
		boolean lastLoss = false;		//������һ�����Ƿ����ƻ��
		boolean secondLastLoss = false;	//�����ڶ������Ƿ����ƻ��
		
		for (int i = 0; i < n; i++) {
			int m = scanner.nextInt();
			int apple = scanner.nextInt();
			boolean hasLoss = false;
			for (int j = 1; j < m; j++) {
				int number = scanner.nextInt();
				if (number > 0) {	//ͳ��ƻ������
					hasLoss = hasLoss || (number < apple);	//��ƻ������
					apple = number;
				}else {				//���
					apple += number;
				}
			}
			if (hasLoss) {	//��ƻ������
				if (i == 0) firstLoss = true;
				if (i == 1) secondLoss = true;
				if (i == n-1) lastLoss = true;
				if (i == n-2) secondLastLoss = true;
				++lossTree;
				++treeSum;
				if (treeSum == 3) {
					++E;
					--treeSum;
				}
			}else {
				treeSum = 0;
			}
			sum += apple;
		}
		if(n > 3) {
			if (firstLoss && lastLoss) {
				if (secondLoss) {
					++E;
				}
				if (secondLastLoss) {
					++E;
				}
			}
		}
		
		System.out.println(sum + " " + lossTree + " " + E);
		scanner.close();
	}
}
