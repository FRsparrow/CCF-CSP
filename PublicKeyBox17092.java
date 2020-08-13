package ccf;
//100
import java.util.Arrays;
import java.util.Scanner;

public class PublicKeyBox17092 {
	static int N;	//钥匙数
	static int K;	//老师数
	static int[] keys;
	static int[] keyPosition;
	static KeyTransaction[] keyTransactions;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		K = scanner.nextInt();
		keys = new int[N+1];
		keyPosition = new int[N+1];
		keyTransactions = new KeyTransaction[2*K];
		
		for (int i = 1; i <= N; i++) {
			keys[i] = i;
			keyPosition[i] = i;
		}
		
		for (int i = 0; i < K; i++) {
			int key = scanner.nextInt();
			int beginTime = scanner.nextInt();
			int endTime = beginTime + scanner.nextInt();
			keyTransactions[i] = new KeyTransaction(true, key, beginTime);
			keyTransactions[K+i] = new KeyTransaction(false, key, endTime);
		}
		
		scanner.close();
		Arrays.sort(keyTransactions);
		
		int index = 0;
		while (index < 2*K) {
			if (keyTransactions[index].type) {	//取钥匙
				int position = keyPosition[keyTransactions[index].key];
				keyPosition[keyTransactions[index].key] = 0;
				keys[position] = 0;
			}
			else {	//还钥匙
				for (int i = 1; i <= N; i++) {
					if (keys[i] == 0) {
						keys[i] = keyTransactions[index].key;
						keyPosition[keys[i]] = i;
						break;
					}
				}
			}
			++index;
		}
		
		for (int i = 1; i < N; i++) {
			System.out.print(keys[i] + " ");
		}
		System.out.println(keys[N]);
	}
}

class KeyTransaction implements Comparable<KeyTransaction>{
	boolean type;	//true代表拿钥匙，false代表还钥匙
	int key;
	int time;
	
	public KeyTransaction(boolean type, int key, int time) {
		this.type = type;
		this.key = key;
		this.time = time;
	}
	
	@Override
	public int compareTo(KeyTransaction o) {
		if (time < o.time) {
			return -1;
		}
		if (time > o.time) {
			return 1;
		}
		if (type) {
			return 1;
		}
		if (o.type) {
			return -1;
		}
		if (key < o.key) {
			return -1;
		}
		return 1;
	}
}