package ccf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception{
//		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
//		ArrayList<Integer> arrayList = new ArrayList<Integer>(3);
//		System.out.println("size:" + arrayList.size());
//		arrayList.set(0, 1);
//		arrayList.set(2, 3);
//		arrayList.set(1, 2);
//		for (int i = 0; i < arrayList.size(); i++) {
//			System.out.println(arrayList.get(i));
//		}
//		HashSet<ArrayList<Integer>> test = getAllCombination(1, 7, 3);
//		for (Iterator iterator = test.iterator(); iterator.hasNext();) {
//			ArrayList<Integer> arrayList = (ArrayList<Integer>) iterator.next();
//			for (int i = 0; i < arrayList.size(); i++) {
//				System.out.print(arrayList.get(i) + " ");
//			}
//			System.out.println();
//		}
//		Integer integer = 2;
//		f(integer);
//		System.out.println(integer);
//		String filePath = "C:\\Users\\Sparrow\\Desktop\\test\\data.txt";
//        FileInputStream fin = new FileInputStream(filePath);
//        InputStreamReader reader = new InputStreamReader(fin);
//        BufferedReader buffReader = new BufferedReader(reader);
//        String strTmp = "";
//        Long sum = 0l;
//        long totalTime = 0l;
//        int n = 12034 * 32;
//        for (int i = 0; i < 20; i++) {
//        	int[] a = new int[n];
//            Long startTime = System.currentTimeMillis();
////            for (int i = 0; i < n; i++) {
////    			a[i] = Integer.parseInt(buffReader.readLine());
////    			sum += a[i];
////    		}
//            for (int j = 0; j < n; j++) {
//    			a[j] = Integer.parseInt(buffReader.readLine());
//    		}
//            for (int j = 0; j < n; j++) {
//    			sum += a[j];
//    		}
//            Long endTime = System.currentTimeMillis();
//            totalTime += endTime - startTime;
//            buffReader.close();
//		}
//        System.out.println(totalTime / 20);
//        
//        for (int j = 0; j < 20; j++) {
//        	int[] a = new int[n];
//            Long startTime = System.currentTimeMillis();
//            for (int i = 0; i < n; i++) {
//    			a[i] = Integer.parseInt(buffReader.readLine());
//    			sum += a[i];
//    		}
//            Long endTime = System.currentTimeMillis();
//            totalTime += endTime - startTime;
//            buffReader.close();
//		}
//        System.out.println(totalTime / 20);
        
//        System.out.println(sum);
//        System.out.println(endTime-startTime);
		Scanner scanner = new Scanner(System.in);
		int n = 0;
//		while (scanner.hasNextLine()) {
////			System.out.println(scanner.nextLine());
//			scanner.nextLine();
//			++n;
//		}
		System.out.println(scanner.hasNextLine());
		System.out.println(scanner.hasNextLine());
		System.out.println(scanner.nextLine());
		System.out.println("end");
		System.out.println("n = " + n);
		scanner.close();
	}
	
	static int lowbit(int i) {
		return i & -i;
	}
	
	static void g(String[] strings) {
		strings[0] = "fsg";
	}
	
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

class A {
	int number;
	A[] a;
	
	public A(int number) {
		this.number = number;
		a = new A[number-1];
	}
	
	public void initialize() {
		for (int i = 0; i < number; i++) {
			a[i] = new A(number-1);
		}
	}
}