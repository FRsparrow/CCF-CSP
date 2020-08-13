package ccf;
//50
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Crontab17123 {
	static int n;
	static Date beginDate;
	static Date endDate;
	static Command[] commands;
	static SimpleDateFormat simpleDateFormat;
	
	public static void main(String[] args) throws Exception{
		Command.initializeMap();
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		String beginDateString = scanner.next();
		String endDateString = scanner.next();
		int finishedNumber = 0;
		int[] index = new int[n];
		commands = new Command[n];
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		beginDate = simpleDateFormat.parse(beginDateString);
		endDate = simpleDateFormat.parse(endDateString);
		
		for (int i = 0; i < n; i++) {
			index[i] = 0;
			commands[i] = new Command();
			commands[i].produceTransaction(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
			commands[i].command = scanner.next();
		}
		scanner.close();
		
		//输出结果
		while (finishedNumber < n) {
			Date minDate = new Date(200, 0, 1, 0, 0);
			int minIndex = 0;
			for (int i = 0; i < n; i++) {
				if (index[i] < commands[i].dates.size() && minDate.compareTo(commands[i].dates.get(index[i])) > 0) {
					minDate = commands[i].dates.get(index[i]);
					minIndex = i;
				}	
			}
			System.out.println(simpleDateFormat.format(commands[minIndex].dates.get(index[minIndex])) + " " + commands[minIndex].command);
			++index[minIndex];
			if (index[minIndex] >= commands[minIndex].dates.size()) {
				++finishedNumber;
			}
		}
//		System.out.println(beginDate);
//		System.out.println((beginDate.getYear()+1900) + "年"
//				+ (beginDate.getMonth()+1) + "月"
//				+ beginDate.getDate() + "日"
//				+ beginDate.getHours() + "时"
//				+ beginDate.getMinutes() + "分");
	}
}

class Command {
	ArrayList<Date> dates;
	String command;
	static HashMap<String, Integer> map;
	static int[] commonYearDayNumber = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	static void initializeMap() {
		map = new HashMap<String, Integer>();
		for (int i = 0; i < 60; i++) {
			map.put(i+"", i);
			map.put("0"+i, i);
		}
		String[] weekDays = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};
		String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		for (int i = 0; i < 7; i++) {
			map.put(weekDays[i], i);
		}
		for (int i = 0; i < 12; i++) {
			map.put(months[i], i+1);
		}
	}
	
	public void produceTransaction(String minutesString, String hoursString, String daysString, String monthsString, String weekDaysString) {
		PriorityQueue<Integer> minutes = timeToIntegerQueue(minutesString, 0, 59);
		PriorityQueue<Integer> hours = timeToIntegerQueue(hoursString, 0, 23);
		PriorityQueue<Integer> days = timeToIntegerQueue(daysString, 1, 31);
		PriorityQueue<Integer> months = timeToIntegerQueue(monthsString, 1, 12);
		PriorityQueue<Integer> weekDays = timeToIntegerQueue(weekDaysString, 0, 6);
		
		int beginYear = Crontab17123.beginDate.getYear();
		int endYear = Crontab17123.beginDate.getYear();
		
		dates = new ArrayList<Date>();
		for (int year = beginYear; year <= endYear; year++) {				//年
			for (Integer month : months) {									//月
				int dayUp = commonYearDayNumber[month-1];
				if (year % 4 == 0 && month == 2) {	//闰年的二月
					++dayUp;
				}
				for (int day = 1; day <= dayUp; day++) {					//日
					int weekDay = new Date(year, month-1, day).getDay();
					if (days.contains(day) && weekDays.contains(weekDay)) {
						for (Integer hour : hours) {						//时
							for (Integer minute : minutes) {				//分
								Date date = new Date(year, month-1, day, hour, minute);
								if (date.compareTo(Crontab17123.beginDate) >= 0 &&
										date.compareTo(Crontab17123.endDate) < 0) {
									dates.add(date);
								}
							}
						}
					}
				}
			}
		}
	}
	
	static PriorityQueue<Integer> timeToIntegerQueue(String description, int low, int up) {
		PriorityQueue<Integer> a = new PriorityQueue<Integer>();
		if (description.equals("*")) {
			for (int i = low; i <= up; i++) {
				a.add(i);
			}
			return a;
		}
		String[] times = description.split(",");
		for (String item : times) {
			if (item.contains("-")) {
				int begin = map.get(item.substring(0, item.indexOf("-")).toLowerCase());
				int end = map.get(item.substring(item.indexOf("-")+1).toLowerCase());
				for (int i = begin; i <= end; i++) {
					a.add(i);
				}
			}
			else {
				a.add(map.get(item.toLowerCase()));
			}
		}
		return a;
	}
}
