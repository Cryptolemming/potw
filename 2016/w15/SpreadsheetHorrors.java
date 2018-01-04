package week15;
import java.util.*;

/*
 * Ali Ayoub [23.01.2017]
 * POTW 2016 week 15 created by Quinn Perfetto
 * http://potw.quinnftw.com/problem/2016/15/
 * 
 * 'Mexcel' Spreadsheets - aka Spreadsheet Horrors!
 * If we could go ahead and compute cell values based on given input 
 * cell positions and/or cell values that would be great.
 */

public class SpreadsheetHorrors {
	
	public static void collectCellValues(HashMap<String, String> cellsPreComputation, 
										 TreeMap<String, Integer> cellsPostComputation, Scanner in, int n) {
		
		for(int i = 0; i < n; i++) {
			StringBuilder cell = new StringBuilder();
			char row = (char) ('A' + i);
			cell.append(row);
			String[] values = in.nextLine().split(",");
			
			for(int j = 0; j < n; j++) {
				char col = (char) ('A' + j);
				cell.append(col);
				if (requiresComputation(cellsPostComputation, cell.toString(), values[j])) {
					cellsPreComputation.put(cell.toString(), values[j]);

				}
				cell.deleteCharAt(cell.length()-1);
			}
		}
		
	}
	
	private static boolean requiresComputation(TreeMap<String, Integer> numbers, String cell, String data) {
		int sign = 1;
		String value = "";
		boolean num = false;
		if(data.charAt(0) == '-') {
			sign = -1;
			value = data.substring(1);
			num = true;
		} else if (Character.isDigit(data.charAt(0))) {
			value = data;
			num = true;
		}
		if (num) {
			numbers.put(cell, sign*(Integer.parseInt(value)));
			return false;
		}
		
		return true;
	}
	
	public static void computeCellValues(HashMap<String, String> cellsPreComputation, TreeMap<String, Integer> cellsPostComputation) {
		Queue<String> q = new LinkedList<String>();
		String withDelimiter = "((?<=[+|-])|(?=[+|-]))";
		
		for (String s : cellsPreComputation.keySet()) {
			q.add(s);
		}
		
		while (!q.isEmpty()) {
			String currentCell = q.poll();

			String currentCellValue = cellsPreComputation.get(currentCell);
			String[] cellComputations = currentCellValue.split(withDelimiter);
			if (!computeCellValue(cellsPreComputation, currentCell, cellsPostComputation, cellComputations)) {
				q.add(currentCell);
			}
			
		}
	}
	
	private static boolean computeCellValue(HashMap<String, String> cells, String currentCell, 
									   TreeMap<String, Integer> numbers, String[] cellComputations) {
		int result = 0;
		for (int i = 0; i < cellComputations.length; i++) {
			if (i%2 != 0) {
				continue;
			}
			if (i%2 == 0) {
				if (!numbers.containsKey(cellComputations[i])) {
					return false;
				}
				if (i == 0) {
					result = numbers.get(cellComputations[i]);
					continue;
				}
				int sign = cellComputations[i-1] == "-" ? -1 : 1;
				if (sign < 0) {
					result -= numbers.get(cellComputations[i]);
				} else {
					result += numbers.get(cellComputations[i]);
				}
			}
		}
		
		numbers.put(currentCell, result);
		return true;
	}
	
	public static void printValues(TreeMap<String, Integer> numbers, int n) {
		Collection<Integer> getValues = numbers.values();
		Integer[] values = getValues.toArray(new Integer[getValues.size()]);
		
		for (int i = 0; i < n*n; i++) {
			if (i != 0 && i%n == 0) {
				System.out.println("");
			}
			System.out.printf("%d", values[i]);
			if ((i+1)%n != 0) {
				System.out.printf(", ");
			}
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		
		HashMap<String, String> cellsPreComputation = new HashMap<String, String>();
		TreeMap<String, Integer>  cellsPostComputation = new TreeMap<String, Integer>();

		collectCellValues(cellsPreComputation, cellsPostComputation, in, n);
		computeCellValues(cellsPreComputation, cellsPostComputation);
		printValues(cellsPostComputation, n);
		
		in.close();

	}
}
