package etencr.convert_to_hexadecimal;

public class Solution {

	public static void main(String[] args) {
		int num = 291;
		System.out.println(toHex(num));
	}

	static String toHex(int num) {
		StringBuilder result = new StringBuilder();
		if (num >= 16) {
			int div = 0;
			int rem = 0;

			do {
				rem = num % 16;
				result.append(getDigitString(rem));
				div = num / 16;
				num = div;
			} while (div >= 16);

			result.append(getDigitString(div));

			return result.reverse().toString();
		}

		return getDigitString(num);
	}

	static String getDigitString(int num) {
		if (num < 10) {
			return String.valueOf(num);
		}

		switch (num) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			break;
		}

		return "";
	}
}
