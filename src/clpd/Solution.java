package clpd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

	public static void main(String... args) {
		Business A = new Business("A", new HashMap<>());
		Business B = new Business("B", new HashMap<>());
		Business C = new Business("C", new HashMap<>());
		Business D = new Business("D", new HashMap<>());
		B.neighbouringBusinesses.put(D, 1);
		A.neighbouringBusinesses.put(B, 7);
		A.neighbouringBusinesses.put(C, 4);
		C.neighbouringBusinesses.put(B, 2);
		C.neighbouringBusinesses.put(D, 1);

		System.out.println(reachableBusinesses(A, 5).toString());
	}

	public static List<String> reachableBusinesses(Business startingBusiness, int distance) {
		Queue<QueueNode> queue = new LinkedList<>();
		queue.add(new QueueNode(startingBusiness, 0));
		Set<String> businesses = new HashSet<>();

		QueueNode node = null;
		int totalDist = 0;
		while (!queue.isEmpty()) {
			node = queue.remove();

			for (Map.Entry<Business, Integer> entry : node.business.neighbouringBusinesses.entrySet()) {
				totalDist = node.distance + entry.getValue();
				if (totalDist <= distance) {
					businesses.add(entry.getKey().name);
					queue.add(new QueueNode(entry.getKey(), totalDist));
				}
			}
		}

		return new ArrayList<>(businesses);
	}
}

class Business {
	public String name;
	public Map<Business, Integer> neighbouringBusinesses;

	public Business(String n, Map<Business, Integer> nB) {
		name = n;
		neighbouringBusinesses = nB;
	}
}

class QueueNode {
	public Business business;
	public int distance;

	public QueueNode(Business b, int d) {
		business = b;
		distance = d;
	}
}
