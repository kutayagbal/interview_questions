package etencr.prioritize_orders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Character;

public class Solution {

	public static void main(String[] args) {
		List<String> orderList = new ArrayList<String>();
		orderList.add("zld 93 12");
		orderList.add("zajhs str1 str2");
		orderList.add("ajshv str3 str4");
		orderList.add("njak 29 12");
		orderList.add("lasj 13 62");
		orderList.add("akb str2 str5");
		
		List<String> resultList = prioritizedOrders(6, orderList);
		
		for(int i=0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
		}
	}
	
	public static List<String> prioritizedOrders(int numOrders, List<String> orderList){
		List<String> nonPrimes = new ArrayList<>();
		List<String> primes = new ArrayList<>();
		
		for(int i=0; i<orderList.size(); i++) {
			String order = orderList.get(i);
			String[] orderStrArr = order.split(" ");
			
			if(Character.isDigit(orderStrArr[1].charAt(0))) {
				nonPrimes.add(order);
			}else {
				String tempOrder = "";
				
				for(int j=1; j<orderStrArr.length; j++) {
					tempOrder = tempOrder + " " + orderStrArr[j];
				}
				
				tempOrder = tempOrder +  " " + orderStrArr[0];

				primes.add(tempOrder);
			}
		}
		
		Collections.sort(primes);
		
		for(int i=0; i<primes.size(); i++) {
			String prime = primes.get(i);
			
			String[] primeStrArr = prime.split(" ");
			String tempPrime = "";

			for(int j=0; j<primeStrArr.length - 1; j++) {
				tempPrime = tempPrime + " " + primeStrArr[j];
			}
			
			primes.set(i, primeStrArr[primeStrArr.length - 1] + " " + tempPrime);
		}
		
		List<String> resultList = new ArrayList<>();
		resultList.addAll(primes);
		resultList.addAll(nonPrimes);
		
		return resultList;
		
	}

}
