package fis.repo.io;

import fis.core.Student;
import fis.core.Transaction;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WriteTransactionFile {
    private final String[] header = new String[]{"TransferType", "Time", "Amount", "Message"};

    public void writeTransactionListToFile(List<Transaction> transactionList, String transactionPath) throws IOException {
        // create workbook
        Workbook workbook = createWorkBook(transactionPath);
        // create sheet
        Sheet sheet = workbook.createSheet("Transaction List");
        int rowIndex = 0;
        // create header
        writeHeader(sheet, rowIndex, header.length);
        // write data
        rowIndex++;
        for(Transaction t : transactionList) {
            // create row
            Row row = sheet.createRow(rowIndex);
            // write data on row
            writeData(t, row);
            rowIndex++;
        }

        // Auto rezise column
        for (int columnIndex = 0; columnIndex < header.length; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }

        createOutputFile(workbook, transactionPath);
        System.out.println("Write success");

    }
    private void writeData(Transaction transaction, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(String.valueOf(transaction.getType()));

        cell = row.createCell(1);
        cell.setCellValue(transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        cell = row.createCell(2);
        cell.setCellValue(String.valueOf(transaction.getAmount()));

        cell = row.createCell(3);
        cell.setCellValue(transaction.getMessage());
    }

    private void writeHeader(Sheet sheet, int rowIndex, int cellNumber) {
        // Create header row
        Row row = sheet.createRow(rowIndex);

        // Create cells

        for(int i = 0; i < cellNumber; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    private Workbook createWorkBook(String transactionPath) throws IOException {
        Workbook workbook;
        if(transactionPath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if(transactionPath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("File de luu khong dung dinh dang file excel!");
        }
        return workbook;
    }

    private void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    public List<Transaction> createTransactionList(List<String> message) {
        List<Transaction> transactionList = new ArrayList<>();
        message.forEach(d -> {
            Transaction t = new Transaction(amountRandom(), d);
            transactionList.add(t);
        });
        return transactionList;
    }

    public List<String> getMessage(String studentPath) throws IOException {
        ReadStudentListFile readStudentList = new ReadStudentListFile();
        List<Student> studentList = readStudentList.readExcel(studentPath);
        List<String> messageArr = new ArrayList<>(studentList.size());
        studentList.forEach(d -> {
            StringBuilder sb = new StringBuilder();
            sb.append(d.getPaymentCode()).append("_").append(d.getFullName());
            messageArr.add(sb.toString());
        });
        return messageArr;
    }

    public BigDecimal amountRandom() {
        double random = Math.random()*1000+1;
        return BigDecimal.valueOf((double) Math.round(random * 100) / 100);
    }
}
