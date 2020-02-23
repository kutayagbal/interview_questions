package etencr.schedule_tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) {
		System.out.println(scheduleTasks(createTasks(), 4));
	}

	static int scheduleTasks(String[] tasks, int n) {
		Map<String, Long> map = Stream.of(tasks)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		List<String> sortedTasks = new ArrayList<>();
		map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(e -> {
			IntStream.range(0, e.getValue().intValue()).forEach(k -> sortedTasks.add(e.getKey()));
		});

		String task = null;
		for (int i = 0; i < sortedTasks.size(); i++) {
			task = sortedTasks.get(i);

			if (map.get(task) == 0) {

			}
		}

		return 0;
	}

	static String[] createTasks() {
		String[] tasks = new String[20];
		for (int i = 0; i < 20; i++) {
			int randomAsciiCode = new Random().nextInt(5) + 65;
			char c = (char) randomAsciiCode;
			tasks[i] = String.valueOf(c);
		}

		return tasks;
	}

}
