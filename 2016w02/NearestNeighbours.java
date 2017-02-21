package week2;
import java.util.*;

/*
 * Ali Ayoub [15.01.2017]
 * POTW 2016 week 2 created by Quinn Perfetto
 * http://potw.quinnftw.com/problem/2016/2/
 * 
 * K nearest neighbors
 * finding the k closest people to Will
 * - tree method using Tree Set and heap method using Priority Queue both implemented for practice
 *
 */

public class NearestNeighbours {
	
	public static class Tree {
		private Person root;
		
		public void insert(Person p) {
			if (root == null) {
				root = p;
				return;
			}
			
			if (p.distance <= root.distance) {
				if (root.left == null) {
					root.left = p;
				} else {
					insert(p, root.left);
				}
			} else {
				if (root.right == null) {
					root.right = p;
				} else {
					insert(p, root.right);	
				}
			}
		}
		
		private void insert(Person p, Person parent) {
			if (p.distance <= parent.distance) {
				if (parent.left == null) {
					parent.left = p;
				} else {
					insert(p, parent.left);
				}
			} else {
				if (parent.right == null) {
					parent.right = p;
				} else {
					insert(p, parent.right);
				}
			}
		}
		
		public void collectClosestK(Person root, ArrayList<Person> closest, int k) {
			if (root == null) {
				return;
			}
			
			collectClosestK(root.left, closest, k);
			if (closest.size() < k) {
				closest.add(root);
			} else {
				return;
			}
			collectClosestK(root.right, closest, k);
		}
	}
	
	public static class Person {
		private int x;
		private int y;
		private int distance;
		private Person left;
		private Person right;
		
		public Person(int x, int y) {
			this.x = x;
			this.y = y;
			left = null;
			right = null;
		}
	
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		int k = in.nextInt();
		int n = in.nextInt();
		
		// create Will
		Person w = new Person(x, y);
		w.distance = 0;

		Tree tree = new Tree();
		Person p1 = new Person(in.nextInt(), in.nextInt());
		p1.distance = ((int)Math.pow(p1.x - w.x, 2) + (int)Math.pow(p1.y - w.y, 2));
		tree.insert(p1);
		
		for (int i = 0; i < n-1; i++) {
			Person p = new Person(in.nextInt(), in.nextInt());
			p.distance = ((int)Math.pow(p.x - w.x, 2) + (int)Math.pow(p.y - w.y, 2));
			tree.insert(p);
		}
		
		in.close();

		ArrayList<Person> closest = new ArrayList<Person>();
		tree.collectClosestK(tree.root, closest, k);
		for(Person p : closest) {
			System.out.println(p.x + " " + p.y);
		}
		
	}

}
