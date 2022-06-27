package com.example.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oracle
 */
public class JoinTest {

    public static void main(String[] args) {

        List<SalesTxn> tList = SalesTxn.createTxnList();

        // Print out a sorted list of unique buyer names
        System.out.println("=== Sorted Buyer's List ===");
		String result = tList.stream().map(p -> p.getBuyerName()).distinct().sorted().collect(Collectors.joining(","));

        System.out.println("Buyer list: " + result);

    }
}
