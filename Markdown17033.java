package ccf;

import java.util.Scanner;

public class Markdown17033 {
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = null;
		boolean firstStar = true;
		boolean firstPara = true;
		sb = new StringBuilder();
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
//			System.out.println("line:" + line);
			if(!line.equals("")) {
				char first = line.charAt(0);
				if (first == '*') {
					if (!firstPara) {
						sb.deleteCharAt(sb.length()-1);
						sb.append("</p>\n\n");
						firstPara = true;
					}
					if (firstStar) {
						sb.append("<ul>\n");
						firstStar = false;
					}
					addItem(line);
				}
				else if(first == '#') {
					if (!firstStar) {
						sb.append("</ul>\n\n");
						firstStar = true;
					}
					if (!firstPara) {
						sb.deleteCharAt(sb.length()-1);
						sb.append("</p>\n\n");
						firstPara = true;
					}
					addTitle(line);
				}
				else {
					if (!firstStar) {
						sb.append("</ul>\n\n");
						firstStar = true;
					}
					if (firstPara) {
						sb.append("<p>");
						firstPara = false;
					}
					sb.append(deal(line));
//					while (!line.equals("")) {
//						sb.append(deal(line) + "\n");
//						if (scanner.hasNextLine()) {
//							line = scanner.nextLine();
//						}
//						else {
//							break;
//						}
//					}
//					sb.deleteCharAt(sb.length()-1);
//					sb.append("</p>\n");
				}
				sb.append("\n");
			}
			else {
				if (!firstStar) {
					sb.append("</ul>\n\n");
					firstStar = true;
				}
				if (!firstPara) {
					sb.deleteCharAt(sb.length()-1);
					sb.append("</p>\n\n");
					firstPara = true;
//					System.out.println("当前结果："+sb.toString());
				}
			}
		}
		if (!firstStar) {
			sb.append("</ul>");
		}
		if (!firstPara) {
			sb.deleteCharAt(sb.length()-1);
			sb.append("</p>");
		}
		scanner.close();
		System.out.println(sb.toString());
	}
	
	static void addItem(String itemLine) {
		int index = 0;
		while (itemLine.charAt(index) == '*' || itemLine.charAt(index) == ' ') {
			++index;
		}
		sb.append("<li>" + deal(itemLine.substring(index)) + "</l>");
	}

	static void addTitle(String titleLine) {
		int index = 0, count = 0;
		while (titleLine.charAt(index) == '#') { ++index; }
		count = index;
		while (titleLine.charAt(++index) == ' ') {}
		
		sb.append("<h"+ count + ">" + deal(titleLine.substring(index)) + "</h" + count + ">\n");
	}

	
	static String deal(String text) {
		String result = "";
		int index = 0;
		while (index < text.length()) {
			char c = text.charAt(index);
			if (c == '_') {
				int end = index;
				while (text.charAt(++end) != '_') {}
				result += "<em>" + dealText(text.substring(index+1, end)) + "</em>";
				index = end + 1;
			}
			else if (c == '[') {
				int end = index, rightend;
				while (text.charAt(++end) != ']') {}
				rightend = end + 2;
				while (text.charAt(++rightend) != ')') {}
				result += "<a href=\"" + text.substring(end+2, rightend) + "\">" + dealText(text.substring(index+1, end)) + "</a>";
				index = rightend + 1;
			}
			else {
				result += text.charAt(index++);
			}
		}
		return result;
	}
	
	static String dealText(String text) {
		String result = "";
		int index = 0;
		while (index < text.length()) {
			char c = text.charAt(index);
			if (c == '_') {
				result += "<em>";
				while (text.charAt(++index) != '_') { result += text.charAt(index);}
				result += "</em>";
				++index;;
			}
			else if (c == '[') {
				int end = index, rightend;
				while (text.charAt(++end) != ']') {}
				rightend = end + 2;
				while (text.charAt(++rightend) != ')') {}
				result += "<a href=\"" + text.substring(end+2, rightend) + "\">" + text.substring(index+1, end) + "</a>";
				index = rightend + 1;
			}
			else {
				result += text.charAt(index++);
			}
		}
		return result;
	}
	
	
}
