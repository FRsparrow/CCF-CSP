package ccf;
//35,´íÎó
import java.util.Scanner;

public class CommunicationNetwork17094 {
	static int n;
	static int m;
	static boolean[][] graph;
	static boolean[][] reach;
	static boolean[][] hasConnect;
	static boolean hasUpdate = true;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		graph = new boolean[n+1][n+1];
		reach = new boolean[n+1][n+1];
		hasConnect = new boolean[n+1][n+1];
		
		for (int i = 0; i < m; i++) {
			int v = scanner.nextInt();
			int u = scanner.nextInt();
			graph[v][u] = true;
			graph[v][0] = true;
			reach[v][u] = true;
			reach[v][0] = true;
		}
		scanner.close();
		
		for (int i = 1; i <= n; i++) {
			graph[i][i] = true;
			reach[i][i] = true;
		}
		
		while (hasUpdate) {
			hasUpdate = false;
			for (int v = 1; v <= n; v++) {
				if (graph[v][0]) {
					graph[v][0] = false;
					for (int up = 1; up < n; up++) {
						if (graph[up][v]) {
							for (int i = 1; i <= n; i++) {
								if (!reach[up][i] && reach[v][i]) {
									reach[up][i] = true;
									graph[up][0] = true;
									hasUpdate = true;
								}
							}
						}
					}
				}
			}
		}
		
		for (int v = 1; v <= n; v++) {
			for (int u = 1; u <= n; u++) {
				if (reach[v][u]) {
					hasConnect[v][u] = true;
					hasConnect[u][v] = true;
				}
			}
		}
		
		int count = 0;
		for (int i = 1; i <= n; i++) {
			boolean connectAll = true;
			for (int j = 1; j <= n; j++) {
				if (!hasConnect[i][j]) {
					connectAll = false;
					break;
				}
			}
			if (connectAll) {
				++count;
			}
		}
		
		System.out.println(count);
	}
}
