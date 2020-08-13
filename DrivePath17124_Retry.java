package ccf;
//30，运行超时
import java.util.ArrayList;
import java.util.Scanner;

public class DrivePath17124_Retry {
	static int n;
	static int m;
	static int ans = Integer.MAX_VALUE;	
	static int[][] distance;
	static CrossingRetry[] CrossingRetryGraph;
	static Length[] lengths;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		distance = new int[n+1][n+1];
		CrossingRetryGraph = new CrossingRetry[n+1];
		lengths = new Length[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			CrossingRetryGraph[i] = new CrossingRetry(i);
			lengths[i] = new Length();
			visited[i] = false;
		}
		
		for (int i = 0; i < m; i++) {
			int type = scanner.nextInt();
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			CrossingRetryGraph[a].addNeighbour(b, c, type);
			CrossingRetryGraph[b].addNeighbour(a, c, type);
			distance[a][b] = c;
			distance[b][a] = c;
		}
		scanner.close();
		
		visited[1] = true;
		visited[n] = false;
		CrossingRetryGraph[1].previousCrossingRetry = CrossingRetryGraph[1];
		lengths[n].visited = true;
		lengths[n].onlyRoadLength = 0;
		lengths[n].paths.add(0);
		lengths[n].roads.add(0);
		
		dfs(CrossingRetryGraph[1]);
		
		ans = Math.min(ans, lengths[1].onlyRoadLength);
		for (int i = 0; i < lengths[1].paths.size(); i++) {
			ans = Math.min(ans, lengths[1].paths.get(i) * lengths[1].paths.get(i) + lengths[1].roads.get(i));
		}
		
		System.out.println(ans);
	}
	
	static void dfs(CrossingRetry previousCrossingRetry) {
//		System.out.print(head + " ");
		int head = previousCrossingRetry.number;
		CrossingRetry p = CrossingRetryGraph[head].neighbourCrossingRetry;
//		System.out.println(visited[p.number] + " " + p.number);
		while (p != null) {
			p.previousCrossingRetry = previousCrossingRetry;
			if (p.number == n || 	//到了终点
					(!visited[p.number] && lengths[p.number].visited)) {	//或者从该邻居可以到终点
				for (int i = 0; i < lengths[p.number].paths.size(); i++) {
					int pathLength = lengths[p.number].paths.get(i), roadLength = lengths[p.number].roads.get(i);
					CrossingRetry q = p;
					do {
						int index = q.previousCrossingRetry.number;
						if (q.type == 0) {
							roadLength += pathLength * pathLength + q.length;
							pathLength = 0;
							lengths[index].onlyRoadLength = Math.min(lengths[index].onlyRoadLength, roadLength);
						}
						else {
							pathLength += q.length;
							lengths[index].addPathAndRoad(pathLength, roadLength);
							lengths[index].visited = true;
						}
//						System.out.println(q.number);
						q = q.previousCrossingRetry;
					} while (q.number != 1);
				}
			}
			else {
				if (!visited[p.number]) {
					visited[p.number] = true;
					dfs(p);
					visited[p.number] = false;
				}
			}
			p = p.neighbourCrossingRetry;
		}
	}
}

class CrossingRetry {
	int number;
	int length;
	int type;	//0为大道，1为小道
	CrossingRetry neighbourCrossingRetry;
	CrossingRetry previousCrossingRetry;
	
	public CrossingRetry(int number) {
		this.number = number;
		neighbourCrossingRetry = null;
		previousCrossingRetry = null;
	}
	
	public void addNeighbour(int neighbourNumber, int roadLength, int type) {
		CrossingRetry p = this;
		while (p.neighbourCrossingRetry != null) {
			p = p.neighbourCrossingRetry;
		}
		p.neighbourCrossingRetry = new CrossingRetry(neighbourNumber);
		p.neighbourCrossingRetry.length = roadLength;
		p.neighbourCrossingRetry.type = type;
	}
}

class Length {
	boolean visited;
	int onlyRoadLength;
	ArrayList<Integer> paths;	//小道
	ArrayList<Integer> roads;	//大道
	
	public Length() {
		visited = false;
		onlyRoadLength = Integer.MAX_VALUE;
		paths = new ArrayList<Integer>();
		roads = new ArrayList<Integer>();
	}
	
	public void addPathAndRoad(int pathLength, int roadLength) {
		for (int i = 0; i < paths.size(); i++) {
			if (pathLength >= paths.get(i) && roadLength >= roads.get(i)) {
				return;
			}
		}
		paths.add(pathLength);
		roads.add(roadLength);
	}
}