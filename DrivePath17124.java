package ccf;
//30,运行超时
import java.util.Scanner;

public class DrivePath17124 {
	static int n;
	static int m;
//	static int head = 1;
	static int ans = Integer.MAX_VALUE;
	static Crossing[] crossingGraph;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		crossingGraph = new Crossing[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			crossingGraph[i] = new Crossing(i);
			visited[i] = false;
		}
		
		for (int i = 0; i < m; i++) {
			int type = scanner.nextInt();
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			crossingGraph[a].addNeighbour(b, c, type);
			crossingGraph[b].addNeighbour(a, c, type);
		}
		scanner.close();
		
		visited[1] = true;
		dfs(1, 0, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int head, int previousTiredness, int pathLength) {
		Crossing p = crossingGraph[head].neighbourCrossing;
		while (p != null) {
			if (p.number == n) {
				int totalLength = previousTiredness + (p.type == 0 ? 
						(pathLength * pathLength + p.length) :
							(pathLength + p.length) * (pathLength + p.length));
				if (totalLength < ans) {
					ans = totalLength;
				}
				p = p.neighbourCrossing;
				continue;
			}
			if (!visited[p.number]) {
				visited[p.number] = true;
				int newPreviousTiredness = previousTiredness, newPathLength = 0;
				if (p.type == 0) {
					newPreviousTiredness += pathLength * pathLength + p.length;
				}
				else {
					newPathLength = pathLength + p.length;
				}
				dfs(p.number, newPreviousTiredness, newPathLength);
				visited[p.number] = false;
			}
			p = p.neighbourCrossing;
		}
	}

}	

class Crossing {
	int number;
	int length;
	int type;	//0为大道，1为小道
	Crossing neighbourCrossing;
	
	public Crossing(int number) {
		this.number = number;
		neighbourCrossing = null;
	}
	
	public void addNeighbour(int neighbourNumber, int roadLength, int type) {
		Crossing p = this;
		while (p.neighbourCrossing != null) {
			p = p.neighbourCrossing;
		}
		p.neighbourCrossing = new Crossing(neighbourNumber);
		p.neighbourCrossing.length = roadLength;
		p.neighbourCrossing.type = type;
	}
}