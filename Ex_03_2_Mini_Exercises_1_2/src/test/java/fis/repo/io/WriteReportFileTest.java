package fis.repo.io;

import fis.core.Report;
import fis.core.Student;
import fis.core.Transaction;
import junit.framework.TestCase;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteReportFileTest extends TestCase {
    public final String studentPath = "../Ex_03_2_Mini_Exercises_1_2/src/students.xlsx";
    public final String transactionPath = "../Ex_03_2_Mini_Exercises_1_2/src/transactions_history.xlsx";
    public final String reportPath = "../Ex_03_2_Mini_Exercises_1_2/src/report.xlsx";
    public void testWriteReportFile() throws IOException {
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
        WriteReportFile writeReportFile = new WriteReportFile();
        writeReportFile.writeReportFile(listReport, reportPath);
    }
}