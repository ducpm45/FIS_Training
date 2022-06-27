package fis.repo.io;

import fis.core.Transaction;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class WriteTransactionFileTest extends TestCase {
    public final String studentPath = "../Ex_03_2_Mini_Exercises_1_2/src/students.xlsx";
    public final String transactionPath = "../Ex_03_2_Mini_Exercises_1_2/src/transactions_history.xlsx";
    public void testWriteTransactionListToFile() throws IOException {
        WriteTransactionFile writeTransactionFile = new WriteTransactionFile();
        List<String> message = writeTransactionFile.getMessage(studentPath);
        List<Transaction> transactionList = writeTransactionFile.createTransactionList(message);

        writeTransactionFile.writeTransactionListToFile(transactionList, transactionPath);
    }
}