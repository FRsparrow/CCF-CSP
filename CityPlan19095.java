//暴力法，得分20
package ccf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class CityPlan19095 {
	static int distance = 0;
	static boolean[] w;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();		//顶点数
		int m = scanner.nextInt();		//重要公交站数
		int k = scanner.nextInt();		//要选出的重要公交站数
		int minSum = Integer.MAX_VALUE;	//最小路径和
		
		int[] important = new int[m];
		w = new boolean[n+1];
		Node[] graph = new Node[n+1];
		
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new Node(i, 0);
		}
		
		//读入重要节点
		for (int i = 0; i < m; i++) {
			important[i] = scanner.nextInt();
		}
		//读入图的信息
		for (int i = 0; i < n-1; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int weight = scanner.nextInt();
			graph[a].addNeighbour(b, weight);
			graph[b].addNeighbour(a, weight);
		}
		scanner.close();
		
		int importantDistance[][] = new int[m][m];
		
		//计算重要公交站两两之间距离
		for (int i = 0; i < m; i++) {
			importantDistance[i][i] = 0;
			for (int j = 0; j < i; j++) {
				importantDistance[i][j] = distance(n, important[i], important[j], graph);
				importantDistance[j][i] = importantDistance[i][j];
			}
		}
		
		//遍历所有种选法计算最小路径和
		HashSet<ArrayList<Integer>> candidates = getAllCombination(0, m, k);
		for (Iterator iterator = candidates.iterator(); iterator.hasNext();) {
			ArrayList<Integer> item = (ArrayList<Integer>) iterator.next();
			int tempSum = 0;
			for (int i = 0; i < k-1; i++) {
				for (int j = i+1; j < k; j++) {
					tempSum += importantDistance[item.get(i)][item.get(j)];
				}
			}
			if (tempSum < minSum) {
				minSum = tempSum;
			}
		}
		
		System.out.println(minSum);
		
//		System.out.println(distance(n, 1, 2, graph));
//		System.out.println(distance(n, 1, 3, graph));
//		System.out.println(distance(n, 1, 4, graph));
//		System.out.println(distance(n, 3, 4, graph));
//		System.out.println(distance(n, 4, 2, graph));
	}
	
	//计算树graph中顶点v1和v2之间的距离
	static int distance(int n, int v1, int v2, Node[] graph) {
		distance = 0;
		for (int i = 1; i < n+1; i++) {
			if (v1 != i) {
				w[i] = false;
			}
			else {
				w[i] = true;
			}
		}
		
		dfs(v1, v2, w, graph);
		
		return distance;
	}
	
	static void dfs(int v1, int v2, boolean[] w, Node[] graph) {
		if (w[v2]) return;	//找到v2则返回
		Node neighbour = graph[v1].neighbour;
		while (neighbour != null && !w[v2]) {
			if (!w[neighbour.number]) {
				w[neighbour.number] = true;
				distance += neighbour.weight;
				if (neighbour.number == v2) {
					return;
				}
				dfs(neighbour.number, v2, w, graph);
				if (!w[v2]) {
					distance -= neighbour.weight;
				}
			}
			neighbour = neighbour.neighbour;
		}
	}
	
	//在区间[begin, end]中选出k个整数
	static HashSet<ArrayList<Integer>> getAllCombination(int begin, int end, int k) {
		HashSet<ArrayList<Integer>> kCombination = new HashSet<ArrayList<Integer>>();
//		ArrayList<ArrayList<Integer>> returnResult = new ArrayList<ArrayList<Integer>>();
		//从k个数中选k个
		if (end - begin == k) {
			ArrayList<Integer> item = new ArrayList<Integer>();
			for (int i = begin; i < end; i++) {
				item.add(i);
			}
			kCombination.add(item);
			return kCombination;
		}
		//从end-begin个数中选一个
		if (k == 1) {
			for (int i = begin; i < end; i++) {
				ArrayList<Integer> item = new ArrayList<Integer>();
				item.add(i);
				kCombination.add(item);
			}
			return kCombination;
		}
		
		for (int i = begin; i < end; i++) {
			HashSet<ArrayList<Integer>> k_1Combination = getAllCombination(i+1, end, k-1);
			for (Iterator iterator = k_1Combination.iterator(); iterator.hasNext();) {
				ArrayList<Integer> item = (ArrayList<Integer>) iterator.next();
				item.add(i);
				kCombination.add(item);
			}
		}
		return kCombination;
	}
}

class Node{
	int number;
	int weight;
	Node neighbour;
	
	public Node() {}
	
	public Node(int number, int weight) {
		this.number = number;
		this.weight = weight;
		neighbour = null;
	}
	
	public void addNeighbour(int number, int weight) {
		Node p = this;
		while (p.neighbour != null) {
			p = p.neighbour;
		}
		Node nextNode = new Node(number, weight);
		p.neighbour = nextNode;
	}
}