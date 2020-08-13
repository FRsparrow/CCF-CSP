package ccf;
//100
import java.util.Scanner;

public class CompositionEstimate18034 {
	static int T;
//	static int chessNumber;
//	static int[][] chessboard;
	static int[] ans;
	static Composition composition;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		T = scanner.nextInt();
		ans = new int[T];
		
		for (int i = 0; i < T; i++) {
			int chessNumber = 0;
			composition = new Composition();
			for (int j = 0; j < 9; j++) {
				composition.chessboard[j/3][j%3] = scanner.nextInt();
				if (composition.chessboard[j/3][j%3] != 0) {
					++chessNumber;
				}
			}
			//棋盘初始为空则必定平局
			if (chessNumber == 0) {
				ans[i] = 0;
				continue;
			}
			
			//建树
			composition.chessNumber = chessNumber;
			composition.prevoisComposition = null;
			composition.initialize();
			
			ans[i] = composition.score;
		}
		scanner.close();
		
		for (int i = 0; i < T; i++) {
			System.out.println(ans[i]);
		}
	}
}

class Composition {
	int score;
	int chessNumber;
	int[][] chessboard;
	Composition prevoisComposition;
	Composition[] nextCompositions;
	
	public Composition() {
		chessboard = new int[3][3];
		score = 11;
	}
	
	public void initialize() {
		score = chessNumber % 2 == 0 ? -11 : 11;
		computeScore();
		//棋局没结束
		if (score == 11 || score == -11) {
			int index = 0;
			int[] empty = new int[9-chessNumber];
			for (int i = 0; i < 9; i++) {
				if (chessboard[i/3][i%3] == 0) {
					empty[index++] = i;
				}
			}
			nextCompositions = new Composition[9-chessNumber];
			for (int i = 0; i < 9-chessNumber; i++) {
				nextCompositions[i] = new Composition();
				nextCompositions[i].prevoisComposition = this;
//				nextCompositions[i].chessboard = chessboard.clone();
				for (int j = 0; j < 9; j++) {
					nextCompositions[i].chessboard[j/3][j%3] = chessboard[j/3][j%3];
				}
				nextCompositions[i].chessboard[empty[i]/3][empty[i]%3] = (chessNumber % 2 == 0) ? 1 : 2;
				nextCompositions[i].chessNumber = chessNumber + 1;
				nextCompositions[i].initialize();
			}
		}
		if (prevoisComposition != null) {
			if (chessNumber % 2 == 0) {	//上一次是Bob下，要选score最小的下法
				prevoisComposition.score = (prevoisComposition.score < score) ? prevoisComposition.score : score;
			}
			else {	//上一次是Alice下，要选score最大的下法
				prevoisComposition.score = (prevoisComposition.score > score) ? prevoisComposition.score : score;
			}
		}
	}
	
	private void computeScore() {
		for (int i = 0; i < 3; i++) {
			if (chessboard[i][0] != 0 && chessboard[i][0] == chessboard[i][1] && chessboard[i][0] == chessboard[i][2]) {
				score = (chessboard[i][0] == 1) ? 10 - chessNumber : chessNumber - 10;
				return;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (chessboard[0][i] != 0 && chessboard[0][i] == chessboard[1][i] && chessboard[0][i] == chessboard[2][i]) {
				score = (chessboard[0][i] == 1) ? 10 - chessNumber : chessNumber - 10;
				return;
			}
		}
		if (chessboard[0][0] != 0 && chessboard[0][0] == chessboard[1][1] && chessboard[0][0] == chessboard[2][2]) {
			score = (chessboard[0][0] == 1) ? 10 - chessNumber : chessNumber - 10;
			return;
		}
		if (chessboard[2][0] != 0 && chessboard[2][0] == chessboard[1][1] && chessboard[2][0] == chessboard[0][2]) {
			score = (chessboard[2][0] == 1) ? 10 - chessNumber : chessNumber - 10;
			return;
		}
		if (chessNumber == 9) {
			score = 0;
		}
	}
}