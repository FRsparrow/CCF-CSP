package ccf;
//70,运行超时
import java.util.Scanner;

public class DrivePath17124_Dijkstra {
	static int MAX_DISTANCE = 1000000;
	static int n;
	static int m;
	static CrossingDijkstra[] CrossingDijkstraGraph;
	static int[] roadDistance;
	static int[] pathDistance;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		CrossingDijkstraGraph = new CrossingDijkstra[n+1];
		roadDistance = new int[n+1];
		pathDistance = new int[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			CrossingDijkstraGraph[i] = new CrossingDijkstra(i);
			roadDistance[i] = MAX_DISTANCE;
			pathDistance[i] = MAX_DISTANCE;
			visited[i] = false;
		}
		
		for (int i = 0; i < m; i++) {
			int type = scanner.nextInt();
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			CrossingDijkstraGraph[a].addNeighbour(b, c, type);
			CrossingDijkstraGraph[b].addNeighbour(a, c, type);
		}
		scanner.close();
		
		initialize();
		dijkstra();
		
		int ans = roadDistance[n] + pathDistance[n] * pathDistance[n];
		System.out.println(ans);
	}
	
	static void initialize() {
		roadDistance[1] = 0;
		pathDistance[1] = 0;
		visited[1] = true;
		CrossingDijkstra p = CrossingDijkstraGraph[1].neighbourCrossingDijkstra;
		while (p != null) {
			roadDistance[p.number] = (1 - p.type) * p.length;
			pathDistance[p.number] = p.type * p.length;
			p = p.neighbourCrossingDijkstra;
		}
	}
	
	static void dijkstra() {
		for (int i = 1; i <= n; i++) {
			long minDistance = MAX_DISTANCE;
			int minDistanceCrossing = 0;
			for (int j = 2; j <= n; j++) {
				if (!visited[j] && roadDistance[j] < MAX_DISTANCE) {
					long tempDistance = roadDistance[j] + pathDistance[j] * pathDistance[j];
					if (tempDistance < minDistance) {
						minDistance = tempDistance;
						minDistanceCrossing = j;
					}
				}
			}
			visited[minDistanceCrossing] = true;	//将最短距离的点加入集合
			if (minDistanceCrossing == n) {
				return;
			}
			//更新最短距离的点的邻居的距离
			CrossingDijkstra p = CrossingDijkstraGraph[minDistanceCrossing].neighbourCrossingDijkstra;
			while (p != null) {
				if (!visited[p.number]) {
//					if (roadDistance[p.number] < MAX_DISTANCE) {
//						
//					}
					long directDistance = (long) roadDistance[p.number] + (long) pathDistance[p.number] * (long) pathDistance[p.number];
					int tempPathDistance = p.type * p.length;
					int tempRoadDistance = p.length - tempPathDistance;
					int tempDistance = roadDistance[minDistanceCrossing]
							+ (pathDistance[minDistanceCrossing] + tempPathDistance) * (pathDistance[minDistanceCrossing] + tempPathDistance)
							+ tempRoadDistance;
					if (directDistance > tempDistance) {
						if (p.type == 0) {
							roadDistance[p.number] = tempDistance;
							pathDistance[p.number] = 0;
						}
						else {
							roadDistance[p.number] = roadDistance[minDistanceCrossing];
							pathDistance[p.number] = pathDistance[minDistanceCrossing] + p.length;
						}
					}
				}
				p = p.neighbourCrossingDijkstra;
			}
		}
		
	}
}	

class CrossingDijkstra {
	int number;
	int length;
	int type;	//0为大道，1为小道
	CrossingDijkstra neighbourCrossingDijkstra;
	
	public CrossingDijkstra(int number) {
		this.number = number;
		neighbourCrossingDijkstra = null;
	}
	
	public void addNeighbour(int neighbourNumber, int roadLength, int type) {
		CrossingDijkstra p = this;
		while (p.neighbourCrossingDijkstra != null) {
			p = p.neighbourCrossingDijkstra;
		}
		p.neighbourCrossingDijkstra = new CrossingDijkstra(neighbourNumber);
		p.neighbourCrossingDijkstra.length = roadLength;
		p.neighbourCrossingDijkstra.type = type;
	}
}