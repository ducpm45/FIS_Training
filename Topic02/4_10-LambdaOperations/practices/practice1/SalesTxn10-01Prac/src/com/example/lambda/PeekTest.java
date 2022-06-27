package com.example.lambda;

import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author oracle
 */
public class PeekTest {
    
    public static void main(String[] args) {
    
    List<SalesTxn> tList = SalesTxn.createTxnList();
    Consumer<SalesTxn> taxReport = 
      t -> System.out.printf("Id: " + t.getTxnId() 
        + " Buyer: " + t.getBuyerName() + 
        " Txn amt: $%,9.2f ", t.getTransactionTotal());
    
    // Write report using peek
    System.out.println("=== Widget Pro Sales Tax in CA ===");
	tList.stream().filter(p -> p.getState().equals(State.CA)).filter(p -> p.getProduct().equals("Widget Pro"))
			.peek(taxReport).map(p -> p.getTransactionTotal() * TaxRate.byState(p.getState()))
			.forEach(result -> System.out.printf("Txn amt: $%,9.2f ", result));
    }
}
