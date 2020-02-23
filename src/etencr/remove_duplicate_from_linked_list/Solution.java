package etencr.remove_duplicate_from_linked_list;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		Node head = create();

		print(head);

		removeDup(head);

		print(head);
	}

	static Node create() {
		Node list = new Node(new Random().nextInt(100));
		Node curr = list;

		for (int i = 0; i < 20; i++) {
			curr.next = new Node(new Random().nextInt(10));
			curr = curr.next;
		}

		return list;
	}

	static void print(Node list) {
		Node curr = list;
		while (curr != null) {
			System.out.print(curr.value + " -> ");
			curr = curr.next;
		}

		System.out.println();
	}

	static void removeDup(Node list) {
		Set<Integer> set = new HashSet<>();

		Node prev = list;
		Node curr = list;
		while (curr != null) {
			if (set.contains(curr.value)) {
				prev.next = curr.next;
				curr = curr.next;
			} else {
				set.add(curr.value);
				prev = curr;
				curr = curr.next;
			}
		}
	}
}

class Node {
	int value;
	Node next;

	public Node(int v) {
		this.value = v;
	}
}
