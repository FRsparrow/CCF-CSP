package ccf;

import java.util.Scanner;

public class DataCenter18124 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int root = scanner.nextInt();
		int Tmax = 0;
		int[] dis = new int[n+1];
		boolean[] visited = new boolean[n+1];
		Vertex[] graph = new Vertex[n+1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new Vertex(i, 0);
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
			graph[u].addNeighbour(v, t);
			graph[v].addNeighbour(u, t);
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
				int distance = graph[closest].getDis(i);
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

class Vertex {
	int number;
	int time;
	Vertex neighbour;
	
	public Vertex(int number, int time) {
		this.number = number;
		this.time = time;
		neighbour = null;
	}
	
	public Vertex() {}
	
	public int getDis(int neighbourNumber) {
		Vertex pVertex = neighbour;
		while (pVertex != null) {
			if (pVertex.number == neighbourNumber) {
				return pVertex.time;
			}
			pVertex = pVertex.neighbour;
		}
		return Integer.MAX_VALUE;
	}
	
	public void addNeighbour(int neighbourNumber, int time) {
		if (neighbour == null) {
			neighbour = new Vertex(neighbourNumber, time);
			return;
		}
//		if (neighbour.number == neighbourNumber) {
//			if (neighbour.time > time) {
//				neighbour.time = time;
//				return;
//			}
//		}
		Vertex p = neighbour;
		while (p.neighbour != null) {
//			if (p.number == neighbourNumber) {
//				if (p.time > time) {
//					p.time = time;
//				}
//				return;
//			}
			p = p.neighbour;
		}
		p.neighbour = new Vertex(neighbourNumber, time);
	}
}