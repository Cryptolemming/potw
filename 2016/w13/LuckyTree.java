package week13;
import java.util.*;

public class LuckyTree {
	
	public static ArrayList<Integer> readData() {
		ArrayList<Integer> data = new ArrayList<Integer>();
		Scanner readInput= new Scanner(System.in);
		
		int rows = readInput.nextInt();
		int n = 1;
		while(n <= (int)(Math.pow(2, rows) - 1)) {
			data.add(readInput.nextInt());
			n++;
		}
		
		readInput.close();
		return data;
	}
	
	public static boolean isLuckyTree(ArrayList<Integer> data, int targetSum) {
		int rootIndex = 0;
		return isLuckyTree(data, rootIndex, targetSum);
	}
	
	private static boolean isLuckyTree(ArrayList<Integer> data, int rootIndex, int currentSum) {
		currentSum -= data.get(rootIndex);
		
		if ((rootIndex*2+2) < data.size()) {
			int leftChildIndex = rootIndex*2+1;
			int rightChildIndex = rootIndex*2+2;
			
			return isLuckyTree(data, leftChildIndex, currentSum) 
					|| isLuckyTree(data, rightChildIndex, currentSum);
		} else {
			return currentSum == 0;
		}
	}
	
	public static String printResult(boolean isLuckyTree) {
		return isLuckyTree ? "lucky" : "not lucky";
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = readData();
		boolean result = isLuckyTree(list, 13);
		System.out.println(printResult(result));
	}

}
