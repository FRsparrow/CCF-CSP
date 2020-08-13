package ccf;

import java.util.HashSet;
import java.util.Scanner;

public class BrokenRAID19033 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();	//Ӳ����
		int s = scanner.nextInt();	//������С���飩
		int l = scanner.nextInt();	//�ִ�Ӳ����
		int multiply = s * (n-1);
		int radix = 16;
		boolean repairable = l >= (n-1);
		HashSet<Integer> existDisk = new HashSet<Integer>();
		
		int firstDiskNumber = scanner.nextInt();
		existDisk.add(firstDiskNumber);
		String firstDiskData = scanner.next();
		int blockNumber = firstDiskData.length() / 8;
		int maxBlockNumber = (n-1) * blockNumber - 1;
		String[][] data = new String[blockNumber][n];
		//�����һ��Ӳ������
		for (int i = 0; i < blockNumber * 8; i += 8) {
			data[i/8][firstDiskNumber] = firstDiskData.substring(i, i+8);
		}
		//��������Ӳ������
		for (int i = 1; i < l; i++) {
			int currentDiskNumber = scanner.nextInt();
			existDisk.add(currentDiskNumber);
			String currentDiskData = scanner.next();
			for (int j = 0; j < blockNumber * 8; j += 8) {
				data[j/8][currentDiskNumber] = currentDiskData.substring(j, j+8);
			}
		}
		
		String[] lackDisk = new String[blockNumber];
		
		int m = scanner.nextInt();
		String[] result = new String[m];
		for (int i = 0; i < m; i++) {
			int queryBlockNumber = scanner.nextInt();
			if (queryBlockNumber > maxBlockNumber) {	//ָ���Ŀ鳬�������ܳ���
				result[i] = "-";
				continue;
			}
			int row = queryBlockNumber / multiply * s + queryBlockNumber % s;
			int startColumn = (n - (row / s % n)) % n;
			int startBlockNumber = multiply * (row / s);
			int diskNumber = (startColumn + (queryBlockNumber - startBlockNumber) / s) % n;
			if (existDisk.contains(diskNumber)) {	//�ô���δȱʧ
				result[i] = data[row][diskNumber];
			}
			else {	//�ô���ȱʧ
				if (repairable) {	//��ͨ������������
					if (lackDisk[row] != null) {	//�ô��̵Ŀ��ѱ������
						result[i] = lackDisk[row];
						continue;
					}
					long tempResult = 0;
					for (int j = 0; j < n; j++) {
						if (j != diskNumber) {
							tempResult ^= Long.parseLong(data[row][j], radix);
						}
					}
					result[i] = Long.toHexString(tempResult).toUpperCase();
					while (result[i].length() < 8) {
						result[i] = "0" + result[i];
					}
					lackDisk[row] = result[i];
				}
				else {	//�������
					result[i] = "-";
				}
			}
		}
		scanner.close();
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
}
