package ccf;
//暴力，60，运行超时
import java.util.Scanner;

public class CommunicationNetwork17094DFS {
	static int n;
	static boolean[][] graph;
	static boolean[][] know;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		int m = scanner.nextInt();
		graph = new boolean[n+1][n+1];
		know = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			know[i][0] = true;
			graph[i][i] = true;
			know[i][i] = true;
		}
		
		for (int i = 0; i < m; i++) {
			graph[scanner.nextInt()][scanner.nextInt()] = true;
		}
		scanner.close();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				visited[j] = false;
			}
			visited[i] = true;
			DFS(i, i);
		}
		
		int count = 0;
		for (int i = 1; i <= n; i++) {
			know[i][0] = true;
			for (int j = 1; j <= n; j++) {
				if (!know[i][j]) {
					know[i][0] = false;
					break;
				}
			}
			if (know[i][0]) {
				++count;
			}
		}
		
		System.out.println(count);
	}
	
	static void DFS(int start, int current) {
		for (int i = 1; i <= n; i++) {
			if (!visited[i] && graph[current][i]) {
				visited[i] = true;
				know[start][i] = true;
				know[i][start] = true;
				DFS(start, i);
			}
		}
	}
}
