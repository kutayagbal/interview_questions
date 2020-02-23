package etencr.minimum_hours;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {

	public static void main(String... args) {
		int rows = 4;
		int columns = 5;
		List<List<Integer>> grid = initGrid(rows, columns);
		System.out.println(grid.toString());

		System.out.println(minimumHours(rows, columns, grid));
	}

	public static List<List<Integer>> initGrid(int rows, int columns) {
		List<List<Integer>> grid = new ArrayList<>();

		List<Integer> row = null;
		for (int i = 0; i < rows; i++) {
			row = new ArrayList<>();
			for (int j = 0; j < columns; j++) {
				row.add(new Random().nextInt(2));
			}

			grid.add(row);
		}

		return grid;
	}

	/*
	 * R = row count, C = col count, Time Complexity = O(max(C,R).C.R)
	 */
	public static int minimumHours(int rows, int columns, List<List<Integer>> grid) {
		int hours = 0;

		List<Cell> sendToCells = new ArrayList<>();
		boolean isSentToAll;
		do {
			sendToCells.clear();
			isSentToAll = true;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (grid.get(i).get(j) == 0) {
						if (willSendToCell(rows, columns, i, j, grid)) {
							sendToCells.add(new Cell(i, j));
						} else {
							isSentToAll = false;
						}
					}
				}
			}

			hours++;

			sendFile(grid, sendToCells);
		} while (!isSentToAll);

		return hours;
	}

	private static void sendFile(List<List<Integer>> grid, List<Cell> switchCells) {
		switchCells.stream().forEach(cell -> {
			grid.get(cell.row).set(cell.col, 1);
		});
	}

	public static boolean willSendToCell(int rows, int columns, int row, int col, List<List<Integer>> grid) {
		if (row + 1 < rows) {
			// down
			if (grid.get(row + 1).get(col) == 1)
				return true;
		}

		if (col + 1 < columns) {
			// right
			if (grid.get(row).get(col + 1) == 1)
				return true;
		}

		if (col - 1 >= 0) {
			// left
			if (grid.get(row).get(col - 1) == 1)
				return true;
		}

		if (row - 1 >= 0) {
			// up
			if (grid.get(row - 1).get(col) == 1)
				return true;
		}

		return false;
	}
}

class Cell {
	public int row;
	public int col;

	Cell(int r, int c) {
		row = r;
		col = c;
	}
}
