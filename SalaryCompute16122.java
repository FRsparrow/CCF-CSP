package ccf;
//100
import java.util.Scanner;

public class SalaryCompute16122 {
	static int salary;
	static int[] bound = {0,    1500, 4500, 9000, 35000, 55000, 80000};
	static float sum;
	static float[] tax = {0.03f,0.10f,0.20f,0.25f,0.30f, 0.35f, 0.45f};
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		salary = scanner.nextInt();
		scanner.close();
		System.out.println(beforeTax());
	}
	
	static int beforeTax() {
		if (salary <= 3500) {
			return salary;
		}
		salary -= 3500;
		int level = level();
		int beforeTax = 3500;
		beforeTax += bound[level];
		beforeTax += (salary - sum) / (1 - tax[level]);
		return beforeTax;
	}
	
	static int level() {
		int level = 0;
		sum = 0;
		do{
			sum += (bound[level+1] - bound[level]) * (1 - tax[level]);
		}while (salary > sum && ++level < 6);
		sum -= level < 6 ? (bound[level+1] - bound[level]) * (1 - tax[level]) : 0;
		return level;
	}
}
