package etencr.remove_obstacle_from_lot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {

	public static void main(String[] args) {
		
		int r = new Random().nextInt(10);
		int c = new Random().nextInt(10);
		
		List<List<Integer>> lot = new ArrayList<>();
		for(int i=0; i<10; i++) {
			List<Integer> row = new ArrayList<>();
			
			for(int j=0; j<10; j++) {
				if(i == r && j == c) {
					row.add(9);
				}else {
					row.add(new Random().nextInt(2));
				}
				
			}
			
			lot.add(row);
		}
		
		for(int i=0; i<10; i++) {
			List<Integer> row = lot.get(i);
			
			for(int j=0; j<10; j++) {
				System.out.print(row.get(j) + " ,");
			}
			
			System.out.println();
		}
		
		removeObsticle(10, 10, lot);
		System.out.println();
	}
	
	public static int removeObsticle(int numRows, int numColumns, List<List<Integer>> lot) {
		int result = getPath(0, 0, lot, 0, -1);
		System.out.println("Result: " + result);
		return result;
	}
	
	public static int getPath(int row, int col, List<List<Integer>> lot, int move, int lastGo) {

		if(row + 1 < lot.size() && lastGo != 1) {
			int loc = lot.get(row + 1).get(col);
			if(loc == 1) {
				//go down
				return getPath(row+1, col, lot, move+1, 0);
			}else if(loc == 9) {
				return move + 1;
			}
		}
		
		if(row - 1 >= 0 && lastGo != 0) {
			int loc = lot.get(row - 1).get(col);
			if(loc == 1) {
				//go up
				return getPath(row-1, col, lot, move+1, 1);
			}else if(loc == 9) {
				return move + 1;
			}
		}
		
		if(col - 1 >= 0 && lastGo != 3) {
			int loc = lot.get(row).get(col - 1);
			if(loc == 1) {
				//go left
				return getPath(row, col-1, lot, move+1, 2);
			}else if(loc == 9) {
				return move + 1;
			}
		}
		
		if(col + 1 < lot.get(0).size() && lastGo != 2) {
			int loc = lot.get(row).get(col + 1);
			if(loc == 1) {
				//go right
				return getPath(row, col+1, lot, move+1, 3);
			}else if(loc == 9) {
				return move + 1;
			}
		}
		
		return -1;
	}

}
