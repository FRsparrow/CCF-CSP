package ccf;

import java.util.Scanner;

public class DataCenter18124_AdjMatrix {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int root = scanner.nextInt();
		int Tmax = 0;
		int[] dis = new int[n+1];
		boolean[] visited = new boolean[n+1];
		int[][] graph = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
			visited[i] = false;
			dis[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int t = scanner.nextInt();
//			if (u == v) {
//				continue;
//			}
			if (u == 1) {
				dis[v] = t;
			}
			if (v == 1) {
				dis[u] = t;
			}
			graph[u][v] = t;
			graph[v][u] = t;
		}
		scanner.close();
		
		//求最小生成树的权值最大的边
		visited[1] = true;
		dis[1] = 0;
		for (int j = 0; j < n-1; j++) {
			int minTime = Integer.MAX_VALUE;
			int closest = 0;
			for (int i = 2; i <= n; i++) {
//				if (visited[i]) {
//					Vertex p = graph[i].neighbour;
//					while (p != null) {
//						if (!visited[p.number] && p.time < minTime) {
//							closest = p.number;
//							minTime = p.time;
//						}
//						p = p.neighbour;
//					}
//				}
				if (!visited[i] && dis[i] < minTime) {
					minTime = dis[i];
					closest = i;
				}
			}
			visited[closest] = true;
			
			for (int i = 2; i <= n; i++) {
				if (i == closest) {
					continue;
				}
				int distance = graph[closest][i];
				if (!visited[i] && dis[i] > distance) {
					dis[i] = distance;
				}
			}
			
			if (minTime > Tmax) {
				Tmax = minTime;
			}
		}
		System.out.println(Tmax);
	}
}