package fis.service;

import fis.core.Report;
import fis.core.Student;
import fis.core.Transaction;
import fis.repo.io.ReadStudentListFile;
import fis.repo.io.ReadTransactionListFile;
import junit.framework.TestCase;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServiceTest extends TestCase {
    public final String studentPath = "../Ex_03_2_Mini_Exercises_1_2/src/students.xlsx";
    public final String transactionPath = "../Ex_03_2_Mini_Exercises_1_2/src/transactions_history.xlsx";
    public void testGetReport() throws IOException {
        List<Report> listReport = new ArrayList<>();
        // read student list from excel file
        ReadStudentListFile readStudentListFile = new ReadStudentListFile();
        ReadTransactionListFile readTransactionListFile = new ReadTransactionListFile();
        List<Student> studentList = readStudentListFile.readExcel(studentPath);
        List<Transaction> transactionList = readTransactionListFile.readExcel(transactionPath);
        Map<String, BigDecimal> paidMap = new HashMap<>();
        transactionList.forEach(t -> {
            String[] arr = t.getMessage().split("_");
            paidMap.put(arr[0], t.getAmount());
        });
        studentList.forEach(s -> {

            if(paidMap.containsKey(s.getPaymentCode())) { // svien da nop tien
                Report report = new Report();
                report.setId(s.getId());
                report.setName(s.getFullName());
                report.setDebt(s.getAmount().subtract(paidMap.get(s.getPaymentCode())));
                listReport.add(report);
            } else { // svien chua nop tien
                Report report = new Report();
                report.setId(s.getId());
                report.setName(s.getFullName());
                report.setDebt(s.getAmount());
                listReport.add(report);
            }
        });
        listReport.forEach(System.out::println);
    }
}