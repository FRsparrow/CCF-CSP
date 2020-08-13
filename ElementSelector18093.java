package ccf;
//100
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ElementSelector18093 {
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	static ArrayList<Integer> multiAns = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] numbers = scanner.nextLine().split(" ");
		int n = Integer.parseInt(numbers[0]);
		int m = Integer.parseInt(numbers[1]);
		TreeNode root = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		for (int i = 1; i <= n; i++) {
			String[] strings = scanner.nextLine().split(" ");
			int depth = strings[0].lastIndexOf(".");
			String label = strings[0].substring(depth+1);
			String id = null;
			if (strings.length == 2) {	//有id选择器
				id = strings[1].substring(1);
			}
			TreeNode treeNode = new TreeNode(i, depth, label, id);
			if (stack.isEmpty()) {
				stack.add(treeNode);
				root = treeNode;
			}
			else {
				while (stack.peek().depth >= depth) {
					stack.pop();
				}
				stack.peek().children.add(treeNode);
				stack.add(treeNode);
			}
		}
		
		ArrayList<Integer>[] result = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			String selector = scanner.nextLine();
			result[i] = select(root, selector);
		}
		scanner.close();
		
		for (int i = 0; i < m; i++) {
			System.out.print(result[i].size());
			for (int j = 0; j < result[i].size(); j++) {
				System.out.print(" " + result[i].get(j));
			}
			System.out.println();
		}
	}
	
	static ArrayList<Integer> select(TreeNode root, String selector) {
		ans.clear();
		if (!selector.contains(" ")) {	//不是多级选择器
			if (selector.charAt(0) == '#') {	//id选择器
				idSelect(root, selector.substring(1));
				return (ArrayList<Integer>) ans.clone();
			}
			else {	//标签选择器
				labelSelect(root, selector);
				return (ArrayList<Integer>) ans.clone();
			}
		}
		//多级选择器
		multiAns.clear();
		mutiSelect(root, selector);
		return (ArrayList<Integer>) multiAns.clone();
	}
	
	static void idSelect(TreeNode root, String idSelector) {
		if (root.id != null) {
			if (root.id.equals(idSelector)) {
				ans.add(root.row);
			}
		}
		for (TreeNode child : root.children) {
			idSelect(child, idSelector);
		}
	}
	
	static void labelSelect(TreeNode root, String labelSelector) {
		if (root.label.equalsIgnoreCase(labelSelector)) {
			ans.add(root.row);
		}
		for (TreeNode child : root.children) {
			labelSelect(child, labelSelector);
		}
	}
	
	static void mutiSelect(TreeNode root, String multiSelector) {
		String[] selectors = multiSelector.split(" ");
		if (selectors.length == 1) {
			ans.clear();
			if (selectors[0].charAt(0) == '#') {
				idSelect(root, selectors[0].substring(1));
			}
			else {
				labelSelect(root, selectors[0]);
			}
			for (Integer row : ans) {
				multiAns.add(row);
			}
		}
		else {
			if (selectors[0].charAt(0) == '#') {
				if (selectors[0].substring(1).equals(root.id)) {
					for (TreeNode child : root.children) {
						mutiSelect(child, multiSelector.substring(multiSelector.indexOf(" ")+1));
					}
				}
				else {
					for (TreeNode child : root.children) {
						mutiSelect(child, multiSelector);
					}
				}
			}
			else {
				if (selectors[0].equalsIgnoreCase(root.label)) {
					for (TreeNode child : root.children) {
						mutiSelect(child, multiSelector.substring(multiSelector.indexOf(" ")+1));
					}
				}
				else {
					for (TreeNode child : root.children) {
						mutiSelect(child, multiSelector);
					}
				}
			}
		}
	}
}

class TreeNode {
	int row;
	int depth;
	String label;
	String id;
	ArrayList<TreeNode> children;
	
	public TreeNode(int row, int depth, String label, String id) {
		this.row = row;
		this.depth = depth;
		this.label = label;
		this.id = id;
		children = new ArrayList<TreeNode>();
	}
}