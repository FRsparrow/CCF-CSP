package ccf;
//70,运行超时，并查集实现Kruskal算法
import java.util.Arrays;
import java.util.Scanner;

public class MetroBuild17034_Kruskal {
	static int n, m;
	static int[] type;
	static Tunnel[] tunnels;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		type = new int[n+1];
		tunnels = new Tunnel[m];
		
		for (int i = 1; i <= n; i++) {
			type[i] = i;
		}
		for (int i = 0; i < m; i++) {
			tunnels[i] = new Tunnel(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		}
		scanner.close();
		Arrays.sort(tunnels);
		for (int i = 0; i < m; i++) {
			int aType = getType(tunnels[i].a);
			int bType = getType(tunnels[i].b);
			type[aType] = bType;
			if (getType(1) == getType(n)) {
				System.out.println(tunnels[i].day);
				break;
			}
		}
	}
	
	static int getType(int hub) {
		while (type[hub] != hub) {
			hub = type[hub];
		}
		return hub;
	}
}

class Tunnel implements Comparable<Tunnel>{
	int a;
	int b;
	int day;
	
	public Tunnel(int u, int v, int d) {
		a = u;
		b = v;
		day = d;
	}
	
	@Override
	public int compareTo(Tunnel o) {
		return ((Integer) day).compareTo(o.day);
	}
}