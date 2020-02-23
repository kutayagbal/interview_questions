package muksbmqk.money_change_maker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeMap;

public class Solution {
	public static void main(String args[]) throws Exception {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */

		MoneyChangeDevice device = null;
		String line = null;
		BufferedReader reader = null;

		// BufferedWriter writer = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		reader = new BufferedReader(new InputStreamReader(System.in));
		line = reader.readLine();

		device = new MoneyChangeDevice(line);

		line = reader.readLine();

		int numOfTrx = Integer.parseInt(line);
		for (int i = 0; i < numOfTrx; i++) {
			line = reader.readLine();

			String result = device.makeChangeForAmount(line);

			System.out.println(result);
			// writer.write(result);
			// writer.newLine();
		}

		if (reader != null)
			reader.close();

		// if(writer != null){
		// writer.close();
		// }

	}
}

// If you want you can define multiple classes or interfaces as follows:

class MoneyChangeDevice {
	private TreeMap<BigDecimal, Integer> slotMap;
	private BigDecimal total;

	/**
	 * Constructor for MoneyChangeDevice class
	 * 
	 * @param initialSetup
	 *            the initial money change device slots setup following the format
	 *            in this test description.
	 * @throws NullPointerException
	 *             if initialSetup is null
	 * @throws IllegalArgumentException
	 *             if initialSetup is on invalid format
	 */
	public MoneyChangeDevice(String initialSetup) {
		/* Code the constructor here */
		String[] slotSetups = initialSetup.split(" ");

		slotMap = new TreeMap<>();
		total = BigDecimal.ZERO;
		for (String slotSetup : slotSetups) {
			String[] vq = slotSetup.split(":");
			BigDecimal value = new BigDecimal(vq[0]);
			Integer quantity = Integer.parseInt(vq[1]);

			slotMap.put(value, quantity);
			total = total.add(value.multiply(new BigDecimal(quantity)));
		}
	}

	/**
	 * Calculates the money change for a given amount.
	 * 
	 * @param amount
	 *            The amount to be changed following the format described in this
	 *            test description.
	 * @return The money change and the resulting change slots setup as per this
	 *         test description. In case of no possible change, please also refer
	 *         this test tescription
	 * @throws NullPointerException
	 *             if amount is null
	 * @throws IllegalArgumentException
	 *             if amount is on invalid format or is an invalid number
	 */
	public String makeChangeForAmount(String amount) {
		BigDecimal amnt = new BigDecimal(amount);

		if (amnt.compareTo(total) > 0)
			return "no change possible for " + amount;

		TreeMap<BigDecimal, Integer> dispenseMap = makeChange(amnt);

		if (dispenseMap == null)
			return "no change possible for " + amount;

		StringBuffer buf = new StringBuffer();

		Iterator<BigDecimal> it = dispenseMap.descendingKeySet().iterator();
		while (it.hasNext()) {
			BigDecimal value = it.next();
			Integer quantity = dispenseMap.get(value);
			buf.append(value + ":" + quantity + " ");
			slotMap.put(value, slotMap.get(value) - quantity);
			total = total.subtract(value.multiply(new BigDecimal(quantity)));
		}

		buf.deleteCharAt(buf.length() - 1);
		buf.append("; ");

		it = slotMap.descendingKeySet().iterator();
		while (it.hasNext()) {
			BigDecimal value = it.next();
			Integer quantity = slotMap.get(value);
			buf.append(value + ":" + quantity + " ");
		}

		buf.deleteCharAt(buf.length() - 1);

		return buf.toString();
	}

	private TreeMap<BigDecimal, Integer> makeChange(BigDecimal amnt) {
		if (amnt.compareTo(BigDecimal.ZERO) == 0)
			return new TreeMap<>();

		TreeMap<BigDecimal, Integer> dispenseMap = null;
		Iterator<BigDecimal> it = slotMap.descendingKeySet().iterator();
		while (it.hasNext()) {
			BigDecimal value = it.next();

			if (value.compareTo(amnt) <= 0) {
				dispenseMap = makeChange(amnt.subtract(value));

				if (dispenseMap == null) {
					continue;
				}

				Integer quantity = dispenseMap.get(value);
				if (quantity == null) {
					dispenseMap.put(value, 1);
				} else {
					if (slotMap.get(value).compareTo(quantity) == 0) {
						return null;
					}

					dispenseMap.put(value, quantity + 1);
				}

				break;
			}
		}

		return dispenseMap;
	}
}