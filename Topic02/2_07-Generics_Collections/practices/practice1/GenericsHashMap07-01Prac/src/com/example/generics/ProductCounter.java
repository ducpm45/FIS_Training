package com.example.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductCounter {
    // Create a Counting Map
	Map<String, Integer> productCountMap = new HashMap<>();
    // Create a Name Mapping Map
	Map<String, String> productNames;

    public static void main(String[] args) {

        // List of part data
        String[] parts = new String[]{"1S01", "1S01", "1S01", "1S01", "1S01", "1S02", "1S02", "1S02", "1H01", "1H01", "1S02", "1S01", "1S01", "1H01", "1H01", "1H01", "1S02", "1S02", "1M02", "1M02", "1M02"};

        // Create Product Name Part Number map
        Map<String, String> productNames = new TreeMap<>();
        productNames.put("Blue Polo Shirt", "1S01");
        productNames.put("Black Polo Shirt", "1S02");
        productNames.put("Red Ball Cap", "1H01");
        productNames.put("Duke Mug   ", "1M02");

        // Create Product Counter Object and process data
		ProductCounter pc1 = new ProductCounter(productNames);
		pc1.processList(parts);
		pc1.printReport();
    }

	public ProductCounter(Map<String, String> productNames) {
		this.productNames = productNames;
    }

    public void processList(String[] list) {
		int productQuantity = 0;
		for (String p : list) {
			if (productCountMap.containsKey(p)) {
				productQuantity = productCountMap.get(p);
				productQuantity++;
				productCountMap.put(p, productQuantity);
			} else {
				productCountMap.put(p, 1);
			}
		}
    }

    public void printReport() {
		System.out.println("=== Product Report ===");
		for (String key : productNames.keySet()) {
			System.out.print("Name: " + key);
			System.out.println("\t\tCount: " + productCountMap.get(productNames.get(key)));
		}
    }
}
