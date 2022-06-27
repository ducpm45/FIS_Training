package com.example.lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author oracle
 */
public class GroupTest {

    public static void main(String[] args) {

        List<SalesTxn> tList = SalesTxn.createTxnList();
        Map<String, List<SalesTxn>> tMap;
        // Print out transactions grouped by Buyer
		tMap = tList.stream().collect(Collectors.groupingBy(SalesTxn::getBuyerName));
        System.out.println("=== Transactions Grouped by Buyer ===");

		tMap.forEach((b, t) -> {
			System.out.println("Buyer Name: " + b);
			t.forEach(SalesTxn::printSummary);
		});
    }
}
