package ccf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class RecommendSystem19094 {
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>[]> queryResult = new ArrayList<ArrayList<Integer>[]>();	//存储所有查询结果
		ArrayList<Commodity> commodities = new ArrayList<Commodity>();
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		
		for (int i = 0; i < n; i++) {
			//读入第i+1个商品的编号及得分
			int number = scanner.nextInt();
			int score = scanner.nextInt();
			for (int j = m - 1; j >= 0; j--) {
				//为每一类添加商品，由于数据结构是小根堆，故按升序插入元素
				commodities.add(new Commodity(j, number, score));
			}
		}
		
		int opNum = scanner.nextInt();
		for (int i = 0; i < opNum;) {
			int opType = scanner.nextInt();
			switch (opType) {
			case 1:	//增加商品
				int type1 = scanner.nextInt();
				int number1 = scanner.nextInt();
				int score1 = scanner.nextInt();
				commodities.add(new Commodity(type1, number1, score1));
				++i;
				break;
			case 2:	//删除商品
				int type2 = scanner.nextInt();
				int number2 = scanner.nextInt();
				commodities.remove(new Commodity(type2, number2, 0));
				++i;
				break;
			case 3:	//查询
				int K = scanner.nextInt();
				int sumK = 0;
				int selected = 0;
				int[] k = new int[m];
				ArrayList<Integer>[] currentQueryResult = new ArrayList[m];
				for (int j = 0; j < m; j++) {
					currentQueryResult[j] = new ArrayList<Integer>();
					k[j] = scanner.nextInt();
					sumK += k[j];
				}
				K = K > sumK ? sumK : K;	//K为最多可能选出的商品数
				Object[] commoditiesArray = commodities.toArray();
				Arrays.sort(commoditiesArray);	//按优先级升序排序
				int index = commoditiesArray.length - 1;
				
				//开始查询
				while (selected < K && index >= 0) {
					Commodity currentCommodity = ((Commodity) commoditiesArray[index]); 
					int currentType = currentCommodity.type; 
					if (k[currentType] > 0) {	//当前种类未选满则选
						currentQueryResult[currentType].add(currentCommodity.number);
						++selected;
						--k[currentType];
					}
					--index;
				}
				//选完之后值没选的类结果为-1,选了的按商品编号从小到大排序
				for (int j = 0; j < m; j++) {
					if (currentQueryResult[j].isEmpty()) {
						currentQueryResult[j].add(-1);
					}
					else {
						Collections.sort(currentQueryResult[j]);
					}
				}
				queryResult.add(currentQueryResult);
				++i;
				break;
			default:
				++i;
				break;
			}
		}
		
		scanner.close();
		//输出结果
		for (int j = 0; j < queryResult.size(); j++) {
			for (int j2 = 0; j2 < m; j2++) {
				for (int k = 0; k < queryResult.get(j)[j2].size(); k++) {
					if (k > 0) System.out.print(" ");
					System.out.print(queryResult.get(j)[j2].get(k));
				}
				System.out.println();
			}
		}
	}
	
}

class Commodity implements Comparable<Commodity>{
	int type;		//商品种类号
	int number;		//商品编号
	int score;		//商品得分
//	Commodity nextCommodity;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Commodity commodity = (Commodity) obj;
		return type == commodity.type && number == commodity.number;
	}
	
	@Override
	public int compareTo(Commodity commodity2) {
		if (score > commodity2.score) return 1;
		if (score < commodity2.score) return -1;
		if (type < commodity2.type) return 1;
		if (type > commodity2.type) return -1;
		return number < commodity2.number ? 1 : -1;
	}
	
	public Commodity() {
		// TODO Auto-generated constructor stub
	}
	
	public Commodity(int type, int number, int score) {
		this.type = type;
		this.number = number;
		this.score = score;
	}

}

