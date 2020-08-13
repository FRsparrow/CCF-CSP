package ccf;
//90,‘À––¥ÌŒÛ
import java.util.HashMap;
import java.util.Scanner;

public class JSONQuery17093 {
	static int n;
	static int m;
	static String[] ans;
//	static HashMap<String, String> map;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		scanner.nextLine();
//		map = new HashMap<String, String>();
		ans = new String[m];
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			stringBuilder.append(scanner.nextLine());
		}
		
		HashMap<String, String> map = JSONParser(stringBuilder.toString());
		
		for (int i = 0; i < m; i++) {
			String query = scanner.nextLine();
			if (map.containsKey(query)) {
				if (map.get(query) != null) {
					ans[i] = "STRING " + map.get(query);
				}
				else {
					ans[i] = "OBJECT";
				}
			}
			else {
				ans[i] = "NOTEXIST";
			}
		}
		scanner.close();
		
		for (int i = 0; i < m; i++) {
			System.out.println(ans[i]);
		}
	}
	
	static HashMap<String, String> JSONParser(String jsonData) {
		HashMap<String, String> map = new HashMap<String, String>();
		String head = "";
		int keyStart = 0, keyEnd = 0, valueStart = 0, valueEnd = 0;
		String key = "", value = "";
		while (keyStart < jsonData.length() && valueEnd < jsonData.length()) {
			key = "";
			value = "";
			while (jsonData.charAt(keyStart) != '\"') { ++keyStart;}
			keyEnd = keyStart + 1;
			while (jsonData.charAt(keyEnd) != '\"') {
				if (jsonData.charAt(keyEnd) == '\\') {
					key += jsonData.charAt(++keyEnd);
				}
				else {
					key += jsonData.charAt(keyEnd);
				}
				++keyEnd;
			}
			valueStart = keyEnd + 1;
			while (jsonData.charAt(valueStart) != '\"' && jsonData.charAt(valueStart) != '{') { ++valueStart;}
			valueEnd = valueStart + 1;
			if (jsonData.charAt(valueStart) == '{') {
				map.put(head + key, null);
				head += key + ".";
				keyStart = valueEnd;
			}
			else {
				while (jsonData.charAt(valueEnd) != '\"') {
					if (jsonData.charAt(valueEnd) == '\\') {
						value += jsonData.charAt(++valueEnd);
					}
					else {
						value += jsonData.charAt(valueEnd);
					}
					++valueEnd;
				}
				map.put(head + key, value);
				keyStart = valueEnd + 1;
				while (++valueEnd < jsonData.length()
						&& (jsonData.charAt(valueEnd) == '}' || jsonData.charAt(valueEnd) == ' ' || jsonData.charAt(valueEnd) == ',')) {
					if (jsonData.charAt(valueEnd) == '}' && head.contains(".")) {
						int index = head.length() - 2;
						while (index > 0 && head.charAt(index) != '.') { --index;}
						if (index == 0) {
							head = "";
						}
						else {
							head = head.substring(0, index+1);
						}
					}
				}
			}
			
		}
		
		return map;
	}
}
