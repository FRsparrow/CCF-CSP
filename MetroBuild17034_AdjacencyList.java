package ccf;
//80,运行超时
//堆优化dijkstra算法，图使用邻接表存储
import java.util.PriorityQueue;
import java.util.Scanner;

public class MetroBuild17034_AdjacencyList {
	static boolean[] visited;
	static int n, m;
	static int[] d;
	static Hub[] hubs;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		visited = new boolean[n+1];
		d = new int[n+1];
		hubs = new Hub[n+1];
		for (int i = 1; i <= n; i++) {
			hubs[i] = new Hub(i, 0);
			d[i] = 1000001;
		}
		
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt(), v = scanner.nextInt(), day = scanner.nextInt();
			hubs[u].addNeighbour(v, day);
			hubs[v].addNeighbour(u, day);
		}
		scanner.close();
		dijkstra();
		System.out.println(d[n]);
	}
	
	static void dijkstra() {
		PriorityQueue<Day> queue = new PriorityQueue<Day>();
		queue.add(new Day(1, 0));
		while (!queue.isEmpty()) {
			Day element = queue.poll();
			if (element.nodeNumber == n) {
				d[n] = element.day;
				return;
			}
			if (visited[element.nodeNumber]) {
				continue;
			}
			visited[element.nodeNumber] = true;
			d[element.nodeNumber] = element.day;
			Hub hub = hubs[element.nodeNumber];
			while (hub.neighbour != null) {
				hub = hub.neighbour;
				int indirect = Math.max(element.day, hub.day);
				if (indirect < d[hub.number]) {
					d[hub.number] = indirect;
					queue.add(new Day(hub.number, indirect));
				}
			}
		}
	}
}

class Hub{
	int number;
	int day;
	Hub neighbour;
	
	public Hub(int n, int d) {
		number = n;
		day = d;
		neighbour = null;
	}
	
	public void addNeighbour(int neighbourNumber, int day) {
		Hub p = this;
		while (p.neighbour != null) {
			p = p.neighbour;
		}
		p.neighbour = new Hub(neighbourNumber, day);
	}
}

class Day implements Comparable<Day>{
	int nodeNumber;
	int day;
	
	public Day(int n, int d) {
		nodeNumber = n;
		day = d;
	}
	
	@Override
	public int compareTo(Day o) {
		return ((Integer) day).compareTo(o.day);
	}
}