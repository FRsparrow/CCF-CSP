package ccf;
//35，运行超时
import java.util.ArrayList;
import java.util.Scanner;

public class TradeRoute17125 {
	static int T;
	static int n;
	static boolean[] hasSubordinate;
	static CityNode[] cities;
	static long[] routeValue;
	static long[] ans;
	static long Q = (long) 1e18;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		T = scanner.nextInt();
		ans = new long[T];
		
		for (int i = 0; i < T; i++) {
			n = scanner.nextInt();
			cities = new CityNode[n+1];
			hasSubordinate = new boolean[n+1];
			//读图
			for (int j = 1; j <= n; j++) {
				cities[j] = new CityNode(scanner.nextLong(), scanner.nextLong(), scanner.nextLong(), scanner.nextLong());
				hasSubordinate[(int) cities[j].up] = true;
			}
			//遍历叶子节点
			for (int j = 1; j <= n; j++) {
				if (!hasSubordinate[j]) {
					ArrayList<Long> lengthList = new ArrayList<Long>();
					ArrayList<Long> routeValueList = new ArrayList<Long>();
					int index = j;
					while (cities[index].up != 0) {
						lengthList.add(cities[index].length);
						routeValueList.add(cities[index].max);
						int upIndex = (int) cities[index].up;
						int totalLength = 0;
						int size = lengthList.size();
						for (int k = size - 1; k >=0 ; k--) {
							totalLength += lengthList.get(k);
							long tempValue = cities[upIndex].v - (cities[upIndex].f - totalLength) * (cities[upIndex].f - totalLength) + routeValueList.get(k);
							if (tempValue > cities[upIndex].max) {
								cities[upIndex].max = tempValue;
							}
						}
						index = upIndex;
					}
				}
			}
			
			long totalValue = 0;
			for (int j = 1; j <= n; j++) {
				totalValue = (totalValue + cities[j].max) % Q; 
			}
			ans[i] = totalValue;
		}
		scanner.close();
		
		for (int i = 0; i < T; i++) {
			System.out.println(ans[i]);
		}
	}
}

class CityNode {
	long up;		//上级编号
	long length;	//距上级距离
	long v;			//最理想小段价值
	long f;			//最佳的小段长度
	long max;
	
	public CityNode(long up, long length, long v, long f) {
		this.length = length;
		this.v = v;
		this.up = up;
		this.f = f;
		max = 0;
	}
}