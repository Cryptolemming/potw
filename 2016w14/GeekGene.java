package week14;
import java.util.*;

/*
 * Ali Ayoub [16.01.2017]
 * POTW 2016 week 14 created by Quinn Perfetto
 * http://potw.quinnftw.com/problem/2016/14/
 * 
 * GeekGene
 * count the number of gene "mutations" required to convert initial input to "GEEK"
 * if not possible return -1
 */

public class GeekGene {
	
	public static int countMutations(HashSet<String> genes, String input, String desiredWord) {
		if (input.equals(desiredWord)) {
			return 0;
		}
		
		HashSet<String> visited = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		q.add(input);
		visited.add(input);
		
		char[] nucleotides = {'G', 'E', 'E', 'K'};
	    return countMutations(q, genes, visited, nucleotides, desiredWord);
	}
	
	private static int countMutations(Queue<String> q, HashSet<String> genes, HashSet<String> visited, 
									char[] nucleotides, String desiredWord) {
		int mutations = 0;
		while(!q.isEmpty()) {
			int currentGenes = q.size();
			for (int i = 0; i < currentGenes; i++) {
				String current = q.remove();
				if (current.equals(desiredWord)) {
					return mutations;
				}
				char[] currentChars = current.toCharArray();
				for (int j = 0; j < currentChars.length; j++) {
					char currentChar = currentChars[j];
					for (char nucleotide : nucleotides) {
						currentChars[j] = nucleotide;
						String currentString = new String(currentChars);
						if (genes.contains(currentString) &&
							!visited.contains(currentString)) {
							visited.add(currentString);
							q.add(currentString);
						}
					}
					currentChars[j] = currentChar;
				}
			}
			mutations++;
		}
		return -1;	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int n = sc.nextInt();		
		HashSet<String> genes = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			genes.add(sc.next());
		}
		sc.close();

		int result = countMutations(genes, s, "GEEK");
		System.out.println(result);
	}

}
