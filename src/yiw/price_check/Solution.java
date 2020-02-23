package yiw.price_check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		List<String> products = new ArrayList<>();
		products.add("rice");
		products.add("sugar");
		products.add("wheat");
		products.add("cheese");

		List<Float> productPrices = new ArrayList<>();

		productPrices.add(16.89F);
		productPrices.add(56.92F);
		productPrices.add(20.89F);
		productPrices.add(345.99F);

		List<String> productSold = new ArrayList<>();
		productSold.add("rice");
		productSold.add("cheese");

		List<Float> soldPrice = new ArrayList<>();
		soldPrice.add(18.99F);
		soldPrice.add(400.89F);

		System.out.println(priceCheck(products, productPrices, productSold, soldPrice));
	}

	public static int priceCheck(List<String> products, List<Float> productPrices, List<String> productSold,
			List<Float> soldPrice) {

		Map<String, Float> priceMap = new HashMap<>();
		for (int i = 0; i < products.size(); i++) {
			priceMap.put(products.get(i), productPrices.get(i));
		}

		int errCount = 0;
		for (int i = 0; i < productSold.size(); i++) {
			String ps = productSold.get(i);
			Float psPrice = soldPrice.get(i);

			if (!psPrice.equals(priceMap.get(ps))) {
				errCount++;
			}
		}

		return errCount;
	}

}
