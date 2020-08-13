package ccf;

import java.util.Arrays;
import java.util.Scanner;

public class DataCenter18124_Kruskal {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int root = scanner.nextInt();
		int Tmax = 0;
		int[] type = new int[n+1];
		Edge[] edges = new Edge[m];
		
		for (int i = 1; i <= n; i++) {
			type[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int time = scanner.nextInt();
			edges[i] = new Edge();
			edges[i].u = u;
			edges[i].v = v;
			edges[i].time = time;
		}
		scanner.close();
		
		Arrays.sort(edges);
		int count = 0;
		for (int i = 0; i < m; i++) {
			int uType = getType(type, edges[i].u);
			int vType = getType(type, edges[i].v);
			if (uType != vType) {
				type[uType] = vType;
				if (Tmax < edges[i].time) {
					Tmax = edges[i].time;
				}
				++count;
				if (count >= n-1) {
					break;
				}
			}
		}
		System.out.println(Tmax);
		
	}
	
	static int getType(int[] type, int vertex) {
		if (type[vertex] == vertex) {
			return vertex;
		}
		return getType(type, type[vertex]);
	}
}

class Edge implements Comparable<Edge>{
	int u;
	int v;
	int time;
	
	@Override
	public int compareTo(Edge o) {
		if (time > o.time) {
			return 1;
		}
		return (time == o.time ? 0 : -1);
	}
}
