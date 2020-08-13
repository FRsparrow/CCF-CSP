package ccf;

import java.util.LinkedList;
import java.util.Scanner;

public class TwentyFour19032 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String[] result = new String[n];
		
		for (int i = 0; i < n; i++) {
			String s = scanner.next();
			result[i] = isTwentyFour(s);
		}
		
		scanner.close();
		
		for (int i = 0; i < n; i++) {
			System.out.println(result[i]);
		}
	}
	
	static String isTwentyFour(String s) {
		int result = 0;
		LinkedList<Integer> integers = new LinkedList<Integer>();
		LinkedList<Character> characters = new LinkedList<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((i % 2) == 0) {	//Êý×Ö
				integers.add(Integer.parseInt(c + ""));
			}
			else {				//ÔËËã·û
				if (c == '+' || c == '-') {
					characters.add(c);
				}
				else {
					int previous = integers.pollLast();
					int next = Integer.parseInt(s.charAt(i+1) + "");
					if (c == 'x') {
						integers.add(previous * next);
					}
					else {
						integers.add(previous / next);
					}
					++i;
				}
			}
		}
		int size = integers.size();
		result = integers.poll();
		for (int i = 1; i < size; i++) {
			char c = characters.poll();
			if (c == '+') {
				result += integers.poll();
			}
			else {
				result -= integers.poll();
			}
		}
		if (result == 24) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
}
