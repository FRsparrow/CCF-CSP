package ccf;
import java.util.PriorityQueue;
//70,运行超时
import java.util.Scanner;

public class DrivePath17124_DijkstraHeapOptimised {
	static int MAX_DISTANCE = 1000000;
	static int n;
	static int m;
	static long ans;
	static CrossingDijkstraHeapOptimised[] CrossingDijkstraHeapOptimisedGraph;
	static boolean[] visited;
	static PriorityQueue<CrossingDistance> queue;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		CrossingDijkstraHeapOptimisedGraph = new CrossingDijkstraHeapOptimised[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			CrossingDijkstraHeapOptimisedGraph[i] = new CrossingDijkstraHeapOptimised(i);
			visited[i] = false;
		}
		
		for (int i = 0; i < m; i++) {
			int type = scanner.nextInt();
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			CrossingDijkstraHeapOptimisedGraph[a].addNeighbour(b, c, type);
			CrossingDijkstraHeapOptimisedGraph[b].addNeighbour(a, c, type);
		}
		scanner.close();
		
		initialize();
		dijkstra();
		
		System.out.println(ans);
	}
	
	static void initialize() {
		queue = new PriorityQueue<CrossingDistance>();
		visited[1] = true;
		CrossingDijkstraHeapOptimised p = CrossingDijkstraHeapOptimisedGraph[1].neighbourCrossingDijkstraHeapOptimised;
		while (p != null) {
			int roadDistance = (1 - p.type) * p.length;
			int pathDistance = p.type * p.length;
			queue.add(new CrossingDistance(p.number, roadDistance, pathDistance));
			p = p.neighbourCrossingDijkstraHeapOptimised;
		}
	}
	
	static void dijkstra() {
		for (int i = 1; i <= n; i++) {
			CrossingDistance minDistanceCrossing = queue.poll();
			int minDistanceCrossingNumber = minDistanceCrossing.crossing;
			if (visited[minDistanceCrossingNumber]) {
				--i;
				continue;
			}
			visited[minDistanceCrossingNumber] = true;	//将最短距离的点加入集合
			if (minDistanceCrossingNumber == n) {
				ans = minDistanceCrossing.getDistance();
				return;
			}
			//更新最短距离的点的邻居的距离
			CrossingDijkstraHeapOptimised p = CrossingDijkstraHeapOptimisedGraph[minDistanceCrossingNumber].neighbourCrossingDijkstraHeapOptimised;
			while (p != null) {
				if (!visited[p.number]) {
					long roadDistance = 0l, pathDistance = 0l;
					if (p.type == 0) {
						roadDistance = minDistanceCrossing.getDistance() + p.length;
					}
					else {
						roadDistance = minDistanceCrossing.roadDistance;
						pathDistance = minDistanceCrossing.pathDistance + p.length;
					}
					queue.add(new CrossingDistance(p.number, roadDistance, pathDistance));
				}
				p = p.neighbourCrossingDijkstraHeapOptimised;
			}
		}
		
	}
}	

class CrossingDijkstraHeapOptimised {
	int number;
	int length;
	int type;	//0为大道，1为小道
	CrossingDijkstraHeapOptimised neighbourCrossingDijkstraHeapOptimised;
	
	public CrossingDijkstraHeapOptimised(int number) {
		this.number = number;
		neighbourCrossingDijkstraHeapOptimised = null;
	}
	
	public void addNeighbour(int neighbourNumber, int roadLength, int type) {
		CrossingDijkstraHeapOptimised p = this;
		while (p.neighbourCrossingDijkstraHeapOptimised != null) {
			p = p.neighbourCrossingDijkstraHeapOptimised;
		}
		p.neighbourCrossingDijkstraHeapOptimised = new CrossingDijkstraHeapOptimised(neighbourNumber);
		p.neighbourCrossingDijkstraHeapOptimised.length = roadLength;
		p.neighbourCrossingDijkstraHeapOptimised.type = type;
	}
}

class CrossingDistance implements Comparable<CrossingDistance>{
	int crossing;
	long roadDistance;
	long pathDistance;
	
	@Override
	public int compareTo(CrossingDistance o) {
		return ((Long) getDistance()).compareTo(o.getDistance());
	}
	
	public CrossingDistance(int crossing, long roadDistance, long pathDistance) {
		this.crossing = crossing;
		this.roadDistance = roadDistance;
		this.pathDistance = pathDistance;
	}
	
	public long getDistance() {
		return roadDistance + pathDistance * pathDistance;
	}
}