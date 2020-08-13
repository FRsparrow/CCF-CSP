package ccf;

/*得分90*/
import java.util.Scanner;

public class Apple19092 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int sum = 0;		//苹果总数
		int lossTree = 0;	//掉落苹果的树的总数
		int E = 0;			//连续3个相邻的树掉苹果的组数
		int treeSum = 0;	//连续第treeSum棵树掉落苹果
		boolean firstLoss = false;		//第一棵树是否掉落苹果
		boolean secondLoss = false;		//第二棵树是否掉落苹果
		boolean lastLoss = false;		//倒数第一棵树是否掉落苹果
		boolean secondLastLoss = false;	//倒数第二棵树是否掉落苹果
		
		for (int i = 0; i < n; i++) {
			int m = scanner.nextInt();
			int apple = scanner.nextInt();
			boolean hasLoss = false;
			for (int j = 1; j < m; j++) {
				int number = scanner.nextInt();
				if (number > 0) {	//统计苹果数量
					hasLoss = hasLoss || (number < apple);	//有苹果掉落
					apple = number;
				}else {				//疏果
					apple += number;
				}
			}
			if (hasLoss) {	//有苹果掉落
				if (i == 0) firstLoss = true;
				if (i == 1) secondLoss = true;
				if (i == n-1) lastLoss = true;
				if (i == n-2) secondLastLoss = true;
				++lossTree;
				++treeSum;
				if (treeSum == 3) {
					++E;
					--treeSum;
				}
			}else {
				treeSum = 0;
			}
			sum += apple;
		}
		if(n > 3) {
			if (firstLoss && lastLoss) {
				if (secondLoss) {
					++E;
				}
				if (secondLastLoss) {
					++E;
				}
			}
		}
		
		System.out.println(sum + " " + lossTree + " " + E);
		scanner.close();
	}
}
