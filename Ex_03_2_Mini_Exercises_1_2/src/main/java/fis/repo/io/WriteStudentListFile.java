package fis.repo.io;

import fis.core.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class WriteStudentListFile {
    private final String[] header = new String[]{"ID", "Name", "PaymentCode", "Amount"};

    public void writeStudentListTofile(Map<String, Student> studentMap, String excelFilePath) throws IOException {
        Workbook workbook = createWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Student List");
        int rowIndex = 0;
        // create header
        writeHeader(sheet, rowIndex, header.length);
        // write data
        rowIndex++;
        for (Map.Entry<String, Student> e : studentMap.entrySet()) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeData(e.getValue(), row);
            rowIndex++;
        }
        // Auto rezise column
        for (int columnIndex = 0; columnIndex < header.length; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }

        createOutputFile(workbook, excelFilePath);
        System.out.println("Write success");
    }

    private void writeHeader(Sheet sheet, int rowIndex, int cellNumber) {
        // Create header row
        Row row = sheet.createRow(rowIndex);

        // Create cells

        for (int i = 0; i < cellNumber; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    private void writeData(Student student, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(student.getId());

        cell = row.createCell(1);
        cell.setCellValue(student.getFullName());

        cell = row.createCell(2);
        cell.setCellValue(student.getPaymentCode());

        cell = row.createCell(3);
        cell.setCellValue(String.valueOf(student.getAmount()));
    }

    private Workbook createWorkbook(String excelFilePath) {
        Workbook workbook;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("File de luu khong dung dinh dang file excel!");
        }
        return workbook;
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
