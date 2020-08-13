package ccf;
//65,运行超时
//堆优化dijkstra算法，图使用邻接矩阵存储
import java.util.PriorityQueue;
import java.util.Scanner;

public class MetroBuild17034 {
	static boolean[] visited;
	static int n, m;
	static int[] d;
	static int[][] graph;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		visited = new boolean[n+1];
		d = new int[n+1];
		graph = new int[n+1][n+1];
		
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt(), v = scanner.nextInt();
			graph[u][v] = scanner.nextInt();
			graph[v][u] = graph[u][v];
		}
		scanner.close();
		dijkstra();
		System.out.println(d[n]);
	}
	
	static void dijkstra() {
		PriorityQueue<Element> queue = new PriorityQueue<Element>();
		queue.add(new Element(1, 0));
		while (!queue.isEmpty()) {
			Element element = queue.poll();
			if (element.nodeNumber == n) {
				d[n] = element.day;
				return;
			}
			if (visited[element.nodeNumber]) {
				continue;
			}
			visited[element.nodeNumber] = true;
			d[element.nodeNumber] = element.day;
			for (int i = 1; i <= n; i++) {
				if (graph[i][element.nodeNumber] != 0) {
					queue.add(new Element(i, Math.max(element.day, graph[i][element.nodeNumber])));
				}
			}
		}
	}
}

class Element implements Comparable<Element>{
	int nodeNumber;
	int day;
	
	public Element(int n, int d) {
		nodeNumber = n;
		day = d;
	}
	
	@Override
	public int compareTo(Element o) {
		return ((Integer) day).compareTo(o.day);
	}
}