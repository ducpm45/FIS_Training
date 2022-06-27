package fis.repo.io;

import fis.core.Transaction;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class ReadTransactionListFileTest extends TestCase {
    public final String transactionPath = "../Ex_03_2_Mini_Exercises_1_2/src/transactions_history.xlsx";
    public void testReadExcel() throws IOException {
        ReadTransactionListFile readTransactionListFile = new ReadTransactionListFile();
        List<Transaction> transactionList = readTransactionListFile.readExcel(transactionPath);
        transactionList.forEach(System.out::println);
    }
}