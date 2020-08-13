package ccf;
//60
import java.util.Scanner;

public class URLMap18033 {
	static String[] rules;
	static String[] ruleNames;
	static String url;
	static String[] ans;
	
//	static final HashSet<E>
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		rules = new String[n];
		ruleNames = new String[n];
		ans = new String[m];
		
		for (int i = 0; i < n; i++) {
			rules[i] = scanner.next().substring(1);
			ruleNames[i] = scanner.next();
		}
		
		for (int i = 0; i < m; i++) {
			url = scanner.next().substring(1);
			if (!isLegal(url)) {
				ans[i] = "404";
			}
			else {
				match(i);
			}
		}
		scanner.close();
		
		for (int i = 0; i < m; i++) {
			System.out.println(ans[i]);
		}
	}
	
	static void match(int index) {
		for (int i = 0; i < rules.length; i++) {
			ans[index] = ruleNames[i];
			String[] rule = rules[i].split("/");
			String[] urlStrings = url.split("/");
			if (rule.length > urlStrings.length || 
					(!rule[rule.length-1].equals("<path>") && rule.length < urlStrings.length)) {
				continue;
			}
			boolean match = true;
			for (int j = 0; j < rule.length; j++) {
				if (rule[j].equals("<path>")) {
					ans[index] += " " + urlStrings[j];
					for (int j2 = j+1; j2 < urlStrings.length; j2++) {
						ans[index] += "/" + urlStrings[j2];
					}
					if (url.charAt(url.length()-1) == '/') {
						ans[index] += "/";
					}
					return;
				}
				else if (rule[j].equals("<int>")) {
					if (!isInt(urlStrings[j])) {
						match = false;
						break;
					}
					else {
						ans[index] += " " + Integer.parseInt(urlStrings[j]);
					}
				}
				else if (!rule[j].equals("<str>") && !rule[j].equals(urlStrings[j])) {
					match = false;
					break;
				}
				else if (rule[j].equals("<str>")) {
					ans[index] += " " + urlStrings[j];
				}
			}
			if (match) {
				return;
			}
		}
		ans[index] = "404";
	}
	
	static boolean isInt(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	static boolean isLegal(String urlString) {
		for (int i = 0; i < urlString.length(); i++) {
			char ch = urlString.charAt(i);
			if (!Character.isDigit(ch)
					&& !Character.isLetter(ch)
					&& ch != '/'
					&& ch != '-'
					&& ch != '_'
					&& ch != '.') {
				return false;
			}
		}
		return true;
	}
}