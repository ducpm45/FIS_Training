package fis.service;

import fis.core.Report;
import fis.core.Student;
import fis.core.Transaction;
import fis.repo.io.ReadStudentListFile;
import fis.repo.io.ReadTransactionListFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    public List<Report> getReport(String studentPath, String transactionPath) throws IOException {
        // create report list
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

        return listReport;
    }
}
